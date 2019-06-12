package com.wangyao2221.hadoop.itemcf.step1;

import com.wangyao2221.hadoop.kmeans.KMeansMapper;
import com.wangyao2221.hadoop.kmeans.KMeansReducer;
import com.wangyao2221.hadoop.kmeans.Utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Step1 {
    private static String inputPath = "input/itemcf/step1_input/ActionList.txt";
    private static String outputPath = "output/itemcf/step1_output";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        int result = 1;
        result = new Step1().run();

        if (result == 1){
            System.out.println("run success");
        }else {
            System.out.println("run fail");
        }
    }

    public int run() {
        try {
            Configuration conf = new Configuration();

            Job job = Job.getInstance(conf, "itemcf_step1");
            job.setJarByClass(Step1.class);
            job.setMapperClass(Mapper1.class);
            job.setReducerClass(Reducer1.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            FileInputFormat.addInputPath(job, new Path(inputPath));
            FileOutputFormat.setOutputPath(job, new Path(outputPath));

            return job.waitForCompletion(true) ? 1 : -1 ;
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
