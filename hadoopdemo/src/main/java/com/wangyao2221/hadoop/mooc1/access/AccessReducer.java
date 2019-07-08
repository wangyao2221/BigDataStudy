package com.wangyao2221.hadoop.mooc1.access;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class AccessReducer extends Reducer<Text, Access, NullWritable, Access> {
    @Override
    protected void reduce(Text key, Iterable<Access> values, Context context) throws IOException, InterruptedException {
        Iterator<Access> iterator = values.iterator();

        int ups = 0;
        int downs = 0;
        long sum = 0;
        while (iterator.hasNext()) {
            Access access = iterator.next();
            ups += access.getUp();
            downs += access.getDown();
        }

        context.write(NullWritable.get(), new Access(key.toString(), ups, downs, ups + downs));
    }
}
