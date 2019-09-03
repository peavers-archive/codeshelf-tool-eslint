package io.codeshelf.eslint.configuration;

import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chris Turner (chris@forloop.space)
 */
@Configuration
public class AmazonConfig {

    @Bean
    public AmazonKinesisFirehose amazonKinesisFirehose() {

        return AmazonKinesisFirehoseClientBuilder.defaultClient();
    }
}
