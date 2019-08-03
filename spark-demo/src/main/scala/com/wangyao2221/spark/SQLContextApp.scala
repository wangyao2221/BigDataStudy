package com.wangyao2221.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object SQLContextApp {
  def main(args: Array[String]): Unit = {
    val path = args(0)

    // 1) 创建相关的Context
    val sparkConf = new SparkConf()
    sparkConf.setAppName("SQLContextApp").setMaster("local[2]")

    val sc = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sc)

    // 2)相关的处理
    val people = sqlContext.read.json(path)
    people.printSchema()
    people.foreach(row => println(row))

    // 3)关闭资源
    sc.stop()

  }
}
