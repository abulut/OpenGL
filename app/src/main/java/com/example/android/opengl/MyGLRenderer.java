/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.opengl;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Provides drawing instructions for a GLSurfaceView object. This class
 * must override the OpenGL ES drawing lifecycle methods:
 * <ul>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceCreated}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onDrawFrame}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceChanged}</li>
 * </ul>
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {

    private float mAngle = -15.0f;
    private Cube mCube;
    private Cube mCube2;
    private float mRotation;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_NICEST);

        mCube = new Cube();
        mCube2 = new Cube();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Draw background color
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        //gl.glFrustumf(-0.5f, 0.5f, -0.5f, 0.5f, 1.0f, 200.0f);
        //gl.glTranslatef(mAngle, 0.0f, -15.0f);

        gl.glLoadIdentity();   // reset the matrix to its default state

        //Give the Position of the Cube
        gl.glTranslatef(2.0f, 0.0f, -15.0f);

        //Create a rotation for the cube
        gl.glRotatef(mRotation, 0.0f, 1.0f, 1.0f);

        //mCube.setAngleCube(mRotation);
        //Draw Cube
        mCube.draw(gl);

        gl.glLoadIdentity();   // reset the matrix to its default state
        //Give the Position of the Cube
        gl.glTranslatef(-2.0f, 0.0f, -15.0f);

        //Create a rotation for the cube
        gl.glRotatef(-mRotation, 0.0f, 1.0f, 1.0f);

        mCube2.draw(gl);

        gl.glLoadIdentity();   // reset the matrix to its default state

        mRotation += 0.5f;


        //Log.d("mAngle", String.valueOf(mAngle));

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Adjust the viewport based on geometry changes
        // such as screen rotations

        //set he Viewport
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);        // set matrix to projection mode
        gl.glLoadIdentity();                        // reset the matrix to its default state
        // apply the projection matrix
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 250.0f);
        gl.glViewport(0, 0, width, height);         //set he Viewport

        gl.glMatrixMode(GL10.GL_MODELVIEW);         // set matrix to projection mode
        gl.glLoadIdentity();                        // reset the matrix to its default state

        // Adjust the viewport based on geometry changes
        // such as screen rotations
    }

    /**
     * Returns the rotation angle of the triangle shape (mTriangle).
     *
     * @return - A float representing the rotation angle.
     */
    public float getAngle() {
        return mAngle;
    }

    /**
     * Sets the rotation angle of the triangle shape (mTriangle).
     */
    public void setAngle(float angle) {
        mAngle = angle;
    }
}