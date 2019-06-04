package com.wangyao2221.hadoop.kmeans;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class KMeans {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String centerPath = "input/cluster.center.conf.txt";
        String dataPath = "input/kmeans.txt";
        String newCenterPath = "output/center";

        int count = 0;

        while (true) {
            run(centerPath, dataPath, newCenterPath, true);
            System.out.println("第" + (count++) + "次计算");
            if(Utils.compareCenters(centerPath,newCenterPath )){
                run(centerPath,dataPath,newCenterPath,false);
                break;
            }
        }
    }

    public static void run(String centerPath, String dataPath, String newCenterPath, boolean runReduce) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("centersPath", centerPath);

        Job job = Job.getInstance(conf, "kmeans");
        job.setJarByClass(KMeans.class);
        job.setMapperClass(KMeansMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        if (runReduce) {
            job.setReducerClass(KMeansReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);
        }

        FileInputFormat.addInputPath(job, new Path(dataPath));
        FileOutputFormat.setOutputPath(job, new Path(newCenterPath));

        System.out.println(job.waitForCompletion(true));
    }
}
