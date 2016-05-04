/****
 *     time complexity for merge sort
 *     T(n) = 2T(n/2) + Theta(n)
 *     which is O(nlgn)
 * }
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class SortList {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode sortList(ListNode head) {
        if(head == null||head.next == null)
            return head;
        ListNode mid = head.next, temp = head.next, pre = head;
        //time: Theta(n) for search for middle point
        while(temp != null && temp.next!= null)
        {
            pre = mid;
            mid = mid.next;
            temp = temp.next.next;
        }
        pre.next = null;
        //time: T(n/2) for recursion on left part of list
        ListNode front = sortList(head);
        //time T(n/2) for recursion on right part of list
        ListNode rear = sortList(mid);
        //time: Theta(n) for merge
        return merge(front, rear);
    }
    ListNode merge(ListNode first, ListNode second){
        if(first == null)
            return second;
        if(second == null)
            return first;
        ListNode head = first;
        if(first.val > second.val)
            head = second;
        ListNode pre = null;
        while(first !=null && second != null){
            if(first.val <= second.val){
                pre = first;
                first = first.next;
            }
            else{
                ListNode temp = second.next;
                second.next = first;
                if(pre != null)
                    pre.next = second;
                pre = second;
                second = temp;
            }
        }
        if(first == null)
            pre.next = second;
        return head;
    }
}