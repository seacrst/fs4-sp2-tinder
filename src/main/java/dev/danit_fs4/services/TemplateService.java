package dev.danit_fs4.services;

import freemarker.template.Configuration;

import java.nio.charset.StandardCharsets;

public class TemplateService {
    private static Configuration cfg = null;

    public static Configuration config() {
        if (cfg != null) return cfg;
        cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));

        return cfg;
    }

    public static void setResources(String resources) {
        cfg.setClassForTemplateLoading(TemplateService.class, resources);
    }
}
