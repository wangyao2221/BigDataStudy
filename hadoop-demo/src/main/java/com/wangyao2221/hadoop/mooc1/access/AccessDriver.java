package com.wangyao2221.hadoop.mooc1.access;

import com.wangyao2221.hadoop.BaseDriver;
import com.wangyao2221.hadoop.utils.HDFSUtils;
import com.wangyao2221.hadoop.wordcount.WordCount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class AccessDriver extends BaseDriver {
    public static void main(String[] args) throws Exception {
        new AccessDriver().run(args);
    }

    protected Job constructJob(Configuration conf) throws IOException {
//        HDFSUtils.rm("output");

        Job job = Job.getInstance(conf, "access");
        job.setJarByClass(AccessDriver.class);
        job.setMapperClass(AccessMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Access.class);

//        job.setCombinerClass(AccessReducer.class);
        job.setReducerClass(AccessReducer.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Access.class);

        job.setPartitionerClass(AccessPartition.class);
        job.setNumReduceTasks(2);
        job.setSortComparatorClass(Access.Comparator.class);

        return job;
    }
}
