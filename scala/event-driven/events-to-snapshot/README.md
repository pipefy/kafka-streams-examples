# Events to Snapshot

## Disabling schemas compatibility

```bash
curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{"compatibility": "NONE"}' \
  http://kafka-schema-registry:8081/config/pipe-events-avro2-value
```

## Event's topic Avro schema (input)

`updated event`

```json
{
    "type": "record",
    "name": "PipeUpdated",
    "doc": "Event containing the contents of all pipe fields (including those not updated). Field _affected_fields states which fields were affected.",
    "namespace": "com.company",
    "version": "1",
    "fields": [{
            "name": "_uuid",
            "doc": "An unique Global Pipe identifier among all of Pipefy applications.",
            "type": "string"
        },
        {
            "name": "_happened_at",
            "doc": "To mark when event occurred.",
            "type": {
                "type": "long",
                "logicalType": "timestamp-millis"
            }
        },
        {
            "name": "_user_uuid",
            "doc": "The Global user indentifier that trigger the Pipe event",
            "type": "string"
        },
        {
            "name": "_event",
            "doc": "The name of the event",
            "type": "string"
        },
        {
            "name": "_affected_fields",
            "doc": "The list of fields that were affected/touched in the event.",
            "type": {
                "type": "array",
                "items": "string"
            }
        },
        {
            "name": "_fields",
            "type": {
                "type": "record",
                "name": "_fields_record",
                "fields": [{
                        "name": "name",
                        "type": [
                            "null",
                            "string"
                        ],
                        "default": null
                    },
                    {
                        "name": "title_field_id",
                        "type": [
                            "null",
                            "int"
                        ],
                        "default": null
                    },
                    {
                        "name": "expiration_time",
                        "type": [
                            "null",
                            "int"
                        ],
                        "default": null
                    },
                    {
                        "name": "only_admin_can_remove_cards",
                        "type": [
                            "null",
                            "boolean"
                        ],
                        "default": null
                    },
                    {
                        "name": "cloning",
                        "type": [
                            "null",
                            "boolean"
                        ],
                        "default": null
                    },
                    {
                        "name": "public",
                        "type": [
                            "null",
                            "boolean"
                        ],
                        "default": null
                    },
                    {
                        "name": "suid",
                        "type": [
                            "null",
                            "string"
                        ],
                        "default": null
                    },
                    {
                        "name": "only_assignees_can_edit_cards",
                        "type": [
                            "null",
                            "boolean"
                        ],
                        "default": null
                    },
                    {
                        "name": "anyone_can_create_card",
                        "type": [
                            "null",
                            "boolean"
                        ],
                        "default": null
                    },
                    {
                        "name": "subtitles",
                        "type": [
                            "null",
                            {
                                "type": "array",
                                "items": "string"
                            }
                        ],
                        "default": null
                    },
                    {
                        "name": "email_inbox_name",
                        "type": [
                            "null",
                            "string"
                        ],
                        "default": null
                    },
                    {
                        "name": "authorization",
                        "type": [
                            "null",
                            "int"
                        ],
                        "default": null
                    },
                    {
                        "name": "count_only_week_days",
                        "type": [
                            "null",
                            "boolean"
                        ],
                        "default": null
                    },
                    {
                        "name": "create_card_label",
                        "type": [
                            "null",
                            "string"
                        ],
                        "default": null
                    },
                    {
                        "name": "description",
                        "type": [
                            "null",
                            "string"
                        ],
                        "default": null
                    },
                    {
                        "name": "slug",
                        "type": [
                            "null",
                            "string"
                        ],
                        "default": null
                    },
                    {
                        "name": "icon",
                        "type": [
                            "null",
                            "string"
                        ],
                        "default": null
                    }
                ]
            }
        }
    ]
}
```

Registering

