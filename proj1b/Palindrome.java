public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque test = new ArrayDeque();
        for (int i = 0; i < word.length(); i += 1) {
            test.addLast(word.charAt(i));
        }
        return test;
    }

    /* Iterative version of isPalindrome. */
    public boolean isPalindrome(String word) {
        if(word.length() <= 1) {
            return true;
        }
        for(int i = 0; i < word.length() / 2; i += 1) {
            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /* Recursive version of isPalindrome. */
    public boolean IsPalindrome(String word) {
        Deque d = wordToDeque(word);
        return IsPalindromeHelper(d);
    }

    public boolean IsPalindromeHelper(Deque d) {
        if (d.size() <= 1) {
            return true;
        }   else if (d.removeFirst() != d.removeLast()) {
            return false;
        }   else
            return IsPalindromeHelper(d);
    }

    /* An overloaded version of isPalindrome (for OffByOne/OffByN palindrome). */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if(word.length() <= 1) {
            return true;
        }
        for(int i = 0; i < word.length() / 2; i += 1) {
            if(!cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1))) {
                return false;
            }
        }
        return true;
    }


}
