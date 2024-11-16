package hva;

import java.io.Serializable;
import java.util.Comparator;

public class CaseInsensitiveComparator implements Comparator<String>, Serializable {
    @Override
    public int compare(String s1, String s2) {
        // Compare ignoring case
        int cmp = s1.compareToIgnoreCase(s2);
        
        // If two strings are case-insensitively equal,
        // place the uppercase one before the lowercase one
        if (cmp == 0) {
            return s1.compareTo(s2);
        }
        
        return cmp;
    }
}
