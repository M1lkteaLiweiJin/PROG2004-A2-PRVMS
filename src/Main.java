import java.util.Scanner;

/**
 * Interactive main class for the Theme Park Management System
 * Provides a text-based menu for manual user input and demonstration of all core functionalities
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Ride activeRide;  // Current ride being managed
    private static Employee activeOperator;  // Current operator for the ride

    public static void main(String[] args) {
        System.out.println("==================================== THEME PARK MANAGEMENT SYSTEM (PRVMS) ====================================");
        System.out.println("Welcome! This interactive system supports queue management, ride history, operations, and file I/O.");
        System.out.println("===============================================================================================================");

        // Step 1: Initialize ride and operator (user input)
        initializeRideAndOperator();

        // Step 2: Show main menu for interactive operations
        showMainMenu();

        // Cleanup
        scanner.close();
        System.out.println("\nThank you for using PRVMS! Exiting system...");
    }

    /**
     * Initializes ride and operator with user input
     */
    private static void initializeRideAndOperator() {
        System.out.println("\n------------------------------ SYSTEM INITIALIZATION ------------------------------");

        // Input operator details
        System.out.println("\n1. Enter Operator Details:");
        System.out.print("   Enter Operator ID (e.g., E001): ");
        String opId = scanner.nextLine().trim();
        System.out.print("   Enter Operator Name: ");
        String opName = scanner.nextLine().trim();
        System.out.print("   Enter Operator Age: ");
        int opAge = getValidIntegerInput();
        System.out.print("   Enter Operator Role (e.g., Ride Operator): ");
        String opRole = scanner.nextLine().trim();
        System.out.print("   Is the operator available? (Y/N): ");
        boolean opAvailable = getValidYesNoInput();

        // Create operator object
        activeOperator = new Employee(opId, opName, opAge, opRole, opAvailable);
        System.out.println("\nOperator initialized successfully: " + activeOperator.getName() + " (" + (opAvailable ? "Available" : "Unavailable") + ")");

        // Input ride details
        System.out.println("\n2. Enter Ride Details:");
        System.out.print("   Enter Ride ID (e.g., R001): ");
        String rideId = scanner.nextLine().trim();
        System.out.print("   Enter Ride Name (e.g., Roller Coaster): ");
        String rideName = scanner.nextLine().trim();
        System.out.print("   Enter Maximum Riders per Cycle (min: 1): ");
        int maxRiders = getValidIntegerInput(1, Integer.MAX_VALUE);

        // Create ride object
        activeRide = new Ride(rideId, rideName, activeOperator, maxRiders);
        System.out.println("\nRide initialized successfully: " + activeRide.getRideName() + " (Max Riders: " + maxRiders + ")");
    }

    /**
     * Displays main menu and handles user input for operations
     */
    private static void showMainMenu() {
        int choice;
        do {
            System.out.println("\n==================================== MAIN MENU ====================================");
            System.out.println("1. Queue Management (Add/Remove/Print Visitors)");
            System.out.println("2. Ride History Management (Add/Check/Print History)");
            System.out.println("3. Sort Ride History (Age → Name)");
            System.out.println("4. Run Ride Cycle");
            System.out.println("5. Export Ride History to CSV");
            System.out.println("6. Import Ride History from CSV");
            System.out.println("0. Exit System");
            System.out.print("==================================================================================");
            System.out.print("\nEnter your choice (0-6): ");
            choice = getValidIntegerInput(0, 6);

            // Handle menu choice
            switch (choice) {
                case 1:
                    handleQueueManagement();
                    break;
                case 2:
                    handleRideHistoryManagement();
                    break;
                case 3:
                    handleSortHistory();
                    break;
                case 4:
                    activeRide.runOneCycle();
                    break;
                case 5:
                    handleExportHistory();
                    break;
                case 6:
                    handleImportHistory();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 0 and 6.");
            }
        } while (choice != 0);
    }

    /**
     * Sub-menu for queue management operations
     */
    private static void handleQueueManagement() {
        int choice;
        do {
            System.out.println("\n==================================== QUEUE MANAGEMENT ====================================");
            System.out.println("1. Add Visitor to Queue");
            System.out.println("2. Remove Visitor from Queue (Front)");
            System.out.println("3. Print Queue");
            System.out.println("0. Return to Main Menu");
            System.out.print("=========================================================================================");
            System.out.print("\nEnter your choice (0-3): ");
            choice = getValidIntegerInput(0, 3);

            switch (choice) {
                case 1:
                    // Add visitor to queue (user input)
                    System.out.println("\nEnter Visitor Details for Queue:");
                    Visitor newVisitor = createVisitorFromInput();
                    if (newVisitor != null) {
                        activeRide.addVisitorToQueue(newVisitor);
                    }
                    break;
                case 2:
                    activeRide.removeVisitorFromQueue();
                    break;
                case 3:
                    activeRide.printQueue();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 0 and 3.");
            }
        } while (choice != 0);
    }

    /**
     * Sub-menu for ride history management operations
     */
    private static void handleRideHistoryManagement() {
        int choice;
        do {
            System.out.println("\n==================================== RIDE HISTORY MANAGEMENT ====================================");
            System.out.println("1. Add Visitor to History");
            System.out.println("2. Check Visitor in History");
            System.out.println("3. Print Total Historical Visitors");
            System.out.println("4. Print Full Ride History");
            System.out.println("0. Return to Main Menu");
            System.out.print("==================================================================================================");
            System.out.print("\nEnter your choice (0-4): ");
            choice = getValidIntegerInput(0, 4);

            switch (choice) {
                case 1:
                    // Add visitor to history (user input)
                    System.out.println("\nEnter Visitor Details for History:");
                    Visitor historyVisitor = createVisitorFromInput();
                    if (historyVisitor != null) {
                        activeRide.addVisitorToHistory(historyVisitor);
                    }
                    break;
                case 2:
                    // Check visitor in history (user input)
                    System.out.println("\nEnter Visitor ID to Check in History:");
                    System.out.print("Visitor ID: ");
                    String checkId = scanner.nextLine().trim();
                    // Create dummy visitor with ID (only ID is used for check)
                    Visitor checkVisitor = new Visitor(checkId, "", 0, "", "");
                    activeRide.checkVisitorFromHistory(checkVisitor);
                    break;
                case 3:
                    activeRide.numberOfVisitors();
                    break;
                case 4:
                    activeRide.printRideHistory();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 0 and 4.");
            }
        } while (choice != 0);
    }

    /**
     * Handles ride history sorting
     */
    private static void handleSortHistory() {
        System.out.println("\n==================================== SORT RIDE HISTORY ====================================");
        VisitorComparator comparator = new VisitorComparator();
        activeRide.sortRideHistory(comparator);
        // Print sorted history for verification
        System.out.println("\nSorted Ride History:");
        activeRide.printRideHistory();
    }

    /**
     * Handles exporting ride history to CSV
     */
    private static void handleExportHistory() {
        System.out.println("\n==================================== EXPORT HISTORY TO CSV ====================================");
        System.out.print("Enter file path to save CSV (e.g., ride_history.csv): ");
        String filePath = scanner.nextLine().trim();
        activeRide.exportRideHistory(filePath);
    }

    /**
     * Handles importing ride history from CSV
     */
    private static void handleImportHistory() {
        System.out.println("\n==================================== IMPORT HISTORY FROM CSV ====================================");
        System.out.print("Enter file path to import CSV (e.g., ride_history.csv): ");
        String filePath = scanner.nextLine().trim();
        activeRide.importRideHistory(filePath);
        // Print imported history for verification
        System.out.println("\nImported Ride History:");
        activeRide.printRideHistory();
    }

    /**
     * Creates a Visitor object from user input
     * @return Visitor object if input is valid, null otherwise
     */
    private static Visitor createVisitorFromInput() {
        System.out.print("   Visitor ID (e.g., V001): ");
        String id = scanner.nextLine().trim();
        System.out.print("   Visitor Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("   Visitor Age: ");
        int age = getValidIntegerInput(1, 120);
        System.out.print("   Ticket Type (e.g., Single Day): ");
        String ticketType = scanner.nextLine().trim();
        System.out.print("   Visit Date (YYYY-MM-DD): ");
        String visitDate = scanner.nextLine().trim();

        // Validate required fields
        if (id.isEmpty() || name.isEmpty() || ticketType.isEmpty() || visitDate.isEmpty()) {
            System.out.println("Error: All fields are required! Visitor not created.");
            return null;
        }

        return new Visitor(id, name, age, ticketType, visitDate);
    }

    /**
     * Helper method to get valid integer input (no range restriction)
     * @return Valid integer input
     */
    private static int getValidIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }

    /**
     * Helper method to get valid integer input within a specified range
     * @param min Minimum allowed value
     * @param max Maximum allowed value
     * @return Valid integer input within range
     */
    private static int getValidIntegerInput(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Invalid input! Please enter a number between %d and %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }

    /**
     * Helper method to get valid yes/no input (Y/N)
     * @return true for Y/y, false for N/n
     */
    private static boolean getValidYesNoInput() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            } else {
                System.out.print("Invalid input! Please enter Y (Yes) or N (No): ");
            }
        }
    }
}