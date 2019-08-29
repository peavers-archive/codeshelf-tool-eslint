package com.example.eslint.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/** @author Chris Turner (chris@forloop.space) */
@Component
@Configuration
public class AmazonConfig {

  @Value("${amazon.accessKey}")
  public String accessKey;

  @Value("${amazon.secretKey}")
  public String secretKey;

  @Bean
  public AmazonKinesisFirehose amazonKinesisFirehose() {

    return AmazonKinesisFirehoseClientBuilder.standard()
        .withCredentials(
            new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
        .withRegion(Regions.US_EAST_1)
        .build();
  }
}
