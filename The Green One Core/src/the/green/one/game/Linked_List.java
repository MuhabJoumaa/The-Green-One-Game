package the.green.one.game;

/**
 *
 * @author muhab
 * @param <T>
 */
public class Linked_List<T> implements LinkedList<T> {

    Node head;

    public Linked_List() {
        this.head = null;
    }

    @Override
    public void add(T val) {
        Node node = new Node();
        node.data = val;
        node.next = null;
        if (this.head == null) {
            this.head = node;
        } else {
            Node temp = this.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    @Override
    public void add(int index, T val) {
        if (index < 0 || index > this.size()) {
            return;
        }
        if (index == 0) {
            this.addFirst(val);
        } else {
            this.insertAfter(this.getNodeAt(index - 1), val);
        }
    }

    @Override
    public void addFirst(T val) {
        Node node = new Node();
        node.data = val;
        node.next = this.head;
        this.head = node;
    }

    @Override
    public int size() {
        Node temp = this.head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public void print() {
        if (isEmpty()) {
            throw new NullPointerException("The List Is Empty");
        }
        Node temp = this.head;
        for (int i = 0; i < size(); i++) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    @Override
    public T getElementAt(int index) {
        Node current = null;
        try {
            if (index < 0 || index > size()) {
                return null;
            } else {
                current = this.head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
            }
            return (T) current.data;
        } catch (NullPointerException ex) {
        }
        return null;
    }

    @Override
    public Node getNodeAt(int index) {
        if (index < 0 || index > size()) {
            return null;
        } else {
            Node current = this.head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }
    }

    @Override
    public boolean isContain(T val) {
        Node temp = this.head;
        boolean contain = false;
        for (int i = 0; i < size(); i++) {
            if (temp.data == val) {
                contain = true;
                break;
            }
            temp = temp.next;
        }
        return contain;
    }

    @Override
    public void clear() {
        this.head = null;
    }

    @Override
    public T deleteLast() {
        Node node = null;
        if (isEmpty()) {
            throw new NullPointerException("The List Is Empty");
        } else {
            if (this.head.next == null) {
                node = this.head;
                clear();
            } else {
                Node prevLast = this.head;
                while (prevLast.next.next != null) {
                    prevLast = prevLast.next;
                }
                node = prevLast.next;
                prevLast.next = null;
            }
        }
        return (T) node.data;
    }

    @Override
    public T deleteFirst() {
        Node node = null;
        if (isEmpty()) {
            throw new NullPointerException("The List Is Empty");
        } else {
            node = this.head;
            transferNext();
        }
        return (T) node.data;
    }

    @Override
    public boolean deleteVal(T val) {
        boolean know = false;
        if (isEmpty()) {
            throw new NullPointerException("The List Is Empty");
        } else {
            if (this.head.data == val) {
                this.head.next = null;
                this.head = null;
                know = true;
            } else {
                int i = 0;
                if (isContain(val)) {
                    know = true;
                    Node temp = this.head;
                    while (temp.next.data != val) {
                        i++;
                        temp = temp.next;
                    }
                    if (i == size()) {
                        deleteLast();
                    } else {
                        Node next_node = temp.next.next;
                        temp.next = null;
                        temp.next = next_node;
                    }
                }
            }
        }
        return know;
    }

    @Override
    public void insertAfter(Node prevNode, T val) {
        if (prevNode == null) {
            throw new NullPointerException("The Given Previous Node Cannot Be NULL");
        }
        Node newNode = new Node();
        newNode.data = val;
        newNode.next = prevNode.next;
        prevNode.next = newNode;
    }

    @Override
    public void transferNext() {
        this.head = this.head.next;
    }

}
