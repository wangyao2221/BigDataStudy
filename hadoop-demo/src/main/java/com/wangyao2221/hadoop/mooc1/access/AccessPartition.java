package com.wangyao2221.hadoop.mooc1.access;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AccessPartition extends Partitioner<Text,Access> {
    public int getPartition(Text text, Access access, int i) {
        if (text.toString().startsWith("13")) {
            return 0;
        } else {
            return 1;
        }
    }
}
