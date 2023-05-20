package dev.danit_fs4.config;

import freemarker.template.Configuration;

import java.nio.charset.StandardCharsets;

public interface Config {
    String url();
    String username();
    String password();
    int port(int defaultPort);
}
