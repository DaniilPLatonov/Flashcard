package flashcards;

import java.util.LinkedHashMap;

public class CardsErrors {

    static LinkedHashMap<String, Integer> check = new LinkedHashMap<>();


    public static Integer[] countCards(AddRemove add_remove) {
        Integer[] arr = new Integer[2];
        int max = 0;
        int count = 0;
        for (var entry : add_remove.getTmCheck().entrySet()) {
            if (entry.getValue() > max) {
                count = 1;
                max = entry.getValue();
                check.clear();
                check.put(entry.getKey(), entry.getValue());
            } else if (entry.getValue() == max && entry.getValue() != 0) {
                count++;
                check.put(entry.getKey(), entry.getValue());
            }
        }
        arr[0] = max;
        arr[1] = count;
        return arr;
      }



    public static void hardestCard(AddRemove add_remove) {
        Integer[] arr = countCards(add_remove);
        int count = arr[1];
        int max = arr[0];
        if (count == 1) {
            System.out.println("The hardest card is \"" + (check.keySet().toString().replaceAll("[()\\[\\]<>]", "")
                    + "\". You have " + max + " errors answering it.\n"));
            check.clear();
            Main.listLog.add("The hardest card is \"" + (check.keySet()
                    + "\". You have " + max + " errors answering it.\n"));
        } else if (count > 1) {
            System.out.print("The hardest cards are ");
            Main.listLog.add("The hardest cards are ");
            String[] val = check.keySet().toString().replaceAll("[()\\[\\]<>]", "").replaceAll(" ", "").split(",");
            printValue(val);
            check.clear();
            System.out.println(" You have " + max + " errors answering them.\n");
            Main.listLog.add(" You have " + max + " errors answering them.\n");
        } else if (count == 0) {
            System.out.println("There are no cards with errors.\n");
            Main.listLog.add("There are no cards with errors.\n");
            check.clear();
        }
    }

    public static void printValue(String[] val){
        for (int j = 0; j < val.length; j++) {
            if (j == val.length - 1) {
                System.out.print("\"" + val[j] + "\".");
                Main.listLog.add("\"" + val[j] + "\".");
            } else {
                System.out.print("\"" + val[j] + "\", ");
                Main.listLog.add("\"" + val[j] + "\", ");
            }
        }
    }

    public static void resetStats(AddRemove add_remove) {
        LinkedHashMap<String, Integer> tmCheck = add_remove.getTmCheck();
        tmCheck.clear();
        check.clear();
        System.out.println("Card statistics have been reset.");
        Main.listLog.add("Card statistics have been reset.");
    }
}

