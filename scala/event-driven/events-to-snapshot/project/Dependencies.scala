import sbt._

object Dependencies {
  // Versions
  lazy val kafkaVersion = "2.2.0"

  // Libraries
  // All
  val kafkaStreams = "org.apache.kafka" % "kafka-streams" % kafkaVersion
  val avroSerializer = "io.confluent" % "kafka-avro-serializer" % "5.3.0"
  val avroSerde = "io.confluent" % "kafka-streams-avro-serde" % "5.0.0"
  val kafkaScala = "org.apache.kafka" %% "kafka-streams-scala" % kafkaVersion
  val logback = "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
  val typeSafe = "com.typesafe" % "config" % "1.3.4"
  val log4s = "org.log4s" %% "log4s" % "1.6.1"
  val avro4s = "com.sksamuel.avro4s" %% "avro4s-core" % "1.9.0"

  // Test
  val scalactic = "org.scalactic" %% "scalactic" % "3.0.5"
  val kStreamsTestUtils = "org.apache.kafka" % "kafka-streams-test-utils" % "2.3.1"
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  val avro = "org.apache.avro" % "avro" % "1.9.1"

  // Environments
  val allDeps =
    Seq(kafkaStreams,
      avroSerializer,
      avroSerde,
      kafkaScala,
      logback,
      typeSafe,
      log4s,
      avro4s)

  val testDeps =
    Seq(scalactic % Test,
      kStreamsTestUtils % Test,
      scalaTest % Test,
      avro % Test)
}
