package io.codeshelf.eslint.runner;

import io.codeshelf.tool.executor.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.util.ArrayList;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@RequiredArgsConstructor
public class ProcessRunner implements CommandLineRunner {

  private final ProcessService processService;

  @Value("${codeshelf.workingDir}")
  private String workingDir;

  @Value("${codeshelf.deliveryStream}")
  private String deliveryStream;

  @Override
  public void run(final String... strings) {

    final ArrayList<String> command = new ArrayList<>();
    command.add("eslint");
    command.add(workingDir + "/**");
    command.add("--format");
    command.add("json");

    try {
      processService.execute(command, deliveryStream);
    } catch (final IOException | InterruptedException e) {
      log.error("error {}", e.getMessage());
    }
  }
}
