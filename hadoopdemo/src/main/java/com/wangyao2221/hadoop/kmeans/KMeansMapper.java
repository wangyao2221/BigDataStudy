package com.wangyao2221.hadoop.kmeans;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.ArrayList;

public class KMeansMapper extends Mapper<LongWritable, Text, IntWritable,Text> {
    ArrayList<ArrayList<Double>> centers = null;
    int k = 0;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        centers = Utils.getCentersFromHDFS(context.getConfiguration().get("centersPath"),false);
        k = centers.size();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        ArrayList<Double> fields = Utils.textToArray(value);
        int fieldSize = fields.size();

        double minDistance = Integer.MAX_VALUE;
        int centerIndex = 0;

        for (int i = 0; i < k; i++) {
            double distance = 0;
            for (int j = 0; j < fieldSize; j++) {
                double centerField = centers.get(i).get(j);
                double field = fields.get(j);
                distance += Math.pow(centerField - field,2);
            }

            if (distance < minDistance){
                minDistance = distance;
                centerIndex = i;
            }
        }

        context.write(new IntWritable(centerIndex + 1), value);
    }
}
