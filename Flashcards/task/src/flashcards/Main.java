package flashcards;

import java.util.Scanner;
import java.util.*;

class Main {

    static Scanner scanner;
    static LinkedList<String> listLog = new LinkedList<>();
    static String importFile = "";
    static String exportFile = "";
    static boolean checkImport;
    static boolean checkExport;

    public static void main(String[] args) {
        comandLine(args);
        AddRemove addRemove = new AddRemove();
        if (!checkImport) {
            importExport.importFromFiles(addRemove);
        }
        mainMenu(addRemove, args);

    }
//g
    public static void comandLine(String[] args) {
        checkImport = true;
        checkExport = true;

        if (args.length > 0) {

            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-import":
                        importFile += args[i + 1];
                        checkImport = false;
                        break;
                    case "-export":
                        exportFile += args[i + 1];
                        checkExport = false;
                        break;
                }
            }
        }
    }

    public static String beginMenu() {
        listLog.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        return "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
    }


    public static void mainMenu(AddRemove addRemove, String[] args) {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println(beginMenu());
            String change = scanner.nextLine();
            listLog.add(change);
            switch (change) {
                case "add":
                    addRemove.nameOfCard();
                    break;

                case "remove":
                    addRemove.removeOfCard();
                    break;

                case "import":
                    importExport.importFromFiles(addRemove);
                    break;

                case "export":
                    importExport.exportFromFiles(addRemove);
                    break;

                case "ask":
                    AskWords.words(addRemove);
                    break;

                case "log":
                    importExport.log();
                    break;

                case "hardest card":
                    CardsErrors.hardestCard(addRemove);
                    break;

                case "reset stats":
                    CardsErrors.resetStats(addRemove);
                    break;
                case "exit":
                    System.out.println("Bye bye!");

                    for (String arg : args) {
                        if (arg.equals("-export")) {
                            importExport.exportFromFiles(addRemove);
                            System.out.println(addRemove.getTm().size() + " cards have been saved.");
                        }
                    }

                    System.exit(0);
                    break;
            }
        }
    }
}