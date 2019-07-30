package com.wangyao2221.hadoop.matrix.step1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Mapper1 extends Mapper<LongWritable, Text,Text,Text> {
    Text outKey = new Text();
    Text outValue = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] rowAndLine = value.toString().split("  ");

        String row = rowAndLine[0];
        String line = rowAndLine[1];
        String[] lines = line.split(",");

        for (int i = 0; i < lines.length; i++) {
            String column = lines[i].split("_")[0];
            String valueStr = lines[i].split("_")[1];

            outKey.set(column);
            outValue.set(row + "_" + valueStr);
            context.write(outKey,outValue);
        }
    }
}
