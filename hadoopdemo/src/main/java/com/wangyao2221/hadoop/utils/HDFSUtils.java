package com.wangyao2221.hadoop.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class HDFSUtils {
    static FileSystem fileSystem;

    static {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://172.18.0.2:9000");
        try {
            fileSystem = FileSystem.get(URI.create("hdfs://172.18.0.2:9000"), configuration, "root");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean rm(String pathString) throws IOException {
        Path path = new Path(pathString);
        return fileSystem.delete(path, true);
    }
}
