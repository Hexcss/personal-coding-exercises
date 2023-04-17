import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * The Main class runs the program to check if a word is a palindrome.
 */
public class Main {
    public static void main(String[] args) {
        // Use CachedPalindromeChecker for efficient palindrome checking
        PalindromeChecker palindromeChecker = new CachedPalindromeChecker(new RecursivePalindromeChecker());
        UserIO userIO = new ConsoleUserIO();

        // Repeatedly get input from the user and display the result
        while (true) {
            String word = userIO.getInput();
            boolean isPalindrome = palindromeChecker.isPalindrome(word);
            userIO.showResult(word, isPalindrome);
        }
    }
}

/**
 * The UserIO interface defines the methods for getting input from the user and showing the result.
 */
interface UserIO {
    String getInput();

    void showResult(String word, boolean isPalindrome);
}

/**
 * The ConsoleUserIO class implements the UserIO interface for console-based input and output.
 */
class ConsoleUserIO implements UserIO {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String getInput() {
        System.out.println("Input your word: ");
        return scanner.nextLine();
    }

    @Override
    public void showResult(String word, boolean isPalindrome) {
        System.out.printf("Is %s a palindrome? %b %s", word, isPalindrome, System.lineSeparator());
    }
}

/**
 * The PalindromeChecker interface defines the method for checking if a word is a palindrome.
 */
interface PalindromeChecker {
    boolean isPalindrome(String word);
}

/**
 * The CachedPalindromeChecker class implements the PalindromeChecker interface and
 * caches previously checked palindromes for faster subsequent checks.
 */
class CachedPalindromeChecker implements PalindromeChecker {
    private final Set<String> palindromes = new HashSet<>();
    private final PalindromeChecker delegate;

    public CachedPalindromeChecker(PalindromeChecker delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean isPalindrome(String word) {
        if (palindromes.contains(word)) {
            return true;
        }
        boolean result = delegate.isPalindrome(word);
        if (result) {
            palindromes.add(word);
        }
        return result;
    }
}

/**
 * The RecursivePalindromeChecker class implements the PalindromeChecker interface
 * using a recursive approach to check if a word is a palindrome.
 */
class RecursivePalindromeChecker implements PalindromeChecker {
    @Override
    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        if (word.charAt(0) == word.charAt(word.length() - 1)) {
            return isPalindrome(word.substring(1, word.length() - 1));
        }
        return false;
    }
}
