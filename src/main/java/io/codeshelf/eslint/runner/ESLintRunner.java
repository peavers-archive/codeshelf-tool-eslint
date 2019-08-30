package io.codeshelf.eslint.runner;

import io.codeshelf.eslint.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;

/**
 * @author Chris Turner (chris@forloop.space)
 */
@Slf4j
@RequiredArgsConstructor
public class ESLintRunner implements CommandLineRunner {

    private final ProcessService processService;

    @Override
    public void run(final String... strings) {
        try {
            processService.execute();
        } catch (IOException | InterruptedException e) {
            log.error("something went wrong... {}", e.getMessage());
        }
    }
}
