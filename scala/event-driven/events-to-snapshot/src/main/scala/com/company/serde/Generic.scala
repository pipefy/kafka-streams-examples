package com.company.serde

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.Serde
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient

object Generic {
  def value(schemaRegistryClient: SchemaRegistryClient): Serde[GenericRecord] = new GenericAvroSerde(schemaRegistryClient)
}

