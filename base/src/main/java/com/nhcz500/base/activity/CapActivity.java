package com.nhcz500.base.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.nhcz500.base.R;
import com.nhcz500.base.camerax.DecodeAnalyzer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private Camera camera;
    int screenAspectRatio;
    int rotation;
    CameraSelector cameraSelector;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        previewView=findViewById(R.id.prv_cam);
        cameraExecutor = Executors.newSingleThreadExecutor();
        previewView.post(new Runnable() {
            @Override
            public void run() {
                setCamera();
            }
        });
    }

    private void setCamera(){
        metrics=new DisplayMetrics();
        previewView.getDisplay().getRealMetrics(metrics);
        screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels);
        rotation = previewView.getDisplay().getRotation();
        setUpCamera();
    }

    private void  bindCameraUseCases(){
        preview = new Preview.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(rotation)
                .build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        imageAnalyzer = new ImageAnalysis.Builder()
                .build();
        imageAnalyzer.setAnalyzer(cameraExecutor,new DecodeAnalyzer());
        cameraSelector = new CameraSelector.Builder().requireLensFacing(lensFacing).build();
        cameraProvider.unbindAll();
       camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector,preview,new ImageCapture.Builder()
                       .build(),imageAnalyzer);

    }

    private int aspectRatio(int width,int height){
        Double previewRatio =Double.valueOf( Math.max(width, height)) / Math.min(width, height);
        if (Math.abs(previewRatio - RATIO_4_3_VALUE) <= Math.abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3;
        }
        return AspectRatio.RATIO_16_9;
    }

    private void setUpCamera()     {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            // CameraProvider
            try {
                cameraProvider=cameraProviderFuture.get();
                bindCameraUseCases();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },ContextCompat.getMainExecutor(this));

    }
  }
