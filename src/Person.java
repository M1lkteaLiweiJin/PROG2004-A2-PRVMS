
public class Person {

    private String id;       // Unique identifier (employee number/tourist number)
    private String name;     // Name
    private int age;         // age

    public Person() {}

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        if (age > 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("Error: Age must be between 1-120!");
        }
    }

    @Override
    public String toString() {
        return "ID:" + id + ", Name:" + name + ", age:" + age;
    }
    
}
