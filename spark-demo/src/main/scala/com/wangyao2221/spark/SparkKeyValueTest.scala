package com.wangyao2221.spark

import org.apache.spark.{SparkConf, SparkContext}

object SparkKeyValueTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SparkKeyValueTest").setMaster("local");
    val sc = new SparkContext(conf)
  }
}
