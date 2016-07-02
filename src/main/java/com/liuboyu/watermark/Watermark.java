package com.liuboyu.watermark;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class Watermark {
	
	public static void main(String[] args) throws Exception {
		
//		//1.jpg是你的 主图片的路径
//        InputStream is = new FileInputStream("/Users/liuboyu/Downloads/template.jpg");
//        
//        
//        //通过JPEG图象流创建JPEG数据流解码器
//        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
//        //解码当前JPEG数据流，返回BufferedImage对象
//        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
//        //得到画笔对象
//        Graphics g = buffImg.getGraphics();
//        
//        //创建你要附加的图象。
//        //2.jpg是你的小图片的路径
////        ImageIcon imgIcon = new ImageIcon("/Users/liuboyu/Desktop/2.jpg"); 
////        
////        //得到Image对象。
////        Image img = imgIcon.getImage();
//        
//        //将小图片绘到大图片上。
//        //5,300 .表示你的小图片在大图片上的位置。
////        g.drawImage(img,5,330,null);
//        
//        
//        
//        //设置颜色。
//        
//        //最后一个参数用来设置字体的大小
//        Font f = new Font("宋体",Font.BOLD,15);
//        
//        g.setFont(f);
//        g.setColor(new Color(54, 100, 139));
//        
//        //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
//        g.drawString("MAC 99:FC:19:DD:FD:13          杭州新市街店",70,170);
//        
//        g.dispose();
//        
//        
//        
//        OutputStream os = new FileOutputStream("/Users/liuboyu/Desktop/2.jpg");
//        
//        //创键编码器，用于编码内存中的图象数据。
//        
//        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
//        en.encode(buffImg);
//        
//        
//        is.close();
//        os.close();
//        
//        System.out.println ("合成结束。。。。。。。。");
        
        test();
    }
	
	public static void test() throws Exception {

		//1.jpg是你的 主图片的路径
		InputStream is = new FileInputStream("/Users/liuboyu/Downloads/template.jpg");
        
		ImageInputStream imageInput = ImageIO.createImageInputStream(is);
		BufferedImage buffImg = ImageIO.read(imageInput);
		
        //通过JPEG图象流创建JPEG数据流解码器
//        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(template_is);
        //解码当前JPEG数据流，返回BufferedImage对象
//        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
        //得到画笔对象
        Graphics g = buffImg.getGraphics();
        Font f = new Font("宋体",Font.BOLD,15);
        g.setFont(f);
        g.setColor(new Color(54, 100, 139));
        //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
        g.drawString(String.format("MAC 000000000000                    fv旗舰店"),70,170);
        g.dispose();
        OutputStream os = new FileOutputStream("/Users/liuboyu/Desktop/2.jpg");
        //创键编码器，用于编码内存中的图象数据。
//        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
//        en.encode(buffImg);
        ImageIO.write(buffImg, "jpg", os);
        is.close();
        os.close();
        System.out.println ("合成结束。。。。。。。。");
	
	}
	
	
}
