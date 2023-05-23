package dev.danit_fs4.utils;

import dev.danit_fs4.services.TemplateService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class View {
    private final Configuration templateConfig = TemplateService.config();

    public View(String resources) {
        TemplateService.setResources(resources);
    }

    public View() {
        TemplateService.setResources("/templates");
    }

    public void render(PrintWriter write, Map<String, Object> data, String template) {
        try(write) {
            templateConfig.getTemplate(template).process(data, write);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
