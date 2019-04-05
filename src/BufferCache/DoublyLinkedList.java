package BufferCache;
class Node{
    // �����Ͱ� ����� �ʵ�
    Object data;
    // ���� ��带 ����Ű�� �ʵ�
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
    // ù��° ��带 ����Ű�� �ʵ�
    private Node head;
    private Node tail;
    private int size = 0;
    public DoublyLinkedList() {
    	this.head=null;
    	this.tail=null;
    }
    public void addFirst(Object input) {//����Ʈ ó���� ��� �߰�
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
    public void addLast(Object input) {//����Ʈ �������� ��� �߰�
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
            // k ��° ��带 temp2�� �����մϴ�.
            Node temp2 = temp1.next;
            // ���ο� ��带 �����մϴ�.
            Node newNode = new Node(input);
            // temp1�� ���� ���� ���ο� ��带 �����մϴ�.
            temp1.next = newNode;
            // ���ο� ����� ���� ���� temp2�� �����մϴ�.
            newNode.next = temp2;
            // temp2�� ���� ���� ���ο� ��带 �����մϴ�.
            if (temp2 != null)
                temp2.prev = newNode;
            // ���ο� ����� ���� ���� temp1�� �����մϴ�.
            newNode.prev = temp1;
            size++;
    	}
    }
    public void overWrite(int k, Object input) {//����ٴ� �����̹Ƿ� k��° ���� �ݵ�� �����ϹǷ� k��° �߰� �� k+1����
    	add(k,input);
    	remove(k+1);
    		
    }
    public Node node(int index) {//�ε����� ��� ����
    	// ����� �ε����� ��ü ��� ���� �ݺ��� ū�� ������ ���
        if (index < size / 2) {
            // head���� next�� �̿��ؼ� �ε����� �ش��ϴ� ��带 ã���ϴ�.
            Node x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            // tail���� prev�� �̿��ؼ� �ε����� �ش��ϴ� ��带 ã���ϴ�.
            Node x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    public Object removeFirst() {
        // ù��° ��带 temp�� �����ϰ� head�� ���� �ι�° ���� �����մϴ�.
        Node temp = head;
        head = temp.next;
        // �����͸� �����ϱ� ���� ������ ���� �ӽ� ������ ����ϴ�.
        Object returnData = temp.data;
        temp = null;
        // ����Ʈ ���� ��尡 �ִٸ� head�� ���� ��带 null�� �����մϴ�.
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
        // k-1��° ��带 temp�� �����մϴ�.
        Node temp = node(k - 1);
        // temp.next�� �����ϱ� ���� todoDeleted ������ �����մϴ�.
        Node todoDeleted = temp.next;
        // ���� ��� ��带 ���ῡ�� �и��մϴ�.
        temp.next = temp.next.next;
        if (temp.next != null) {
            // ������ ����� ���� ��带 �����մϴ�.
            temp.next.prev = temp;
        }
        // ������ ����� �����͸� �����ϱ� ���ؼ� returnData�� �����͸� �����մϴ�.
        Object returnData = todoDeleted.data;
        // ������ ��尡 tail�̾��ٸ� tail�� ���� ��带 tail�� �����մϴ�.
        if (todoDeleted == tail) {
            tail = temp;
        }
        // cur.next�� ���� �մϴ�.
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
        // Ž�� ����� �Ǵ� ��带 temp�� �����մϴ�.
        Node temp = head;
        // Ž�� ����� ���° ������Ʈ�� �ִ����� �ǹ��ϴ� ������ index�� ����մϴ�.
        int index;
        // Ž�� ���� Ž�� ����� ���� ���մϴ�.
       for(index=0;index<size;index++) {
    	   if(temp.data==data)
    		   return index;
    	   temp=temp.next;
       }
        // Ž�� ����� ã�Ҵٸ� ����� �ε��� ���� �����մϴ�.
        return -1;
    }
    public int getSize() {//����Ʈ�� ����� ����
    	return size;
    }
    public void print() {//����Ʈ�� ���¸� �������
    	Node n=head;
    	for(int i=0;i<size;i++) {
    		System.out.print(n.data+" ");
    		n=n.next;
    	}
    }

}
