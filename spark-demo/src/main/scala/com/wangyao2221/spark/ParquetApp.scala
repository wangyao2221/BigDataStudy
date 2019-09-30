package com.wangyao2221.spark

import org.apache.spark.sql.SparkSession

object ParquetApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("ParquetApp").master("local[2]").getOrCreate()
    val inputPath = "D:\\Workspace\\IDEA\\BigDataStudy\\spark-demo\\input\\people.json"
    val peopleDF = spark.read.format("json").load(inputPath)

    val outputPath = "D:\\Workspace\\IDEA\\BigDataStudy\\spark-demo\\input\\people.parquet"
//    peopleDF.write.format("parquet").save(outputPath)

    val peopleDF2 = spark.read.format("parquet").load(outputPath)
    peopleDF2.printSchema()
    peopleDF2.show()

    spark.stop()
  }
}
