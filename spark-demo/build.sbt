name := "spark-demo"

version := "0.1"

scalaVersion := "2.11.12"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.12"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "SparkStudy",
    libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.2.0",
    libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.2.0",
    libraryDependencies += "org.apache.spark" % "spark-hive_2.11" % "2.2.0"
  )
