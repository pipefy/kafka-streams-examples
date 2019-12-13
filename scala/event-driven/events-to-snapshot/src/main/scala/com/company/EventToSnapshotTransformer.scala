package com.company

import com.company.utils.Settings
import com.company.domain.SnapshotReducer

import org.apache.kafka.streams.KeyValue
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.streams.scala.Serdes.String
import org.apache.kafka.streams.state.KeyValueStore
import org.apache.kafka.streams.processor.ProcessorContext

import org.apache.kafka.streams.kstream.{Transformer, TransformerSupplier}

class EventToSnapshotTransformer
  extends Transformer[String, GenericRecord, KeyValue[String, GenericRecord]] {

  private var context: ProcessorContext = _

  private var store: KeyValueStore[String, GenericRecord] = _

  override def init(context: ProcessorContext): Unit = {
    this.context = context
    store = context
      .getStateStore(Settings.Stores.Snapshots)
      .asInstanceOf[KeyValueStore[String, GenericRecord]]
  }

  override def transform(id: String, message: GenericRecord): KeyValue[String, GenericRecord] = {
    val snapshot: GenericRecord = loadSnapshot(id)
    val result: GenericRecord = SnapshotReducer.handle(snapshot, message)

    updateSnapshot(id, result)

    KeyValue.pair(id, result)
  }

  override def close(): Unit = ()

  private def loadSnapshot(id: String): GenericRecord =
    Option(store.get(id)).getOrElse(SnapshotReducer.empty)

  private def updateSnapshot(id: String, snapshot: GenericRecord): Unit = store.put(id, snapshot)
}

object EventToSnapshotTransformer {
  val Supplier: TransformerSupplier[String, GenericRecord, KeyValue[String, GenericRecord]] =
    () => new EventToSnapshotTransformer
}
