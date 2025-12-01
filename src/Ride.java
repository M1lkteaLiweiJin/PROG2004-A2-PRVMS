import java.io.*;
import java.util.*;

/**
 * Ride class implementing RideInterface
 * Manages core ride functionalities: queue, history, operations, and file I/O
 */
public class Ride implements RideInterface {
    // ------------------------------ Core Attributes (Part 1) ------------------------------
    private String rideId;       // Unique ride identifier (e.g., "R001")
    private String rideName;     // Ride name (e.g., "Roller Coaster", "Ferris Wheel")
    private Employee operator;   // Assigned operator (controls ride availability)
    private int maxRider;        // Maximum riders per cycle (minimum: 1)
    private int numOfCycles;     // Total number of cycles operated (default: 0)

    // ------------------------------ Collection Attributes (Part 3/4) ------------------------------
    private Queue<Visitor> waitingQueue;    // FIFO queue for waiting visitors (LinkedList implementation)
    private LinkedList<Visitor> rideHistory;// Historical records of riders (supports Iterator)

    // ------------------------------ Constructors ------------------------------
    /**
     * Default constructor: Initializes collections and default values
     */
    public Ride() {
        this.waitingQueue = new LinkedList<>();  // LinkedList for efficient queue operations
        this.rideHistory = new LinkedList<>();   // LinkedList for Iterator support
        this.numOfCycles = 0;                    // Start with 0 operated cycles
    }

