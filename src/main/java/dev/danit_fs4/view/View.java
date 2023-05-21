package dev.danit_fs4.view;

import dev.danit_fs4.Entity.User;
import dev.danit_fs4.Servlet.ResourcesOps;
import dev.danit_fs4.services.TemplateService;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View {
    private final Configuration templateConfig = TemplateService.config();

    public View (String resources) {
        TemplateService.setResources(resources);
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
}
