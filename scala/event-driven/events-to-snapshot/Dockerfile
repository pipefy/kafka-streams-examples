FROM mozilla/sbt as builder

RUN mkdir /app
COPY . /app
WORKDIR /app

ENV PFY_STREAMS_PIPE_ES_APP_ENV=prod
RUN sbt assembly

FROM java:8

WORKDIR /app/pipe_events_to_snapshot_stream

COPY --from=builder /app/target/scala-2.12/com.company-assembly-1.0.jar /app/pipe_events_to_snapshot_stream
