package the.green.one.game;

/**
 *
 * @author muhab
 * @param <T>
 */
public interface LinkedList<T> {

    public class Node<T> {

        T data;
        Node next;
    }

    public void add(T val);

    public void add(int index, T val);

    public void addFirst(T val);

    public int size();

    public boolean isEmpty();

    public void print();

    public T getElementAt(int index);

    public Node getNodeAt(int index);

    public boolean isContain(T val);

    public void clear();

    public T deleteLast();

    public T deleteFirst();

    public boolean deleteVal(T val);

    public void insertAfter(Node prevNode, T val);

    public void transferNext();
}
