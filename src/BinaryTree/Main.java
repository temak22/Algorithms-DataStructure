package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree tree = new BinaryTree();
        tree.insert(new Person(50, "Peter"));
        tree.insert(new Person(75, "Mike"));
        tree.insert(new Person(25, "John"));
        tree.insert(new Person(3, "John"));
        tree.insert(new Person(60, "Jack"));
        tree.insert(new Person(65, "George"));
        tree.insert(new Person(90, "Lucas"));

        Node found = tree.find(60);
        if (found != null)
            System.out.println("Found the node with this key");
        else
            System.out.println("Could not find node with this key");

        tree.delete(75);

        List<Person> sortList = tree.getSortList(tree.getRoot(), new ArrayList<>());
        System.out.println(sortList);
    }
}
