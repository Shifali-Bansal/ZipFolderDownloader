package com.newgen.app;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ZipFolderDownloader {
    public static void main(String[] args) {
        // Replace "path/to/source/directory" with the actual path of your source directory
        String sourceDirectoryPath = "path/to/source/directory";

        // Replace "path/to/destination/directory" with the desired path of your destination directory
        String destinationDirectoryPath = "path/to/destination/directory";

        try {
            Files.walkFileTree(Paths.get(sourceDirectoryPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // Check if the file is a zip folder (ends with ".zip")
                    if (file.toString().toLowerCase().endsWith(".zip")) {
                        // Create the destination path by combining the destination directory and the file name
                        Path destinationPath = Paths.get(destinationDirectoryPath, file.getFileName().toString());

                        // Copy the zip folder to the destination directory
                        Files.copy(file, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                        System.out.println("Downloaded: " + file.toString());
                    }

                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Download completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

