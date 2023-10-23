
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UnitConverter {

    // Global constants for exit and report options
    private static final int OPTION_EXIT = 0;
    private static final int OPTION_REPORT = 1;

    public static void main(String[] args) {
        // Initialize conversion factors using HashMap
        Map<String, Double> conversionFactors = initializeConversionFactors();

        // Get user input
        try (Scanner scanner = new Scanner(System.in)) {
            int userOption;

            do {
                // Display menu
                displayMenu();
                // Get user option
                userOption = scanner.nextInt();

                if (userOption == OPTION_EXIT) {
                    // User chose to exit
                    System.out.println("Exiting the unit converter. Goodbye!");
                    break;
                } else if (userOption == OPTION_REPORT) {
                    // Generate and display the report
                    generateReport(conversionFactors);
                } else {
                    // Process conversion
                    performConversion(scanner, conversionFactors);
                }
            } while (true);
        }
    }

    private static Map<String, Double> initializeConversionFactors() {
        // Initialize and return conversion factors using HashMap
        Map<String, Double> conversionFactors = new HashMap<>();
        conversionFactors.put("kilometers", 1.0);
        conversionFactors.put("meters", 0.001);
        conversionFactors.put("inches", 3.28084);
        conversionFactors.put("feet", 39.3701);

        return conversionFactors;
    }

    private static void displayMenu() {
        // Display user menu
        System.out.println("\n=== Unit Converter Menu ===");
        System.out.println("1. Perform Conversion");
        System.out.println("2. Generate Report");
        System.out.println("0. Exit");
        System.out.println("Enter your choice (0, 1, or 2):");
    }

    private static void performConversion(Scanner scanner, Map<String, Double> conversionFactors) {
        System.out.println("Enter the value to convert:");
        double inputValue = scanner.nextDouble();

        System.out.println("Enter the unit to convert from (e.g., meters, kilometers, feet, inches):");
        String fromUnit = scanner.next().toLowerCase();

        System.out.println("Enter the unit to convert to (e.g., meters, kilometers, feet, inches):");
        String toUnit = scanner.next().toLowerCase();

        // Perform conversion
        double convertedValue = convert(inputValue, fromUnit, toUnit, conversionFactors);

        // Display the result
        System.out.println(inputValue + " " + fromUnit + " is equal to " + convertedValue + " " + toUnit);
    }

    private static double convert(double value, String fromUnit, String toUnit, Map<String, Double> conversionFactors) {
        // Check if units are valid
        if (!conversionFactors.containsKey(fromUnit) || !conversionFactors.containsKey(toUnit)) {
            System.out.println("Invalid units. Please enter valid units.");
            System.exit(1);
        }

        double fromFactor = conversionFactors.get(fromUnit);
        double toFactor = conversionFactors.get(toUnit);

        return value * (fromFactor / toFactor);
    }

    private static void generateReport(Map<String, Double> conversionFactors) {
        // Display the conversion factors report
        System.out.println("=== Conversion Factors Report ===");
        for (Map.Entry<String, Double> entry : conversionFactors.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}