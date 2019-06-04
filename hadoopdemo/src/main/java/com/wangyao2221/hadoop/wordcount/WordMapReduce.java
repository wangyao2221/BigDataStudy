package com.wangyao2221.hadoop.wordcount;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordMapReduce {

    public static void main(String[] args) throws Exception {
        if (args.length != 2){
            System.err.println("Usage: <input path> <output path>");
        }

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        Job job = Job.getInstance(conf, "WordCount");

        job.setJarByClass(WordMapReduce.class);
        job.setMapperClass(WordMappper.class);
        job.setReducerClass(WordReducer.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
