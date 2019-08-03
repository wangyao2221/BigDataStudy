package com.wangyao2221.spark

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object HiveContextApp {
  def main(args: Array[String]): Unit = {
    val tableName = args(0)

    val sparkConf = new SparkConf()
    sparkConf.setAppName("HiveContextApp").setMaster("local[2]")

    val sc = new SparkContext(sparkConf)
    val hiveContext = new HiveContext(sc)

    hiveContext.table(tableName).show()

    sc.stop()
  }
}
