package com.lanmushan.framework.util.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.netflix.client.http.HttpResponse;
import org.apache.tomcat.util.http.parser.HttpParser;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * @Author dy
 * @Date 2020/4/20 20:53
 * @Version 1.0
 */
public class QRCodeUtil {
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 300;
    /**
     * logo宽度
     */
    private static final int WIDTH = 60;
    /**
     *  logo高度
     */
    private static final int HEIGHT = 60;

    /**
     * 创建二维码图片
     * @param content
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content)  {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                    hints);
        } catch (WriterException e) {
            throw new RuntimeException("二维码生成失败");
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    /**
     * 插入logo
     * @param source 原图片
     * @param logoImage logo图片
     * @param needCompress  是否压缩
     * @throws Exception
     */
    private static BufferedImage insertImage(BufferedImage source, Image  logoImage, boolean needCompress)  {
        int width = logoImage.getWidth(null);
        int height = logoImage.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = logoImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            logoImage = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(logoImage, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
        return source;
    }

    /**
     * 创建二维码图片
     * @param content
     * @return
     * @throws Exception
     */
    public static BufferedImage createQrCodeImage(String content)  {
         return   createImage(content);
    }
    public static BufferedImage createQrCodeImage(String content,Image image) {
        BufferedImage source=   createImage(content);
        return   insertImage(source,image,true);
    }
    public static BufferedImage createQrCodeImage(String content,Image image,boolean needCompress)  {
        BufferedImage source=   createImage(content);
        return   insertImage(source,image,needCompress);
    }
    public static void createQrCodeImage(String content, Image image, OutputStream out){
        BufferedImage bufferedImage=createQrCodeImage(content,image,true);
        try {
            ImageIO.write(bufferedImage,FORMAT_NAME,out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createQrCodeImage(String content, Image image, HttpServletResponse response){
        try {
            response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
            response.setDateHeader("Expire", 0);
            createQrCodeImage(content,image,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }
    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "https://www.baidu.com";
        // 嵌入二维码的图片路径
        String imgPath = "G:/head2.jpg";
        // 生成的二维码的路径及名称
        String destPath = "G:/head2/jam.jpg";
        //生成二维码
       // QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);

    }
}
