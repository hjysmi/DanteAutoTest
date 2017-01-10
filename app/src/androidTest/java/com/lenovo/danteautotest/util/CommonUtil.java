package com.lenovo.danteautotest.util;

import android.os.Environment;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2017/1/10.
 */

public class CommonUtil {
    public static void screenshot(UiDevice device, String tag) {
        StackTraceElement testClass = findTestClassTraceElement(Thread.currentThread().getStackTrace());
        String className = testClass.getClassName().replaceAll("[^A-Za-z0-9._-]", "_");
        String methodName = testClass.getMethodName();
        File dir = new File(Environment.getExternalStorageDirectory()
                + File.separator + "app_spoon-screenshots" + File.separator
                + className + File.separator + methodName);
        Log.v("TEST", dir.getAbsolutePath());
        if (!dir.exists()) {
            //dir.delete();
            dir.mkdirs();
        }

        String screenshotName = System.currentTimeMillis() + "_" + tag + ".png";
        File screenshotFile = new File(dir, screenshotName);
        device.takeScreenshot(screenshotFile);
        sleep(2000);

    }


    static StackTraceElement findTestClassTraceElement(StackTraceElement[] trace) {
        for (int i = trace.length - 1; i >= 0; --i) {
            StackTraceElement element = trace[i];
            if ("android.test.InstrumentationTestCase".equals(element.getClassName()) && "runMethod".equals(element.getMethodName())) {
                return trace[i - 3];
            }

            if ("org.junit.runners.model.FrameworkMethod$1".equals(element.getClassName()) && "runReflectiveCall".equals(element.getMethodName())) {
                return trace[i - 3];
            }
        }

        throw new IllegalArgumentException("Could not find test class!");
    }

    public static void sleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
