import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Exchange rates (for example purposes)
        double usdToEurRate = 0.85;
        double usdToGbpRate = 0.75;
        double usdToInrRate = 73.5; // Exchange rate for Indian Rupees

        System.out.println("Welcome to Currency Converter!");

        int ic = getValidCurrency("Enter the input currency:\n1. USD\n2. Euro (EUR)\n3. British Pound (GBP)\n4. Indian Rupees (INR)", scanner);

        double amount = getValidAmount(scanner);

        System.out.println("Choose a currency to convert to:");
        System.out.println("1. USD");
		System.out.println("2. Euro (EUR)");
        System.out.println("3. British Pound (GBP)");
        System.out.println("4. Indian Rupees (INR)");

        int oc = getValidCurrency("Enter the output currency:", scanner);

        double Camount = convertCurrency(amount, getExchangeRate(ic, oc));

        System.out.printf("%.2f %s is equal to %.2f %s%n",
                amount, getCurrencyName(ic),
                Camount, getCurrencyName(oc));

        scanner.close();
    }

    private static int getValidCurrency(String prompt, Scanner scanner) {
        int currency = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.println(prompt);

            if (scanner.hasNextInt()) {
                currency = scanner.nextInt();
                if (currency >= 1 && currency <= 4) {
                    isValid = true;
                } else {
                    System.out.println("Invalid currency. Please choose a valid option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid numerical value.");
                scanner.next(); // Consume the invalid input
            }
        }

        return currency;
    }

    private static double getValidAmount(Scanner scanner) {
        double amount = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Enter the amount in the input currency: ");

            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                isValid = true;
            } else {
                System.out.println("Invalid input. Please enter a valid numerical value.");
                scanner.next(); // Consume the invalid input
            }
        }

        return amount;
    }

    private static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    private static double getExchangeRate(int ic, int oc) {
        // You would typically fetch the latest exchange rates from an external source
        // For simplicity, using fixed rates in this example
        switch (ic) {
            case 1: // USD
                switch (oc) {
                    case 1: // EUR
                        return 0.85;
                    case 2: // GBP
                        return 0.75;
                    case 3: // INR
                        return 73.5;
                }
                break;
            case 2: // EUR
                // Add cases for other input currencies if needed
                // For simplicity, assuming 1:1 conversion for EUR to other currencies
                switch (oc) {
                    case 1: // USD
                        return 1 / 0.85;
                    case 2: // GBP
                        return 1 / 1.11;
                    case 3: // INR
                        return 1 / 88.0;
                }
                break;
            // Add cases for other input currencies if needed
            // For simplicity, assuming 1:1 conversion for GBP and INR to other currencies
            case 3: // GBP
				switch (oc) {
                    case 1: // USD
                        return 1 / 0.85;
                    case 2: // EUR
                        return 1 / 0.85;
                    case 3: // INR
                        return 1 / 88.0;
                }
            case 4: // INR
                switch (oc) {
                    case 1: // USD
                        return 1 / getExchangeRate(oc, ic);
                    case 2: // EUR
                        return 1 / getExchangeRate(oc, ic);
					case 3: // GBP
						return 1 / getExchangeRate(oc, ic);
                }
                break;
        }

        return 0; // Default case, should not happen in a well-defined program
    }

    private static String getCurrencyName(int currencyCode) {
        switch (currencyCode) {
            case 1:
                return "USD";
            case 2:
                return "EUR";
            case 3:
                return "GBP";
            case 4:
                return "INR";
            default:
                return "Unknown Currency";
        }
    }
}
