package com.nhcz500.base.activity;

import android.graphics.ImageFormat;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import java.nio.ByteBuffer;

public class LuminosityAnalyzer implements ImageAnalysis.Analyzer {

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

    }

}
