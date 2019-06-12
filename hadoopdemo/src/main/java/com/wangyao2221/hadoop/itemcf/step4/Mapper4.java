package com.wangyao2221.hadoop.itemcf.step4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Mapper4 extends Mapper<LongWritable, Text,Text,Text> {
    private Text outKey = new Text();
    private Text outValue = new Text();

    private List<String> cacheList = new ArrayList<String>();

    DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        Configuration conf = context.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        FSDataInputStream in = fs.open(new Path("output/itemcf/step2_output/part-r-00000"));

        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line = null;
        while ((line = br.readLine()) != null){
            cacheList.add(line);
        }

        br.close();
        in.close();
        fs.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String matrix1Row = value.toString().split("\t")[0];
        String[] matrix1Values = value.toString().split("\t")[1].split(",");

        for (String line : cacheList) {
            String matrix2Row = line.split("\t")[0];
            String[] matrix2Values = line.split("\t")[1].split(",");

            double sum = 0;

            for (String matrix1Value : matrix1Values) {
                String matrix1Column = matrix1Value.split("_")[0];
                String matrix1ColumnValue = matrix1Value.split("_")[1];

                for (String matrix2Value : matrix2Values) {
                    String matrix2Column = matrix2Value.split("_")[0];
                    String matrix2ColumnValue = matrix2Value.split("_")[1];

                    if (matrix1Column.equals(matrix2Column)){
                        double num1 = Double.parseDouble(matrix1ColumnValue);
                        double num2 = Double.parseDouble(matrix2ColumnValue);
                        sum += num1 * num2;
                    }
                }
            }

            if (sum == 0){
                continue;
            }

            outKey.set(matrix2Row);
            outValue.set(matrix1Row + "_" + df.format(sum));
            context.write(outKey,outValue);
        }
    }
}
