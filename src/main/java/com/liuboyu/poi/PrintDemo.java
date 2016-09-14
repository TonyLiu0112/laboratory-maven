package com.liuboyu.poi;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import java.io.File;
import java.io.FileInputStream;

/**
 * 打印word文档
 * <p>
 * Created by Tony on 9/13/16.
 */
public class PrintDemo {

    public static void print1(String path) {
        ComThread.InitSTA();
        ActiveXComponent xl = new ActiveXComponent("Excel.Application");
        try {
            // System.out.println("version=" + xl.getProperty("Version"));
            //不打开文档
            Dispatch.put(xl, "Visible", new Variant(true));
            Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
            //打开文档
            Dispatch excel = Dispatch.call(workbooks, "Open", path).toDispatch();
            //开始打印
            Dispatch.get(excel, "PrintOut");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ComThread.Release();
        }
    }

    public static void print2() {
//        JFileChooser fileChooser = new JFileChooser(); //创建打印作业
//        int state = fileChooser.showOpenDialog(null);
//        if (state == fileChooser.APPROVE_OPTION) {
        File file = new File("~/Desktop/1.jpg"); //获取选择的文件
        //构建打印请求属性集
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        //设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //查找所有的可用的打印服务
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, pras);
        //定位默认的打印服务
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        //显示打印对话框
        PrintService service = ServiceUI.printDialog(null, 200, 200, printService,
                defaultService, flavor, pras);
        if (service != null) {
            try {
                DocPrintJob job = service.createPrintJob(); //创建打印作业
                FileInputStream fis = new FileInputStream(file); //构造待打印的文件流
                DocAttributeSet das = new HashDocAttributeSet();
                Doc doc = new SimpleDoc(fis, flavor, das);
                job.print(doc, pras);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        }
    }

    public static void print3() {
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(MediaSizeName.ISO_A4);
        PrintService[] pservices = PrintServiceLookup.lookupPrintServices(flavor, aset);
        if (pservices.length > 0) {
            DocPrintJob pj = pservices[0].createPrintJob();
            try {
                FileInputStream fis = new FileInputStream("/Users/liuboyu/Desktop/121.pdf");
                Doc doc = new SimpleDoc(fis, flavor, null);
                pj.print(doc, aset);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(System.getProperty("java.library.path"));
//        print1("/tmp/demo.docx");
        print3();
    }

}
