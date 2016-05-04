/*
   Given a linked list, remove the nth node from the end of list and return its head.
   
   For example,
   
      Given linked list: 1->2->3->4->5, and n = 2.
   
      After removing the second node from the end, the linked list becomes 1->2->3->5.
   Note:
   Given n will always be valid.
   Try to do this in one pass.
*/
import java.util.HashMap;

public class RemoveFromEnd {

  //Definition for singly-linked list.
     public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }
 
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n==0)
            return null;
        
        HashMap<Integer, ListNode> nodesMap = new HashMap<Integer, ListNode>();
        ListNode node = head;
        int index=0;
        while(node != null){
            nodesMap.put(index, node);
            node = node.next;
            index++;
        }
        if(n == index){
            return head.next;
        }
        //(index-n)th from front
        ListNode pre = nodesMap.get(index-n-1);
        if(pre == null)//shouldn't happen, since n will always be valid
            return null;
        pre.next = pre.next.next;
        return head;
    }
    //another solution: using two pointers
    public ListNode removeNthFromEnd2(ListNode head, int n){
      if(head == null || n == 0)
         return null;
      ListNode slow = head, fast = head;
      int index = 1;
      //using <=n here to make pointer fast moves to (n+1)th node from front
      //then pointer slow is the previous pointer of the target note when fast arrives end
      while(index <= n){
         fast = fast.next;
         index++;
      }
      if(fast == null)//when n is the length of the list
         return head.next;
         
      while(fast.next != null){
         slow = slow.next;
         fast = fast.next;
      }
      slow.next = slow.next.next;
      return head;
    }
}