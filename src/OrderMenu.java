import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class OrderMenu {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Product> menu = new ArrayList<>();
        ArrayList<Product> currentOrder = new ArrayList<>();

        menu.add(new Coffee("Espresso", 1.5, "Σκέτος", false, false));
        menu.add(new Coffee("Espresso Double", 2.1, "Σκέτος", false, false));
        menu.add(new Coffee("Espresso Americano", 2.1, "Σκέτος", false, false));
        menu.add(new Coffee("Espresso Freddo", 2.4, "Σκέτος", false, false));
        menu.add(new Coffee("Cappuccino", 1.9, "Σκέτος", false, false));
        menu.add(new Coffee("Cappuccino Double", 2.4, "Σκέτος", false, false));
        menu.add(new Coffee("Cappuccino Freddo", 2.6, "Σκέτος", false, false));
        menu.add(new Coffee("Φίλτρου", 2.2, "Σκέτος", false, false));
        menu.add(new Coffee("Nescafe", 2.2, "Σκέτος", false, false));

        boolean isRunning = true;

        while (isRunning) {
            try {
                System.out.println("\n---ΜΕΝΟΥ ΚΑΦΕ---");
                for (int i = 0; i < menu.size(); i++) {
                    System.out.println((i + 1) + ". " + menu.get(i).getName());
                }
                System.out.println("0. Ταμείο & Έξοδος");
                System.out.print("Επιλογή: ");

                int choice = input.nextInt();

                // Αν πατήσει 0, βγαίνει αμέσως από το while για να πάει στο Ταμείο
                if (choice == 0) {
                    break;
                }

                // Έλεγχος εγκυρότητας επιλογής μενού
                if (choice < 1 || choice > menu.size()) {
                    System.out.println("Λάθος επιλογή! Παρακαλώ επιλέξτε έναν καφέ από το μενού.");
                    continue;
                }

                String sugarLabel = "";
                Product selected = menu.get(choice - 1);
                boolean validSugar = false;

                // Loop για τη Ζάχαρη
                while (!validSugar) {
                    try {
                        System.out.print("Επιλέξτε ζάχαρη (1. Σκέτος, 2. Μέτριος, 3. Γλυκός) : ");
                        int sugarChoice = input.nextInt();

                        switch (sugarChoice) {
                            case 1:
                                sugarLabel = "Σκέτος";
                                validSugar = true;
                                break;
                            case 2:
                                sugarLabel = "Μέτριος";
                                validSugar = true;
                                break;
                            case 3:
                                sugarLabel = "Γλυκός";
                                validSugar = true;
                                break;
                            default:
                                System.out.println("Παρακαλώ επιλέξτε 1, 2 ή 3!");
                        }
                    } catch (Exception e) {
                        System.out.println("Λάθος! Πρέπει να πληκτρολογήσετε αριθμό.");
                        input.nextLine(); // καθαρισμός buffer
                    }
                }

                // Loop για το Extra Shot (με αριθμούς 1 ή 2)
                boolean extraShot = false;
                boolean validExtraShot = false;
                while (!validExtraShot) {
                    try {
                        System.out.print("Έξτρα Δόση; (1. Ναι, 2. Όχι): ");
                        int shotChoice = input.nextInt();
                        if (shotChoice == 1) {
                            extraShot = true;
                            validExtraShot = true;
                        } else if (shotChoice == 2) {
                            extraShot = false;
                            validExtraShot = true;
                        } else {
                            System.out.println("Παρακαλώ επιλέξτε 1 για Ναι ή 2 για Όχι.");
                        }
                    } catch (Exception e) {
                        System.out.println("Λάθος! Πρέπει να πληκτρολογήσετε αριθμό.");
                        input.nextLine();
                    }
                }

                // Loop για το Extra Vegan Milk (με αριθμούς 1 ή 2)
                boolean extraVeganMilk = false;
                boolean validVeganMilk = false;
                while (!validVeganMilk) {
                    try {
                        System.out.print("Έξτρα Γάλα Vegan; (1. Ναι, 2. Όχι): ");
                        int milkChoice = input.nextInt();
                        if (milkChoice == 1) {
                            extraVeganMilk = true;
                            validVeganMilk = true;
                        } else if (milkChoice == 2) {
                            extraVeganMilk = false;
                            validVeganMilk = true;
                        } else {
                            System.out.println("Παρακαλώ επιλέξτε 1 για Ναι ή 2 για Όχι.");
                        }
                    } catch (Exception e) {
                        System.out.println("Λάθος! Πρέπει να πληκτρολογήσετε αριθμό.");
                        input.nextLine();
                    }
                }

                // Προσθήκη στην παραγγελία
                currentOrder.add(new Coffee(selected.getName(), selected.getPrice(), sugarLabel, extraShot, extraVeganMilk));
                System.out.println("Το προϊόν προστέθηκε στην παραγγελία!");

            } catch (InputMismatchException e) {
                System.out.println("Παρακαλώ επιλέξτε αριθμό!");
                input.nextLine(); // καθαρισμός buffer
            }
        }

        // --- ΤΑΜΕΙΟ & ΑΠΟΔΕΙΞΗ ---
        System.out.println("\n***Απόδειξη Πληρωμής***");
        double totalSum = 0;
        for (Product p : currentOrder) {
            System.out.println(p.toString());
            totalSum = totalSum + p.calculateTotal();
        }
        System.out.printf("Σύνολο: %.2f€\n", totalSum);
    }
}