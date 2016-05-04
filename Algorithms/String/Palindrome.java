public class Palindrome {
    public boolean isPalindrome(String s) {
        if(s.isEmpty())
            return true;
        char [] chs = s.toCharArray();
        int len = chs.length;
        int front = 0, rear = len-1;
        for(int i=0; i<len; ++i){
            while(front < len && !Character.isLetterOrDigit(chs[front]))
                front++;
            while(rear >= 0 && !Character.isLetterOrDigit(chs[rear]))
                rear--;
            if(front >= rear)
                break;
            if(Character.toLowerCase(chs[front++]) != Character.toLowerCase(chs[rear--]))
                return false;
        }
        return true;
    }
}