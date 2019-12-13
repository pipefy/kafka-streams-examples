// scalastyle:off null

package com.company.domain

import scala.io.{BufferedSource, Source}

import org.apache.avro.Schema
import org.apache.avro.generic.{GenericData, GenericRecord}

object Pipe {
  def schemaSource(): String = {
    val source: BufferedSource = Source.fromURL(getClass.getResource("/avro/pipe.avsc"))
    val src: String = source.mkString
    source.close()

    src
  }

  val schema: Schema = new Schema.Parser().parse(schemaSource())

  val Draft: GenericRecord = new GenericData.Record(schema)

  def delete(pipe: GenericRecord, deletedAt: Object): GenericRecord = {
    set(pipe, "deletedAt", deletedAt)

    pipe
  }

  def update(pipe: GenericRecord, updatedAt: Object): GenericRecord = {
    set(pipe, "updatedAt", updatedAt)

    pipe
  }

  def set(pipe: GenericRecord, field: String, value: Object): GenericRecord = field match {
    case "updatedAt" | "deletedAt" => {
      value match {
        case null => {
          pipe.put(field, value)
          pipe
        }
        case _ => {
          pipe.put(field, value.toString().toLong)
          pipe
        }
      }
    }
    case "name" | "icon" => {
      value match {
        case null => {
          pipe.put(field, value)
          pipe
        }
        case _ => {
          pipe.put(field, value.toString())
          pipe
        }
      }
    }
    case "authorization" => {
      value match {
        case null => {
          pipe.put(field, value)
          pipe
        }
        case _ => {
          pipe.put(field, value.toString().toInt)
          pipe
        }
      }
    }
    case "public" => {
      value match {
        case null => {
          pipe.put(field, value)
          pipe
        }
        case _ => {
          pipe.put(field, value.toString().toBoolean)
          pipe
        }
      }
    }
  }
}
