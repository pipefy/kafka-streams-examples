package com.company.utils

import java.util.Properties

import com.typesafe.config.ConfigFactory
import org.apache.kafka.streams.StreamsConfig

object Settings {
  val appEnv: String = scala.util.Properties.envOrElse("PFY_STREAMS_PIPE_ES_APP_ENV", "dev")
  val config = ConfigFactory.load(s"config/${appEnv}.conf")

  private val appConfig = config.getConfig("app")
  private val kafkaConfig = config.getConfig("kafka")

  object App {
    val ID: String = appConfig.getString("id")
  }

  object Kafka {
    val BootStrapServers: String = kafkaConfig.getString("bootStrapServers")

    object SchemaRegistry {
      val Url: String = kafkaConfig.getString("schemaRegistryUrl")
      val IdentityMapCapacity: Integer = 30
    }
  }

  object Events {
    val Topic: String = kafkaConfig.getString("eventsTopic")
  }

  object Stores {
    val Snapshots: String = kafkaConfig.getString("storesSnapshots")
  }

  def createBasicStreamProperties(applicationId: String) : Properties = { // TODO: Move it from here
    val props = new Properties()

    props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId)
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, Kafka.BootStrapServers)
    props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10000.asInstanceOf[Object]) // Set commit interval to 1 second.
    props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 10485760.asInstanceOf[Object]) // Enable record cache of size 10 MB

    props.setProperty("offsets.topic.replication.factor", "1")
    props.setProperty("transaction.state.log.replication.factor", "1")
    props.setProperty("transaction.state.log.min.isr", "1")

    props
  }
}
