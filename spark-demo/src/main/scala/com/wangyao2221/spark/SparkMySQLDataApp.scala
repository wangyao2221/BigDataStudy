package com.wangyao2221.spark

import org.apache.spark.sql.SparkSession

/**
 * CREATE TEMPORARY VIEW jbdcTable
 * USING org.apache.spark.sql.jdbc
 * OPTIONS {
 *  url "jdbc:mysql://localhost:3306",
 *  dbtable "metastore.TBLS",
 *  user 'root',
 *  password 'cloudera',
 *  drive com.mysql.jdbc.Driver
 * }
 */
object SparkMySQLDataApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("SparkMySQLDataApp").master("local[2]").getOrCreate();
    val mysqlDF = spark.read.format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/metastore")
      .option("dbtable", "metastore.TBLS")
      .option("user", "root")
      .option("password", "cloudera")
      .option("driver", "com.mysql.jdbc.Driver")
      .load

    mysqlDF.show()
  }
}
