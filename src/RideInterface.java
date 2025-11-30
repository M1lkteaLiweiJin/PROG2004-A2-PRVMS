public interface RideInterface {
    // Queue Management
    void addVisitorToQueue(Visitor visitor); // Add tourists to the queue
    void removeVisitorFromQueue();          // Remove tourists from the queue
    void printQueue();                      // Print queue

    // Ride history management
    void addVisitorToHistory(Visitor visitor); // Add to History
    boolean checkVisitorFromHistory(Visitor visitor); // Check if it exists in the history
    int numberOfVisitors(); // Return historical tourist numbers
    void printRideHistory(); // print history

    // Operating cycle
    void runOneCycle(); // Run one amusement cycle

}
