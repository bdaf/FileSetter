package org.rsi;


import java.io.IOException;

public class App
{
    public static void main( String[] args ) {
        DirectoryCreator dirCreator = new DirectoryCreator(args[0]);
        dirCreator.createHOMEDirectory();
        dirCreator.createDEVDirectory();
        dirCreator.createTESTDirectory();

        try {
            WatchDogOfFiles watchDog = new WatchDogOfFiles(args[0], new FileCounter( "/home/count.txt"));
            watchDog.watchCreatingFilesInInfiniteLoopAndMoveThem();
        } catch (IOException aE) {
            System.out.println("Error during getting access / manage files, details: " + aE.getMessage());
        } catch (InterruptedException aE) {
            System.out.println("Watch service error, details: " + aE.getMessage());
        }
    }
}
