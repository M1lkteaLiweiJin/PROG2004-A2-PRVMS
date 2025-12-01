/**
 * Interface defining core behaviors for theme park rides
 * Enforces implementation of queue management, ride history, and operation logic
 */
public interface RideInterface {
    // ------------------------------ Queue Management (Part 3) ------------------------------
    /**
     * Adds a visitor to the ride's waiting queue (FIFO order)
     * @param visitor Visitor object to add to the queue
     */
    void addVisitorToQueue(Visitor visitor);

    /**
     * Removes the first visitor from the waiting queue (FIFO order)
     */
    void removeVisitorFromQueue();

    /**
     * Prints all visitors in the waiting queue with detailed information
     */
    void printQueue();

    // ------------------------------ Ride History Management (Part 4A) ------------------------------
    /**
     * Adds a visitor to the ride's historical records (post-ride completion)
     * @param visitor Visitor object to add to history
     */
    void addVisitorToHistory(Visitor visitor);

    /**
     * Checks if a visitor exists in the ride's historical records
     * @param visitor Visitor object to check
     * @return true if visitor exists in history, false otherwise
     */
    boolean checkVisitorFromHistory(Visitor visitor);

    /**
     * Returns the total number of visitors in the ride's historical records
     * @return Total count of historical visitors
     */
    int numberOfVisitors();

    /**
     * Prints all visitors in the ride's historical records (uses Iterator)
     */
    void printRideHistory();

    // ------------------------------ Ride Operation (Part 5) ------------------------------
    /**
     * Runs one complete cycle of the ride
     * Logic: Validate operator availability → Check queue → Board max riders → Update history
     */
    void runOneCycle();
}