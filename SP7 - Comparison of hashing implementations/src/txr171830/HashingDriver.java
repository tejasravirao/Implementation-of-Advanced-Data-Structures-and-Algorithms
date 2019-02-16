/**SP7 - HASHING
 *
 * SP7 32 - MEMBERS: ESHA PUNJABI, NET ID: ehp170000
 *                   TEJAS RAVI RAO, NET ID: txr171830
 *
 * Please check readme.txt file
 *
 * Robin Hood Hashing
 * *
 * */



package txr171830;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashSet;
import java.util.Scanner;

public class HashingDriver {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        Scanner in2;

        in = new Scanner(System.in);

        //Use Custom Hashing Implementation for all operations
        Hashing<Long> customHash = new Hashing<>();

        //Use Java inBuilt HashSet for all operations
        HashSet<Long> inbuiltHash = new HashSet<>();

        //set size of array to create array of random numbers
        int n=1000000;
        Object[] arrRandom=new Object[n];

        System.out.println("Choose option: 1. Run file with million operations  2. User Input/Check performance between HashSet and Custom Hashing Implementation on Distinct elements ");

        whileloop:
        while(in.hasNext()){
            int com = in.nextInt();

            switch (com){

                case 1:
                    System.out.println("Choose option: 1.Using Custom Implementation  2.Using Java inBuilt HashSet ");
                    in2 = new Scanner(System.in);

                    innerwhileloop1:
                    while(in2.hasNext()){
                        int opt = in2.nextInt();

                        switch(opt){
                            case 1: //
                                File file = new File(args[0]);
                                in2 = new Scanner(file);

                                String operation = "";
                                long operand = 0;

                                Timer timer = new Timer();

                                innerwhileloop2:
                                while (!((operation = in2.next()).equals("End"))) {
                                    switch (operation) {
                                        case "Add": {
                                            operand = in2.nextLong();
                                            customHash.add(operand);
                                            break;
                                        }

                                        case "Remove": {
                                            operand = in2.nextLong();
                                            customHash.remove(operand);
                                            break;
                                        }
                                        case "Contains": {
                                            operand = in2.nextLong();
                                            customHash.contains(operand);
                                            break;
                                        }

                                        default:
                                            break;

                                    }



                                }
                                timer.end();
                                System.out.println(timer);
                                System.out.println("Number of elements in the Hash Table(Custom): "+ customHash.size);
                                break whileloop;

                            case 2://
                                 file = new File(args[0]);
                                in2 = new Scanner(file);

                                operation = "";
                                 operand = 0;
                                 timer = new Timer();

                                while (!((operation = in2.next()).equals("End"))) {
                                    switch (operation) {
                                        case "Add": {
                                            operand = in2.nextLong();
                                            inbuiltHash.add(operand);
                                            break;
                                        }

                                        case "Remove": {
                                            operand = in2.nextLong();
                                            inbuiltHash.remove(operand);
                                            break;
                                        }
                                        case "Contains": {
                                            operand = in2.nextLong();
                                            inbuiltHash.contains(operand);
                                            break;
                                        }

                                        default:
                                            break;

                                    }

                                }

                                timer.end();
                                System.out.println(timer);
                                System.out.println("Number of elements in the Hash Table(inBuilt): "+ inbuiltHash.size());
                                break whileloop;

                            default:break whileloop;

                        }

                    }

                    break whileloop;

                case 2:
                    System.out.println("Choose options: 1.Add  2.Contains  3.Remove   4.Performance check between HashSet and Custom Hashing for Distinct elements (Large n)");
                    System.out.println();
                    System.out.println("Please enter required number after choosing option");

                    long x = 0;
                    in2 = new Scanner(System.in);
                    Timer timer = new Timer();

                    innerwhileloop1:
                    while(in2.hasNext()){
                         int opt = in2.nextInt();

                        switch (opt) {

                            case 1: //add
                                x = in2.nextInt();
                                customHash.add(x);
                                customHash.printTable();
                                break;

                            case 2://contains
                                x = in2.nextInt();
                                System.out.print(customHash.contains(x));
                                break;

                            case 3://delete
                                x = in2.nextInt();
                                System.out.print(customHash.remove(x));
                                customHash.printTable();
                                break;
                            case 4://generate array of random integers an test the performanece

                                customHash.generateRandomArray(arrRandom,n);

                                System.out.println("HashSet and Custom Hashing Implementation Comparison. This is Done for large n.");
                                System.out.println();

                                //calculate how many distinct elements using  Hash Set
                                timer.start();
                                int distinctn=customHash.distinctElements(arrRandom);
                                //calculate how many distinct elements it has
                                timer.end();
                                System.out.println("Number of distinct elements using inBuilt HashSet: " + distinctn);
                                System.out.println("Built in HashSet Usage Details:"+timer);

                                System.out.println();

                                //calculate how many distinct elements using custom Hashing Implementation
                                timer.start();
                                distinctn=customHash.distinctElementsCustom(arrRandom);
                                timer.end();
                                System.out.println("Number of distinct elements using Custom Hashing Implementation: " + distinctn);
                                System.out.println("Custom Hashing Implementation Usage Details:"+timer);


                                break;
                            default:
                                break whileloop;
                        }

                    }

                default: break whileloop;

            }
        }

    }
}