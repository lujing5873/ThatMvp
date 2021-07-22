package com.nhcz500.base.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileBodyUtils {
    public static RequestBody getBody(String path){
        File file=new File(path);
        MediaType mediaType=MediaType.Companion.parse("multipart/form-data");
        RequestBody fileBody=RequestBody.Companion.create(file,mediaType);
        MultipartBody body= new MultipartBody.Builder().setType(mediaType).addFormDataPart("file", file.getName(),fileBody).build();
        return body;
    }

    public static RequestBody getBody(List<String> list){
        MediaType mediaType=MediaType.Companion.parse("multipart/form-data");
        MultipartBody.Builder body= new MultipartBody.Builder().setType(mediaType);
        for(String path:list){
            File file=new File(path);
            RequestBody fileBody=RequestBody.Companion.create(file,mediaType);
            body.addFormDataPart("files", file.getName(),fileBody).build();
        }
        return body.build();
    }



    public static HashMap<String,RequestBody> getRequestParams(String path){
        HashMap<String,RequestBody> params=new HashMap<>();
        File file=new File(path);
        MediaType mediaType=MediaType.Companion.parse("multipart/form-data");
        RequestBody fileBody=RequestBody.Companion.create(file,mediaType);
        params.put("file\"; filename=\""+ file.getName(), fileBody);
        return params;
    }

    public static MultipartBody.Part getPart(String path){
        File file=new File(path);
        RequestBody fileRQ = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part =MultipartBody.Part.createFormData("file", file.getName(), fileRQ);
        return part;
    }
}

