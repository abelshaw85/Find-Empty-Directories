package folderinfodisplayer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Folder Info Displayer!");
        System.out.println("Please enter a path to a folder: ");
        String path = scanner.nextLine();
        System.out.println("Please select an option by selecting its number:\n" +
                "1. Find all empty directories\n" +
                "2. Find the folder with the most files\n" +
                "3. Find the deepest file/directory");
        String command = scanner.nextLine();
        switch (command) {
            case "1":
                EmptyDirectoryFinder.find(path);
                break;
            case "2":
                DirectoryWithMostFilesFinder.find(path);
                break;
            case "3":
                DeepestFileFinder.find(path);
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
    }
}
