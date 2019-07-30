import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCount {
  def main(args: Array[String]) {
    val logFile = "D:\\Workspace\\IDEA\\spark-demo\\input\\words";
    val conf = new SparkConf().setAppName("SparkTest").setMaster("local[2]");
    val sc = new SparkContext(conf)


    val logData = sc.textFile(logFile)
    //    logData.foreach(println(_))
    //    logData
    //      .map(s => s.split(" "))
    //      .foreach(s => {
    //        s.foreach(println(_))
    //        println()
    //      })

    logData
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
      .foreach(println)
  }

  def append(s: String): String = {
    return s + "xxx"
  }

  def mymap(): Unit ={

  }
}