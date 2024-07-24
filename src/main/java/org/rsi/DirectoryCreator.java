package org.rsi;

import java.io.File;
import java.security.InvalidParameterException;

public class DirectoryCreator {
    private static final String HOME = "HOME";
    private static final String DEV = "DEV";
    private static final String TEST = "TEST";

    private final String workDirectoryPath;

    public DirectoryCreator(String workDirectoryPath) {
        this.workDirectoryPath = workDirectoryPath;
        checkIfIsDirectory(workDirectoryPath);
    }

    public void createHOMEDirectory() {
        createDirectoryWithGivenName(HOME);
    }

    public void createDEVDirectory() {
        createDirectoryWithGivenName(DEV);
    }

    public void createTESTDirectory() {
        createDirectoryWithGivenName(TEST);
    }

    private void checkIfIsDirectory(String workDirectoryPath) {
        File folder = new File(workDirectoryPath);
        if (!folder.isDirectory()) {
            throw new InvalidParameterException("Folder '" + folder.getAbsolutePath() + "' is not a directory");
        }
    }

    private void createDirectoryWithGivenName(String directoryName) {
        File theDir = new File(workDirectoryPath + directoryName);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
    }
}
