name := "talent-request-baker"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.8"

libraryDependencies += "com.ing.baker" %% "baker-recipe-dsl" % "2.0.1"
libraryDependencies += "com.ing.baker" %% "baker-runtime" % "2.0.1"
libraryDependencies += "com.ing.baker" %% "baker-compiler" % "2.0.1"
libraryDependencies += "guru.nidi" % "graphviz-java" % "0.8.0"

libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.11.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.11.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.11.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
