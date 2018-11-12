import sbt.Keys._
import sbt._
import sbtrelease.Version

name := "getTitle"

resolvers += Resolver.sonatypeRepo("public")
scalaVersion := "2.12.6"
releaseNextVersion := { ver => Version(ver).map(_.bumpMinor.string).getOrElse("Error") }
assemblyJarName in assembly := "getTitle.jar"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-lambda-java-events" % "2.2.1",
  "com.amazonaws" % "aws-lambda-java-core" % "1.2.0"
)

libraryDependencies += "net.ruippeixotog" %% "scala-scraper" % "2.1.0"
libraryDependencies += "org.tsers.zeison" %% "zeison" % "0.8.0"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings")
