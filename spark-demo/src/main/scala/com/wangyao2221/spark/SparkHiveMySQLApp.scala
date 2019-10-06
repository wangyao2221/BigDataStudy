package com.wangyao2221.spark

import org.apache.spark.sql.SparkSession

class SparkHiveMySQLApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SparkMySQLDataApp")
      .master("local[2]")
      .getOrCreate();


    val hiveDF = spark.table("emp")

    val mysqlDF = spark.read.format("jdbc") .option("url", "jdbc:mysql://localhost:3306/spark").option("dbtable", "spark.DEPT").option("user", "root").option("password", "cloudera").option("driver", "com.mysql.jdbc.Driver").load

    val resultDF = hiveDF.join(mysqlDF,hiveDF.col("deptno") === mysqlDF.col("DEPTNO"))

    spark.stop()
  }
}
