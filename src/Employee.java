/**
 * Employee class extending Person
 * Tracks theme park ride operators and their availability for ride operations
 */
public class Employee extends Person {
    // Unique attributes for employees
    private String role;        // Job role (e.g., "Ride Operator", "Maintenance Technician")
    private boolean isAvailable;// Availability status (true = can operate rides)

    // Default constructor
    public Employee() {}

    // Parameterized constructor (initializes parent and child attributes)
    public Employee(String id, String name, int age, String role, boolean isAvailable) {
        super(id, name, age);  // Call parent class constructor
        this.role = role;
        this.isAvailable = isAvailable;
    }

    // Getter methods
    public String getRole() {
        return role;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter methods
    public void setRole(String role) {
        this.role = role;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Override toString for detailed employee information
    @Override
    public String toString() {
        return super.toString() + "," + role + "," + (isAvailable ? "Available" : "Unavailable");
    }
}