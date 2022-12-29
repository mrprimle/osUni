package org.example.managers;

import os.lab1.compfuncs.basic.*;

public class ImplementG {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1)
            System.out.println("Missing argument for computation g!");

        System.out.println(compute(Integer.parseInt(args[0])));
    }
    private static int compute(int x) throws InterruptedException {
        var res = IntOps.trialG(x);
        if(res.isPresent())
            return res.get();

        throw new IllegalArgumentException("Could not compute F");
    }
}
