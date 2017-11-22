package com.service;

import com.bo.Page;
import com.dao.proDao;
import com.util.FileType;
import com.vo.Project;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileService {
   FileType fileType =new FileType();
    public static void main(String[] args) throws IOException {
        FileService d = new FileService();
        d.scan("D:\\ideaWorkspace\\report2");
        d.println();
    }

    public StringBuilder getBuf(String path) throws IOException {
        FileService d = new FileService();
        d.scan("D:\\lookPro\\pro\\"+path);
        return buf;
    }

    public void scan(String path) throws IOException {
             File f = new File(path);
             if (f.isDirectory())
             {
                 scan(new File(path));
                 buf.delete(buf.length() - 2, buf.length());
             }
             else System.out.format("{\"text\" : \"%s\"}", f.getName());
         }

    static StringBuilder buf;
    int kai = 0;
    public FileService() {
        buf = new StringBuilder();
    }
         public void println() {
             System.out.println(buf);
         }

         private void scan(File f) throws IOException {
             if (f.isDirectory())
             {
                 buf.append(space(kai)).append("{\n").append(space(++kai)).append("\"text\" : \"").append(f.getName()).append("\",\n").append("\"icon\":\"glyphicon glyphicon-folder-open\",").append(space(kai)).append("\"nodes\" : [\n");
                 kai++;
                 for (File _f : f.listFiles()) scan(_f);
                 buf.delete(buf.length() - 2, buf.length());
                 buf.append("\n").append(space(--kai)).append("]\n").append(space(--kai)).append("},\n");
             }
             else if(fileType.fileType(f.getName()).equals("文档")){
                 buf.append(space(kai)).append("{\n").append(space(++kai)).append("\"text\" : \"").append(f.getName()).append("\",\n").append("\"icon\":\"glyphicon glyphicon-file\"").append(space(--kai)).append("},\n");
             }
             else if(fileType.fileType(f.getName()).equals("音乐")){
                 buf.append(space(kai)).append("{\n").append(space(++kai)).append("\"text\" : \"").append(f.getName()).append("\",\n").append("\"icon\":\"glyphicon glyphicon-headphones\"").append(space(--kai)).append("},\n");
             }
             else if(fileType.fileType(f.getName()).equals("图片")){
                 buf.append(space(kai)).append("{\n").append(space(++kai)).append("\"text\" : \"").append(f.getName()).append("\",\n").append("\"icon\":\"glyphicon glyphicon-picture\"").append(space(--kai)).append("},\n");
             }
             else if(fileType.fileType(f.getName()).equals("网页")){
                 buf.append(space(kai)).append("{\n").append(space(++kai)).append("\"text\" : \"").append(f.getName()).append("\",\n").append("\"icon\":\"glyphicon glyphicon-flag\"");
                 String a = f.getAbsolutePath().substring(14).replaceAll("\\\\","/");

                 buf.append(",\"href\":\"../pro"+a+"\"").append(space(--kai)).append("},\n");
                 String b = f.getCanonicalPath();
                 String c = f.getPath();
             }
             else buf.append(space(kai)).append("{\n").append(space(++kai)).append("\"text\" : \"").append(f.getName()).append("\"\n").append(space(--kai)).append("},\n");

         }

         public String space(int kai) {
             if (kai <= 0)
             {
                 return "";
             }
             char[] cs = new char[kai<<1];
             Arrays.fill(cs, ' ');
             return new String(cs, 0, cs.length);
         }
     }
