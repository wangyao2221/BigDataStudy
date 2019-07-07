package com.wangyao2221.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

/**
 * Created by kingcos on 25/03/2017.
 */
public class HDFSOperations {

    FileSystem fileSystem;

    @Before
    public void configure() throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://172.18.0.2:9000");
        fileSystem = FileSystem.get(URI.create("hdfs://172.18.0.2:9000"), configuration, "root");
    }

    @Test
    public void listFiles() throws IOException {
        Path path = new Path("/");
        RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(path, true);

        while (iterator.hasNext()) {
            LocatedFileStatus status = iterator.next();
            System.out.println(status.getPath().getName());
        }
    }

    @Test
    public void rm() throws IOException {
        Path path = new Path("/");
        fileSystem.delete(path, true);
    }

    @Test
    public void mkdir() throws IOException {
        Path path = new Path("/demo");
        fileSystem.mkdirs(path);
    }

    @Test
    public void text() throws IOException {
        Path path = new Path("/user/root/output/part-r-00000");
        FSDataInputStream input = fileSystem.open(path);
        IOUtils.copyBytes(input,System.out,1024);
    }
}