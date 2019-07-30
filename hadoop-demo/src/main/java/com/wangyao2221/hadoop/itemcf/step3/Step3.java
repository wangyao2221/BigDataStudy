package com.wangyao2221.hadoop.itemcf.step3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Step3 {
    private static String inputPath = "output/itemcf/step1_output/part-r-00000";
    private static String outputPath = "output/itemcf/step3_output";

    public static void main(String[] args) {
        int result = -1;
        result = new Step3().run();

        if (result == 1){
            System.out.println("run success");
        }else {
            System.out.println("run fail");
        }
    }

    public int run() {
        try {
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "itemcf_step3");

            job.setJarByClass(Step3.class);
            job.setMapperClass(Mapper3.class);
            job.setReducerClass(Reducer3.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            FileSystem fs = FileSystem.get(conf);
            if (fs.exists(new Path(inputPath))) {
                FileInputFormat.addInputPath(job, new Path(inputPath));
            }

            FileOutputFormat.setOutputPath(job, new Path(outputPath));

            return job.waitForCompletion(true) ? 1 : -1 ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return -1;
    }
}
