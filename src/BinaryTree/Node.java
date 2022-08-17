package BinaryTree;

public class Node {
    private Person person;
    public Node leftChild;
    public Node rightChild;

    public Node(Person person) {
        this.person = person;
        leftChild = null;
        rightChild = null;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
