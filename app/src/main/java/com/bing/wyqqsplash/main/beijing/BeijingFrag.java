package com.bing.wyqqsplash.main.beijing;

import android.opengl.GLSurfaceView;

import com.bing.opengl.OpenGlManager;
import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseFragment;
import com.bing.wyqqsplash.base.ViewInject;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_beijing)
public class BeijingFrag extends BaseFragment {

    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.gl_surface)
    GLSurfaceView glSurface;


    @Override
    public void afterBindView() {
        glSurface.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                OpenGlManager.onSurfaceCreated();
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                OpenGlManager.onSurfaceChanged(width, height);
            }

            @Override
            public void onDrawFrame(GL10 gl) {
                OpenGlManager.onDrawFrame();
            }
        });
    }
}