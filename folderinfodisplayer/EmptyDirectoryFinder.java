package folderinfodisplayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class EmptyDirectoryFinder {

    private EmptyDirectoryFinder() { }

    public static void find(String path) {
        if (Files.exists(Paths.get(path))) {
            try (Stream<Path> walk = Files.walk(Paths.get(path))) {
                walk.filter(Files::isDirectory)
                        .map(Path::toFile)
                        .filter(file -> Objects.requireNonNull(file.list()).length == 0)
                        .map(File::getName)
                        .forEach(dir -> System.out.print(dir + " "));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cannot traverse folder - folder does not exist");
        }
    }
}
