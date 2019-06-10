package com.wangyao2221.hadoop.itemcf.step2;

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

public class Mapper2 extends Mapper<LongWritable, Text,Text,Text> {
    Text outKey = new Text();
    Text outValue = new Text();
    List<String>  scoreList = new ArrayList<String>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        Configuration conf = context.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        FSDataInputStream in = fs.open(new Path("output/itemcf/step1_output/part-r-00000"));
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line = null;
        while ((line = br.readLine()) != null){
            scoreList.add(line);
        }

        br.close();
        in.close();
        fs.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String itemID = value.toString().split("\t")[0];
        String scoresStr = value.toString().split("\t")[1];
        String[] scores = scoresStr.split(",");

        for (int i = 0; i < scoreList.size(); i++) {
            String itemIDTmp = scoreList.get(i).toString().split("\t")[0];
            String scoresTmpStr = scoreList.get(i).toString().split("\t")[1];
            String[] scoresTmp = scoresTmpStr.split(",");

            double sum = 0;
            double squareSum = 0;
            for (int j = 0; j < scores.length; j++) {
                String userID = scores[j].split("_")[0];
                String scoreStr = scores[j].split("_")[1];
                int score = Integer.valueOf(scoreStr);

                squareSum += score * score;

                for (int k = 0; k < scoresTmp.length; k++) {
                    String userIDTmp = scoresTmp[k].split("_")[0];
                    String scoreStrTmp = scoresTmp[k].split("_")[1];
                    int scoreTmp = Integer.valueOf(scoreStrTmp);

                    if (userID.equals(userIDTmp)){
                        sum += score * scoreTmp;
                    }
                }
            }

            double sqqureSumTmp = 0;
            for (int j = 0; j < scoresTmp.length; j++) {
                String scoreStrTmp = scoresTmp[j].split("_")[1];
                int scoreTmp = Integer.valueOf(scoreStrTmp);
                squareSum += scoreTmp * scoreTmp;
            }

            double distance = sum / (Math.sqrt(squareSum) + Math.sqrt(sqqureSumTmp));

            outKey.set(itemID);
            outValue.set(itemIDTmp + "_" + distance);

            context.write(outKey,outValue);
        }
    }
}
