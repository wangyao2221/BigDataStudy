package com.wangyao2221.hadoop;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
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
        configuration.set("fs.defaultFS", "hdfs://localhost:9000");
        fileSystem = FileSystem.get(URI.create("hdfs://localhost:9000"), configuration, "root");
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
}