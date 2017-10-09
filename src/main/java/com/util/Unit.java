package com.util;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.List;


public class Unit {
    private static final int BUFFEREDSIZE =1024;

    /**
     * 解压zip格式的压缩文件到指定位置
     * @param zipFileName
     * @param extPlace
     */
    @SuppressWarnings("unchecked")
    public synchronized  void unzip(String zipFileName,String extPlace){
        try {
            (new File(extPlace)).mkdirs();
            File f =new File(zipFileName);
            ZipFile zipFile =new ZipFile(zipFileName);
            if((!f.exists())&&f.length()<=0){
                throw new Exception("要解压的文件不存在");
            }
            String strPath,gbkPath,strtemp;
            File tempFile =new File(extPlace);
            strPath=tempFile.getAbsolutePath();
            java.util.Enumeration e =zipFile.getEntries();
            while(e.hasMoreElements()){
                org.apache.tools.zip.ZipEntry zipEnt = (ZipEntry) e.nextElement();
                gbkPath=zipEnt.getName();
                if (zipEnt.isDirectory()){
                    strtemp =strPath+File.separator+gbkPath;
                    File dir =new File(strtemp);
                    dir.mkdirs();
                    continue;
                }else {
                    InputStream is = zipFile.getInputStream(zipEnt);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    gbkPath=zipEnt.getName();
                    strtemp = strPath + File.separator + gbkPath;

                    //建目录
                    String strsubdir = gbkPath;
                    for(int i = 0; i < strsubdir.length(); i++) {
                        if(strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
                            String temp = strPath + File.separator + strsubdir.substring(0, i);
                            File subdir = new File(temp);
                            if(!subdir.exists())
                                subdir.mkdir();
                        }
                    }
                    FileOutputStream fos = new FileOutputStream(strtemp);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    int c;
                    while((c = bis.read()) != -1) {
                        bos.write((byte) c);
                    }
                    bos.close();
                    fos.close();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 解压rar格式的压缩文件到指定目录下
     * @param rarFileName 压缩文件
     * @param extPlace 解压目录
     * @throws Exception
     */
    public synchronized void unrar(String rarFileName, String extPlace) throws Exception{
        if (!rarFileName.toLowerCase().endsWith(".rar")) {
            System.out.println("非rar文件！");
            return;
        }
        File dstDiretory = new File(extPlace);
        if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
            dstDiretory.mkdirs();
        }
        Archive a = null;
        try {
            a = new Archive(new File(rarFileName));
            if (a != null) {
                a.getMainHeader().print(); // 打印文件信息.
                FileHeader fh = a.nextFileHeader();
                while (fh != null) {
                    if (fh.isDirectory()) { // 文件夹
                        File fol = new File(extPlace + File.separator
                                + fh.getFileNameString());
                        fol.mkdirs();
                    } else { // 文件
                        File out = new File(extPlace + File.separator
                                + fh.getFileNameString().trim());
                        //System.out.println(out.getAbsolutePath());
                        try {// 之所以这么写try，是因为万一这里面有了异常，不影响继续解压.
                            if (!out.exists()) {
                                if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
                                    out.getParentFile().mkdirs();
                                }
                                out.createNewFile();
                            }
                            FileOutputStream os = new FileOutputStream(out);
                            a.extractFile(fh, os);
                            os.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    fh = a.nextFileHeader();
                }
                a.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
