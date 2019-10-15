package com.wangyao2221.spark.log.dao

import java.sql.{Connection, PreparedStatement}

import com.wangyao2221.spark.log.model.{DayCityAccessStat, DayVideoAccessStat, DayVideoTrafficsStat}
import com.wangyao2221.spark.log.util.JDBCUtil

import scala.collection.mutable.ListBuffer

object StatDao {
  def insertDayVedioAccessTopN(list: ListBuffer[DayVideoAccessStat]): Unit = {
    var connection: Connection = null
    var statement: PreparedStatement = null

    try {
      connection = JDBCUtil.getConnetction()

      val sql = "insert into day_video_access_topn_stat values(?, ?, ?)"
      statement = connection.prepareStatement(sql)

      for (elem <- list) {
        statement.setString(1, elem.day)
        statement.setLong(2, elem.cmdId)
        statement.setLong(3, elem.times)
        statement.addBatch()
      }

      statement.executeBatch()
//      connection.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      JDBCUtil.release(statement, connection)
    }
  }

  def insertDayCityAccessTopN(list: ListBuffer[DayCityAccessStat]): Unit = {
    var connection: Connection = null
    var statement: PreparedStatement = null

    try {
      connection = JDBCUtil.getConnetction()

      val sql = "insert into day_video_city_access_topn_stat values(?, ?, ?, ?, ?)"
      statement = connection.prepareStatement(sql)

      for (elem <- list) {
        statement.setString(1, elem.day)
        statement.setLong(2, elem.cmdId)
        statement.setString(3, elem.city)
        statement.setLong(4, elem.times)
        statement.setInt(5, elem.times_rank)
        statement.addBatch()
      }

      statement.executeBatch()
      //      connection.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      JDBCUtil.release(statement, connection)
    }
  }

  def insertDayTrafficsTopN(list: ListBuffer[DayVideoTrafficsStat]): Unit = {
    var connection: Connection = null
    var statement: PreparedStatement = null

    try {
      connection = JDBCUtil.getConnetction()

      val sql = "insert into day_video_traffic_topn_stat values(?, ?, ?)"
      statement = connection.prepareStatement(sql)

      for (elem <- list) {
        statement.setString(1, elem.day)
        statement.setLong(2, elem.cmdId)
        statement.setLong(3, elem.traffics)
        statement.addBatch()
      }

      statement.executeBatch()
      //      connection.commit()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      JDBCUtil.release(statement, connection)
    }
  }
}
