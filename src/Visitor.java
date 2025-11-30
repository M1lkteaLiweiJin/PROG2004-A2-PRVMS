/**
 * 游客类，继承自Person
 * 用于跟踪主题公园游客信息，支持CSV格式解析（文件IO用）
 */
public class Visitor extends Person {
    // 2个专属实例变量（符合游客管理需求）
    private String ticketType;  // 门票类型（如：单日票、学生票、年卡）
    private String visitDate;   // 参观日期（格式：YYYY-MM-DD）

    // 无参构造器
    public Visitor() {}

    // 带参构造器（初始化父类属性+子类专属属性）
    public Visitor(String id, String name, int age, String ticketType, String visitDate) {
        super(id, name, age);  // 调用父类构造器
        this.ticketType = ticketType;
        this.visitDate = visitDate;
    }

    // Getter方法
    public String getTicketType() {
        return ticketType;
    }

    public String getVisitDate() {
        return visitDate;
    }

    // Setter方法
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    // 重写toString为CSV格式（用于文件导出/导入）
    @Override
    public String toString() {
        return super.toString() + "," + ticketType + "," + visitDate;
    }

    // 静态方法：从CSV字符串解析为Visitor对象（文件导入用）
    public static Visitor fromCsvString(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            return null;
        }
        // 按逗号分割CSV字段
        String[] parts = csvLine.trim().split(",");
        // 验证字段数（必须包含5个字段：ID、姓名、年龄、门票类型、参观日期）
        if (parts.length != 5) {
            return null;
        }
        try {
            // 解析字段（年龄需转为int）
            String id = parts[0];
            String name = parts[1];
            int age = Integer.parseInt(parts[2]);
            String ticketType = parts[3];
            String visitDate = parts[4];
            // 创建并返回Visitor对象
            return new Visitor(id, name, age, ticketType, visitDate);
        } catch (NumberFormatException e) {
            // 年龄解析失败（非数字）
            return null;
        }
    }
}