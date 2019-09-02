package io.codeshelf.eslint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

  private final FirehoseService firehoseService;

  @Value("${eslint.workingDir}")
  private String workingDir;

  @Override
  public void execute() throws IOException {

    final ProcessBuilder processBuilder = new ProcessBuilder(eslintCommand());
    final Process process = processBuilder.start();
    final String output = consoleOutput(process.getInputStream());
    final String error = consoleOutput(process.getErrorStream());

    if (StringUtils.isNotBlank(error)) {
      log.error(error);
    } else {
      log.info("eslint result {}", output);
    }

    process.getInputStream().close();
    process.getErrorStream().close();

    firehoseService.pushRecord("code-linter", output.getBytes());
  }

  private List<String> eslintCommand() {
    final ArrayList<String> commands = new ArrayList<>();
    commands.add("eslint");
    commands.add(workingDir + "/**");
    commands.add("--format");
    commands.add("json");

    return commands;
  }

  private String consoleOutput(final InputStream stream) {
    final BufferedReader output = new BufferedReader(new InputStreamReader(stream));

    return output
        .lines()
        .map(line -> line + System.getProperty("line.separator"))
        .collect(Collectors.joining());
  }
}
