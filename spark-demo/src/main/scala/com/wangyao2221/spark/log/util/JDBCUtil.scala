package com.wangyao2221.spark.log.util

import java.sql.{Connection, DriverManager, Statement}

object JDBCUtil {
  def getConnetction(): Connection = {
    Class.forName("com.mysql.cj.jdbc.Driver")
    DriverManager.getConnection("jdbc:mysql://localhost:3306/spark_imooc_log_db?serverTimezone=UTC", "root", "123456")
  }

  def release(statement: Statement, connection: Connection) = {
    try {
      if (null != statement) {
        statement.close()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (null != connection) {
        connection.close()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val connection = JDBCUtil.getConnetction()
  }
}
