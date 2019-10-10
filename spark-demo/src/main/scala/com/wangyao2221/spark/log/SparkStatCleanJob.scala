package com.wangyao2221.spark.log

import org.apache.spark.sql.SparkSession

object SparkStatCleanJob {
  def main(args: Array[String]) = {
    val spark = SparkSession.builder()
      .appName("SparkStatCleanJob")
      .master("local[2]")
      .getOrCreate()

    import spark.implicits._
    val accessRDD = spark.sparkContext.textFile("input/access.log")
    accessRDD.take(10).foreach(println)

//    spark.createDataFrame()

    spark.stop()
  }
}
