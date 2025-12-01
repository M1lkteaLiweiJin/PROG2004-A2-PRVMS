/**
 * Visitor class extending Person
 * Tracks theme park visitors and supports CSV parsing for file I/O operations
 */
public class Visitor extends Person {
    // Unique attributes for visitors
    private String ticketType;  // Ticket type (e.g., "Single Day", "Student", "Annual Pass")
    private String visitDate;   // Visit date (format: YYYY-MM-DD)

    // Default constructor
    public Visitor() {}

    // Parameterized constructor (initializes parent and child attributes)
    public Visitor(String id, String name, int age, String ticketType, String visitDate) {
        super(id, name, age);  // Call parent class constructor
        this.ticketType = ticketType;
        this.visitDate = visitDate;
    }

    // Getter methods
    public String getTicketType() {
        return ticketType;
    }

    public String getVisitDate() {
        return visitDate;
    }

    // Setter methods
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    // Override toString for CSV-compatible output (used in export/import)
    @Override
    public String toString() {
        return super.toString() + "," + ticketType + "," + visitDate;
    }

    /**
     * Static method to parse a CSV string into a Visitor object
     * Used for importing visitor data from CSV files
     * @param csvLine Single line of CSV data (format: ID,Name,Age,TicketType,VisitDate)
     * @return Visitor object if parsing succeeds, null otherwise
     */
    public static Visitor fromCsvString(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            return null;
        }

        String[] parts = csvLine.trim().split(",");
        // Validate CSV format (must contain exactly 5 fields)
        if (parts.length != 5) {
            return null;
        }

        try {
            String id = parts[0];
            String name = parts[1];
            int age = Integer.parseInt(parts[2]);  // Parse age to integer
            String ticketType = parts[3];
            String visitDate = parts[4];
            return new Visitor(id, name, age, ticketType, visitDate);
        } catch (NumberFormatException e) {
            // Return null if age is not a valid integer
            return null;
        }
    }
}