    /**
     * Parameterized constructor: Initializes all core attributes
     * @param rideId Unique ride ID
     * @param rideName Ride name
     * @param operator Assigned employee operator
     * @param maxRider Maximum riders per cycle
     */
    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this();  // Call default constructor to initialize collections
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        // Enforce minimum 1 rider per cycle
        this.maxRider = maxRider >= 1 ? maxRider : 1;
    }

    // ------------------------------ Getter/Setter Methods ------------------------------
    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = maxRider >= 1 ? maxRider : 1;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    // ------------------------------ Queue Management Implementation (Part 3) ------------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[" + rideName + "] Error: Cannot add null visitor to queue!");
            return;
        }
        waitingQueue.offer(visitor);  // Use offer() for safe queue addition
        System.out.println("[" + rideName + "] Successfully added visitor to queue: " + visitor.getName());
    }

    @Override
    public void removeVisitorFromQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[" + rideName + "] Error: Queue is empty - cannot remove visitor!");
            return;
        }
        Visitor removedVisitor = waitingQueue.poll();  // Remove and return front of queue
        System.out.println("[" + rideName + "] Removed visitor from queue: " + removedVisitor.getName());
    }

    @Override
    public void printQueue() {
        System.out.println("\n[" + rideName + "] Waiting Queue Status:");
        if (waitingQueue.isEmpty()) {
            System.out.println("  Queue is empty");
            return;
        }

        System.out.println("  Total visitors in queue: " + waitingQueue.size());
        System.out.println("  Visitor List (FIFO Order):");
        int index = 1;
        // Iterate through queue to print visitors
        for (Visitor visitor : waitingQueue) {
            System.out.printf("    %d. ID: %s | Name: %s | Age: %d | Ticket: %s | Date: %s%n",
                    index++, visitor.getId(), visitor.getName(), visitor.getAge(),
                    visitor.getTicketType(), visitor.getVisitDate());
        }
    }

    // ------------------------------ Ride History Management Implementation (Part 4A) ------------------------------
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[" + rideName + "] Error: Cannot add null visitor to history!");
            return;
        }
        rideHistory.add(visitor);
        System.out.println("[" + rideName + "] Successfully added visitor to history: " + visitor.getName());
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[" + rideName + "] Error: Cannot check history for null visitor!");
            return false;
        }

        // Check history by visitor ID (unique identifier)
        for (Visitor v : rideHistory) {
            if (v.getId().equals(visitor.getId())) {
                System.out.println("[" + rideName + "] Visitor found in history: " + visitor.getName());
                return true;
            }
        }
        System.out.println("[" + rideName + "] Visitor NOT found in history: " + visitor.getName());
        return false;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("[" + rideName + "] Total visitors in history: " + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n[" + rideName + "] Ride History Status:");
        if (rideHistory.isEmpty()) {
            System.out.println("  Ride history is empty");
            return;
        }

        System.out.println("  Total historical visitors: " + rideHistory.size());
        System.out.println("  Visitor List (Iterator Traversal):");
        int index = 1;
        // Mandatory Iterator usage (assignment requirement)
        Iterator<Visitor> iterator = rideHistory.iterator();
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.printf("    %d. ID: %s | Name: %s | Age: %d | Ticket: %s | Date: %s%n",
                    index++, visitor.getId(), visitor.getName(), visitor.getAge(),
                    visitor.getTicketType(), visitor.getVisitDate());
        }
    }

    // ------------------------------ Ride History Sorting (Part 4B) ------------------------------
    /**
     * Sorts the ride history using the custom VisitorComparator
     * @param comparator Custom comparator for Visitor sorting
     */
    public void sortRideHistory(VisitorComparator comparator) {
        if (comparator == null) {
            System.out.println("[" + rideName + "] Error: Cannot sort - comparator is null!");
            return;
        }
        if (rideHistory.isEmpty()) {
            System.out.println("[" + rideName + "] Error: Cannot sort - ride history is empty!");
            return;
        }

        Collections.sort(rideHistory, comparator);
        System.out.println("[" + rideName + "] Ride history sorted successfully (Age → Name)");
    }

    // ------------------------------ Ride Cycle Operation (Part 5) ------------------------------
    @Override
    public void runOneCycle() {
        System.out.println("\n[" + rideName + "] Attempting to start one ride cycle...");

        // 1. Validate operator availability
        if (operator == null || !operator.isAvailable()) {
            System.out.println("[" + rideName + "] Error: Cannot start cycle - no available operator!");
            return;
        }

        // 2. Validate non-empty queue
        if (waitingQueue.isEmpty()) {
            System.out.println("[" + rideName + "] Error: Cannot start cycle - queue is empty!");
            return;
        }

        // 3. Board riders (up to maxRider) and update history
        int ridersBoarded = 0;
        while (!waitingQueue.isEmpty() && ridersBoarded < maxRider) {
            Visitor rider = waitingQueue.poll();
            addVisitorToHistory(rider);
            ridersBoarded++;
        }

        // 4. Update cycle count
        numOfCycles++;

        // 5. Print cycle completion details
        System.out.println("[" + rideName + "] Ride cycle completed successfully!");
        System.out.printf("  - Riders boarded: %d (Max capacity: %d)%n", ridersBoarded, maxRider);
        System.out.printf("  - Total cycles operated: %d%n", numOfCycles);
    }

    // ------------------------------ File Export (Part 6) ------------------------------
    /**
     * Exports ride history to a CSV file
     * @param filePath Path to save the CSV file (e.g., "ride_history.csv")
     */
    public void exportRideHistory(String filePath) {
        if (rideHistory.isEmpty()) {
            System.out.println("[" + rideName + "] Error: Cannot export - ride history is empty!");
            return;
        }

        // Try-with-resources to auto-close the writer (resource management)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write each visitor as a CSV line
            for (Visitor visitor : rideHistory) {
                writer.write(visitor.toString());
                writer.newLine();  // New line for each visitor
            }
            System.out.println("[" + rideName + "] Successfully exported history to: " + filePath);
        } catch (IOException e) {
            System.out.println("[" + rideName + "] Export failed: " + e.getMessage());
        }
    }

    // ------------------------------ File Import (Part 7) ------------------------------
    /**
     * Imports ride history from a CSV file
     * @param filePath Path to the CSV file (e.g., "ride_history.csv")
     */
    public void importRideHistory(String filePath) {
        // Check if file exists
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("[" + rideName + "] Error: Import failed - file not found: " + filePath);
            return;
        }

        int importedCount = 0;
        // Try-with-resources to auto-close the reader (resource management)
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read file line by line
            while ((line = reader.readLine()) != null) {
                // Parse CSV line to Visitor object
                Visitor visitor = Visitor.fromCsvString(line);
                if (visitor != null) {
                    rideHistory.add(visitor);
                    importedCount++;
                } else {
                    System.out.println("[" + rideName + "] Skipping invalid CSV line: " + line);
                }
            }
            System.out.println("[" + rideName + "] Import completed! Total visitors imported: " + importedCount);
        } catch (IOException e) {
            System.out.println("[" + rideName + "] Import failed: " + e.getMessage());
        }
    }
}