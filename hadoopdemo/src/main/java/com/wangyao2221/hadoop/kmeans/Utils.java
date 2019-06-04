package com.wangyao2221.hadoop.kmeans;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.LineReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static ArrayList<ArrayList<Double>> getCentersFromHDFS(String centersPath, boolean isDirectory) throws IOException {
        ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();

        Path path = new Path(centersPath);
        Configuration configuration = new Configuration();
        FileSystem fileSystem = path.getFileSystem(configuration);

        if (isDirectory) {
            FileStatus[] listFile = fileSystem.listStatus(path);
            for (FileStatus fileStatus : listFile) {
                result.addAll(getCentersFromHDFS(fileStatus.getPath().toString(), false));
            }
            return result;
        }

        FSDataInputStream fsis = fileSystem.open(path);
        LineReader reader = new LineReader(fsis, configuration);

        Text line = new Text();
        while (reader.readLine(line) > 0) {
            ArrayList<Double> tempList = textToArray(line);
            result.add(tempList);
        }
        reader.close();

        return result;
    }

    public static ArrayList<Double> textToArray(Text text) {
        ArrayList<Double> list = new ArrayList<Double>();
        String[] fileds = text.toString().split(",");
        for (String filed : fileds) {
            list.add(Double.valueOf(filed));
        }
        return list;
    }

    public static void deletePath(String pathStr) throws IOException {
        Configuration conf = new Configuration();
        Path path = new Path(pathStr);
        FileSystem hdfs = path.getFileSystem(conf);
        hdfs.delete(path, true);
    }

    public static boolean compareCenters(String centerPath, String newCenterPath) throws IOException {
        List<ArrayList<Double>> oldCenters = Utils.getCentersFromHDFS(centerPath, false);
        List<ArrayList<Double>> newCenters = Utils.getCentersFromHDFS(newCenterPath, true);

        boolean same = true;

        for (int i = 0; i < oldCenters.size(); i++) {
            for (int j = 0; j < oldCenters.get(i).size(); j++) {
                double fieldOld = oldCenters.get(i).get(j);
                double fieldNew = newCenters.get(i).get(j);
                if (fieldOld != fieldNew) same = false;
            }
        }

        if (same) {
            Utils.deletePath(newCenterPath);
            return true;
        } else {
            Configuration conf = new Configuration();
            Path output = new Path(centerPath);
            FileSystem fileSystem = output.getFileSystem(conf);

            // 不加这三行会怎么样
            FSDataOutputStream overWrite = fileSystem.create(output, true);
            overWrite.writeChars("");
            overWrite.close();

            Path input = new Path(newCenterPath);
            FileStatus[] listFiles = fileSystem.listStatus(input);
            for (int i = 0; i < listFiles.length; i++) {
                FSDataOutputStream out = fileSystem.create(output);
                FSDataInputStream in = fileSystem.open(listFiles[i].getPath());
                IOUtils.copyBytes(in, out, 4096, true);
            }
            Utils.deletePath(newCenterPath);
        }

        return false;
    }
}
