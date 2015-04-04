import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

class TreeNode {
    public TreeNode parent;
    public List<TreeNode> children;
    public char myLetter;

    public TreeNode(char letter) {
        this.children = new LinkedList<TreeNode>();
        this.parent = null;
        this.myLetter = letter;
    }

    public void addChild(TreeNode child) {
        child.parent = this;
        children.add(child);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TreeNode child : children) { // is there no #join method in java??
            sb.append(child.toString());
            sb.append(", ");
        }
        return "<" + myLetter + " children: [" + sb.toString() + "]>";
    }

}
public class TreeIsomorphism {

    public static void puts(String s) {
        System.err.println(s); // Sam figured out that outputting using System.err is ignored by UMD's output equivalence checker
    }
    static TreeNode parseTree(String tree) {
        TreeNode root = new TreeNode(tree.charAt(0));
        TreeNode curr = root;

        for (int i = 1; i < tree.length(); i++) {
            if (tree.charAt(i) != '#' && tree.charAt(i) != ' ') { // I knew that you had to check for ' ' chars and it STILL messed me up!
                TreeNode tn = new TreeNode(tree.charAt(i));
                curr.addChild(tn);
                curr = tn;
            } else if (tree.charAt(i) == '#') {
                curr = curr.parent;
            }
        }
        return root;
    }

    static boolean treeEquals(List<TreeNode> nodes1, List<TreeNode> nodes2) {
        if (nodes1.size() != nodes2.size()) { return false; }
        if (nodes2.size() == 0) { return true; }

        for (int i = 0; i < nodes1.size(); i++) {
            if (!treeEquals(nodes1.get(i).children, nodes2.get(i).children)) // originally had nodes2 in both, so it would always return true!
                return false;
        }
        return true;
    }

    static List<List<TreeNode>> permutations(List<TreeNode> children) { // had trouble figuring out when it should be List<TreeNode> and when it should be List<List<TreeNode>>
        List<List<TreeNode>> perms = new LinkedList<List<TreeNode>>(); // the fact that writing (TreeNode root).children is equivalent to List<TreeNode> was confusing, wasn't sure if root nodes should be passed in.
        if (children.size() == 1) {
            List<List<TreeNode>> l = new LinkedList<List<TreeNode>>();
            l.add(children);
            return l;
        } else {
            for (int i = 0; i < children.size(); i++) {
                TreeNode locked = children.get(i);
                List<TreeNode> copied = new LinkedList<TreeNode>(children);
                copied.remove(i);
                List<List<TreeNode>> childPerms = permutations(copied);
                for (List<TreeNode> childNodes : childPerms) {
                    childNodes.add(0, locked);
                    perms.add(childNodes);
                }
            }
            return perms;
        }
    }

    private static boolean solveTreeIsomorphism(String one, String two) {
        puts(Boolean.toString(treeEquals(parseTree("abcd##e##").children, parseTree("hijkm##l##").children)));
        /* *
         *
         * The procedure should return true if the two trees (represented by one, and two) are isomorphic to each other.
         *
         * */

        TreeNode root1 = parseTree(one);
        TreeNode root2 = parseTree(two);

        puts(root1.toString());
        puts(root2.toString());
        for (List<TreeNode> permutation : permutations(root2.children)) {
            if (treeEquals(root1.children, permutation)) {
                return true;
            }
        }

        /* -------------------- END OF INSERTION --------------------*/

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Scanner sc = new Scanner(br.readLine());
        int numTests = sc.nextInt();

        for (int i = 0; i < numTests; i++) {
            String line1 = br.readLine();
            String line2 = br.readLine();

            System.out.println("The two trees are " + (solveTreeIsomorphism(line1, line2) ? "isomorphic." : "not isomorphic."));
        }
        br.close();
    }
}
