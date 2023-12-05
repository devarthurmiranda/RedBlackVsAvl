package Tests;

import java.util.Random;

import ArrayOpener.Opener;
import Avl.AvlTree;
import RedBlack.RedBlackTree;

public class Main {
    public static void main(String[] args) {
        int[] arrayOne = Opener.openArchive("dados100_mil.txt");
        int[] arrayTwo = Opener.openArchive("dados100_mil.txt");
        Random random = new Random();
        int randomNumber;
        //
        long startTime = System.currentTimeMillis();
        AvlTree avl = new AvlTree();
        for(int i = 0; i < arrayOne.length; i++){
            avl.root = avl.insert(avl.root, arrayOne[i]);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("\n\nInsertion time for AVL Tree: " + (endTime - startTime) + "ms");
        //
        startTime = System.currentTimeMillis();
        RedBlackTree redBlack = new RedBlackTree();
        for(int i = 0; i < arrayOne.length; i++){
            redBlack.insert(arrayTwo[i]);
        }
        endTime = System.currentTimeMillis();
        System.out.println("\n\nInsertion time for Red Black Tree: " + (endTime - startTime) + "ms");

        /*
         * Random draw of another 50,000 numbers between -9999 and 9999. 
         * If the drawn number is a multiple of 3, insert that number into the tree, 
         * if the drawn number is a multiple of 5, remove that number from the tree. 
         * If it is not a multiple of 3 or 5, count how many times this number appears in the tree.
         */
        startTime = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            randomNumber = random.nextInt(19999) - 9999;
            if(randomNumber % 3 == 0){
                avl.root = avl.insert(avl.root, randomNumber);
            } else if(randomNumber % 5 == 0){
                avl.root = avl.delete(avl.root, randomNumber);
            } else {
                avl.search(avl.root, randomNumber);
            }
            
        }
        endTime = System.currentTimeMillis();
        System.out.println("\n\nTime for AVL Tree: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for(int i = 0; i < 50000; i++){
            randomNumber = random.nextInt(19999) - 9999;
            if(randomNumber % 3 == 0){
                redBlack.insert(randomNumber);
            } else if(randomNumber % 5 == 0){
                redBlack.delete(randomNumber);
            } else {
                redBlack.search(randomNumber);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("\n\nTime for Red Black Tree: " + (endTime - startTime) + "ms");
    }
}
