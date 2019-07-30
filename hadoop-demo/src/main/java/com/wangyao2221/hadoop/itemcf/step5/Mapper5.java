package com.wangyao2221.hadoop.itemcf.step5;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Mapper5 extends Mapper<LongWritable, Text, Text, Text> {
    Text outKey = new Text();
    Text outValue = new Text();

    List<String> cacheList = new ArrayList<String>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        Configuration conf = context.getConfiguration();
        FileSystem fs = FileSystem.get(conf);

        FSDataInputStream in = fs.open(new Path("output/itemcf/step4_output/part-r-00000"));
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line = null;
        while ((line = br.readLine()) != null) {
            cacheList.add(line);
        }

        br.close();
        in.close();
        fs.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String leftItemID = value.toString().split("\t")[0];
        String leftUserScoresStr = value.toString().split("\t")[1];

        for (String cache : cacheList) {
            String rightItemID = cache.split("\t")[0];
            String rightUserScoresStr = cache.split("\t")[1];

            if (leftItemID.equals(rightItemID)) {
                String[] rightUserScores = rightUserScoresStr.split(",");

                for (String rightUserScore : rightUserScores) {
                    // 从计算得出的评分矩阵中取出一行中的一个分数项，在下面一个循环中通过遍历原评分矩阵，判断是否在原评分矩阵中出现过
                    String rightUserID = rightUserScore.split("_")[0];
                    String rightScore = rightUserScore.split("_")[1];

                    String[] leftUserScores = leftUserScoresStr.split(",");
                    boolean exist = false;

                    for (String leftUserScore : leftUserScores) {
                        String leftUserID = leftUserScore.split("_")[0];

                        if (leftUserID.equals(rightUserID)){
                            exist = true;
                        }
                    }

                    if (exist == false){
                        outKey.set(rightUserID);
                        outValue.set(rightItemID + "_" + rightScore);
                        context.write(outKey,outValue);
                    }
                }
            }
        }
    }
}
