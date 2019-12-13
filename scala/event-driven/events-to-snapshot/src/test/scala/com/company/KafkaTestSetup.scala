package com.company

import java.util.Properties

import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.common.serialization.Serdes
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient

trait KafkaTestSetup {
  val config = new Properties()
  config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "testing")
  config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "testing:1234")
  config.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
  config.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass.getName)

  val schemaRegistry: SchemaRegistryClient = new MockSchemaRegistryClient()
}
