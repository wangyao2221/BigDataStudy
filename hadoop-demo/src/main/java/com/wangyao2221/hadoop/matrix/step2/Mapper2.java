package com.wangyao2221.hadoop.matrix.step2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Mapper2 extends Mapper<LongWritable, Text,Text,Text> {
    private Text outKey = new Text();
    private Text outValue = new Text();

    private List<String> cacheList = new ArrayList<String>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        Configuration conf = context.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        FSDataInputStream in = fs.open(new Path("D:\\Workspace\\IDEA\\BigDataStudy\\hadoopdemo\\output\\matrix\\step1_output\\part-r-00000"));

        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line = null;
        while ((line = br.readLine()) != null){
            cacheList.add(line);
        }

        br.close();
        in.close();
        fs.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String leftRow = value.toString().split("   ")[0];
        String[] leftValues = value.toString().split("   ")[1].split(",");

        for (String line : cacheList) {
            String rightRow = line.split("\t")[0];
            String[] rightValues = line.split("\t")[1].split(",");

            long sum = 0;

            for (String leftValue : leftValues) {
                String leftColumn = leftValue.split("_")[0];
                String leftColumnValue = leftValue.split("_")[1];

                for (String rightValue : rightValues) {
                    String rightColumn = rightValue.split("_")[0];
                    String rightColumnValue = rightValue.split("_")[1];

                    if (leftColumn.equals(rightColumn)){
                        long num1 = Long.parseLong(leftColumnValue);
                        long num2 = Long.parseLong(rightColumnValue);
                        sum += num1 * num2;
                    }
                }
            }

            outKey.set(rightRow);
            outValue.set(leftRow + "_" + sum);
            context.write(outKey,outValue);
        }
    }
}
