/**
 * Created by googl_000 on 9/17/2014.
 */
public class LList<T> {


    public static void main(String[] args)
    {
        LListTest.runTests();
    }

    // O( n ) where n = data.length
    public LList(T... data)
    {
        this.size = 0;
        Node nextNode = null;
        for (int i = data.length - 1; i >= 0; i--)
        {
            nextNode = new Node(data[i], nextNode);

            this.size++;
        }

        firstLink = nextNode;
    }

    // O( 1 )
    private Node getFirst()
    {
        return firstLink;
    }

    // O( n )
    public Object get(int index)
    {
        Node currNode = getFirst();

        if (index >= this.size())
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for LList " + this.toString());

        for (int i = 0; i < index; i++)
        {
            currNode = currNode.next;
        }

        return currNode.data;
    }

    // O( n )
    public void add(T item)
    {
        Node loopLink = getFirst();
        while (loopLink.next != null)
            loopLink = loopLink.next;

        loopLink.setNext(new Node(item, null));

        this.size++;
    }

    // O( n )
    public void add(T item, int index)
    {
        Node currNode = this.getFirst();
        for (int i = 0; i < index - 1; i++) // gets the Node just before the index
        {
            currNode = currNode.next;
        }

        // perform insertion
        Node nextNode = currNode.next;
        currNode.next = new Node(item, nextNode);

        this.size++;
    }

    // O( 1 )
    public int size() { return this.size; }

    // O( n )
    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[this.size()]; // can't directly create an array of type T, thanks Java

        Node nextNode = this.getFirst();
        for (int i = 0; i < this.size(); i++)
        {
            result[i] = nextNode.data;
            nextNode = nextNode.next;
        }
        return result;
    }

    // O( n )
    public String toString()
    {
        StringBuilder sb = new StringBuilder().append("<LList elements=[");
        Node nextNode = this.getFirst();

        int iter = 0;
        while (nextNode != null)
        {
            sb.append(nextNode.data);
            nextNode = nextNode.next;

            if (iter < this.size() - 1)
                sb.append(", ");
            iter++;
        }

        return sb.append("]>").toString();
    }

    class Node {
        public Node(T data, Node nextNode) { this.data = data; this.next = nextNode; }

        // O( 1 )
        public String toString() {
            return new StringBuilder()
                    .append("<Node data=\"").append(this.data.toString())
                    .append("\", next_null=").append(this.next == null).append(">").toString();
        }

        // O( 1 )
        public void setNext(Node next) {
            this.next = next;
        }

        private T data;
        private Node next;
    }

    private Node firstLink;
    private int size;
}
