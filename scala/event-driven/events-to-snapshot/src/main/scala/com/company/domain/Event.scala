package com.company.domain

import scala.io.{BufferedSource, Source}

import org.apache.avro.Schema

trait Event {
  val Event: String
}

object Event {
  object Schema {
    val Event = "_event"
    val HappenedAt = "_happened_at"
  }
}

object Deleted extends Event {
  override val Event = "deleted"

  def schemaSource(): String = {
    val source: BufferedSource = Source.fromURL(getClass.getResource("/avro/deleted.avsc"))
    val src: String = source.mkString
    source.close()

    src
  }

  val schema: Schema = new Schema.Parser().parse(schemaSource())
}

object Updated extends Event {
  override val Event = "updated"
  object Schema {
    val Fields = "_fields"
    val AffectedFields = "_affected_fields"
  }
}
