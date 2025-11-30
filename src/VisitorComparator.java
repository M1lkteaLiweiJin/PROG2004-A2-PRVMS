import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 1. 先按年龄比较（主要条件）
        int ageCompare = Integer.compare(v1.getAge(), v2.getAge());
        if (ageCompare != 0) {
            return ageCompare; // 年龄不同，直接返回结果
        }
        // 2. 年龄相同，按姓名字典序比较（次要条件，忽略大小写）
        return v1.getName().compareToIgnoreCase(v2.getName());
    }
}

