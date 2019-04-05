package BufferCache;
class Node{
    // 데이터가 저장될 필드
    Object data;
    // 다음 노드를 가리키는 필드
    Node next;
    Node prev;
 
    public Node(Object input) {
        this.data = input;
        this.next = null;
        this.prev = null;
    }
    public Node() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }
}

public class DoublyLinkedList {
    // 첫번째 노드를 가리키는 필드
    private Node head;
    private Node tail;
    private int size = 0;
    public DoublyLinkedList() {
    	this.head=null;
    	this.tail=null;
    }
    public void addFirst(Object input) {//리스트 처음에 노드 추가
		Node newNode=new Node(input);
    	if(size==0) {
    		head=newNode;
    		head.next=newNode;
    		head.prev=newNode;
    		tail=head;
    	}
    	else {
    		head.prev=newNode;
    		newNode.next=head;
    		head=newNode;
    		newNode.prev=tail;
    		tail.next=head;
    	}
    	size++;
    }
    public void addLast(Object input) {//리스트 마지막에 노드 추가
    	Node newNode=new Node(input);
    	if(size==0) {
    		tail=newNode;
    		tail.next=newNode;
    		tail.prev=newNode;
    		head=tail;
    	}
    	else {
    		tail.next=newNode;
    		newNode.prev=tail;
    		tail=newNode;
    		newNode.next=head;
    		head.prev=tail;
    	}
    	size++;
    }
    public void add(int k, Object input) {
    	if(k==0)
    		addFirst(input);
    	else if(k==size)
    		addLast(input);
    	else {
    		Node temp1 = node(k - 1);
            // k 번째 노드를 temp2로 지정합니다.
            Node temp2 = temp1.next;
            // 새로운 노드를 생성합니다.
            Node newNode = new Node(input);
            // temp1의 다음 노드로 새로운 노드를 지정합니다.
            temp1.next = newNode;
            // 새로운 노드의 다음 노드로 temp2를 지정합니다.
            newNode.next = temp2;
            // temp2의 이전 노드로 새로운 노드를 지정합니다.
            if (temp2 != null)
                temp2.prev = newNode;
            // 새로운 노드의 이전 노드로 temp1을 지정합니다.
            newNode.prev = temp1;
            size++;
    	}
    }
    public void overWrite(int k, Object input) {//덮어쓴다는 개념이므로 k번째 노드는 반드시 존재하므로 k번째 추가 후 k+1삭제
    	add(k,input);
    	remove(k+1);
    		
    }
    public Node node(int index) {//인덱스로 노드 추출
    	// 노드의 인덱스가 전체 노드 수의 반보다 큰지 작은지 계산
        if (index < size / 2) {
            // head부터 next를 이용해서 인덱스에 해당하는 노드를 찾습니다.
            Node x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            // tail부터 prev를 이용해서 인덱스에 해당하는 노드를 찾습니다.
            Node x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    public Object removeFirst() {
        // 첫번째 노드를 temp로 지정하고 head의 값을 두번째 노드로 변경합니다.
        Node temp = head;
        head = temp.next;
        // 데이터를 삭제하기 전에 리턴할 값을 임시 변수에 담습니다.
        Object returnData = temp.data;
        temp = null;
        // 리스트 내에 노드가 있다면 head의 이전 노드를 null로 지정합니다.
        if (head != null)
            head.prev = null;
        size--;
        return returnData;
    }
    public void MakeEmpty() {
    	head=null;
    	tail=null;
    	size=0;
    }
    public Object remove(int k) {
        if (k == 0)
            return removeFirst();
        // k-1번째 노드를 temp로 지정합니다.
        Node temp = node(k - 1);
        // temp.next를 삭제하기 전에 todoDeleted 변수에 보관합니다.
        Node todoDeleted = temp.next;
        // 삭제 대상 노드를 연결에서 분리합니다.
        temp.next = temp.next.next;
        if (temp.next != null) {
            // 삭제할 노드의 전후 노드를 연결합니다.
            temp.next.prev = temp;
        }
        // 삭제된 노드의 데이터를 리턴하기 위해서 returnData에 데이터를 저장합니다.
        Object returnData = todoDeleted.data;
        // 삭제된 노드가 tail이었다면 tail을 이전 노드를 tail로 지정합니다.
        if (todoDeleted == tail) {
            tail = temp;
        }
        // cur.next를 삭제 합니다.
        todoDeleted = null;
        size--;
        return returnData;
    }
    public Object removeLast() {
        return remove(size - 1);
    }
    public Object get(int k) {
        Node temp = node(k);
        return temp.data;
    }
    public int indexOf(Object data) {
        // 탐색 대상이 되는 노드를 temp로 지정합니다.
        Node temp = head;
        // 탐색 대상이 몇번째 엘리먼트에 있는지를 의미하는 변수로 index를 사용합니다.
        int index;
        // 탐색 값과 탐색 대상의 값을 비교합니다.
       for(index=0;index<size;index++) {
    	   if(temp.data==data)
    		   return index;
    	   temp=temp.next;
       }
        // 탐색 대상을 찾았다면 대상의 인덱스 값을 리턴합니다.
        return -1;
    }
    public int getSize() {//리스트의 사이즈를 리턴
    	return size;
    }
    public void print() {//리스트의 상태를 출력해줌
    	Node n=head;
    	for(int i=0;i<size;i++) {
    		System.out.print(n.data+" ");
    		n=n.next;
    	}
    }

}
