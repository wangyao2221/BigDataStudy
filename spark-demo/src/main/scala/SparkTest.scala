import org.apache.spark.{SparkConf, SparkContext}

object SparkTest {
  def main(args: Array[String]) {
    val logFile ="D:\\Program Files\\TxGameAssistant\\UI\\TxGaDcc.log";
    print("hello ")

//    val conf = new SparkConf().setAppName("SparkTest").setMaster("local");
//
//    val sc = new SparkContext(conf)
//    val logData = sc.textFile(logFile, 2).cache()
//    val numAs = logData.filter(line => line.contains("a")).count()
//    val numBs = logData.filter(line => line.contains("b")).count()
//    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
//    print("---------------");
  }
}