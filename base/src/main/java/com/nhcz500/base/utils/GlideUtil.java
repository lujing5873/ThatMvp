package com.nhcz500.base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.nhcz500.base.R;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;

/**
 * @discribe 拓展工具
 */
@SuppressWarnings("all")
public class GlideUtil {
    private static float density = -1F;
 
    private static RequestOptions options = new RequestOptions()
//            .placeholder(R.mipmap.ic_launcher)
//            .error(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.ALL);
 
    /**
     * 加载常规图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(options).into(imageView);
    }


    /**
     * 加载常规图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, int url, ImageView imageView) {
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 加载常规图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, Uri uri, ImageView imageView) {
        Glide.with(context).load(uri).apply(options).into(imageView);
    }
 
    /**
     * 加载图片自动适配容器大小
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImageFit(Context context, String url, ImageView imageView) {
        options.centerCrop();
        Glide.with(context).load(url).apply(options).into(imageView);
    }
 
    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param corner
     */
    public static void loadRectImage(Context context, String url, ImageView imageView, float corner) {
        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .transform(new CenterCrop(),new RoundedCorners(dip2px(context, corner)));
        Glide.with(context).load(url).apply(options).into(imageView);
    }
 
    /**
     * 加载圆角图片-自定义图片四个角弧度
     *
     * @param context
     * @param url
     * @param imageView
     * @param leftTop
     * @param rightTop
     * @param rightBottom
     * @param leftBottom
     * @param corner
     */
    public static void loadRectImageCustom(Context context, String url, ImageView imageView, boolean leftTop, boolean rightTop, boolean rightBottom, boolean leftBottom, int corner) {
        RoundedCornersTransform transform = new RoundedCornersTransform(context, dip2px(context, corner));
        transform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom);
        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .bitmapTransform(transform);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
 
    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .bitmapTransform(new CircleCrop());
        Glide.with(context).load(url).apply(options).into(imageView);
    }


    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundImage(Context context, int url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .bitmapTransform(new CircleCrop());
        Glide.with(context).load(url).apply(options).into(imageView);
    }


    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundImageAppla(Context context, Uri url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .bitmapTransform(new CircleCrop());
        Glide.with(context).load(url).apply(options).into(imageView);
    }



    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundImage(Context context, Uri url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .bitmapTransform(new CircleCrop());
        Glide.with(context).load(url).apply(options).into(imageView);
    }


    public static void loadBackground(Context context, String url, ImageView imageView){
        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .bitmapTransform(new CircleCrop());
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull @NotNull Drawable resource, @Nullable @org.jetbrains.annotations.Nullable Transition<? super Drawable> transition) {
                        imageView.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable @org.jetbrains.annotations.Nullable Drawable placeholder) {

                    }
                });
    }
 
    /**
     * 加载gif
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadGif(Context context, String url, ImageView imageView) {
        Glide.with(context).asGif().load(url).apply(options).into(imageView);
    }
 
    /**
     * 加载gif
     *
     * @param context
     * @param resourceId
     * @param imageView
     */
    public static void loadGif(Context context, int resourceId, ImageView imageView) {
        Glide.with(context).asGif().load(resourceId).apply(options).into(imageView);
    }
 
    /**
     * dp转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5F);
    }
 
    /**
     * px转dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        return (int) (pxValue / getDensity(context) + 0.5F);
    }
 
    /**
     * 获取当前像素密度
     *
     * @param context
     * @return
     */
    public static float getDensity(Context context) {
        if (density <= 0F) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }
    /**
     * 自定义角度
     */
    static class RoundedCornersTransform implements Transformation<Bitmap> {
        private BitmapPool mBitmapPool;
 
        private float radius;
 
        private boolean isLeftTop, isRightTop, isLeftBottom, isRightBotoom;
 
        /**
         * 需要设置圆角的部分
         *
         * @param leftTop     左上角
         * @param rightTop    右上角
         * @param leftBottom  左下角
         * @param rightBottom 右下角
         */
        public void setNeedCorner(boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
            isLeftTop = leftTop;
            isRightTop = rightTop;
            isLeftBottom = leftBottom;
            isRightBotoom = rightBottom;
        }
 
        /**
         * @param context 上下文
         * @param radius  圆角幅度
         */
        public RoundedCornersTransform(Context context, float radius) {
            this.mBitmapPool = Glide.get(context).getBitmapPool();
            this.radius = radius;
        }
 
        @NonNull
        @Override
        public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {

 
            Bitmap source = resource.get();
            int finalWidth, finalHeight;
            //输出目标的宽高或高宽比例
            float scale;
            if (outWidth > outHeight) {
                //如果 输出宽度 > 输出高度 求高宽比
                scale = (float) outHeight / (float) outWidth;
                finalWidth = source.getWidth();
                //固定原图宽度,求最终高度
                finalHeight = (int) ((float) source.getWidth() * scale);
                if (finalHeight > source.getHeight()) {
                    //如果 求出的最终高度 > 原图高度 求宽高比
                    scale = (float) outWidth / (float) outHeight;
                    finalHeight = source.getHeight();
                    //固定原图高度,求最终宽度
                    finalWidth = (int) ((float) source.getHeight() * scale);
                }
            } else if (outWidth < outHeight) {
                //如果 输出宽度 < 输出高度 求宽高比
                scale = (float) outWidth / (float) outHeight;
                finalHeight = source.getHeight();
                //固定原图高度,求最终宽度
                finalWidth = (int) ((float) source.getHeight() * scale);
                if (finalWidth > source.getWidth()) {
                    //如果 求出的最终宽度 > 原图宽度 求高宽比
                    scale = (float) outHeight / (float) outWidth;
                    finalWidth = source.getWidth();
                    finalHeight = (int) ((float) source.getWidth() * scale);
                }
            } else {
                //如果 输出宽度=输出高度
                finalHeight = source.getHeight();
                finalWidth = finalHeight;
            }
 
            //修正圆角
            this.radius *= (float) finalHeight / (float) outHeight;
            Bitmap outBitmap = this.mBitmapPool.get(finalWidth, finalHeight, Bitmap.Config.ARGB_8888);
            if (outBitmap == null) {
                outBitmap = Bitmap.createBitmap(finalWidth, finalHeight, Bitmap.Config.ARGB_8888);
            }
 
            Canvas canvas = new Canvas(outBitmap);
            Paint paint = new Paint();
            //关联画笔绘制的原图bitmap
            BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            //计算中心位置,进行偏移
            int width = (source.getWidth() - finalWidth) / 2;
            int height = (source.getHeight() - finalHeight) / 2;
            if (width != 0 || height != 0) {
                Matrix matrix = new Matrix();
                matrix.setTranslate((float) (-width), (float) (-height));
                shader.setLocalMatrix(matrix);
            }
 
            paint.setShader(shader);
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0.0F, 0.0F, (float) canvas.getWidth(), (float) canvas.getHeight());
            //先绘制圆角矩形
            canvas.drawRoundRect(rectF, this.radius, this.radius, paint);
 
            //左上角圆角
            if (!isLeftTop) {
                canvas.drawRect(0, 0, radius, radius, paint);
            }
            //右上角圆角
            if (!isRightTop) {
                canvas.drawRect(canvas.getWidth() - radius, 0, canvas.getWidth(), radius, paint);
            }
            //左下角圆角
            if (!isLeftBottom) {
                canvas.drawRect(0, canvas.getHeight() - radius, radius, canvas.getHeight(), paint);
            }
            //右下角圆角
            if (!isRightBotoom) {
                canvas.drawRect(canvas.getWidth() - radius, canvas.getHeight() - radius, canvas.getWidth(), canvas.getHeight(), paint);
            }
 
            return BitmapResource.obtain(outBitmap, this.mBitmapPool);
        }
 
 
        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        }
    }
 
    /**
     * 清除glide全部缓存
     *
     * @param context
     */
    public static void clearCache(Context context) {
        Glide.get(context).clearDiskCache();
        Glide.get(context).clearMemory();
    }



    public static class GlideCircleTransform extends BitmapTransformation {

        private Paint mBorderPaint;
        private float mBorderWidth;

        public GlideCircleTransform(int borderWidth, int borderColor) {
            mBorderWidth = borderWidth;
            mBorderPaint = new Paint();
            mBorderPaint.setDither(true);
            mBorderPaint.setAntiAlias(true);
            mBorderPaint.setColor(borderColor);
            mBorderPaint.setStyle(Paint.Style.STROKE);
            mBorderPaint.setStrokeWidth(mBorderWidth);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = (int) (Math.min(source.getWidth(), source.getHeight()) - (mBorderWidth / 2));
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            if (mBorderPaint != null) {
                float borderRadius = r - mBorderWidth / 2;
                canvas.drawCircle(r, r, borderRadius, mBorderPaint);
            }
            return result;
        }

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        }

    }
}