```bash
curl -vs --stderr - -XPOST -i -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema":"{\"type\":\"record\",\"name\":\"PipeUpdated\",\"doc\":\"Event containing the contents of all pipe fields (including those not updated). Field _affected_fields states which fields were affected.\",\"namespace\":\"com.company\",\"version\":\"1\",\"fields\":[{\"name\":\"_uuid\",\"doc\":\"An unique Global Pipe identifier among all of Pipefy applications.\",\"type\":\"string\"},{\"name\":\"_happened_at\",\"doc\":\"To mark when event occurred.\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"_user_uuid\",\"doc\":\"The Global user indentifier that trigger the Pipe event\",\"type\":\"string\"},{\"name\":\"_event\",\"doc\":\"The name of the event\",\"type\":\"string\"},{\"name\":\"_affected_fields\",\"doc\":\"The list of fields that were affected/touched in the event.\",\"type\":{\"type\":\"array\",\"items\":\"string\"}},{\"name\":\"_fields\",\"type\":{\"type\":\"record\",\"name\":\"_fields_record\",\"fields\":[{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"title_field_id\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"expiration_time\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"only_admin_can_remove_cards\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"cloning\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"public\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"suid\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"only_assignees_can_edit_cards\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"anyone_can_create_card\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"subtitles\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}],\"default\":null},{\"name\":\"email_inbox_name\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"authorization\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"count_only_week_days\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"create_card_label\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"description\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"slug\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"icon\",\"type\":[\"null\",\"string\"],\"default\":null}]}}]}"}' http://kafka-schema-registry:8081/subjects/pipe-events-avro2/versions
```

`deleted event`

```json
{
    "type": "record",
    "name": "PipeDeleted",
    "namespace": "com.company",
    "doc": "Event contains notification that the pipe was deleted.",
    "version": "1",
    "fields": [{
            "name": "_uuid",
            "doc": "An unique Global Pipe identifier among all of Pipefy applications.",
            "type": "string"
        },
        {
            "name": "_happened_at",
            "doc": "To mark when event occurred.",
            "type": {
                "type": "long",
                "logicalType": "timestamp-millis"
            }
        }, {
            "name": "_user_uuid",
            "doc": "The Global user indentifier that trigger the Pipe event",
            "type": "string"
        }, {
            "name": "_event",
            "doc": "The name of the event",
            "type": "string"
        }
    ]
}
```

Registering

```bash
curl -vs --stderr - -XPOST -i -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema":"{\"type\":\"record\",\"name\":\"PipeDeleted\",\"namespace\":\"com.company\",\"doc\":\"Event contains notification that the pipe was deleted.\",\"version\":\"1\",\"fields\":[{\"name\":\"_uuid\",\"doc\":\"An unique Global Pipe identifier among all of Pipefy applications.\",\"type\":\"string\"},{\"name\":\"_happened_at\",\"doc\":\"To mark when event occurred.\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"_user_uuid\",\"doc\":\"The Global user indentifier that trigger the Pipe event\",\"type\":\"string\"},{\"name\":\"_event\",\"doc\":\"The name of the event\",\"type\":\"string\"}]}"}' http://kafka-schema-registry:8081/subjects/pipe-events-avro2/versions
```

## Entity Snpashot topic Avro schema (output)

```json
{
	"type": "record",
	"name": "pipe",
	"fields": [{
			"name": "name",
			"type": ["null", "string"],
			"default": null
		},
		{
			"name": "icon",
			"type": ["null", "string"],
			"default": null
		},
		{
			"name": "authorization",
			"type": ["null", "int"],
			"default": null
		},
		{
			"name": "public",
			"type": ["null", "boolean"],
			"default": null
		},
		{
			"name": "deletedAt",
			"type": ["null", {
				"type": "long",
				"logicalType": "timestamp-millis"
			}],
			"default": null
		}, {
			"name": "updatedAt",
			"type": ["null", {
				"type": "long",
				"logicalType": "timestamp-millis"
			}],
			"default": null
		}
	]
}
```

Registering

