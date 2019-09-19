package com.wangyao2221.spark

import org.apache.spark.sql.SparkSession

object DataFrameApp {
  def main(args: Array[String]) {
    val spark = SparkSession.builder().appName("DataFrameApp").master("local[2]").getOrCreate()
    val peopleDF = spark.read.format("json").load("D:\\Workspace\\IDEA\\BigDataStudy\\spark-demo\\input\\people.json")

    // 输入DataFrame对应的Scheme信息
    peopleDF.printSchema()

    // 输出数据集的前20条记录
    peopleDF.show();

    peopleDF.select("name").show()

    peopleDF.select(peopleDF.col("name"),(peopleDF.col("age") + 10).as("age2")).show()

    peopleDF.filter(peopleDF.col("age") > 19).show()

    peopleDF.groupBy(peopleDF.col("age")).count().show()

    spark.stop();
  }
}
