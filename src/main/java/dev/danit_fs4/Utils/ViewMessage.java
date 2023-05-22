package dev.danit_fs4.Utils;

import dev.danit_fs4.Entity.Chat;
import dev.danit_fs4.Entity.User;
import dev.danit_fs4.services.TemplateService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewMessage {
    private final Configuration templateConfig = TemplateService.config();

    public ViewMessage(String resources) {
        TemplateService.setResources(resources);
    }

    public void renderMessages(PrintWriter write, Chat chat, String template) {

        try (write) {
            Map<String, Object> data = new HashMap<>();
            data.put("messages", chat.getHistory());
            data.put("hostName", chat.getHostName());
            data.put("guestName", chat.getGuestName());
            data.put("hostId", chat.getHostId());
            data.put("guestId", chat.getGuestId());
            templateConfig.getTemplate(template).process(data, write);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
