public class Employee extends Person {
    // 2个专属实例变量（符合员工管理需求）
    private String role;        // 职位（如：游乐设施操作员、维修员）
    private boolean isAvailable;// 是否可用（用于判断能否运营游乐设施）

    // 无参构造器
    public Employee() {}

    // 带参构造器（初始化父类属性+子类专属属性）
    public Employee(String id, String name, int age, String role, boolean isAvailable) {
        super(id, name, age);  // 调用父类构造器初始化通用属性
        this.role = role;
        this.isAvailable = isAvailable;
    }

    // Getter方法
    public String getRole() {
        return role;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter方法
    public void setRole(String role) {
        this.role = role;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // 重写toString，包含员工专属信息
    @Override
    public String toString() {
        return super.toString() + "," + role + "," + (isAvailable ? "可用" : "不可用");
    }
}
