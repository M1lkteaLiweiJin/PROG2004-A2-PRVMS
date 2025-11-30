import java.io.*;
import java.util.*;

/**
 * 游乐设施类，实现RideInterface接口
 * 整合队列管理、乘坐历史、运行周期、文件IO等核心功能
 */
public class Ride implements RideInterface {
    // ------------------------------ 基础属性（Part1）------------------------------
    private String rideId;       // 游乐设施ID（如：R001）
    private String rideName;     // 游乐设施名称（如：过山车、摩天轮）
    private Employee operator;   // 操作员（Employee类型，判断是否开放）
    private int maxRider;        // 单周期最大载客量（≥1，Part5）
    private int numOfCycles;     // 累计运行次数（默认0，Part5）

    // ------------------------------ 集合属性（Part3/4）------------------------------
    private Queue<Visitor> waitingQueue;    // 等待队列（FIFO，存储待乘坐游客）
    private LinkedList<Visitor> rideHistory;// 乘坐历史（存储已乘坐游客，支持Iterator）

    // ------------------------------ 构造器 ------------------------------
    /**
     * 无参构造器：初始化集合和默认值
     */
    public Ride() {
        this.waitingQueue = new LinkedList<>();  // Queue用LinkedList实现（高效）
        this.rideHistory = new LinkedList<>();   // 乘坐历史用LinkedList（支持Iterator）
        this.numOfCycles = 0;                    // 初始运行次数为0
    }

    /**
     * 带参构造器：初始化所有核心属性
     * @param rideId 游乐设施ID
     * @param rideName 游乐设施名称
     * @param operator 操作员
     * @param maxRider 单周期最大载客量
     */
    public Ride(String rideId, String rideName, Employee operator, int maxRider) {
        this();  // 调用无参构造器初始化集合
        this.rideId = rideId;
        this.rideName = rideName;
        this.operator = operator;
        // 验证最大载客量（≥1）
        this.maxRider = maxRider >= 1 ? maxRider : 1;
    }

