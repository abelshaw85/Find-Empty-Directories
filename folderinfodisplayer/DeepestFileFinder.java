package folderinfodisplayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class DeepestFileFinder {
    private DeepestFileFinder()  { }

    public static void find(String path) {
        if (Files.exists(Paths.get(path))) {
            try (Stream<Path> walk = Files.walk(Paths.get(path))) {
                File deepestFile = walk
                        .map(Path::toFile)
                        .max(Comparator.comparingInt(file -> (int) file.getAbsolutePath().chars().filter(c -> c == '\\').count()))
                        .get();
                System.out.println(deepestFile.getName() + " is the deepest file.\n" +
                        "Located at: " + deepestFile.getAbsolutePath() + "\n" +
                        "Number of parents: " + (deepestFile.getAbsolutePath().chars().filter(c -> c == '\\').count() - 1)); // -1 to exclude the main drive letter
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cannot traverse folder - folder does not exist");
        }

    }
}
