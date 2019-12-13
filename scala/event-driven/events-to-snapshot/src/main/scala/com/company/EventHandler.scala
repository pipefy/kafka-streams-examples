package com.company

import com.company.utils.Settings

import org.apache.kafka.streams.Topology
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.streams.state.Stores
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.scala.StreamsBuilder
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient

class EventHandler(val registry: SchemaRegistryClient) extends StreamProcessor {
  import org.apache.kafka.streams.scala.Serdes._
  import org.apache.kafka.streams.scala.ImplicitConversions._

  override def appId(): String = Settings.App.ID
  override def schemaRegistryClient: SchemaRegistryClient = registry

  override def topology: Topology = {
    val builder = new StreamsBuilder
    val eventsTopic = Settings.Events.Topic

    val storeBuilder = Stores.keyValueStoreBuilder(
      Stores.persistentKeyValueStore(Settings.Stores.Snapshots),
      String,
      Serdes.serdeFrom(genericAvroSerde.serializer(), genericAvroSerde.deserializer())).withCachingEnabled()

    builder.addStateStore(storeBuilder)

    val events = builder.stream[String, GenericRecord](eventsTopic)

    events.transform(
      EventToSnapshotTransformer.Supplier,
      Settings.Stores.Snapshots)

    builder.build()
  }
}
