package dev.danit_fs4.Servlet;

import java.net.URISyntaxException;

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
}
