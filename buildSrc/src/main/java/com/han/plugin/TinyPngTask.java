package com.han.plugin;

import com.tinify.Tinify;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TinyPngTask extends DefaultTask {


    TinyPngInfo info;

    public TinyPngTask() {
        info = (TinyPngInfo) getProject().findProperty("tinyInfo");

    }

    @TaskAction
    public void doAction() {
        ArrayList<String> srcList = info.resourcesSrc;

        String apiKey = info.apiKey;
        for (int i = 0; i < srcList.size(); i++) {
            String src = srcList.get(i);
            File fileDir = new File(getProject().getProjectDir(), src);
            //判断文件是否存在及是否是文件夹
            if (fileDir.exists() && fileDir.isDirectory()) {

                File[] files = fileDir.listFiles();
                for (int j = 0; j < files.length; j++) {
                    File file = files[j];

                    String filePath = file.getPath();
                    System.out.println(filePath);
                    //使用TinyPng的APi
                    Tinify.setKey(apiKey);
                    try {
                        Tinify.fromFile(filePath).toFile(filePath);
                        System.out.println("压缩完成");
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("压缩失败");
                    }


                }
            }
        }
    }
}

