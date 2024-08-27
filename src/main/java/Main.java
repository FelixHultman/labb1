import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HourlyPrice[] priceData = null;
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().toLowerCase();
            System.out.println("you chose " + choice);


            switch (choice) {
                case "1":
                    System.out.println("Inmatning");
                    priceData = priceInput(scanner);
                    break;
                case "2":
                    System.out.println("Min, Max och Medel");
                    if (priceData != null && priceData.length > 0) {
                        calculateMaxMidMinPrice(priceData, scanner);
                    } else {
                        System.out.println("Ingen data inmatad, Välj alternativ 1 för att infoga data");
                    }
                    break;
                case "3":
                    if (priceData != null && priceData.length > 0) {
                        sortList(priceData, scanner);
                    } else {
                        System.out.println("Ingen data inmatad, Välj alternativ 1 för att infoga data");
                    }
                    break;
                case "4":
                    if (priceData != null && priceData.length > 0) {
                        bestLoadingPeriod(priceData, scanner);
                    } else {
                        System.out.println("Ingen data inmatad, Välj alternativ 1 för att infoga data");
                    }
                    break;
                case "e":
                    System.out.println("Avsluta");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, Pick something else");
                    break;
            }
        }
    }

    private static void bestLoadingPeriod(HourlyPrice[] priceData, Scanner scanner) {
        int hourSpan = 4;
        int n = priceData.length;

        if (n < hourSpan) {
            System.out.println("Saknas data för att göra uträkningar, Fyll på med mer data och försök igen");
            return;
        }

        int minSum = 0;
        for (int i = 0; i < hourSpan; i++) {
            minSum += priceData[i].price;
        }
        int currentSum = minSum;
        int bestValueStartinghour = 0;

        for (int i = 1; i <= n - hourSpan; i++) {
            currentSum = currentSum - priceData[i - 1].price + priceData[i + hourSpan - 1].price;

            if (currentSum < minSum) {
                minSum = currentSum;
                bestValueStartinghour = i;
            }
        }

        System.out.println("Börja ladda din bil klockan: " +
                String.format("%02d", bestValueStartinghour) +
                " för att optimera ditt timpris"
                +
                "\nMedelvärdet är då " +
                (minSum / (double) hourSpan) + " öre");
        waitForEnter(scanner);
    }

    private static void sortList(HourlyPrice[] priceData, Scanner scanner) {
        Arrays.sort(priceData);
        printPriceData(priceData);
        waitForEnter(scanner);
    }

    private static void calculateMaxMidMinPrice(HourlyPrice[] priceData, Scanner scanner) {
        int minPrice = Integer.MAX_VALUE;
        int maxPrice = Integer.MIN_VALUE;
        int minHour = -1;
        int maxHour = -1;
        int total = 0;

        for (HourlyPrice hp : priceData) {
            if (hp.price < minPrice) {
                minPrice = hp.price;
                minHour = hp.hour;
            }
            if (hp.price > maxPrice) {
                maxPrice = hp.price;
                maxHour = hp.hour;
            }
            total += hp.price;
        }

        double averagePrice = total / 24.0;

        System.out.println("Lägsta pris: " + minPrice + " öre, Timme: " + String.format("%02d", minHour) + "-" + String.format("%02d", (minHour + 1) % 24));
        System.out.println("Högsta pris: " + maxPrice + " öre, Timme: " + String.format("%02d", maxHour) + "-" + String.format("%02d", (maxHour + 1) % 24));
        System.out.println("Medelpris: " + String.format("%.2f", averagePrice) + " öre");
        waitForEnter(scanner);
    }


    private static void printMenu() {
        System.out.println("""
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta""");
        System.out.println("Välj ett alternativ: ");
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
        printPriceData(priceData);
        waitForEnter(scanner);
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

    private static void waitForEnter(Scanner scanner) {
        System.out.println("Tryck på enter för att komma tillbaka till huvudmenyn");
        scanner.nextLine();
    }
}

class HourlyPrice implements Comparable<HourlyPrice> {
    int hour;
    int price;

    HourlyPrice(int hour, int price) {
        this.hour = hour;
        this.price = price;
    }

    @Override
    public int compareTo(HourlyPrice other) {
        return Integer.compare(this.price, other.price);
    }
}