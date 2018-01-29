package com.dyp;

/**
 * Created by Administrator on 2018/1/29.
 */

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 二维码工具类
 *
 */
public class MyQRcodeUtils {

    private static final String CHARSET = "utf-8";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;

    /**
     *
     * @param content   二维码所包含的内容
     * @param imgPath   图片类型
     * @param needCompress
     * @return
     * @throws Exception
     */
    public static BufferedImage createQRImage(String content, String imgPath,
                                            boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        return null;
    }

    /**
     *
     * @param filePath  图片路径
     * @return
     * @throws Exception
     */
    public static String DeQRcode(String filePath) throws Exception{
        MultiFormatReader formatReader = new MultiFormatReader();
        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer  binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        Result result = formatReader.decode(binaryBitmap,hints);

        System.out.println("result = "+ result.toString());
        System.out.println("resultFormat = "+ result.getBarcodeFormat());
        System.out.println("resultText = "+ result.getText());

        return result.getText();
    }

}