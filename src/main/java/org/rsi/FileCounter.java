package org.rsi;

import java.io.IOException;

public class FileCounter {
    private FileSaverAndReader fileSaverAndReader = new FileSaverAndReader();
    private String counterTxt;
    private int movedToAnyDirFiles = 0;
    private int movedToDevDirFiles = 0;
    private int movedToTestDirFiles = 0;

    public FileCounter(int aMovedToAnyDirFiles, int aMovedToDevDirFiles, int aMovedToTestDirFiles, String aCounterTxt) {
        movedToAnyDirFiles = aMovedToAnyDirFiles;
        movedToDevDirFiles = aMovedToDevDirFiles;
        movedToTestDirFiles = aMovedToTestDirFiles;
        counterTxt = aCounterTxt;
    }

    public void saveDataToTxt() {
        StringBuilder content = new StringBuilder();
        content.append(movedToAnyDirFiles).append("\n").append(movedToDevDirFiles).append("\n").append(movedToTestDirFiles);
        fileSaverAndReader.writeToFile(counterTxt, content.toString());
    }

    public void loadDataFromTxt(String aFileName) {
        try {
            String content = fileSaverAndReader.getFromFile(aFileName);
            String[] valuesInString = content.split("\n");
            movedToAnyDirFiles = Integer.parseInt(valuesInString[0]);
            movedToDevDirFiles = Integer.parseInt(valuesInString[1]);
            movedToTestDirFiles = Integer.parseInt(valuesInString[2]);
        } catch (IOException aE) {
            System.out.println("Unfortunately cannot get data from file: " + aFileName);
        }
    }
}
