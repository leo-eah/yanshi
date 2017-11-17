package com.service;

import com.bo.Page;
import com.dao.proDao;
import com.vo.Project;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileService {

    public static void main(String[] args)
    {
        FileService d = new FileService();
        d.scan("D:\\ideaWorkspace\\report2");
        d.println();
    }

    public StringBuilder getBuf(String path) {
        FileService d = new FileService();
        d.scan("D:\\lookPro\\pro\\"+path);
        return buf;
    }

    public void scan(String path) {
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

         private void scan(File f) {
             if (f.isDirectory())
             {
                 buf.append(space(kai)).append("{\n").append(space(++kai)).append("\"text\" : \"").append(f.getName()).append("\",\n").append("\"icon\":\"glyphicon glyphicon-file\",").append(space(kai)).append("\"nodes\" : [\n");
                 kai++;
                 for (File _f : f.listFiles()) scan(_f);
                 buf.delete(buf.length() - 2, buf.length());
                 buf.append("\n").append(space(--kai)).append("]\n").append(space(--kai)).append("},\n");
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
