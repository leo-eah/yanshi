

import org.apache.poi.hwpf.HWPFDocument;

import java.io.*;

public class DocTest {
    public static void main(String[] args) throws  IOException {
//        XWPFDocument doc = new XWPFDocument(OPCPackage.open(new File("D:\\word\\1.doc")));
        String path = "D://word//7.doc";
       FileInputStream is = new FileInputStream(path);
        HWPFDocument doc=null;
        try{
            doc=new HWPFDocument(is);
            OutputStream os = new FileOutputStream("D:\\word\\5.doc");
            doc.write(os);
            os.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}

