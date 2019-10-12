package com.wangyao2221.spark.log

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
/**
 * TopN同级
 */
object TopNStatJob {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .config("spark.sql.sources.partitionColumnTypeInference.enabled","false")
      .appName("SparkStatFormatJob")
      .master("local[*]")
      .getOrCreate()

    val accessDF = spark.read.format("parquet").load("input/clean")
    accessDF.printSchema()
    accessDF.show(false)

    videoAccessTopNStat(spark, accessDF)

    spark.stop()
  }

  def videoAccessTopNStat(spark: SparkSession, accessDF: DataFrame) = {
    import spark.implicits._

    val videoTopNDF = accessDF.filter($"day" === "20161110" && $"cmsType" === "video")
      .groupBy("day", "cmsId").agg(count("cmsId").as("times"))
      .orderBy($"times".desc)

    videoTopNDF.show(false)
  }

}
