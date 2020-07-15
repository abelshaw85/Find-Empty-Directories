package folderinfodisplayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class DirectoryWithMostFilesFinder {

    private DirectoryWithMostFilesFinder() { }

    public static void findFolderWithMostFiles(String path) {
        if (Files.exists(Paths.get(path))) {
            try (Stream<Path> walk = Files.walk(Paths.get(path))) {
                File folderWithMostFiles = walk.filter(Files::isDirectory)
                        .skip(1) //skips the "root" directory... otherwise it would always be the folder with the most files :)
                        .map(Path::toFile)
                        .max(Comparator.comparingInt(file -> Objects.requireNonNull(file.list()).length))
                        .get();
                System.out.println(folderWithMostFiles.getName() + " has the most files.\n" +
                        "Number of files: " + folderWithMostFiles.list().length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cannot traverse folder - folder does not exist");
        }
    }
}
