package com.wangyao2221.hadoop.itemcf.step4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reducer4 extends Reducer<Text,Text,Text,Text> {
    Text outKey = new Text();
    Text outValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();

        for (Text value : values) {
            stringBuilder.append(value.toString() + ",");
        }

        String line = null;
        if (stringBuilder.toString().endsWith(",")){
            line = stringBuilder.subSequence(0,stringBuilder.length() - 1).toString();
        }

        outKey.set(key);
        outValue.set(line);
        context.write(outKey,outValue);
    }
}
