package com.wangyao2221.spark.log

import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

object AccessConvertUtil {
  val struct = StructType(
    Array(
      StructField("url", StringType),
      StructField("cmsType", StringType),
      StructField("cmsId", LongType),
      StructField("traffic", LongType),
      StructField("ip", StringType),
      StructField("city", StringType),
      StructField("time", StringType),
      StructField("day", StringType)
    )
  )

  def parseLog(log: String) = {
    val splits = log.split("\t")
    val time = splits(0)
    val ip = splits(1)
    val url = splits(2)
    val traffic = splits(3)
  }
}
