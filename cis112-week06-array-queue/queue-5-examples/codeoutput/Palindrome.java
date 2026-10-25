import java.util.ArrayDeque;
import java.util.Deque;

/** A palindrome read with a deque: take one letter from each end. */
public class Palindrome {
    static boolean isPalindrome(String s) {
        Deque<Character> d = new ArrayDeque<>();
        for (char c : s.toCharArray())
            if (Character.isLetter(c)) d.addLast(Character.toLowerCase(c));
        while (d.size() > 1)
            if (!d.removeFirst().equals(d.removeLast())) return false;
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"ey edip adanada pide ye", "kucuk", "merhaba", "Küçük"};
        for (String w : words) {
            String result = isPalindrome(w) ? "palindrome" : "not a palindrome";
            System.out.println("\"" + w + "\" -> " + result);
        }
        System.out.println("note: case, spaces and punctuation are ignored");
    }
}
