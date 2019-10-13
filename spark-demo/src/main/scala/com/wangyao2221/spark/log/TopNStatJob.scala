package com.wangyao2221.spark.log

import com.wangyao2221.spark.log.dao.StatDao
import com.wangyao2221.spark.log.model.DayVedioAccessStat
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

import scala.collection.mutable.ListBuffer
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
//    import spark.implicits._
//
//    val videoAccessTopNDF = accessDF.filter($"day" === "20161110" && $"cmsType" === "video")
//      .groupBy("day", "cmsId").agg(count("cmsId").as("times"))
//      .orderBy($"times".desc)
//    videoAccessTopNDF.show(false)

    accessDF.createOrReplaceTempView("access_logs")
    val videoAccessTopNDF = spark.sql("select day,cmsId,count(1) as times " +
      "from access_logs " +
      "where day='20161110' and cmsType='video' " +
      "group by day,cmsId " +
      "order by times desc")
    videoAccessTopNDF.show(false)

    try {
      videoAccessTopNDF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[DayVedioAccessStat]

        partitionOfRecords.foreach(info => {
          val day = info.getAs[String]("day")
          val cmdId = info.getAs[Long]("cmsId")
          val times = info.getAs[Long]("times")
          list.append(DayVedioAccessStat(day, cmdId, times))
        })

        StatDao.insertDayVedioAccessTopN(list)
      })
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

}
