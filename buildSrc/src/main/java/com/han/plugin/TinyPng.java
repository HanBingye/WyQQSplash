package com.han.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.tasks.TaskContainer;



public class TinyPng implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        ExtensionContainer extensions = project.getExtensions();
        TinyPngInfo tinyInfo = extensions.create("tinyInfo", TinyPngInfo.class);
        TaskContainer tasks = project.getTasks();
        Task tinyPngTask = tasks.create("tinyPngTask", TinyPngTask.class);

    }
}
