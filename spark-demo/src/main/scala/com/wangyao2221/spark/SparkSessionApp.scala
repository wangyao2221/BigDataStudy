package com.wangyao2221.spark

import org.apache.spark.sql.internal.SQLConf
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.hadoop.hive.ql.exec.Utilities.copyTableJobPropertiesToConf

object SparkSessionApp {
  def main(args: Array[String]): Unit = {
    val path = args(0)

    val spark = SparkSession.builder()
      .appName("SparkSessionApp")
      .master("local[2]")
      .getOrCreate()

    val people = spark.read.json(path)
    people.show()

    spark.stop()
  }
}
