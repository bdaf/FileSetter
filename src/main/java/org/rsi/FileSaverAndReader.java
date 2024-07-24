package org.rsi;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileSaverAndReader {

    public void writeToFile(String aFilePath, String aFileContent) {
        FileWriter write = null;
        try {
            write = new FileWriter(aFilePath, false);
        } catch (IOException aE) {
            System.out.println("An error occurred while writing to the file " + aFilePath);
            return;
        }
        PrintWriter printWriter = new PrintWriter(write);
        printWriter.println(aFileContent);
        printWriter.close();
    }

    public String getFromFile(String aFilePath) throws IOException {
        int chaNum;
        FileReader fileReader = null;
        StringBuilder sb = new StringBuilder();
        while ((chaNum = fileReader.read())!=-1)
            sb.append((char)chaNum);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
