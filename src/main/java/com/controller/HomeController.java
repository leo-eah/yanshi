package com.controller;

import com.bo.Page;
import com.service.HomeService;
import com.util.TextSearchUnit;
import com.util.Unit;
import com.vo.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 *
 */
// 注解标注此类为springmvc的controller，url映射为"/home"
@Controller
@RequestMapping("/lookPro")
public class HomeController {
    //添加一个日志器
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    //映射一个action

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse  response) throws FileNotFoundException {
        String proName ="";
        String author ="";
        int PageNum = Integer.valueOf(1);
        int PageSize = 5;
        HomeService  hs = new HomeService();
        Page pb =hs.findAllProWithPage(PageNum,PageSize,proName,author);
        request.setAttribute("page",pb);
        return "home";
    }
    public static void find(String pathname,int depth) throws IOException {
        int filecount =0;
        File dirFile =new File(pathname);
        if(! dirFile.exists()){
            System.out.println("do not exist");
            return;
        }
        if(!dirFile.isDirectory()){
            if(dirFile.isFile()){
                System.out.println(dirFile.getCanonicalFile());
            }
            return;
        }
        for (int j=0;j<depth;j++){
            System.out.println(" ");
        }

    }
    @RequestMapping("/upload")
    public  String Upload(HttpServletRequest request, HttpServletResponse response , Project pro) throws Exception {
       CommonsMultipartFile file= pro.getPro();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
       pro.setCreateTime(format.format(new Date()));
       pro.setProVersion("1");
       int index =file.getOriginalFilename().lastIndexOf(".");
       String str =file.getOriginalFilename().substring(index);
       String fileName=file.getOriginalFilename().substring(0,index);
        UUID uuid =UUID.randomUUID();
       if(!file.isEmpty()){
           try {
               FileOutputStream os = new FileOutputStream("D:/lookPro/rar/"+fileName+".rar");
               InputStream in = file.getInputStream();
               int b=0;
               while((b=in.read())!=-1){ //读取文件
                   os.write(b);
               }
               os.flush(); //关闭流
               in.close();
               os.close();

           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }

       }
       File file1 =new File("D:/lookPro/log.txt");
       if (!file1.exists()){
           file1.createNewFile();
       }
       FileWriter writer;
       try {
           //序号，名称，提交人，版本号，发布时间，存储位置
           writer=new FileWriter(file1,true);
//         BufferedWriter bw = new BufferedWriter(writer);
           writer.write(uuid.toString()+",");
           writer.write(pro.getProName()+",");
           writer.write(pro.getAuthor()+",");
           writer.write(pro.getProVersion()+",");
           writer.write(pro.getCreateTime()+",");
           writer.write(fileName+",");
           writer.write("\r\n");
           writer.flush();
           writer.close();
//           bw.close();
       }catch (Exception e){
           e.printStackTrace();
       }
       Unit unit =new Unit();
       String path = "D:/lookPro/pro";
       String filepath = "D:/lookPro/rar/"+fileName;
       if(!StringUtils.isEmpty(str)&& str.equals(".zip")){
           unit.unzip(filepath+".zip",path);
       }
       if (!StringUtils.isEmpty(str)&& str.equals(".rar"))
       {
           unit.unrar(filepath+".rar",path);
       }
//        TextSearchUnit textSearchUnit = new TextSearchUnit();
//        textSearchUnit.searchFile(path+"","index.html");
        return "redirect:/lookPro/index";
    }
    @RequestMapping("/openIndex")
    public void openIndex(HttpServletRequest request, HttpServletResponse response, String proName) throws IOException {

        File file = new File("D:/lookPro/pro/" + proName + "/index.html");

        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setHeader("Content-Disposition", "attachment;fileName=" + proName);
        FileInputStream inputStream = new FileInputStream(file);

        ServletOutputStream out;
        // 3.通过response获取ServletOutputStream对象(out)
        out = response.getOutputStream();

        int b = 0;
        byte[] buffer = new byte[1024];
        while ((b = inputStream.read(buffer)) != -1) {
            // 4.写到输出流(out)中
            out.write(buffer, 0, b);
        }
        inputStream.close();
        out.flush();
        out.close();


    }
    @RequestMapping("/openView")
    public  String openView(HttpServletRequest request ,HttpServletResponse response,String proName){
        request.setAttribute("proName",proName);
        return "/lookView";
    }
    @RequestMapping("/deletePro")
    public  String deletePrp(HttpServletResponse response ,HttpServletRequest request,String proName,String fileName){
        if (proName!=null&&!proName.equals("")){
            String project = "D:/lookPro/pro/"+fileName;
            String project1 = "D:/lookPro/rar/"+fileName;
            File file =new File(project);
            File file1 =new File(project1);
            if (file.exists()&&file.isDirectory()){
                deleteDir(file);
            }
            if (file1.exists()&&file1.isDirectory()){
                deleteDir(file1);
            }
        }
        ArrayList<String> temp = new ArrayList<String>();
        try {
            BufferedReader br =new BufferedReader(new FileReader(new File("D:/lookPro/log.txt")));
            String s="";
            String[] a=null;
            s=br.readLine();
            while (s!=null&&!StringUtils.isEmpty(s)){
                a= s.split(",");
                if(!a[1].trim().equals(proName)){
                    temp.add(s);
                }
                s=br.readLine();
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        };
        FileWriter writer;

        try {
            writer=new FileWriter(new File("D:/lookPro/log.txt"));

            for (String t:temp
                 ) {
               writer.write(t);
                writer.write("\r\n");
            }
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/lookPro/index";
    }
    /**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    private static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
//　　　　　　递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    @RequestMapping("/queryPro")
    public String queryPro(HttpServletRequest request,HttpServletResponse response,String proName,String author){
       ArrayList<Project> proList =new ArrayList<Project>();
       try{
        BufferedReader br =new BufferedReader(new FileReader(new File("D:/lookPro/log.txt")));
        String s="";
        String[] a =null;
        s=br.readLine();
        String flag="";
        while (s!=null){
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
                    proList.add(pro);
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
                    proList.add(pro);
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
                    proList.add(pro);
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
                    proList.add(pro);
                }
            }

            s=br.readLine();
        }
        br.close();
    }catch (Exception e){
        e.printStackTrace();
    };
            request.setAttribute("proname",proName);
            request.setAttribute("publisherName",author);
            request.setAttribute("proList",proList);

        return "home";
    }
   @RequestMapping("/findPro")
    public String findPro(HttpServletResponse response, HttpServletRequest request,String proName ,String author){
        int PageNum = Integer.valueOf(request.getParameter("pageNum"));
        int PageSize = 5;
        HomeService  hs = new HomeService();
        Page pb =hs.findAllProWithPage(PageNum,PageSize,proName,author);
        request.setAttribute("page",pb);
       request.setAttribute("proname",proName);
       request.setAttribute("publisherName",author);
        return "home";

    }
    @RequestMapping("/update")
    public  String update(HttpServletRequest request, HttpServletResponse response , Project pro) throws Exception {
        CommonsMultipartFile file= pro.getPro();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        pro.setCreateTime(format.format(new Date()));
        pro.setProVersion(pro.getProVersion()+1);
        int index =file.getOriginalFilename().lastIndexOf(".");
        String str =file.getOriginalFilename().substring(index);
        String fileName=file.getOriginalFilename().substring(0,index);
        if(!file.isEmpty()){
            try {
                FileOutputStream os = new FileOutputStream("D:/lookPro/rar/"+fileName+".rar");
                InputStream in = file.getInputStream();
                int b=0;
                while((b=in.read())!=-1){ //读取文件
                    os.write(b);
                }
                os.flush(); //关闭流
                in.close();
                os.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        File file1 =new File("D:/lookPro/log.txt");
        if (!file1.exists()){
            file1.createNewFile();
        }
        try {
        BufferedReader br = new BufferedReader(new FileReader((file1)));
        StringBuffer sb= new StringBuffer();
        String str1 = null;
        String[] a =null;
        while((str1=br.readLine()) != null){//一行一行读，如果不为空，继续运行
           a= str1.split(",");
           if(a[0].equals(pro.getId())){
               sb.append(a[0]+a[1]+a[2]+Integer.valueOf(a[3]+1)+a[4]+a[5]+"\n");
           }
           sb.append(str1+"\n");

        }
        sb.setLength(sb.length()-1);//因为多加了一个换行符，所以截掉
        br.close();//关闭输入流

//写入
        PrintWriter   out = new PrintWriter(new BufferedWriter(new FileWriter("D:/lookPro/log.txt")));
        out.write(sb.toString());//把sb里面的内容读入E:test.txt中

        out.flush();
        out.close();
    }catch(FileNotFoundException e){
        System.out.println("文件未找到");
    } catch (IOException e) {
        e.printStackTrace();
    }
        Unit unit =new Unit();
        String path = "D:/lookPro/pro";
        String filepath = "D:/lookPro/rar/"+fileName;
        if(!StringUtils.isEmpty(str)&& str.equals(".zip")){
            unit.unzip(filepath+".zip",path);
        }
        if (!StringUtils.isEmpty(str)&& str.equals(".rar"))
        {
            unit.unrar(filepath+".rar",path);
        }
        return "redirect:/lookPro/index";
    }

}