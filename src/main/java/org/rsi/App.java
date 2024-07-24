package org.rsi;

import java.io.File;

public class App
{
    public static void main( String[] args ) {
        File folder = new File("/Users/kamil/tmp");

        String[] files = folder.list();

        for (String file : files)
        {
            System.out.println(file);
        }
    }
}
