package pers.nchz.thatmvpdemo.biz;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import pers.nchz.thatmvpdemo.bean.ImageBean;


/**
 *
 */

public class ImageBiz {
    public static List<ImageBean> getAllPhoto(Context context) {
        List<ImageBean> photos = new ArrayList<>();
        String[] projection = new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA, MediaStore.Images.ImageColumns.DISPLAY_NAME};
        //asc 按升序排列
        //    desc 按降序排列
        //projection 是定义返回的数据，selection 通常的sql 语句，例如  selection=MediaStore.Images.ImageColumns.MIME_TYPE+"=? " 那么 selectionArgs=new String[]{"jpg"};
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.ImageColumns.DATE_MODIFIED + "  desc");
        String imageId = null;
        String fileName;
        String filePath;
        int i=0;
        while (cursor.moveToNext()) {
            i++;
            imageId = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID));
            fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
            ImageBean fileItem = new ImageBean();
            fileItem.setImageId(imageId);
            fileItem.setFileName(fileName);
            fileItem.setFilePath(filePath);
            if(i>10){
                break;
            }
            photos.add(fileItem);
        }
        cursor.close();
        cursor = null;
        return photos;

    }
}
