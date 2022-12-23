package org.example.managers;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public final class JavaProcess {

    public JavaProcess() {}

    public static ProcessBuilder exec(Class<?> myClass, List<String> args) throws InterruptedException, IOException {
        String javaHome = System.getProperty("JAVA_HOME");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = myClass.getName();

        List<String> command = new LinkedList<String>();
        command.add(javaBin);
        command.add("-cp");
        command.add(classpath);
        command.add(className);
        if (args != null) {
            command.addAll(args);
        }

        return new ProcessBuilder(command);
    }
}