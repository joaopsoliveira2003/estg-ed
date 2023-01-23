import Collections.Trees.AVLTree;
import Collections.Trees.AVLTreeADT;

import java.util.Iterator;
public class TestIterators {

    public static void main(String[] args) {


        AVLTreeADT<String> tree = new AVLTree<>();
        tree.addElement("A");
        tree.addElement("B");
        tree.addElement("C");
        tree.addElement("D");
        tree.addElement("E");

        System.out.println("ITERATOR IN ORDER");
        Iterator<String> it = tree.iteratorInOrder();
        while (it.hasNext()) {
            System.out.println(it.next());
        }


        System.out.println("ITERATOR LEVEL ORDER");
        Iterator<String> it2 = tree.iteratorLevelOrder();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }


        System.out.println("ITERATOR POST ORDER");
        Iterator<String> it3 = tree.iteratorPostOrder();
        while (it3.hasNext()) {
            System.out.println(it3.next());
        }


        System.out.println("ITERATOR PRE ORDER");
        Iterator<String> it4 = tree.iteratorPreOrder();
        while (it4.hasNext()) {
            System.out.println(it4.next());
        }








    }
}
