package com.dyp;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Administrator on 2018/1/29.
 *  将生成的二维码写到页面
 */
public class WebController {


    public static String createQRCode(String order_price, String out_trade_no)
            throws Exception {
        String url = "http://www.baidu.com";
        BufferedImage bufferImage = MyQRcodeUtils.createQRImage(url, null, false);

        //将二维码写到文件  begin....
        File file = new File("D:\\cache\\test.jpg");
        FileImageOutputStream imageOutputStream = new FileImageOutputStream(file);
        ImageIO.write(bufferImage, "JPEG", imageOutputStream);
        //将二维码写到文件  end....
        //ImageIO.write(bufferImage, "JPEG", response.getOutputStream()); // 通过write方法将图像从内存中写到页面上。
        return null;
    }

    /**
     * 读取二维码
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
       MyQRcodeUtils.DeQRcode("D:\\cache\\test.jpg");
    }
}
