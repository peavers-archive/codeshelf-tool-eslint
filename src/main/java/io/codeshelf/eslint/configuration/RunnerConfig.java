package io.codeshelf.eslint.configuration;

import io.codeshelf.eslint.runner.ProcessRunner;
import io.codeshelf.tool.executor.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author Chris Turner (chris@forloop.space) */
@Configuration
@RequiredArgsConstructor
public class RunnerConfig {

  private final ProcessService processService;

  @Bean
  public CommandLineRunner commandLineRunner() {
    return new ProcessRunner(processService);
  }
}
