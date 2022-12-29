package org.example;

import org.example.managers.ImplementF;
import org.example.managers.ImplementG;

import org.example.monitoring.ThreadManager;
import sun.misc.Signal;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        final Class<ImplementF> processFImplement = ImplementF.class;
        final Class<ImplementG> processGImplement = ImplementG.class;
        
        int x = getX();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        ThreadManager threadManagerF = new ThreadManager(x, processFImplement);
        ThreadManager threadManagerG = new ThreadManager(x, processGImplement);

        Future<?> futureF = executor.submit(threadManagerF);
        Future<?> futureG = executor.submit(threadManagerG);

        try {
            futureF.get();
            futureG.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        int resF = threadManagerF.getOutput();
        int resG = threadManagerG.getOutput();

        result(x, resF, resG);

        Signal.handle(new Signal("INT"), signal -> {
            System.out.println("Exited with CTRL + C");
            System.exit(0);
        });
    }
    public static int getX() {
        var scanner = new Scanner(System.in);
        var result = 0;
        var line = "";

        while (true) {
            System.out.print("Enter x: ");
            line = scanner.nextLine();
            try {
                result = Integer.parseInt(line);
                return result;
            } catch (Exception e) {
                System.out.println("Try again, wrong type");
            } finally {
                scanner.close();
            }
        }
    }
    public static void result(int x, int resF, int resG) {
        int out = Math.min(resF, resG);
        System.out.println("Input: " + x);
        System.out.println("Output: " + out);
    }
}
