package flashcards;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static flashcards.Main.*;

public class importExport {
    static Scanner scanner;


    public static File checkingImport() {
        scanner = new Scanner(System.in);
        File file;
        if (checkImport) {
            System.out.println("File name:");
            listLog.add("File name:");
            String a = scanner.nextLine();
            listLog.add(a);
            file = new File(a);
        } else {
            file = new File(importFile);
        }

        return file;
    }

    public static String changeText(String text) {
        text = text.replaceAll("[()\\[\\]<>]", "");
        return text;
    }

    public static void importFromFiles(AddRemove add_remove) {
        LinkedHashMap<String, String> tm2 = new LinkedHashMap<>();
        LinkedHashMap tmCheck = new LinkedHashMap<>();
        File file = checkingImport();

        String temp;
        String[] temp1;
        int count = 0;

        try (Scanner scanner2 = new Scanner(file)) {
            while (scanner2.hasNext()) {
                temp = changeText(scanner2.nextLine());
                if (temp.equals("") || temp.equals(" ")) {
                    break;
                }
                temp1 = temp.split(":");
                tm2.put(temp1[0], temp1[temp1.length - 1]);
                count++;
            }
            while (scanner2.hasNext()) {
                temp = changeText(scanner2.nextLine());
                tmCheck.put(temp.split(":")[0], Integer.valueOf(temp.split(":")[1]));
                Arrays.fill(temp.split(":"), null);

            }

            add_remove.setTm(tm2);
            add_remove.setTmCheck(tmCheck);
            System.out.printf("%d cards have been loaded.\n", count);
            listLog.add(String.format("%d cards have been loaded.\n", count));
            count = 0;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.\n");
            listLog.add("File not found.\n");
        }
    }

    public static File checkingExport() {
        File file;
        if (checkExport) {
            System.out.println("File name:");
            listLog.add("File name:");
            String a = scanner.nextLine();
            listLog.add(a);
            file = new File(a);
        } else {
            file = new File(exportFile);
        }
        return file;
    }

    public static void exportFromFiles(AddRemove addRemove) {
        scanner = new Scanner(System.in);
        File file = checkingExport();
        int count = 0;
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter printWriter = new PrintWriter(file)) {
            LinkedHashMap<String, String> tm = addRemove.getTm();
            LinkedHashMap<String, Integer> tmCheck = addRemove.getTmCheck();
            for (Map.Entry entry : tm.entrySet()) {
                printWriter.println(entry.getKey() + ":" + entry.getValue());
                count++;
            }
            printWriter.println(" ");
            for (Map.Entry entry : tmCheck.entrySet()) {
                printWriter.println(entry.getKey() + ":" + entry.getValue());
            }
            if (checkExport) {
                System.out.printf("%d cards have been saved.\n\n", count);
                listLog.add(String.format("%d cards have been saved.\n\n", count));
            }
        } catch (IOException e) {
            System.out.printf("An exception occurred.\n");
            listLog.add("An exception occurred.\n");
        }
    }


    public static void log() {
        System.out.println("File name:");
        listLog.add("File name:");
        String temp = scanner.nextLine();
        listLog.add(temp);
        if (listLog != null) {
            try {
                FileWriter writer = new FileWriter(temp);
                for (String s : listLog) {
                    writer.write(s + "\n");
                }

                writer.close();
            } catch (IOException e) {
                System.out.println("Issue: " + temp +
                        " in listMap");
            }
        }
        if (listLog != null) {
            listLog.clear();
        }
        System.out.println("The log has been saved.");
        listLog.add("The log has been saved.");
        System.out.println();

    }
}

