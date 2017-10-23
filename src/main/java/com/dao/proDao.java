package com.dao;

import com.vo.Project;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class proDao {
    public static List<Project> findAllPro(String proName,String author) {
        ArrayList<Project> proList = new ArrayList<Project>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("D:/lookPro/log.txt")));
            String s = "";
            String[] a = null;
            s = br.readLine();
            String flag = "";
            while (s != null) {
                a = s.split(",");
                if (!StringUtils.isEmpty(proName)) {
                    flag = "1";
                } else {
                    flag = "0";
                }
                if (!StringUtils.isEmpty(author)) {
                    flag += "1";
                } else {
                    flag += "0";
                }
                if (flag.equals("10")) {
                    if (a[1].trim().contains(proName)) {
                        Project pro = new Project();
                        pro.setId(a[0].trim());
                        pro.setProName(a[1].trim());
                        pro.setAuthor(a[2]);
                        pro.setProVersion(a[3]);
                        pro.setCreateTime(a[4]);
                        pro.setFileName(a[5]);
                        proList.add(pro);
                    }
                }
                if (flag.equals("00")) {
                    Project pro = new Project();
                    pro.setId(a[0].trim());
                    pro.setProName(a[1].trim());
                    pro.setAuthor(a[2]);
                    pro.setProVersion(a[3]);
                    pro.setCreateTime(a[4]);
                    pro.setFileName(a[5]);
                    proList.add(pro);
                }
                if (flag.equals("11")) {
                    if (a[1].trim().contains(proName) && a[2].trim().contains(author)) {
                        Project pro = new Project();
                        pro.setId(a[0].trim());
                        pro.setProName(a[1].trim());
                        pro.setAuthor(a[2]);
                        pro.setProVersion(a[3]);
                        pro.setCreateTime(a[4]);
                        pro.setFileName(a[5]);
                        proList.add(pro);
                    }
                }
                if (flag.equals("01")) {
                    if (a[2].trim().contains(author)) {
                        Project pro = new Project();
                        pro.setId(a[0].trim());
                        pro.setProName(a[1].trim());
                        pro.setAuthor(a[2]);
                        pro.setProVersion(a[3]);
                        pro.setCreateTime(a[4]);
                        pro.setFileName(a[5]);
                        proList.add(pro);
                    }
                }

                s = br.readLine();
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proList;
    }
        public static ArrayList<Project> findAll(int startIndex, int pageSize,String proName,String author) {
            ArrayList<Project> proList =new ArrayList<Project>();
            try{
                BufferedReader br =new BufferedReader(new FileReader(new File("D:/lookPro/log.txt")));
                String s="";
                String[] a =null;
                s=br.readLine();
                String flag="";
                int currrent=0;
                while (s!=null){
                    currrent+=1;
                    a= s.split(",");
                    if(!StringUtils.isEmpty(proName)){
                        flag="1";
                    }else{
                        flag="0";
                    }
                    if(!StringUtils.isEmpty(author)){
                        flag+="1";
                    }else{
                        flag+="0";
                    }
                    if (flag.equals("10")){
                        if(a[1].trim().contains(proName)){
                            Project pro = new Project();
                            pro.setId(a[0].trim());
                            pro.setProName(a[1].trim());
                            pro.setAuthor(a[2]);
                            pro.setProVersion(a[3]);
                            pro.setCreateTime(a[4]);
                            pro.setFileName(a[5]);
                            if(currrent>=startIndex){
                                proList.add(pro);
                            }
                           if(currrent>startIndex+pageSize)
                               break;
                        }
                    }
                    if(flag.equals("00")){
                        Project pro = new Project();
                        pro.setId(a[0].trim());
                        pro.setProName(a[1].trim());
                        pro.setAuthor(a[2]);
                        pro.setProVersion(a[3]);
                        pro.setCreateTime(a[4]);
                        pro.setFileName(a[5]);
                        if(currrent>=startIndex){
                            proList.add(pro);
                        }
                        if(currrent>startIndex+pageSize)
                            break;;
                    }
                    if (flag.equals("11")){
                        if(a[1].trim().contains(proName)&&a[2].trim().contains(author)){
                            Project pro = new Project();
                            pro.setId(a[0].trim());
                            pro.setProName(a[1].trim());
                            pro.setAuthor(a[2]);
                            pro.setProVersion(a[3]);
                            pro.setCreateTime(a[4]);
                            pro.setFileName(a[5]);
                            if(currrent>=startIndex){
                                proList.add(pro);
                            }
                            if(currrent>startIndex+pageSize)
                                break;
                        }
                    }
                    if(flag.equals("01")){
                        if(a[2].trim().contains(author)){
                            Project pro = new Project();
                            pro.setId(a[0].trim());
                            pro.setProName(a[1].trim());
                            pro.setAuthor(a[2]);
                            pro.setProVersion(a[3]);
                            pro.setCreateTime(a[4]);
                            pro.setFileName(a[5]);
                            if(currrent>=startIndex){
                                proList.add(pro);
                            }
                            if(currrent>startIndex+pageSize)
                                break;
                        }
                    }

                    s=br.readLine();
                }
                br.close();
    } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  proList;
        }
}
