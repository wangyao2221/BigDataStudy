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
        FSDataInputStream in = fs.open(new Path("output/itemcf/ste1_output/part-r-00000"));
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
            String itemIDTmp = value.toString().split("\t")[0];
            String scoresTmpStr = value.toString().split("\t")[1];
            String[] scoresTmp = scoresTmpStr.split(",");

            for (int j = 0; j < scores.length; j++) {
                String userID = scores[i].split("_")[0];
                String score = scores[i].split("_")[1];

                for (int k = 0; k < scoresTmp.length; k++) {
                    String userIDTmp = scores[i].split("_")[0];
                    String scoreTmp = scores[i].split("_")[1];

                    if (userID.equals(userIDTmp)){
                    }
                }
            }
        }
    }
}
