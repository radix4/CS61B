public class Palindrome {

    /** Returns an array of characters from String parameter word. */
    public Deque<Character> wordToDeque(String word) {
        Deque deque = new LinkedListDeque<>();
        char[] charArray = word.toCharArray();
        // from the internet, convert a string into an array of characters

        for (int i = 0; i < charArray.length; i++) {
            deque.addLast(charArray[i]);
        }

        return deque;
    }

    /** Returns parameter String word backwards. */
    public Deque<Character> reverseDeque(String word){
        Deque deque = new LinkedListDeque<>();
        char[] charArray = word.toCharArray();
        // from the internet, convert a string into an array of characters

        for (int i = 0; i < charArray.length; i++) {
            deque.addFirst(charArray[i]);
        }

        return deque;
    }

    /** Returns true if given word is a palindrome, false otherwise. */
    public boolean isPalindrome(String word) {
        if (word.isEmpty()) {
            return true;
        }


        Deque a = wordToDeque(word);
        Deque b = wordToDeque(word);

        for (int i = 0; i < word.length(); i++) {
            if (!a.removeFirst().equals(b.removeLast())){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        System.out.println(p.isPalindrome(""));
    }

}
