/*
* Author: Hongwei Lu
* Description: 
* Design and implement a data structure for Least Recently Used (LRU) cache. 
* It should support the following operations: get and set.
* get(key) - Get the value (will always be positive) of the key
* if the key exists in the cache, otherwise return -1.
* set(key, value) - Set or insert the value if the key is not already present. 
* When the cache reached its capacity, it should invalidate the least recently used 
* item before inserting a new item.
*
*/

public class LRUCache {
     public class Node{
      int key;
      int value;
      Node pre;
      Node next;
      Node chainNext;
      public Node(){}
      public Node(int k, int val){
         key = k;
         value = val;         
      }
   }
   
   public static void main(String[] args){
      LRUCache lrumap = new LRUCache(1);
      System.out.println("Capacity is: "+ lrumap.Capacity);

      lrumap.set(2,1);
       System.out.println(lrumap.get(2));
      lrumap.set(3,2);
      //System.out.println(lrumap.get(2));
      System.out.println(lrumap.get(3));
   }
   
   //define the Capacity
   int Capacity;
   int curCap = 0;
   
   //define data structures
   
   
   //double linked list for priority
   Node head , tail;

   
   //define a hash map
   Node [] map;
   

    public LRUCache(int capacity) {
        Capacity = capacity;
        map = new Node [Capacity];
        for(int i=0; i<Capacity; i++){
         map[i] = new Node();
        }
      
        head = new Node();
        tail = new Node();
        head.pre = tail;
        head.next = tail;
        tail.pre = head;
        tail.next = head;
    }
    
    public int get(int key) {
        int mapKey = key % Capacity;
      Node curNode = map[mapKey];
      while(curNode.chainNext != null){
         curNode = curNode.chainNext;
         if(curNode.key == key){
            updataPriority(curNode);            
            return curNode.value;
         }
      }
      //not found key
      return -1;

    }
    
    public void set(int key, int value) {
        int mapKey = key % Capacity;

      Node curNode = map[mapKey];
      while(curNode.chainNext != null){//conflict
         curNode = curNode.chainNext;
         if(curNode.key == key){//update
            curNode.value = value;
            updataPriority(curNode);
            return;
         }
      }
      //new node,  check capacity
      if(curCap >= Capacity){//remove one is LRU
         Node lastN = tail.pre;
         removeFromChain(lastN);
         tail.pre = lastN.pre;
         lastN.pre.next = tail;
      }
      curNode = map[mapKey];
      //in case current node was removed
      while(curNode.chainNext != null)
         curNode = curNode.chainNext;
         
      {//insert when curNode.chainNext is empty
         Node temp = new Node(key,value);
         curNode.chainNext = temp;
         temp.next = head.next;
         temp.pre = head;
         head.next.pre = temp;
         head.next = temp;
         curCap += 1;  
         return;
       }

    }
    public void updataPriority(Node curNode){
      //set priority of curent node
         //remove first
      curNode.pre.next = curNode.next;
      curNode.next.pre = curNode.pre;
        //add to front
      curNode.pre = head;
      curNode.next = head.next;
      head.next.pre = curNode;
      head.next = curNode;
    }
    public void removeFromChain(Node node){
      int mapKey = node.key%Capacity;
      if(node == null)
         return;
      Node curNode = map[mapKey];
      while(curNode.chainNext != node && curNode.chainNext != null){
         curNode = curNode.chainNext;
      }
      if(curNode.chainNext == node){
         curNode.chainNext = node.chainNext;
         curCap--;
      }
   }
}