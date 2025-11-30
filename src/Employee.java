public class Employee extends Person{

    private String role;       // Position (such as' amusement facility operator ')
    private boolean isAvailable; // Is it available (responsible for operation)

    public Employee() {}

    public Employee(String id, String name, int age, String role, boolean isAvailable) {
        super(id, name, age); // Initialize the properties of Person
        this.role = role;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return super.toString() + ", position:" + role + ", Available status:" + (isAvailable ? "yes" : "no");
    }


}

