package com.runner.pay.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: FmOpen
 * @description: 基于Zxing 实现二维码的生成和解析
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 11:39
 */
public class QrCodeUtil {

    /**
     * 生成二维码*/
    public static BufferedImage createQrCode(String msg, int size){
        BufferedImage image;
        //设置参数
        Map<EncodeHintType, Object> hints=new HashMap<>();
        //设置内容的编码格式
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        //设置纠错等级 等级：H:30% Q:25% M:15% L:7% 允许的错误率
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置边距
        hints.put(EncodeHintType.MARGIN,1);
        //创建位图
        try {
            BitMatrix matrix=new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE,size,size,hints);
            //设置格式 颜色 黑白色
            MatrixToImageConfig config=new MatrixToImageConfig(0xFF000001,
                    0xFFFFFFFF);

            image=MatrixToImageWriter.toBufferedImage(matrix,config);
            return image;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 生成二维码，带Logo*/
    public static BufferedImage createQrCodeWithLogo(String msg, int size,byte[] data){
        try {
            //生成二维码
            BufferedImage qrcode=createQrCode(msg, size);
            //获取logo
            BufferedImage logo=ImageIO.read(new ByteArrayInputStream(data));
            int s=size/3;
            int deltaHeight,deltaWidth;
            if(logo.getHeight()>s){
                //logo图片不能超过二维码的1/3
                int ys1=logo.getHeight()/s;
                int ys2=logo.getWidth()/s;
                int ys=ys1>ys2?ys1:ys2;
                //需要进行图片压缩处理
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                Thumbnails.of(new ByteArrayInputStream(data)).
                        size(logo.getWidth()/ys,logo.getHeight()/ys).toOutputStream(baos);
                data=baos.toByteArray();
                deltaHeight = size - logo.getWidth()/ys;
                deltaWidth = size - logo.getHeight()/ys;
                logo=ImageIO.read(new ByteArrayInputStream(data));
            }else {
                 deltaHeight = size - logo.getHeight();
                 deltaWidth = size - logo.getWidth();
            }
            BufferedImage logoqrcode = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) logoqrcode.getGraphics();
            g.drawImage(qrcode, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g.drawImage(logo, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
            return logoqrcode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createQrcodeFile(String msg,int size,String path){
        BufferedImage image=createQrCode(msg, size);
        try {
            ImageIO.write(image,"PNG",new FileOutputStream(path));
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //解析二维码
    public static String parseQrCode(byte[] data){
        try {
            //转换资源
            LuminanceSource source=new BufferedImageLuminanceSource(ImageIO.read(new ByteArrayInputStream(data)));
            //获取二进制位图
            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(source));
            //设置参数
            Map<DecodeHintType,Object> map=new HashMap<>();
            map.put(DecodeHintType.CHARACTER_SET,"UTF-8");
            //创建读取对象
            Result result=new MultiFormatReader().decode(binaryBitmap,map);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String parseQrCodePath(String path){
        try {
            //转换资源
            LuminanceSource source=new BufferedImageLuminanceSource(ImageIO.read(new File(path)));
            //获取二进制位图
            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(source));
            //设置参数
            Map<DecodeHintType,Object> map=new HashMap<>();
            map.put(DecodeHintType.CHARACTER_SET,"UTF-8");
            //创建读取对象
            Result result=new MultiFormatReader().decode(binaryBitmap,map);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}