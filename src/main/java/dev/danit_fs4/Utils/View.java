package dev.danit_fs4.Utils;

import dev.danit_fs4.Entity.User;
import dev.danit_fs4.services.TemplateService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View {
    private final Configuration templateConfig = TemplateService.config();

    public View(String resources) {
        TemplateService.setResources(resources);
    }

    public View() {
        TemplateService.setResources("/templates");
    }
    public void renderUsers(PrintWriter write, List<User> users, String template) {
        try(write) {
            Map<String, Object> data = new HashMap<>();
            data.put("users", users);
            templateConfig.getTemplate(template).process(data, write);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void render(PrintWriter write, Map<String, Object> data, String template) {
        try(write) {
            templateConfig.getTemplate(template).process(data, write);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
