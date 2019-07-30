package com.wangyao2221.hadoop.kmeans;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class KMeansReducer extends Reducer<IntWritable, Text, Text, Text> {
    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        ArrayList<ArrayList<Double>> fieldsList = new ArrayList<ArrayList<Double>>();

        for (Iterator<Text> it = values.iterator(); it.hasNext();){
            ArrayList<Double> tmpList = Utils.textToArray(it.next());
            fieldsList.add(tmpList);
        }

        int fieldSize = fieldsList.get(0).size();
        double[] avg = new double[fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            double sum = 0;
            for (int j = 0; j < fieldsList.size(); j++) {
                sum += fieldsList.get(j).get(i);
            }
            avg[i] = sum / fieldsList.size();
        }

        context.write(new Text(""),new Text(Arrays.toString(avg).replace("[","").replace("]","")));
    }
}
