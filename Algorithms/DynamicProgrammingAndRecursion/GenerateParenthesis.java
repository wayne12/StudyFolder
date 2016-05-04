/*
   Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
   
   For example, given n = 3, a solution set is:
   
   "((()))", "(()())", "(())()", "()(())", "()()()"
*/


import java.util.*;
import java.lang.*;

public class GenerateParenthesis {
   public static void main(String [] args){
      GenerateParenthesis obj = new GenerateParenthesis();
      try{
      List<String> ret = obj.generateParenthesis(3);
      System.out.println(ret);
      }
      catch(Exception ex){
      }
   }

   //method 1: maybe didn't cover all cases , too complicated
    public List<String> generateParenthesis(int n) {
        if(n <= 0)
            return new LinkedList<String>();
            
        //represent each combination as list of pairs(instead of string)
        HashMap<String, List<StringBuilder>> hmap = new HashMap<String, List<StringBuilder>>();
        List<StringBuilder> lst = new LinkedList<StringBuilder>();
        
        lst.add(new StringBuilder("()")); 
        hmap.put("()", lst);// for n = 1;
        HashMap<String, List<StringBuilder>> hmap2 = new HashMap<String, List<StringBuilder>>();
        
        int idx = 2;
        while (idx <= n){
            //ListIterator liter = llst.listIterator();
            Set<Map.Entry<String, List<StringBuilder>>> entryset = hmap.entrySet();
            for(Map.Entry<String, List<StringBuilder>> entry: entryset){// for each combination
                String keyStr = entry.getKey();
                List<StringBuilder> l = entry.getValue();
                List<StringBuilder> [] temps = (List<StringBuilder> []) new List[l.size()];
                StringBuilder [] strs = new StringBuilder [l.size()];
                StringBuilder allStr = new StringBuilder();
                for(int i=0; i<l.size(); i++){
                    strs[i] = new StringBuilder();
                    temps[i] = new LinkedList<StringBuilder>();
                    allStr.append(l.get(i).toString());
                    for(int j=0; j<l.size(); j++){// deep copy list
                        temps[i].add(new StringBuilder(l.get(j)));
                    }
                }
                
                //add new combinations from the existing one
                for(int i=0; i<l.size(); i++){
                    StringBuilder strb = temps[i].get(i);                    
                    for(int j=0; j<l.size(); j++){
                        if(i != j)
                            strs[j].append(strb.toString());
                    }
                    strb.insert(0,"(");
                    strb.append(")");
                    strs[i].append(strb.toString());
                }
                for(int i=0; i<l.size(); i++){
                    hmap2.put(strs[i].toString(), temps[i]);
                }
                // add new combination of parentheses at the end
                if(!hmap2.containsKey(keyStr+"()")){
                   List<StringBuilder> temp1 = new LinkedList<StringBuilder>();
                   for(int j=0; j<l.size(); j++){// deep copy list
                        temp1.add(new StringBuilder(l.get(j)));
                    }

                   temp1.add(new StringBuilder("()"));
                   hmap2.put(keyStr+"()", temp1);
                }
                // add new combination of parentheses at the begin
                if(!hmap2.containsKey("()"+keyStr)){
                   List<StringBuilder> temp2 = new LinkedList<StringBuilder>();
                   temp2.add(new StringBuilder("()"));

                   for(int j=0; j<l.size(); j++){// deep copy list
                        temp2.add(new StringBuilder(l.get(j)));
                    }

                    hmap2.put("()"+keyStr, temp2);
                }
                
                // add new combination by adding surounding parentheses
                if(!hmap2.containsKey("("+keyStr+")")){
                   List<StringBuilder> temp3 = new LinkedList<StringBuilder>();
                   allStr.insert(0, '(');
                   allStr.append(')');
                   temp3.add(allStr);
                   hmap2.put("("+keyStr+")", temp3);
                }
            }
            hmap.clear();
            HashMap<String, List<StringBuilder>> hmap3 = hmap;
            hmap = hmap2;
            hmap2 = hmap3;
            idx++;
        }
        return new LinkedList<String>(hmap.keySet());
        
    }
    
    //method 2: similar with the above one, but covered all cases and is simpler
    /*
      n=1, it is "()"
      n=2, we need to add a new () into all locations of "()": ()(), (()) and ()(). 
         Just remember to remove duplicates. So the final result is: ()() and (())
      n=3, we will add a new () into all locations of ()() and (()) ...
      ...
      n=n...
    */
    public List<String> generateParenthesis2(int n) {
       List<String> result = new LinkedList<>();
       result.add("()");
       for (int i = 1; i < n; ++i) {
           Set<String> buffer = new HashSet<>();
           for (int j = 0; j < result.size(); ++j) {
               String str = result.get(j);
               for (int k = 0; k < str.length(); ++k) {
                       buffer.add(str.substring(0, k) + "()" + str.substring(k, str.length()));
               }
           }
           result.clear();
           result.addAll(buffer);
       }
       return result;
    }
    
