package com.nhcz500.base.camerax;

import com.google.zxing.BarcodeFormat;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

//用于存放解码类型分类
public final class DecodeFormatManager {

    public static final Set<BarcodeFormat> PRODUCT_FORMATS;
    public static final Set<BarcodeFormat> INDUSTRIAL_FORMATS;
    public static final Set<BarcodeFormat> ONE_D_FORMATS;
    public static final Set<BarcodeFormat> QR_CODE_FORMATS = EnumSet.of(BarcodeFormat.QR_CODE);
    public static final Set<BarcodeFormat> DATA_MATRIX_FORMATS = EnumSet.of(BarcodeFormat.DATA_MATRIX);

    static {
        PRODUCT_FORMATS = EnumSet.of(BarcodeFormat.UPC_A,
                BarcodeFormat.UPC_E,
                BarcodeFormat.EAN_13,
                BarcodeFormat.EAN_8,
                BarcodeFormat.RSS_14,
                BarcodeFormat.RSS_EXPANDED);
        INDUSTRIAL_FORMATS = EnumSet.of(BarcodeFormat.CODE_39,
                BarcodeFormat.CODE_93,
                BarcodeFormat.CODE_128,
                BarcodeFormat.ITF,
                BarcodeFormat.CODABAR);
        ONE_D_FORMATS = EnumSet.copyOf(PRODUCT_FORMATS);
        ONE_D_FORMATS.addAll(INDUSTRIAL_FORMATS);
    }

}