package com.wangyao2221.spark

import org.apache.spark.sql.SparkSession

object SchemaMergeApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[2]").appName("SchemaMergeTest").getOrCreate()
    import spark.implicits._

    val squareDF = spark.sparkContext.makeRDD(1 to 5).map(i => (i, i * i)).toDF("value", "square")
    squareDF.write.parquet("data/schema_merge_test_table/key=1")
//    squareDF.printSchema()
//    squareDF.show()

    val cubeDF = spark.sparkContext.makeRDD(5 to 10).map(i => (i , i * i * i)).toDF("value","cude")
    cubeDF.write.parquet("data/schema_merge_test_table/key=2")
//    cubeDF.printSchema()
//    cubeDF.show()

    val mergeDF = spark.read.option("mergeSchema","true").parquet("data/schema_merge_test_table")
    mergeDF.printSchema()
    mergeDF.show()
  }
}
