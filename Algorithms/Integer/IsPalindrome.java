public class IsPalindrome {

    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        if(x < 10)
            return true;
        int reverse = 0;
        int temp = x;
        while(temp != 0){
            reverse = reverse*10 + temp%10;
            temp = temp/10;
        }
        if(reverse == x)
            return true;
        return false;
    }
    
    public boolean isPalindrome2(int x) {
        if(x<0 || x%10 == 0)
            return false;
        if(x < 10)
            return true;
        int reverse = 0;
        while(x != 0){
            reverse = reverse*10 + x%10;
            if(reverse == x || reverse == x/10)
                return true;
            x = x/10;
            if(reverse > x)
                return false;
        }
        return false;
    }
    //a better version of this would be
    public boolean isPalindrome3(int x) {
    if (x<0 || (x!=0 && x%10==0)) return false;
    int reverse = 0;
    while (x>reverse){
        reverse = reverse*10 + x%10;
        x = x/10;
    }
    return (x==rev || x==rev/10);
}
}