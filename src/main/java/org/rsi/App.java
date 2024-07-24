package org.rsi;


public class App
{
    public static void main( String[] args ) {
        DirectoryCreator dirCreator = new DirectoryCreator(args[0]);
        dirCreator.createHOMEDirectory();
        dirCreator.createDEVDirectory();
        dirCreator.createTESTDirectory();

        for(;;) {

        }
    }
}
