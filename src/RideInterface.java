/**
 * 游乐设施接口，定义游乐设施的核心行为规范
 * 强制Ride类实现所有抽象方法，确保功能完整性
 */
public interface RideInterface {
    // ------------------------------ 队列管理（Part3）------------------------------
    /**
     * 添加游客到等待队列
     * @param visitor 待添加的游客对象
     */
    void addVisitorToQueue(Visitor visitor);

    /**
     * 从等待队列移除队首游客（FIFO）
     */
    void removeVisitorFromQueue();

    /**
     * 打印等待队列中所有游客的详细信息
     */
    void printQueue();

    // ------------------------------ 乘坐历史管理（Part4）------------------------------
    /**
     * 添加游客到乘坐历史
     * @param visitor 待添加的游客对象
     */
    void addVisitorToHistory(Visitor visitor);

    /**
     * 检查游客是否存在于乘坐历史中
     * @param visitor 待检查的游客对象
     * @return true=存在，false=不存在
     */
    boolean checkVisitorFromHistory(Visitor visitor);

    /**
     * 返回乘坐历史中的游客总数
     * @return 游客总数
     */
    int numberOfVisitors();

    /**
     * 打印乘坐历史中所有游客的详细信息（必须用Iterator）
     */
    void printRideHistory();

    // ------------------------------ 运行周期（Part5）------------------------------
    /**
     * 运行游乐设施一个周期
     * 逻辑：检查操作员→检查队列→取出maxRider名游客→添加到历史→更新运行次数
     */
    void runOneCycle();
}
