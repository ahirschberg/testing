/**
 * Created by googl_000 on 9/17/2014.
 */
public class LList<T> {

    public static void main(String[] args)
    {
        LList<String> llist = new LList<String>("sudo", "rm", "-rf");
        System.out.println("Data: " + llist.get(1));
        //System.out.println("Data: " + llist.get(4));
        llist.add("echo lel");
        System.out.println("Data: " + llist.get(3));
        System.out.println(llist.toString());
        llist.add("bash crap", 2);
        System.out.println(llist.toString());

        // Test the toArray method
        StringBuilder arraySB = new StringBuilder();
        Object[] llistArr = llist.toArray();
        for (int i = 0; i < llistArr.length; i++ ) {
            arraySB.append(llistArr[i]);
            if (i < llistArr.length - 1) arraySB.append(", ");
        }
        System.out.println(arraySB.toString());
    }

    public LList(T... data)
    {
        this.size = 0;
        Node nextNode = null;
        for (int i = data.length - 1; i >= 0; i--)
        {
            System.out.println("Setting nextNode to node with data " + data[i] + " and next " + nextNode);
            nextNode = new Node(data[i], nextNode);

            this.size++;
        }

        firstLink = nextNode;
    }
    private Node getFirst()
    {
        return firstLink;
    }
    public Object get(int index)
    {
        Node currLink = getFirst();

        if (index >= this.size())
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for LList " + this.toString());

        for (int i = 0; i < index; i++)
        {
            System.out.println(currLink);
            currLink = currLink.next;
        }

        return currLink.data;
    }

    public void add(T item)
    {
        Node loopLink = getFirst();
        while (loopLink.next != null)
            loopLink = loopLink.next;

        loopLink.setNext(new Node(item, null));
        System.out.println("loopLink " + loopLink.toString());

        this.size++;
    }

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

    public int size() { return this.size; }

    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[this.size()];

        Node nextNode = this.getFirst();
        for (int i = 0; i < this.size(); i++)
        {
            result[i] = nextNode.data;
            nextNode = nextNode.next;
        }
        return result;
    }

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

        public String toString() {
            return new StringBuilder()
                    .append("<Node data=\"").append(this.data.toString())
                    .append("\", next_null=").append(this.next == null).append(">").toString();
        }

        public void setNext(Node next) {
            this.next = next;
        }

        private T data;
        private Node next;
    }

    private Node firstLink;
    private int size;
}
