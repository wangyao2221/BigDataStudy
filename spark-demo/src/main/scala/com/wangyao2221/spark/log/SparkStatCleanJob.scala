package com.wangyao2221.spark.log

import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkStatCleanJob {
  def main(args: Array[String]) = {
    val spark = SparkSession.builder()
      .appName("SparkStatCleanJob")
      .master("local[2]")
      .getOrCreate()

    import spark.implicits._
    val accessRDD = spark.sparkContext.textFile("input/access.log")
    accessRDD.take(10).foreach(println)

    val accessDF = spark.createDataFrame(accessRDD.map(x => AccessConvertUtil.parseLog(x)), AccessConvertUtil.struct)
//    accessDF.printSchema()
//    accessDF.show(false)
    accessDF.write.format("parquet").mode(SaveMode.Overwrite).partitionBy("day").save("input/clean")

    spark.stop()
  }
}
