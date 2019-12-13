package com.company

import com.company.utils.Settings

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient

object Main extends App {
  private val client: SchemaRegistryClient = new CachedSchemaRegistryClient(
    Settings.Kafka.SchemaRegistry.Url,
    Settings.Kafka.SchemaRegistry.IdentityMapCapacity);
  private val eventHandler: EventHandler = new EventHandler(client)

  eventHandler.start()
}
