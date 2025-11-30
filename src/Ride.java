public class Ride implements RideInterface{

    private String rideId;    // Amusement equipment ID
    private String rideName;  // Name (such as "roller coaster")
    private Employee operator; // Operator (determine if it is open)

    // New variables (maximum passenger capacity per cycle, number of operations)
    private int maxRider;     // Maximum number of passengers per cycle (≥ 1)
    private int numOfCycles;  // Number of runs (default 0)

    // Set variables (queue, history)
    private Queue<Visitor> waitingQueue; // Waiting queue (FIFO)
    private LinkedList<Visitor> rideHistory; // Riding history

    // No parameter constructor
    public Ride() {
        this.waitingQueue = new LinkedList<>(); // Queue用LinkedList实现
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0; // 默认运行0次
    }

    // Constructor with parameters
    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this(); // Call the parameterless constructor to initialize the collection
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = maxRider;
    }

    public void setMaxRider(int maxRider) {
        if (maxRider >= 1) {
            this.maxRider = maxRider;
        } else {
            System.out.println("Error: Single cycle passenger capacity must be ≥ 1!");
        }
    }

    // Add tourists to the queue
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingQueue.offer(visitor); // Queue的添加方法（比add更安全）
            System.out.println("Successfully added tourist [\"+visitor. getName()+\"] to the [\"+rideName+\"] queue");
        } else {
            System.out.println("Error: The tourist object is empty and cannot be added to the queue");
        }
    }

    // Remove tourists from the queue (FIFO, remove queue leader)
    @Override
    public void removeVisitorFromQueue() {
        if (!waitingQueue.isEmpty()) {
            Visitor removed = waitingQueue.poll(); // Remove and return to the team leader
            System.out.println("Remove tourist ["+removed. getName()+"] from the ["+rideName+"] queue");
        } else {
            System.out.println("Error: [\"+rideName+\"] queue is empty, unable to remove guest");
        }
    }

    // Print all tourists in the queue
    @Override
    public void printQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[" + rideName + "]waiting queue is empty");
            return;
        }
        System.out.println("[+rideName+] Waiting queue (total of \"+waitingQueue. size()+\" people):");
        int index = 1;
        for (Visitor v : waitingQueue) { // Traverse the queue (in order of addition)
            System.out.println(index + ". " + v);
            index++;
        }
    }

    // Add tourists to ride history
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println("Successfully added tourist [\"+visitor. getName()+\"] to [\"+rideName+\"] ride history");
        } else {
            System.out.println("Error: Tourist object is empty, unable to add to history");
        }
    }

    // Check if the tourist is in history (based on ID judgment to avoid object referencing issues)
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Error: Tourist object is empty, unable to query");
            return false;
        }
        for (Visitor v : rideHistory) {
            if (v.getId().equals(visitor.getId())) { // Match by ID (unique identifier)
                System.out.println("Tourist [\"+visitor. getName()+\"] exists in [\"+rideName+\"] ride history");
                return true;
            }
        }
        System.out.println("Tourist [\"+visitor. getName()+\"] does not exist in the [\"+rideName+\"] ride history");
        return false;
    }

    // Return the total number of historical tourists
    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("[" + rideName + "]total number of passengers in history：" + count);
        return count;
    }

    // Print history
    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("[" + rideName + "]ride history is empty");
            return;
        }
        System.out.println("[\"+rideName+\"]ride history(total \"+rideHistory. size()+\" people):");
        Iterator<Visitor> iterator = rideHistory.iterator(); // generator iterator
        int index = 1;
        while (iterator.hasNext()) { // Traverse iterators
            Visitor v = iterator.next();
            System.out.println(index + ". " + v);
            index++;
        }
    }

}
