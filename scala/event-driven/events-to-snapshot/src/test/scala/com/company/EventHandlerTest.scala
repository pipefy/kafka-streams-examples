package com.company

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter

import com.company.utils.Settings
import com.company.domain.{Pipe, Deleted}

import org.apache.avro.Schema
import org.apache.kafka.streams.TopologyTestDriver
import org.apache.kafka.streams.state.KeyValueStore
import org.apache.kafka.streams.test.ConsumerRecordFactory
import org.apache.avro.generic.{GenericData, GenericRecord}
import org.apache.kafka.common.serialization.StringSerializer

class EventHandlerTest extends FunSpec with KafkaTestSetup with BeforeAndAfter {
  private val eventsTopic: String = Settings.Events.Topic
  private val storesSnapshots: String = Settings.Stores.Snapshots
  private val eventHandler: EventHandler = new EventHandler(schemaRegistry)
  private val deletedEventSchema: Schema = Deleted.schema
  private val snapshotSchema: Schema = Pipe.schema

  describe("Deleted Event") {
    before {
      schemaRegistry.register(s"${eventsTopic}-value", deletedEventSchema)
      schemaRegistry.register(s"testing-${storesSnapshots}-changelog-value", snapshotSchema)
    }

    it("should update the snapshot from the deleted event") {
      val driver = new TopologyTestDriver(eventHandler.topology, config)
      val recordFactory: ConsumerRecordFactory[String, GenericRecord] = new ConsumerRecordFactory(
        new StringSerializer(),
        eventHandler.genericAvroSerde.serializer())

      val key: String = "dbd34c39-a2ee-48c1-bdac-a75f29612526" // TODO: Change it to use Faker
      val happenedAt: Long = 343234234.toLong

      val record: GenericRecord = {
        val r = new GenericData.Record(deletedEventSchema)
        r.put("_happened_at", happenedAt)
        r.put("_event", "deleted")
        r.put("_uuid", key)
        r.put("_user_uuid", "a107e8de-d6b4-46c2-9a71-43106f88633b")
        r
      }

      driver.pipeInput(recordFactory.create(eventsTopic, key, record))

      val store: KeyValueStore[String, GenericData] = driver.getKeyValueStore(storesSnapshots)
      val expectedSnapshot: GenericRecord = {
        val r = new GenericData.Record(snapshotSchema)
        r.put("name", null)
        r.put("icon", null)
        r.put("authorization", null)
        r.put("public", null)
        r.put("deletedAt", happenedAt)
        r.put("updatedAt", null)
        r
      }

      assert(store.get(key) === expectedSnapshot)

      driver.close()
    }
  }
}
