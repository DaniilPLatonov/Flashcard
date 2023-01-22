package flashcards;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AskWords {
    static Scanner scanner;

    public static int countQuestion() {
        scanner = new Scanner(System.in);
        System.out.println("How many times to ask?");
        Main.listLog.add("How many times to ask?");
        int countWords = Integer.parseInt(scanner.nextLine());
        Main.listLog.add(String.valueOf(countWords));
        return countWords;
    }

    public static String printDefinition(Map.Entry<String, String> entry) {
        System.out.printf("Print the definition of \"%s\":%n", entry.getKey());
        Main.listLog.add(String.format("Print the definition of \"%s\":%n", entry.getKey()));
        String a = scanner.nextLine();
        Main.listLog.add(a);
        return a;
    }


    public static void words(AddRemove add_remove) {
        LinkedHashMap<String, String> tm3 = add_remove.getTm();
        LinkedHashMap<String, Integer> tmCheck = add_remove.getTmCheck();
        int countWords = countQuestion();
        boolean check3;
        int i = 0;
        String definition;
        while (i < countWords) {
            for (var entry : tm3.entrySet()) {
                if (i == countWords) {
                    break;
                }
                i++;
                check3 = true;
                definition = printDefinition(entry);
                if (entry.getValue().equals(definition)) {
                    System.out.println("Correct!");
                    Main.listLog.add("Correct!");
                } else {
                    for (var entry2 : tm3.entrySet()) {
                        if (entry2.getValue().equals(definition)) {

                            tmCheck.put(entry.getKey(), tmCheck.get(entry.getKey()) + 1);
                            System.out.printf("Wrong. The right answer is \"%s\", " +
                                    "but your definition is correct for \"%s\".%n", entry.getValue(), entry2.getKey());
                            Main.listLog.add(String.format("Wrong. The right answer is \"%s\", " +
                                    "but your definition is correct for \"%s\".%n", entry.getValue(), entry2.getKey()));
                            check3 = false;
                            break;
                        }
                    }
                    if (!entry.getValue().equals(definition) && check3) {
                        tmCheck.put(entry.getKey(), tmCheck.get(entry.getKey()) + 1);
                        System.out.printf("Wrong. The right answer is \"%s\".%n", entry.getValue());
                        Main.listLog.add(String.format("Wrong. The right answer is \"%s\".%n", entry.getValue()));
                    }
                }
            }
        }
        add_remove.setTmCheck(tmCheck);
    }
}
