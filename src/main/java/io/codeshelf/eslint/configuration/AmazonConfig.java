package io.codeshelf.eslint.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author Chris Turner (chris@forloop.space) */
@Configuration
public class AmazonConfig {

  @Value("${amazon.accessKey}")
  public String accessKey;

  @Value("${amazon.secretKey}")
  public String secretKey;

  @Value("${amazon.region}")
  public String region;

  @Bean
  public AmazonKinesisFirehose amazonKinesisFirehose() {

    return AmazonKinesisFirehoseClientBuilder.standard()
        .withCredentials(
            new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
        .withRegion(region)
        .build();
  }
}