```bash
curl -vs --stderr - -XPOST -i -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema":"{\"type\":\"record\",\"name\":\"pipe\",\"fields\":[{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"icon\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"authorization\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"public\",\"type\":[\"null\",\"boolean\"],\"default\":null},{\"name\":\"deletedAt\",\"type\":[\"null\",{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}],\"default\":null},{\"name\":\"updatedAt\",\"type\":[\"null\",{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}],\"default\":null}]}"}' http://kafka-schema-registry:8081/subjects/pipe-events-avro2-handler-store.snapshots-changelog-value/versions
```

## Running

```bash
docker-compose up
```

### Confluent tools

```bash
docker exec -it events-to-snapshot_tools_1 bash
```

inside of it

```bash
cd /opt/confluent-5.3.1/bin
```

#### Producing events

```bash
./kafka-topics --create --bootstrap-server broker:9092 --replication-factor 1 --partitions 1 --topic pipe-events-avro2
```

##### deleted event

```bash
./kafka-avro-console-producer --broker-list broker:29092 --topic pipe-events-avro2 \
  --property parse.key=true \
  --property key.separator=, \
  --property key.schema='{"type":"string"}' \
  --property schema.registry.url=http://kafka-schema-registry:8081 \
  --property value.schema='{ "type": "record", "name": "PipeDeleted", "doc": "Event contains notification that the pipe was deleted.", "namespace": "com.company", "version": "1", "fields": [{ "name": "_uuid", "doc": "An unique Global Pipe identifier among all of Pipefy applications.", "type": "string" }, { "name": "_happened_at", "doc": "To mark when event occurred.", "type": { "type": "long", "logicalType": "timestamp-millis" } }, { "name": "_user_uuid", "doc": "The Global user indentifier that trigger the Pipe event", "type": "string" }, { "name": "_event", "doc": "The name of the event", "type": "string" } ] }'
```

`message`

```json
{
    "_happened_at": 343234234,
    "_event": "deleted",
    "_uuid": "dbd34c39-a2ee-48c1-bdac-a75f29612526",
    "_user_uuid": "a107e8de-d6b4-46c2-9a71-43106f88633b"
}
```

```bash
"dbd34c39-a2ee-48c1-bdac-a75f29612526",{ "_happened_at": 343234234, "_event": "deleted", "_uuid": "dbd34c39-a2ee-48c1-bdac-a75f29612526", "_user_uuid": "a107e8de-d6b4-46c2-9a71-43106f88633b" }
```

##### updated event

```bash
./kafka-avro-console-producer --broker-list broker:29092 --topic pipe-events-avro2 \
  --property parse.key=true \
  --property key.separator=, \
  --property key.schema='{"type":"string"}' \
  --property schema.registry.url=http://kafka-schema-registry:8081 \
  --property value.schema='{ "type": "record", "name": "PipeUpdated", "doc": "Event containing the contents of all pipe fields (including those not updated). Field _affected_fields states which fields were affected.", "namespace": "com.company", "version": "1", "fields": [{ "name": "_uuid", "doc": "An unique Global Pipe identifier among all of Pipefy applications.", "type": "string" }, { "name": "_happened_at", "doc": "To mark when event occurred.", "type": { "type": "long", "logicalType": "timestamp-millis" } }, { "name": "_user_uuid", "doc": "The Global user indentifier that trigger the Pipe event", "type": "string" }, { "name": "_event", "doc": "The name of the event", "type": "string" }, { "name": "_affected_fields", "doc": "The list of fields that were affected/touched in the event.", "type": { "type": "array", "items": "string" } }, { "name": "_fields", "type": { "type": "record", "name": "_fields_record", "fields": [{ "name": "name", "type": [ "null", "string" ], "default": null }, { "name": "title_field_id", "type": [ "null", "int" ], "default": null }, { "name": "expiration_time", "type": [ "null", "int" ], "default": null }, { "name": "only_admin_can_remove_cards", "type": [ "null", "boolean" ], "default": null }, { "name": "cloning", "type": [ "null", "boolean" ], "default": null }, { "name": "public", "type": [ "null", "boolean" ], "default": null }, { "name": "suid", "type": [ "null", "string" ], "default": null }, { "name": "only_assignees_can_edit_cards", "type": [ "null", "boolean" ], "default": null }, { "name": "anyone_can_create_card", "type": [ "null", "boolean" ], "default": null }, { "name": "subtitles", "type": [ "null", { "type": "array", "items": "string" } ], "default": null }, { "name": "email_inbox_name", "type": [ "null", "string" ], "default": null }, { "name": "authorization", "type": [ "null", "int" ], "default": null }, { "name": "count_only_week_days", "type": [ "null", "boolean" ], "default": null }, { "name": "create_card_label", "type": [ "null", "string" ], "default": null }, { "name": "description", "type": [ "null", "string" ], "default": null }, { "name": "slug", "type": [ "null", "string" ], "default": null }, { "name": "icon", "type": [ "null", "string" ], "default": null } ] } } ] }'
```

