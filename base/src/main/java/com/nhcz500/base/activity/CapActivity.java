package com.nhcz500.base.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.nhcz500.base.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class CapActivity extends AppCompatActivity {

    private  double RATIO_4_3_VALUE = 4.0 / 3.0;
    private  double RATIO_16_9_VALUE = 16.0 / 9.0;
    ProcessCameraProvider cameraProvider;
    int lensFacing= CameraSelector.LENS_FACING_BACK;
    PreviewView previewView;
    Preview preview;
    DisplayMetrics metrics;
    ImageAnalysis imageAnalyzer;
    private ExecutorService cameraExecutor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        previewView=findViewById(R.id.prv_cam);
        metrics=new DisplayMetrics();
        previewView.getDisplay().getRealMetrics(metrics);
        int screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels);
        int rotation = previewView.getDisplay().getRotation();

        // CameraProvider
        ProcessCameraProvider cameraProvider = this.cameraProvider;
        // CameraSelector
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(lensFacing).build();

        // Preview
        preview = new Preview.Builder()
                // We request aspect ratio but no resolution
                .setTargetAspectRatio(screenAspectRatio)
                // Set initial target rotation
                .setTargetRotation(rotation)
                .build();

        // ImageCapture
       ImageCapture imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                // We request aspect ratio but no resolution to match preview config, but letting
                // CameraX optimize for whatever specific resolution best fits our use cases
                .setTargetAspectRatio(screenAspectRatio)
                // Set initial target rotation, we will have to call this again if rotation changes
                // during the lifecycle of this use case
                .setTargetRotation(rotation)
                .build();

// ImageAnalysis
        imageAnalyzer = new ImageAnalysis.Builder()
                // We request aspect ratio but no resolution
                .setTargetAspectRatio(screenAspectRatio)
                // Set initial target rotation, we will have to call this again if rotation changes
                // during the lifecycle of this use case
                .setTargetRotation(rotation)
                .build();
//        imageAnalyzer.setAnalyzer(cameraExecutor,);

                // The analyzer can then be assigned to the instance
//                .also {
//            it.setAnalyzer(cameraExecutor, LuminosityAnalyzer { luma ->
//                    // Values returned from our analyzer are passed to the attached listener
//                    // We log image analysis results here - you should do something useful
//                    // instead!
//                    Log.d(TAG, "Average luminosity: $luma")
//            })
//        }


    }
    private int aspectRatio(int width,int height){
        Double previewRatio =Double.valueOf( Math.max(width, height)) / Math.min(width, height);
        if (Math.abs(previewRatio - RATIO_4_3_VALUE) <= Math.abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3;
        }
        return AspectRatio.RATIO_16_9;
    }

    /** Initialize CameraX, and prepare to bind the camera use cases  */
    private void setUpCamera()   {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable(){
            @Override
            public void run()  {
                // CameraProvider
                try {
                    cameraProvider=cameraProviderFuture.get();
                    // Select lensFacing depending on the available cameras
                    if(hasBackCamera()){
                        lensFacing=CameraSelector.LENS_FACING_BACK;
                    }else if(hasFrontCamera()){
                        lensFacing=CameraSelector.LENS_FACING_FRONT;
                    }else{
                        throw new IllegalStateException("Back and front camera are unavailable");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },ContextCompat.getMainExecutor(this));

    }
    private Boolean hasBackCamera() throws Exception{
        if(cameraProvider!=null){
            return cameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA);
        }
        return false;
    }
    private Boolean hasFrontCamera() throws Exception{
        if(cameraProvider!=null){
            return cameraProvider.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA);
        }
        return false;
    }

  }
