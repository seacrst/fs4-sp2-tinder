package dev.danit_fs4.Servlet;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ResourcesOps {
        public static String dirUnsafe(String dir){
           try {
               String resourse =  ResourcesOps.class
                       .getClassLoader()
                       .getResource(dir)
                       .toURI()
                       .getPath();
               if (resourse.startsWith("/")) resourse = resourse.substring(1);
               return resourse;
           } catch (URISyntaxException e) {
               throw new RuntimeException(String.format("Requested path `%s`not found", dir), e);
           }
        }

        public static void writeInto(HttpServletResponse resp, Map<String, Object> data, String template) throws IOException {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
            cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("templates")));

            try (PrintWriter w = resp.getWriter()) {
                cfg
                        .getTemplate(template)
                        .process(data, w);
            } catch (TemplateException | IOException e) {
                throw new RuntimeException(e);
            }
        }
}
