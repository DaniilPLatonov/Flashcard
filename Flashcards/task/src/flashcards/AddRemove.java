package flashcards;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class AddRemove {
    boolean check;
    private String[] arr2;
    Scanner scanner;
    private LinkedHashMap<String, String> tm = new LinkedHashMap();
    private LinkedHashMap<String, Integer> tmCheck = new LinkedHashMap();

    public LinkedHashMap<String, Integer> getTmCheck() {
        return tmCheck;
    }

    public void setTmCheck(LinkedHashMap<String, Integer> tmCheck) {
        this.tmCheck.putAll(tmCheck);
    }


    public LinkedHashMap getTm() {
        return tm;
    }


    public void setTm(LinkedHashMap<String, String> tm) {
        tm.forEach((key, value) -> {
            if (!this.tm.containsKey(key) && !this.tm.containsValue(value)) {
                this.tm.put(key, value);
            } else {
                this.tm.remove(key, value);
                this.tm.put(key, value);
            }
        });
    }


    public void nameOfCard() {
        scanner = new Scanner(System.in);
        String[] arr1 = new String[1];
        arr2 = new String[1];
        check = true;
        for (int i = 0; i < 1; i++) {
            System.out.println("The card");
            Main.listLog.add("The card");
            arr1[i] = scanner.nextLine();
            Main.listLog.add(arr1[i]);
            if (tm.containsKey(arr1[i])) {
                System.out.printf("The card \"%s\" already exists.%n\n", arr1[i]);
                Main.listLog.add(String.format("The card \"%s\" already exists.%n\n", arr1[i]));
                break;
            } else {
                checkDefiniton(i, tm);
                if (check) {
                    tm.put(arr1[i], arr2[i]);
                    tmCheck.put(arr1[i], 0);
                    System.out.println("The pair (\"" + arr1[i] + "\":\"" + arr2[i] + "\") has been added.\n");
                    Main.listLog.add(String.format("The pair (\"" + arr1[i] + "\":\"" + arr2[i] + "\") has been added.\n"));
                }
            }
        }
    }

    public void checkDefiniton(int i, LinkedHashMap tm) {
        scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("The definition of the card:");
            Main.listLog.add("The definition of the card:");
            arr2[i] = scanner.nextLine();
            Main.listLog.add(arr2[i]);
            if (tm.containsValue(arr2[i])) {
                System.out.printf("The definition \"%s\" already exists. Try again:%n\n", arr2[i]);
                Main.listLog.add(String.format("The definition \"%s\" already exists. Try again:%n\n", arr2[i]));
                check = false;
            }
            break;
        }
    }


    public void removeOfCard() {
        scanner = new Scanner(System.in);
        for (int i = 0; i < 1; i++) {
            System.out.println("Which card?");
            Main.listLog.add("Which card?");
            String word = scanner.nextLine();
            Main.listLog.add(word);
            if (tm.containsKey(word)) {
                tm.remove(word);
                System.out.println("The card has been removed.");
                Main.listLog.add("The card has been removed.");
            } else {
                System.out.println("Can't remove \"" + word + "\": there is no such card.\n");
                Main.listLog.add("Can't remove \"" + word + "\": there is no such card.\n");
            }
        }
    }
}
