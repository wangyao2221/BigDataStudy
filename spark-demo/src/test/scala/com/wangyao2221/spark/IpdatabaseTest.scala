package com.wangyao2221.spark

import com.ggstar.util.ip.IpHelper

object IpdatabaseTest {
  def main(args: Array[String]): Unit = {
    println(IpHelper.findRegionByIp("101.227.12.253"))
  }
}
