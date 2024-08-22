import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Elpriser\n" +
                "========\n" +
                "1. Inmatning\n" +
                "2. Min, Max och Medel\n" +
                "3. Sortera\n" +
                "4. Bästa Laddningstid (4h)\n" +
                "e. Avsluta");
        System.out.println("Please make your choice in the menu");
        String choice = scanner.nextLine();
        System.out.println("You choose " + choice);

        switch (choice) {
            case "1":
                System.out.println("Inmatning");
                break;
            case "2":
                System.out.println("Min, Max och Medel");
                break;
            case "3":
                System.out.println("Sortera");
                break;
            case "4":
                System.out.println("Avsluta");
                break;
            case "e":
                System.out.println("Avsluta");
                return;
            default:
                System.out.println("Invalid choice, Pick something else");
                break;

        }
    }
}