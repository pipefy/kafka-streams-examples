package com.company

import java.util.concurrent.CountDownLatch

import com.company.serde.Generic
import com.company.utils.Settings

import org.log4s.getLogger
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.KafkaStreams.State
import org.apache.kafka.streams.{KafkaStreams, Topology}
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient

trait StreamProcessor {

  def appId(): String
  def topology: Topology
  def schemaRegistryClient: SchemaRegistryClient

  private val logger = getLogger
  private val latch = new CountDownLatch(1)
  private val props = Settings.createBasicStreamProperties(appId())

  implicit val genericAvroSerde: Serde[GenericRecord] = Generic.value(schemaRegistryClient)

  def start(): Unit = {
    logger.info("Starting...")

    val streams: KafkaStreams = new KafkaStreams(topology, props)

    streams.setStateListener((newState: State, oldState: State) => {
      logger.info(s"$oldState -> $newState")
    })

    streams.setUncaughtExceptionHandler((_: Thread, e: Throwable) => {
      logger.error(e)(s"Exception was thrown in stream processor ${appId()}")
      latch.countDown()
    })

    streams.start()

    sys.ShutdownHookThread{
      logger.info("Shutting down...")
      streams.close()
    }

    latch.await()
  }
}
