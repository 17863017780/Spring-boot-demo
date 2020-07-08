package com.example.demo.Common.QRCodeTest;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author ccjh1
 * @creat 2019/10/22
 */
public class QRproduct {
    @Value("${cbs.imagesPath}")
    private static String theSetDir; //全局配置文件中设置的图片的路径

    //二维码图片的属性
    private static int height= 300;
    private static int width= 300;
    private static String type ="jpg";

    public static String CreateQRProduct(String content){

        if (content ==null || content.equals("")){
            System.out.println("Error---CreateQRProduct:"+"姓名为空");
            return "Error---CreateQRProduct:姓名为空";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filename = sdf.format(new Date());
        // 定义二维码的配置，使用HashMap
        HashMap hints = new HashMap();
        // 字符集，内容使用的编码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 容错等级，H、L、M、Q
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 边距，二维码距离边的空白宽度
        hints.put(EncodeHintType.MARGIN, 2);

        try {
            // 生成二维码对象，传入参数：内容、码的类型、宽高、配置
            BitMatrix bitMatrix =  new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            File fileDir = new File(theSetDir+filename+".jpg");
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            // 定义一个路径对象
            Path file = fileDir.toPath();
            // 生成二维码，传入二维码对象、生成图片的格式、生成的路径
            MatrixToImageWriter.writeToPath(bitMatrix, type, file);
        } catch (WriterException e) {
            System.out.println("Error---CreateQRProduct:"+e);
        } catch (IOException e) {
            System.out.println("Error---CreateQRProduct:"+e);
        }
        return filename;
    }

    public static Result AnalysisQRcode(String filename){
        Result result=null;
        try {
            // 声明一个解析二维码的对象
            MultiFormatReader formatReader = new MultiFormatReader();
            // 生成一个文件对象，传入刚才生成二维码的路径
            File file = new File("E:/QRcode/"+filename+".jpg");
            // 把文件对象转成一个图片对象
            BufferedImage image = ImageIO.read(file);
            // 最后需要的是一个binaryBitmap对象。
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            // 配置，解析时传入
            HashMap hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 解析得到一个Result对象，该对象包含二维码的信息
            result = formatReader.decode(binaryBitmap, hints);
            // 分别输出二维码类型和内容的方法
//            System.out.println(result.getBarcodeFormat());
//            System.out.println(result.getText());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String content="https://mauction.jd.com";
        String filename=CreateQRProduct(content);
        System.out.println(filename);
        System.out.println(AnalysisQRcode(filename));
    }
}
