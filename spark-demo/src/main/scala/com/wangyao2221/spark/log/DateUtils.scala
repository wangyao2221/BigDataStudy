package com.wangyao2221.spark.log

import java.text.SimpleDateFormat
import java.util.{Date, Locale}

import net.sf.cglib.core.Local
import org.apache.commons.lang3.time.FastDateFormat

object DateUtils {
  val YYYYMMMDDHHMM_TIME_FORMAT = FastDateFormat.getInstance("dd/MMM/YYYY:HH:mm:ss Z",Locale.ENGLISH)
//  val YYYYMMMDDHHMM_TIME_FORMAT = new SimpleDateFormat("dd/MMM/YYYY:HH:mm:ss Z",Locale.ENGLISH)
  val TARGET_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")
//  val TARGET_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def parse(time: String) = {

    TARGET_FORMAT.format(getTime(time))
  }

  def getTime(time: String) = {
    try {
      val start = time.indexOf("[") + 1
      val end = time.indexOf("]")
      YYYYMMMDDHHMM_TIME_FORMAT.parse(time.substring(start, end))
    } catch {
      case e: Exception => {
        0l
      }
    }
  }
}
