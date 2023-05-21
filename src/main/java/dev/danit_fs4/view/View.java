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
import java.util.List;

public class View {

    private final Configuration templateConfig = TemplateService.config();

    public View (String resources) {
        TemplateService.setResources(resources);
    }
    public void renderUsers(PrintWriter write, List<User> users, String template) {
        try(write) {
            templateConfig.getTemplate(template).process(users, write);
        } catch (TemplateNotFoundException | ParseException | MalformedTemplateNameException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
