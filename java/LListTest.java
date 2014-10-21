/**
 * Created by googl_000 on 10/21/2014.
 */
public class LListTest
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
