package org.example.monitoring;

import org.example.managers.JavaProcess;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ThreadManager implements Runnable {
    int output = 0;
    int inputX = 0;
    Class<?> myClass;

    public ThreadManager(int inputX, Class<?> myClass) {
        this.inputX = inputX;
        this.myClass = myClass;
    }

    @Override
    public void run() {
        try {
            String className = myClass.getName();
            String processName = className.substring(className.length() - 1);

            String pipeLocation = "/home/user/workspace/Pipes/pipeFolder/pipe1";

            File bitPipe = new File(pipeLocation);

            Process myProcess = JavaProcess.exec(myClass, Arrays.asList(Integer.toString(inputX))).inheritIO()
                    .redirectOutput(ProcessBuilder.Redirect.to(bitPipe))
                    .redirectError(ProcessBuilder.Redirect.to(bitPipe))
                    .start();

            PipeImplement pi = new PipeImplement();
            pi.writePipe(Integer.toString(inputX), pipeLocation);

            myProcess.waitFor();
            myProcess.destroy();

            String value = read(pi, pipeLocation);
            try {
                output = Integer.parseInt(value);
                System.out.printf("Value on process " + processName + " %s%n", output);
            } catch (NumberFormatException e) {
                System.out.println("HARD FAIL on process " + processName);
                System.exit(-1);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static String read(PipeImplement pi, String pipeLocation){
        String data = pi.readPipe(pipeLocation);
        return data;
    }

    public int getOutput() {
        return output;
    }

}
