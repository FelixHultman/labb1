import java.util.Arrays;
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
        String choice = scanner.nextLine().toLowerCase();
        System.out.println("You choose " + choice);

        HourlyPrice[] priceData = null;

        switch (choice) {
            case "1":
                System.out.println("Inmatning");
                priceData = priceInput(scanner);
                printPriceData(priceData);
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

    private static HourlyPrice[] priceInput(Scanner scanner) {
        HourlyPrice[] priceData = new HourlyPrice[24];
        for (int i = 0; i < 24; i++) {
            while (true) {
                String nextHour = String.format("%02d", (i + 1) % 24);
                System.out.println("Ange pris för tiden " + String.format("%02d", i) + "-" + nextHour + ": ");
                String input = scanner.nextLine();
                try {
                    int price = Integer.parseInt(input);
                    if (price < 0) {
                        System.out.println("Inget negativt pris här, tack.");
                    } else {
                        priceData[i] = new HourlyPrice(i, price);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ogiltigt inmatning. Ange heltal i öre");
                }
            }
        }

        return priceData;

    }

    private static void printPriceData(HourlyPrice[] priceData) {
        for (HourlyPrice hp : priceData) {
            if (hp != null) {
                int nextHour = (hp.hour + 1) % 24;
                System.out.println("Timme: " + String.format("%02d", hp.hour) + "-" + String.format("%02d ", nextHour) + "Pris: " + (hp.price) + " Öre");
            }
        }

    }
}

class HourlyPrice {
    int hour;
    int price;

    HourlyPrice(int hour, int price) {
        this.hour = hour;
        this.price = price;
    }
}