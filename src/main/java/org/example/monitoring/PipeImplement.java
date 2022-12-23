package org.example.monitoring;

import java.io.*;

public class PipeImplement {

    public BufferedReader in;
    public void writePipe(String strWrite, String pipePath){
        PrintWriter out = null;
        try {
            out = new PrintWriter((new BufferedWriter(new FileWriter(pipePath))));
        } catch(IOException e) {
            System.out.println("error while writing");
            e.printStackTrace();
        }
        out.println(strWrite);
        System.out.println("writen");
        out.close();
        System.out.println("close");
    }

    public String readPipe(String pipePath) {
        try {
            in = new BufferedReader(new FileReader(pipePath));
        }catch (FileNotFoundException e) {
           e.printStackTrace();
        }
        String lineRead = "";
        try {
            lineRead = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineRead;
    }
}