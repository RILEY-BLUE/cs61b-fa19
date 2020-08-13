import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @ClassName TestOffByN
 * @Author Yurui Du
 * @Date 20200813 14:00
 * @Version 0.0
 */
public class TestOffByN {
    static OffByN offBy5 = new OffByN(5);

    @Test
    public void testequalChars() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertFalse(offBy5.equalChars('x', 'x'));
    }

    @Test
    public void testOffByNPalindrome() {
        Palindrome palindrome = new Palindrome();
        assertTrue(palindrome.isPalindrome("flaqa", offBy5));
    }
}