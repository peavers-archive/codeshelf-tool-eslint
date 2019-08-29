package com.example.eslint.service;

import java.io.IOException;

/**
 * @author Chris Turner (chris@forloop.space)
 */
public interface ProcessService {

    void execute() throws IOException, InterruptedException;
}
