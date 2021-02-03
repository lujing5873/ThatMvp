package com.nhcz500.base.camerax.view;

import android.os.Bundle;
import android.util.DisplayMetrics;

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
import com.nhcz500.base.camerax.presenter.ScannerPresenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;

import static com.nhcz500.base.Constants.RATIO_16_9_VALUE;
import static com.nhcz500.base.Constants.RATIO_4_3_VALUE;

public class ScannerView  extends ThatBaseView<ScannerPresenter> {

    private Camera camera;
    PreviewView previewView;
    private ExecutorService cameraExecutor;
    private int lensFacing= CameraSelector.LENS_FACING_BACK;
    @Override
    public void initView(Bundle savedInstanceState) {
        previewView=getView(R.id.prv_cam);
        cameraExecutor = Executors.newSingleThreadExecutor();
        previewView.post(new Runnable() {
            @Override
            public void run() {
                startCamera();
            }
        });
    }

    private void startCamera(){
        DisplayMetrics metrics=new DisplayMetrics();
        previewView.getDisplay().getRealMetrics(metrics);
        int screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels);
        int rotation = previewView.getDisplay().getRotation();
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(mActivity);
        cameraProviderFuture.addListener(() -> {
            // CameraProvider
            try {
                ProcessCameraProvider  cameraProvider=cameraProviderFuture.get();
                Preview preview = new Preview.Builder()
                        .setTargetAspectRatio(screenAspectRatio)
                        .setTargetRotation(rotation)
                        .build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());
                ImageAnalysis imageAnalyzer = new ImageAnalysis.Builder()
                        .build();
                imageAnalyzer.setAnalyzer(cameraExecutor,new DecodeAnalyzer());
                CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(lensFacing).build();
                cameraProvider.unbindAll();
                camera=cameraProvider.bindToLifecycle(
                        mActivity, cameraSelector,preview,new ImageCapture.Builder()
                                .build(),imageAnalyzer);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, ContextCompat.getMainExecutor(mActivity));
    }

    private int aspectRatio(int width,int height){
        Double previewRatio =Double.valueOf( Math.max(width, height)) / Math.min(width, height);
        if (Math.abs(previewRatio - RATIO_4_3_VALUE) <= Math.abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3;
        }
        return AspectRatio.RATIO_16_9;
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_camara;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IScannerView.class;
    }
}