    //method 3: adding '(' or ')' to the end each time, and exclude all illeage combinations.
    /*
                                             ""
                                          /      \
                                         (        ) --> (can't start with ')', exclude it)
                                      /     \
                                    /        \
                                  ()          ((
                                 /  \        /   \       
                              ()(   ())   (((     (()     ---> ( "())" should be excluded)
                                        
    
    */
    
    //code from https://leetcode.com/discuss/55826/java-dfs-way-solution
    public List<String> generateParenthesis3(int n) {
       ArrayList<String> list = new ArrayList<String>();
       Stack<String> stack = new Stack<String>();
       Stack<Integer> validationStack = new Stack<Integer>();
       stack.push("(");
       validationStack.push(0);
       while(stack.size() != 0)
       {
           String s = stack.pop();
           int v = validationStack.pop();
           if(s.length() == n * 2)
           {
               list.add(s);
               continue;
           }
           if(s.length() - v < n) //exclude wrong combinations
           {
               stack.push(s + "(");
               validationStack.push(v);
           }
           if(2 * v < s.length()) // exclude wrong combinations
           {
               stack.push(s + ")");
               validationStack.push(v+1);
           }
       }
       return list;
   }
   //using recursion to add '(' or ')' at the end.
   //from https://leetcode.com/discuss/55826/java-dfs-way-solution
   
   //method 4.1: recursion 1
   public List<String> generateParenthesis41(int n) {
       List<String> list = new ArrayList<String>();
       generateOneByOne("", list, n, n);
       return list;
   }
   public void generateOneByOne(String sublist, List<String> list, int left, int right){
       if(left > right){
           return;
       }
       if(left > 0){
           generateOneByOne( sublist + "(" , list, left-1, right);
       }
       if(right > 0){
           generateOneByOne( sublist + ")" , list, left, right-1);
       }
       if(left == 0 && right == 0){
           list.add(sublist);
           return;
       }
   }
   //method 4.2: recursion 2, improved 4.1
   public List<String> generateParenthesis42(int n) 
   {
       List<String> result = new LinkedList<String>();
       if (n > 0) generateParenthesisCore("", n, n, result); 
       return result;
   }
   
   private void generateParenthesisCore(String prefix, int left, int right, List<String> result)
   {
       if (left == 0 && right == 0) result.add(prefix);
       // Has left Parenthesis    
       if (left > 0) generateParenthesisCore(prefix+'(', left-1, right, result);
       // has more right Parenthesis
       if (left < right) generateParenthesisCore(prefix+')', left, right-1, result);
   }
   
   //method 5: Dynamic programming solution
   // from https://leetcode.com/discuss/11509/an-iterative-method
   //  and https://leetcode.com/discuss/55826/java-dfs-way-solution
   
   /* Dynamic programming logic
      f(0): ""
   
      f(1): "("f(0)")"f(0)
      
      f(2): "("f(0)")"f(1), "("f(1)")"f(0)
      
      f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"f(0)
   
      So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")f(0)"
      
      Remove quotations:

      f(n) = (f(0))f(n-1) + (f(1))f(n-2) + ... + (f(n-2))f(1) + (f(n-1))f(0)
   */
   public static List<String> generateParenthesis5(int n) {
       List<String>[]  result = new List[n+1];
       result[0] = Arrays.asList("");
       for(int i=1; i<=n; i++){
           List<String> r = new ArrayList<String>();
           for(int j=0; j<i; j++){
               for(String sl : result[j])
                   for(String sr : result[i-j-1])
                       r.add("(" + sl + ")" + sr);
           }
           result[i] = r;
       }
       return result[n];
   }
   /*
      proof:
         
         1 When n = 0 the algorithm is obviously right.
   
         2 For n > 0
         
         2.1 Assume for i <= n-1 are right.
         
         2.2 If there's one combination is not covered. Say string s. 
            S must start with "(", we will find the matching ")", 
            so the string will be partitioned into: "(" + S1 + ") + S2, assume S1 has x "(", 
            and S2 has y "(",(x, y <= n-1), as the algorithm combines all the previous result for x and y. 
            So if S is not covered, there must be S1 or S2 is not covered in previous step for x or y.
            Which is contradict with condition 2.1
         
         3 Prove it's unique: assume there are two Strings in the result are the same. 
            They can be both represented as "(" + S1 + ") + S2, so they will be generated in the same inner loop.
            As inner loop iterate previous result, so it's not possible has duplicates.
         
   */
}