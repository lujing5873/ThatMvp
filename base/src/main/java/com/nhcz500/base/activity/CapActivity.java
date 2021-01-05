package com.nhcz500.base.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.media.Ringtone;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.nhcz500.base.R;

import java.util.Arrays;
import java.util.Collections;

public class CapActivity extends AppCompatActivity {
    TextureView mTextureView;

    ImageReader mImageReader;
    CaptureRequest.Builder mPreViewBuidler;
    CameraCaptureSession mCameraSession;
    CameraCharacteristics mCameraCharacteristics;
    Ringtone ringtone;
    Handler mHandler;
    Handler mUIHandler;
    private String TAG="CapActivity";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_cap);
        mTextureView=findViewById(R.id.sfv_cap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 111);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 111) {
            CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            try {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                manager.openCamera("1", new CameraDevice.StateCallback() {
                    @Override
                    public void onOpened(@NonNull CameraDevice camera) {

                    }

                    @Override
                    public void onDisconnected(@NonNull CameraDevice camera) {

                    }

                    @Override
                    public void onError(@NonNull CameraDevice camera, int error) {

                    }
                }, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }


    //相机会话的监听器，通过他得到mCameraSession对象，这个对象可以用来发送预览和拍照请求
    private CameraCaptureSession.StateCallback mSessionStateCallBack = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            try {
                mCameraSession = cameraCaptureSession;
                cameraCaptureSession.setRepeatingRequest(mPreViewBuidler.build(), null, mHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {

        }
    };

    private Size mPreViewSize;
    //预览图显示控件的监听器，可以监听这个surface的状态
    //打开相机时候的监听器，通过他可以得到相机实例，这个实例可以创建请求建造者
//    private CameraDevice.StateCallback cameraOpenCallBack = new CameraDevice.StateCallback() {
//        @Override
//        public void onOpened(CameraDevice camer aDevice) {
//            Log.d(TAG, "相机已经打开");
//            try {
//                mPreViewBuidler = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//                SurfaceTexture texture = mTextureView.getSurfaceTexture();
//                texture.setDefaultBufferSize(mPreViewSize.getWidth(), mPreViewSize.getHeight());
//                Surface surface = new Surface(texture);
//                mPreViewBuidler.addTarget(surface);
////                cameraDevice.createCaptureSession(Arrays.asList(surface, mImageReader.getSurface()), mSessionStateCallBack, mHandler);
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onDisconnected(CameraDevice cameraDevice) {
//            Log.d(TAG, "相机连接断开");
//        }
//
//        @Override
//        public void onError(CameraDevice cameraDevice, int i) {
//            Log.d(TAG, "相机打开失败");
//        }
//    };
}
