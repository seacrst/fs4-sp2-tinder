package dev.danit_fs4.config;

public interface Config {
    String url();
    String username();
    String password();
    int port(int defaultPort);
}
