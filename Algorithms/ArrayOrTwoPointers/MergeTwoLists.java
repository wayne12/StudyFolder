
/*
   Merge two sorted linked lists and return it as a new list. 
   The new list should be made by splicing together the nodes of the first two lists.
*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        
        ListNode l3 = new ListNode(0);
        ListNode node1 = l1, node2 = l2, node3 = l3;
        
        while(node1 != null && node2 != null){
            if(node1.val < node2.val){
                node3.next = node1;
                node3 = node1;
                node1 = node1.next;
            }
            else{
                node3.next = node2;
                node3 = node2;
                node2 = node2.next;
            }
        }
        node3.next = node1 == null? node2:node1;
        return l3.next;
    }
}