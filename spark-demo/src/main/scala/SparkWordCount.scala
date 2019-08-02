import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCount {
  def main(args: Array[String]) {
    val logFile = args(0)
    val conf = new SparkConf().setAppName("SparkTest").setMaster("local")
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
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey((a, b) => a + b)
      .foreach(println)
  }

  def append(s: String): String = {
    return s + "xxx"
  }

  def mymap(): Unit = {

  }
}