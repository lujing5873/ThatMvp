package com.nhcz500.base.camerax;

import android.graphics.ImageFormat;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.nhcz500.base.utils.LibyuvUtils;

import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.Vector;

public class DecodeAnalyzer implements ImageAnalysis.Analyzer {

    private byte[] ByteBufferToArray(ByteBuffer byteBuffer){
            byteBuffer.rewind();
            byte[] result=new byte[byteBuffer.remaining()];
            byteBuffer.get(result);
            return result;
    }

    @Override
    public void analyze(@NonNull ImageProxy image) {
        //如果不是yuv_420_888格式直接不处理
        if (ImageFormat.YUV_420_888 != image.getFormat()) {
            image.close();
            return;
        }
        byte[]data=ByteBufferToArray(image.getPlanes()[0].getBuffer());
        //获取图片宽高
        int height = image.getHeight();
        int width = image.getWidth();

        //将图片旋转，这是竖屏扫描的关键一步，因为默认输出图像是横的，我们需要将其旋转90度
        byte[] rotationData = new byte[data.length];
//        int j,k;
//        for (int y=0 ;y< height;y++) {
//            for (int x=0;x< width;x++) {
//                j = x * height + height - y - 1;
//                k = x + y * width;
//                rotationData[j] = data[k];
//            }
//        }
        LibyuvUtils.rotateYUV420SP(data,rotationData,width,height,90);
        PlanarYUVLuminanceSource source=new PlanarYUVLuminanceSource(rotationData,height,width,0,0,height,width,false);
        BinaryBitmap bitmap=new BinaryBitmap(new HybridBinarizer(source));
        try {
            Result result =initReader().decode(bitmap);

        } catch (Exception e) {
            System.out.println(e);
        }finally {
            image.close();
        }
    }


    private MultiFormatReader initReader() {
        MultiFormatReader formatReader = new MultiFormatReader();
        Hashtable hints = new Hashtable<DecodeHintType, Object>();
        Vector decodeFormats = new Vector<BarcodeFormat>();
        //添加一维码解码格式
        decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);

        decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        //添加二维码解码格式
        decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
        hints.put(DecodeHintType.POSSIBLE_FORMATS,decodeFormats);
        //设置解码的字符类型
        hints.put(DecodeHintType.CHARACTER_SET,"UTF-8");
        formatReader.setHints(hints);
        return formatReader;
    }

}
