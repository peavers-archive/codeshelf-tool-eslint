package io.codeshelf.eslint.runner;

import io.codeshelf.eslint.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

/** @author Chris Turner (chris@forloop.space) */
@RequiredArgsConstructor
public class ESLintRunner implements CommandLineRunner {

  private final ProcessService processService;

  @Override
  public void run(final String... strings) throws Exception {
    processService.execute();
  }
}
