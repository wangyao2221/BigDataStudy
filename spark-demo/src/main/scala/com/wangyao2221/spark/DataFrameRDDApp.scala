package com.wangyao2221.spark

import org.apache.spark.sql.SparkSession

object DataFrameRDDApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()

    val rdd = spark.sparkContext.textFile("input/info")
//    spark.sparkContext.textFile("D:\Workspace\IDEA\BigDataStudy\spark-demo\input\info")

    import spark.implicits._
    val infoDF = rdd.map(_.split(",")).map(line => Info(line(0).toInt, line(1).toString, line(2).toInt)).toDF()

    infoDF.show()

    spark.stop()
  }

  case class Info(id: Int,name: String, age: Int)
}
