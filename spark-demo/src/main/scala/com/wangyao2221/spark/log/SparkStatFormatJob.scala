package com.wangyao2221.spark.log

import org.apache.spark.sql.SparkSession

object SparkStatFormatJob {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SparkStatFormatJob")
      .master("local[2]")
      .getOrCreate()

    val access = spark.sparkContext.textFile("input/access.20161111.log")
    //    access.take(10).foreach(println)

    access.map(line => {
      val splits = line.split(" ")
      val ip = splits(0)
      val time = splits(3) + " " + splits(4)
      val url = splits(11).replace("\"", "")
      val traffic = splits(9)
//      (ip, DateUtils.parse(time), url, traffic)
      DateUtils.parse(time) + "\t" + ip + "\t" + url + "\t" + traffic
    }).saveAsTextFile("output\\")
  }
}
