/**
 * Created by googl_000 on 9/17/2014.
 */
public class LList<T> {

    private static class LListTest
    {
        public static void runTests()
        {
            new LListTest().testAll();
        }
        private void testAll()
        {
            testGet();
            testToString();
            testAdd();
            testToArray();
        }

        private LList<String> generateLList()
        {
            return new LList<String>("ele1", "ele2", "ele3", "ele4");
        }

        private void testGet()
        {
            LList<String> llist = generateLList();
            System.out.println("Get element at position 1: " + llist.get(1));
        }

        private void testToString()
        {
            LList<String> llist = generateLList();
            System.out.println("LList.toString() output: " + llist.toString());
        }

        private void testAdd()
        {
            LList<String> llist = generateLList();

            // Add element to the end of the list
            llist.add("new_element_at_end");
            System.out.println("Add element to the end of the list: " + llist.toString());

            // Insert element at index
            llist.add("new_element_at_index_2", 2);
            System.out.println("Add element at index 2: " + llist.toString());
        }

        private void testToArray()
        {
            LList<String> llist = generateLList();

            // Test the toArray method
            StringBuilder arraySB = new StringBuilder();
            Object[] llistArr = llist.toArray();
            for (int i = 0; i < llistArr.length; i++ ) {
                arraySB.append(llistArr[i]);
                if (i < llistArr.length - 1) arraySB.append(", ");
            }
            System.out.println("LList.toArray(): " + arraySB.toString());
        }
    }
    public static void main(String[] args)
    {
        LListTest.runTests();
    }

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
    private Node getFirst()
    {
        return firstLink;
    }
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

    public void add(T item)
    {
        Node loopLink = getFirst();
        while (loopLink.next != null)
            loopLink = loopLink.next;

        loopLink.setNext(new Node(item, null));

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
