import java.util.Comparator;

/**
 * Custom comparator for sorting Visitor objects
 * Sorts by age (ascending) first, then by name (alphabetical, case-insensitive)
 * Used for sorting ride history (Part 4B)
 */
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // Validate input to avoid null pointer exceptions
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Visitor objects cannot be null");
        }

        // Primary sort: Age (ascending order)
        int ageComparison = Integer.compare(v1.getAge(), v2.getAge());
        if (ageComparison != 0) {
            return ageComparison;
        }

        // Secondary sort: Name (alphabetical, case-insensitive)
        return v1.getName().compareToIgnoreCase(v2.getName());
    }
}