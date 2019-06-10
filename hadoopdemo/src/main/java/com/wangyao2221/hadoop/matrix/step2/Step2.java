package com.wangyao2221.hadoop.matrix.step2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Step2 {
    private static String inputPath = "input/matrix/step2_input/matrix1";
    private static String outputPath = "output/matrix/output";
    private static String cache = "output/matrix/step1_output";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        int result = -1;
        result = run(inputPath, outputPath);
        if (result == 1){
            System.out.println("run success");
        }else {
            System.out.println("run fail");
        }
    }

    public static int run(String inputPath, String outputPath) throws IOException, ClassNotFoundException, InterruptedException {
        try {
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "matrix_step2");
            //单机环境分布式缓存不可用
//            job.addCacheArchive(new URI(cache + "#matrix2"));
//            job.addCacheArchive(new URI(cache + "/part-r-00000"));

            job.setJarByClass(Step2.class);
            job.setMapperClass(Mapper2.class);
            job.setReducerClass(Reducer2.class);

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
