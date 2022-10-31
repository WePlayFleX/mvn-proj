package org.example.utils;

import java.io.*;
import java.util.Date;

public class IOUtils {

    public static void writeFileBuff(String path, String content, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, append))) {
            bw.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFileBuff(String path) {
        StringBuilder sb = new StringBuilder(512);
        try (BufferedReader reader = new BufferedReader(new FileReader(path), 10000)) {
            String line;
            while ((line = reader.readLine()) != null)
                sb.append(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }


    public static void writeFile(String path, String content, boolean append) {
        File file = new File(path);
        if (file.exists()) {
            System.out.println("File exists!");
            //return;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("An error during file creation: " + e.getMessage());
            throw new RuntimeException(e);
        }

        try (OutputStream os = new FileOutputStream(file, append)) {
            for (char ch : content.toCharArray())
                os.write(ch);
            System.out.println("Done.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String path, String content) {
        writeFile(path, content, false);
    }

    public static String readFile(String path) {
        if (!new File(path).exists()) {
            System.out.println("File is NOT exists!");
            return null;
        }

        try (InputStream fis = new FileInputStream(path)) {
            int i;
            StringBuilder sb = new StringBuilder(512);
            while ((i = fis.read()) != -1) {
                char ch = (char) i;
                sb.append(ch);
                System.out.print(ch);
            }
            System.out.println();
            return sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println(" No such file: " + path);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println(" Some generic IOException " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public static void copyFile(String sourcePath, String targetPath) {
        if (!new File(sourcePath).exists()) {
            System.out.println("Source File is NOT exists!");
            return;
        }

        if (new File(targetPath).exists()) {
            System.out.println("Target File is already exists!");
            return;
        }

        try {
            writeFile(targetPath, readFile(sourcePath));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // String path = "D:/test/example.txt";

        // writeFile(path, "Hello IO.");
//        writeFile(path, "EXTRA TEXT.", true);
//        readFile(path);
//
//        copyFile(path, "D:/test/example_copy.txt");
//
//
//        String str = "Crime, Drama, Action, Crime"; // -> array of Strings
//
//        str.split(", ");
//        int begin = 0;
//        int end = str.indexOf(',');
//        //"Crime"
//        String word = str.substring(begin, end); // "Crime"
//        //"Drama, Action, Crime"
//        str = str.substring(str.indexOf(word) + word.length() + 2);
//        //"Drama"
//        word = str.substring(begin, end);
//        //"Action, Crime"
//        str = str.substring(str.indexOf(word) + word.length() + 2);
//
//        System.out.println(str);


        printFolderStatInFile("D:\\OpenServer", "D:/test/log_" + new Date()
                .toString().replace(":", "_") + ".txt");


    }

    public static void printFolderStatInFile(String folderPath, String log) {
        File folder = new File(folderPath);

        if (!folder.isDirectory()) {
            writeFile(log, folderPath + " is NOT a Directory", true);
            System.out.println(folderPath + " is NOT a Directory");
            //return ;
        } else {
            writeFile(log, "Folder '" + folder.getName() + "' -> ", true);
            System.out.println("Folder '" + folder.getName() + "' -> ");
            File[] files = folder.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    writeFile(log, "File '" + f.getName() + "': ", true);
                    System.out.println("File '" + f.getName() + "': ");
                    writeFile(log, readFile(f.getPath()), true);

                } else if (f.isDirectory()) {
                    printFolderStatInFile(f.getPath(), log);
                }
            }
        }


    }

}
