/**
 * Abstract base class representing a general person in the theme park
 * Encapsulates common attributes (ID, name, age) and behaviors for all personnel
 * Cannot be instantiated directly - serves as parent for Employee and Visitor
 */
public abstract class Person {
    // Private instance variables (encapsulation principle)
    private String id;         // Unique identifier (employee ID/visitor ID)
    private String name;       // Full name of the person
    private int age;           // Age (valid range: 1-120)

    // Default constructor
    public Person() {}

    // Parameterized constructor to initialize all common attributes
    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter methods (controlled access to private variables)
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setter methods with data validation
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        // Validate age range to ensure data integrity
        if (age > 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("Error: Invalid age! Please enter a value between 1 and 120.");
        }
    }

    // Override toString for CSV-compatible output (used in file I/O)
    @Override
    public String toString() {
        return id + "," + name + "," + age;
    }
}