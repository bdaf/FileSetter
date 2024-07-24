package org.rsi;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.concurrent.TimeUnit;

public class WatchDogOfFiles {
    private final Path directoryPath;
    private FileCounter fileCounter;

    public WatchDogOfFiles(String directoryPathInString, FileCounter fileCounter) {
        directoryPath = Paths.get(directoryPathInString);
        fileCounter = fileCounter;
        fileCounter.loadDataFromTxt();
    }

    public void watchCreatingFilesInInfiniteLoopAndMoveThem() throws InterruptedException, IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        directoryPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    String fileName = event.context().toString();
                    BasicFileAttributes fileAttrs = Files.readAttributes(directoryPath, BasicFileAttributes.class);
                    FileTime fileTime = fileAttrs.creationTime();
                    moveFileBasedOnHisExtensionAndCreatedTime(fileName, fileTime);
                    updateFileCounterIfNecessary();
                }
            }
            key.reset();
        }
    }

    private void moveFileBasedOnHisExtensionAndCreatedTime(String fileName, FileTime fileTime) {
        String fileExtension = fileName.split("\\.")[1];
        boolean isMoved;
        // extension XML or extension JAR with even created time
        boolean XML_or_Jar_with_even_time = fileExtension.equals("xml") || (fileExtension.equals("jar") && fileTime.to(TimeUnit.HOURS) % 2 == 0);
        if (XML_or_Jar_with_even_time) {
            isMoved = FileMover.moveFromTo(directoryPath.toFile(), Paths.get(directoryPath.toString().replace("HOME", "DEV")).toFile());
            if(isMoved) fileCounter.addMovedToDevDirFiles();
        } else if (fileExtension.equals("jar")) {
            isMoved = FileMover.moveFromTo(directoryPath.toFile(), Paths.get(directoryPath.toString().replace("HOME", "TEST")).toFile());
            if(isMoved) fileCounter.addMovedToTestDirFiles();
        }
    }

    private void updateFileCounterIfNecessary() {
        if(fileCounter.isChangedNumber()) {
            fileCounter.setChangedNumberToFalse();
            fileCounter.saveDataToTxt();
        }
    }
}
