package com.example.eslint.service;

import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.PutRecordResult;
import com.amazonaws.services.kinesisfirehose.model.Record;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@Service
@RequiredArgsConstructor
public class FirehoseServiceImpl implements FirehoseService {

  private final AmazonKinesisFirehose amazonKinesisFirehose;

  @Override
  public PutRecordResult pushRecord(final String deliveryStream, final byte[] bytes) {

    final PutRecordRequest putRecordRequest = new PutRecordRequest();
    putRecordRequest.setDeliveryStreamName(deliveryStream);

    final Record record = new Record().withData(ByteBuffer.wrap(bytes));
    putRecordRequest.setRecord(record);

    log.info("sending to firehose...");

    final PutRecordResult putRecordResult = amazonKinesisFirehose.putRecord(putRecordRequest);

    log.info("sent to firehose {}", putRecordResult.getRecordId());

    return putRecordResult;
  }
}