`message`

```json
{
	"_uuid": "dbd34c39-a2ee-48c1-bdac-a75f29612526",
	"_happened_at": 23435343,
	"_user_uuid": "a107e8de-d6b4-46c2-9a71-43106f88633b",
	"_event": "updated",
	"_affected_fields": ["name", "icon", "public", "authorization"],
	"_fields": {
		"name": {"string":"Jon"},
		"title_field_id": null,
		"expiration_time": null,
		"only_admin_can_remove_cards": null,
		"cloning": null,
		"public": {"boolean": false},
		"suid": null,
		"only_assignees_can_edit_cards": null,
		"anyone_can_create_card": null,
		"subtitles": null,
		"email_inbox_name": null,
		"authorization": {"int": 1},
		"count_only_week_days": null,
		"create_card_label": null,
		"description": null,
		"slug": null,
		"icon": {"string": "rocket"}
	}
}
```

```bash
"dbd34c39-a2ee-48c1-bdac-a75f29612526",{ "_uuid": "dbd34c39-a2ee-48c1-bdac-a75f29612526", "_happened_at": 23435343, "_user_uuid": "a107e8de-d6b4-46c2-9a71-43106f88633b", "_event": "updated", "_affected_fields": ["name", "icon", "public", "authorization"], "_fields": { "name": {"string":"John"}, "title_field_id": null, "expiration_time": null, "only_admin_can_remove_cards": null, "cloning": null, "public": {"boolean": false}, "suid": null, "only_assignees_can_edit_cards": null, "anyone_can_create_card": null, "subtitles": null, "email_inbox_name": null, "authorization": {"int": 1}, "count_only_week_days": null, "create_card_label": null, "description": null, "slug": null, "icon": {"string": "rocket"} } }
```

```bash
./kafka-avro-console-consumer --topic pipe-events-avro2 \
  --bootstrap-server broker:29092 \
  --property schema.registry.url=http://kafka-schema-registry:8081 \
  --property print.key=true \
  --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer \
  --from-beginning
```

### Stream App

```bash
docker exec -it events-to-snapshot_app_1 bash
```

inside of it

```bash
cd app
```

```bash
sbt
```

inside of SBT console

```bash
~run
```

#### Consuming the entity snapshot

Find the entity snapshot topic (which should be composed by `${appId}-${storename}-changelog`)
```bash
./kafka-topics --bootstrap-server broker:29092 --list | grep changelog
```

```bash
./kafka-avro-console-consumer --topic pipe-events-avro2-handler-store.snapshots-changelog \
  --bootstrap-server broker:29092 \
  --property schema.registry.url=http://kafka-schema-registry:8081 \
  --property print.key=true \
  --key-deserializer=org.apache.kafka.common.serialization.StringDeserializer \
  --from-beginning
```

### Clean Sbt dependencies

```bash
make clean
```

## Tests

Run docker

```bash
docker-compose up -d test
```

Go into on it

```bash
docker exec -it kafka-streams_test_1 bash
````

`on container`

```bash
cd app
```

```bash
sbt test
```

## Code style checker

```bash
sbt scalastyle
```
