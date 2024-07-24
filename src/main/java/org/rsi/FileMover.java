package org.rsi;

import java.io.File;

public class FileMover {

    public static boolean moveFromTo(File sourceFile, File targetFile) {
        return sourceFile.renameTo(targetFile);
    }
}
