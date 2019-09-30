package com.wangyao2221.spark

import org.apache.spark.sql.SparkSession

object SparkHiveDataApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("SparkHiveDataApp").master("local[2]").getOrCreate()

    spark.sql("show tables").show
    spark.table("emp").show
    spark.sql("select deptno,count(*) from emp group by deptno").show
    spark.sql("select deptno,count(*) from emp group by deptno").filter("deptno is not null").show

    // 由于是伪分布式，所以分区多会比较慢，可以设置小一点
    spark.sqlContext.getConf("spark.sql.shuffle.partitions")
//    spark.sqlContext.setConf("spark.sql.shuffle.partitions",10)

    spark.stop()
  }
}
