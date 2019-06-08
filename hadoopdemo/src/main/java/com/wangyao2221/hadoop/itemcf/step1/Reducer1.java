package com.wangyao2221.hadoop.itemcf.step1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Reducer1 extends Reducer<Text,Text,Text,Text> {
    private Text outKey = new Text();
    private Text outValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String itemID = key.toString();
        Map<String,Integer> map = new HashMap<String, Integer>();

        for (Text value : values){
            String[] item = value.toString().split("_");
            String userID = item[0];
            int score = Integer.parseInt(item[1]);
            if (!map.containsKey(userID)){
                map.put(userID,score);
            }else {
                score = score + map.get(userID);
                map.put(userID,score);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String k : map.keySet()) {
            int score = map.get(k);
            sb.append(score).append(",");
        }
        if (sb.toString().endsWith(",")){
            sb.subSequence(0,sb.length() - 1);
        }

        outKey.set(itemID);
        outValue.set(sb.toString());

        context.write(outKey,outValue);
    }
}