    // ------------------------------ Getter/Setter ------------------------------
    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = maxRider >= 1 ? maxRider : 1;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    // ------------------------------ 队列管理实现（Part3）------------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[" + rideName + "] 错误：游客对象为空，无法添加到队列！");
            return;
        }
        waitingQueue.offer(visitor);  // Queue的offer()方法：添加失败返回false（安全）
        System.out.println("[" + rideName + "] 成功添加游客到队列：" + visitor.getName());
    }

    @Override
    public void removeVisitorFromQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[" + rideName + "] 错误：等待队列为空，无法移除游客！");
            return;
        }
        Visitor removedVisitor = waitingQueue.poll();  // 移除并返回队首游客
        System.out.println("[" + rideName + "] 从队列移除游客：" + removedVisitor.getName());
    }

    @Override
    public void printQueue() {
        System.out.println("\n[" + rideName + "] 等待队列状态：");
        if (waitingQueue.isEmpty()) {
            System.out.println("  队列为空");
            return;
        }
        System.out.println("  队列人数：" + waitingQueue.size());
        System.out.println("  游客列表：");
        int index = 1;
        // 遍历队列（FIFO顺序）
        for (Visitor visitor : waitingQueue) {
            System.out.println("    " + index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------ 乘坐历史管理实现（Part4A）------------------------------
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[" + rideName + "] 错误：游客对象为空，无法添加到历史！");
            return;
        }
        rideHistory.add(visitor);
        System.out.println("[" + rideName + "] 成功添加游客到乘坐历史：" + visitor.getName());
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[" + rideName + "] 错误：游客对象为空，无法查询历史！");
            return false;
        }
        // 按游客ID判断（ID唯一，避免对象引用问题）
        for (Visitor v : rideHistory) {
            if (v.getId().equals(visitor.getId())) {
                System.out.println("[" + rideName + "] 游客" + visitor.getName() + "存在于乘坐历史中");
                return true;
            }
        }
        System.out.println("[" + rideName + "] 游客" + visitor.getName() + "不存在于乘坐历史中");
        return false;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("[" + rideName + "] 乘坐历史总人数：" + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n[" + rideName + "] 乘坐历史状态：");
        if (rideHistory.isEmpty()) {
            System.out.println("  乘坐历史为空");
            return;
        }
        System.out.println("  历史人数：" + rideHistory.size());
        System.out.println("  游客列表（Iterator遍历）：");
        int index = 1;
        // 必须用Iterator遍历（作业要求，否则无分）
        Iterator<Visitor> iterator = rideHistory.iterator();
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println("    " + index + ". " + visitor);
            index++;
        }
    }

    // ------------------------------ 乘坐历史排序（Part4B）------------------------------
    /**
     * 对乘坐历史中的游客进行排序
     * @param comparator 自定义比较器（VisitorComparator）
     */
    public void sortRideHistory(VisitorComparator comparator) {
        if (comparator == null) {
            System.out.println("[" + rideName + "] 错误：比较器为空，无法排序！");
            return;
        }
        if (rideHistory.isEmpty()) {
            System.out.println("[" + rideName + "] 乘坐历史为空，无需排序！");
            return;
        }
        // 使用Collections.sort() + 自定义比较器排序
        Collections.sort(rideHistory, comparator);
        System.out.println("[" + rideName + "] 乘坐历史排序完成（按年龄升序→姓名字典序）");
    }

    // ------------------------------ 运行游乐周期（Part5）------------------------------
    @Override
    public void runOneCycle() {
        System.out.println("\n[" + rideName + "] 尝试运行一个周期...");
        // 1. 检查是否有可用操作员
        if (operator == null || !operator.isAvailable()) {
            System.out.println("[" + rideName + "] 错误：无可用操作员，无法运行！");
            return;
        }
        // 2. 检查等待队列是否有游客
        if (waitingQueue.isEmpty()) {
            System.out.println("[" + rideName + "] 错误：等待队列为空，无法运行！");
            return;
        }
        // 3. 运行周期：取出最多maxRider名游客，添加到历史
        int currentRiders = 0;  // 本次周期实际载客数
        while (!waitingQueue.isEmpty() && currentRiders < maxRider) {
            Visitor rider = waitingQueue.poll();  // 从队列移除游客
            addVisitorToHistory(rider);           // 添加到乘坐历史
            currentRiders++;
        }
        // 4. 更新累计运行次数
        numOfCycles++;
        // 5. 打印运行结果
        System.out.println("[" + rideName + "] 周期运行完成！");
        System.out.println("  本次载客：" + currentRiders + "人（最大载客量：" + maxRider + "）");
        System.out.println("  累计运行次数：" + numOfCycles);
    }

    // ------------------------------ 文件导出（Part6）------------------------------
    /**
     * 将乘坐历史导出到CSV文件
     * @param filePath 导出文件路径（如：ride_history.csv）
     */
    public void exportRideHistory(String filePath) {
        if (rideHistory.isEmpty()) {
            System.out.println("[" + rideName + "] 乘坐历史为空，无需导出！");
            return;
        }
        // try-with-resources：自动关闭流，避免资源泄漏
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 遍历乘坐历史，写入CSV文件（每行一个游客）
            for (Visitor visitor : rideHistory) {
                writer.write(visitor.toString());  // 复用Visitor的CSV格式toString
                writer.newLine();                  // 换行（区分不同游客）
            }
            System.out.println("[" + rideName + "] 成功导出乘坐历史到：" + filePath);
        } catch (IOException e) {
            // 捕获IO异常（如文件权限不足、路径不存在）
            System.out.println("[" + rideName + "] 导出文件失败：" + e.getMessage());
        }
    }

    // ------------------------------ 文件导入（Part7）------------------------------
    /**
     * 从CSV文件导入乘坐历史
     * @param filePath 导入文件路径（如：ride_history.csv）
     */
    public void importRideHistory(String filePath) {
        // 检查文件是否存在
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("[" + rideName + "] 错误：导入文件不存在：" + filePath);
            return;
        }
        // 记录导入成功的数量
        int importedCount = 0;
        // try-with-resources：自动关闭流
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 逐行读取文件
            while ((line = reader.readLine()) != null) {
                // 解析CSV行为Visitor对象
                Visitor visitor = Visitor.fromCsvString(line);
                if (visitor != null) {
                    rideHistory.add(visitor);  // 导入成功，添加到历史
                    importedCount++;
                } else {
                    // 跳过无效行（格式错误）
                    System.out.println("[" + rideName + "] 跳过无效CSV行：" + line);
                }
            }
            // 打印导入结果
            System.out.println("[" + rideName + "] 导入完成！共导入" + importedCount + "名游客");
        } catch (IOException e) {
            // 捕获IO异常
            System.out.println("[" + rideName + "] 导入文件失败：" + e.getMessage());
        }
    }
}