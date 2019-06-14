package com.wangyao2221.hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordMappper extends Mapper<LongWritable, Text,Text, IntWritable> {
    public final static IntWritable one = new IntWritable(1);

    private Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);
        System.out.println(line);

        while (tokenizer.hasMoreTokens()){
            word.set(tokenizer.nextToken());
            context.write(word,one);
        }
    }
}
