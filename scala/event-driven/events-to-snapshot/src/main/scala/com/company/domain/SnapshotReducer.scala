package com.company.domain

import org.apache.avro.util.Utf8
import org.apache.avro.generic.{GenericRecord, GenericData}

object SnapshotReducer {
  val empty: GenericRecord = Pipe.Draft

  def handle(snapshot: GenericRecord, message: GenericRecord): GenericRecord = {
    val event: String = message.get(Event.Schema.Event).toString()

    reduce(snapshot, event, message)
  }

  private def reduce(pipe: GenericRecord, event: String, message: GenericRecord): GenericRecord = event match {
    case Deleted.Event => {
      val happenedAt = message.get(Event.Schema.HappenedAt)

      Pipe
        .delete(pipe, happenedAt)
    }

    case Updated.Event => {
      val payload: GenericRecord = message.get(Updated.Schema.Fields).asInstanceOf[GenericRecord]
      val happenedAt = message.get(Event.Schema.HappenedAt)
      val affectedFields: GenericData.Array[Utf8] = message.get(Updated.Schema.AffectedFields).asInstanceOf[GenericData.Array[Utf8]]
      val affectedFieldsList: List[Object] = affectedFields.toArray().toList

      val newPipe = affectedFieldsList.fold(pipe)((ccPipe, f) => {
        val fs: String = f.toString()
        val value = payload.get(fs)

        Pipe.set(ccPipe.asInstanceOf[GenericRecord], fs, value)
      })

      Pipe.update(newPipe.asInstanceOf[GenericRecord], happenedAt)
    }

    case _ => pipe
  }
}
