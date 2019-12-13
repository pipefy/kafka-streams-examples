import Dependencies._

ThisBuild / organization := "com.company"
ThisBuild / version      := "0.0.1"
ThisBuild / scalaVersion := "2.12.1"

scalastyleFailOnError := true
scalastyleFailOnWarning := true

resolvers += "Confluent" at "http://packages.confluent.io/maven/"

libraryDependencies ++= allDeps
libraryDependencies ++= testDeps
