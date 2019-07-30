name := "spark-demo"

version := "0.1"

scalaVersion := "2.12.8"

lazy val commonSettings=Seq(
  version := "1.0",
  scalaVersion := "2.12.8"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name:="SparkStudy",
    libraryDependencies += "org.apache.spark" % "spark-core_2.12" % "2.4.3"
  )

