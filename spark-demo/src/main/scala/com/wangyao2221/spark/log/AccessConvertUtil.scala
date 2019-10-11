package com.wangyao2221.spark.log

import com.ggstar.util.ip.IpHelper
import org.apache.spark.sql.Row
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
    try {
      val splits = log.split("\t")
      val time = splits(0)
      val ip = splits(1)
      val url = splits(2)
      val traffic = splits(3).toLong

      val doamin = "http://www.imooc.com/"
      var cmsType = ""
      var cmsId = 0l

      if(!"-".equals(url)) {
        val cmsTypeId = url.substring(url.indexOf(doamin) + doamin.length).split("\t")
        if (cmsTypeId.length == 2) {
          cmsType = cmsTypeId(0)
          cmsId = cmsTypeId(1).toLong
        }
      }

      val city = IpHelper.findRegionByIp(ip)
      val day = time.substring(0, 10).replace("-", "")

      Row(url, cmsType, cmsId, traffic, ip, city, time, day)
    } catch {
      case e:Exception => Row(0)
    }
  }
}
