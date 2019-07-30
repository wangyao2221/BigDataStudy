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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Mapper2 extends Mapper<LongWritable, Text,Text,Text> {
    private Text outKey = new Text();
    private Text outValue = new Text();
    private List<String>  scoreList = new ArrayList<String>();
    private DecimalFormat df = new DecimalFormat("0.00");

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
        String leftItemID = value.toString().split("\t")[0];
        String matrixScoresStr = value.toString().split("\t")[1];
        String[] scores = matrixScoresStr.split(",");

        for (int i = 0; i < scoreList.size(); i++) {
            String rightItemID = scoreList.get(i).toString().split("\t")[0];
            String rightScoresStr = scoreList.get(i).toString().split("\t")[1];
            String[] rightScores = rightScoresStr.split(",");

            double sum = 0;
            double squareSum = 0;
            for (int j = 0; j < scores.length; j++) {
                String leftUserID = scores[j].split("_")[0];
                String leftScoreStr = scores[j].split("_")[1];
                int score = Integer.valueOf(leftScoreStr);

                squareSum += score * score;

                for (int k = 0; k < rightScores.length; k++) {
                    String rightUserID = rightScores[k].split("_")[0];
                    String rightScoreStr = rightScores[k].split("_")[1];
                    int rigthScore = Integer.valueOf(rightScoreStr);

                    if (leftUserID.equals(rightUserID)){
                        sum += score * rigthScore;
                    }
                }
            }

            double rightSquareSum = 0;
            for (int j = 0; j < rightScores.length; j++) {
                String rightScoreStr = rightScores[j].split("_")[1];
                int rightScore = Integer.valueOf(rightScoreStr);
                rightSquareSum += rightScore * rightScore;
            }

            double distance = sum / (Math.sqrt(squareSum) * Math.sqrt(rightSquareSum));

            outKey.set(leftItemID);
            outValue.set(rightItemID + "_" + df.format(distance));

            context.write(outKey,outValue);
        }
    }
}
