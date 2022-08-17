package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BinaryTree {
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //inOrder
    public List<Person> getSortList(Node localRoot, List<Person> personList) {
        if (localRoot != null) {
            personList = getSortList(localRoot.leftChild, personList);
            personList.add(localRoot.getPerson());
            personList = getSortList(localRoot.rightChild, personList);
        }
        return personList;
    }

    public Node find(int key) {
        Node current = root;
        while (current.getPerson().getId() != key) {
            if (key < current.getPerson().getId())
                current = current.leftChild;
            else
                current = current.rightChild;

            if (current == null)
                return null;
        }
        return current;
    }

    public void insert(Person person) {
        Node newNode = new Node(person);
        if (root == null)
            root = newNode;
        else {
            Node current = root;
            while (true) {
                if (newNode.getPerson().getId() < current.getPerson().getId()) {
                    if (current.leftChild == null) {
                        current.leftChild = newNode;
                        return;
                    }
                    current = current.leftChild;
                }
                else {
                    if (current.rightChild == null) {
                        current.rightChild = newNode;
                        return;
                    }
                    current = current.rightChild;
                }
            }
        }
    }

    public boolean delete(int key) throws Exception {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;

        //search node for delete
        while (current.getPerson().getId() != key) {
            parent = current;
            if (key < current.getPerson().getId()) {
                isLeftChild = true;
                current = current.leftChild;
            }
            else {
                isLeftChild = false;
                current = current.rightChild;
            }

            if (current == null)
                return false;
        }

        //case 1: no children for this node
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)
                root = null; //delete whole tree
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }

        //case 2: 1 child for this node
        else if (current.rightChild == null) {
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        }

        else if (current.leftChild == null) {
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        }

        //case 3: 2 children for this node
        else {
            Node successor = getSuccessor(current);

            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
            successor.leftChild = current.leftChild;
        }

        return true;
    }

    //for case 3 in deleting
    private Node getSuccessor(Node deleteNode) throws Exception {
        Node successorParent = deleteNode;
        Node successor = deleteNode.rightChild;

        if (successor ==  null)
            throw new Exception("Right child must exist!");

        while (successor.leftChild != null) {
            successorParent = successor;
            successor = successor.leftChild;
        }

        if (successor != deleteNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = deleteNode.rightChild;
        }

        return successor;
    }

}
