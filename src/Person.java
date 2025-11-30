public abstract class Person {
    // 3个通用实例变量（私有，符合封装原则）
    private String id;         // 唯一标识（员工号/游客号）
    private String name;       // 姓名
    private int age;           // 年龄

    // 无参构造器（默认初始化）
    public Person() {}

    // 带参构造器（初始化所有通用属性）
    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter方法（读取属性值）
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setter方法（修改属性值，添加数据验证）
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        // 验证年龄合理性（1-120岁）
        if (age > 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("年龄输入无效！请输入1-120之间的数字。");
        }
    }

    // 重写toString方法，便于打印人员信息（后续队列/历史展示用）
    @Override
    public String toString() {
        return id + "," + name + "," + age;
    }
}
