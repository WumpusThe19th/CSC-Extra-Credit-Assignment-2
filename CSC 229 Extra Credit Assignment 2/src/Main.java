import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int[] a;
        a = new int[2];
        int input;
        int cur_index = 0;

        int largest1 = 0;
        int largest2 = 0;
        int smallest1 = 999;
        int smallest2 = 999;

        int[] test_a={3,5,12,450,1,1,5,6,23,6,8,9,321,2,3};
        //I wouldn't assume this counts against the cost efficiency of the extra credit part of the algorithm.
        //But even if it does, it would be roughly constant and, depending on the size of the array, it would go
        //to about n/2 for the copying process, which occurs log n times because we only call the reallocation
        //when we need to double the array's size.
        while (true){
            System.out.println("Type a positive number to add it to the array. Type a negative number to continue. Type 0 to use test array.");
            input = scnr.nextInt();
            if (input < 0){
                break;
            }
            else if (input == 0){
                a = test_a;
                break;
            }

            if (cur_index > a.length - 1){
                int[] new_a = new int[a.length * 2];
                for (int i = 0; i < a.length; i++){
                    new_a[i] = a[i];
                }
                a = new_a;
            }
            a[cur_index] = input;
            cur_index++;
        }
        //We could. Theoretically. Ignore all basic practices that are smart and just do 4 for loops.
        //Come on why is a large number first loop failing?
        //So 9 comes in first, and because it's the largest element, it gets put at largest 1.
        //Then 4, which goes in as largest 2.

        //This loop is roughly linear in time complexity. Each item costs a loop iteration,
        //and at least 2 if statements.
        for (int item: a) {
            if (item == 0){ //To account for any empty reallocations, and to prevent division errors.
                continue;
            }
            if (item < smallest2) { //If our considered element is smaller than the second smallest.
                if (item < smallest1) {//If our considered element is smaller than the smallest.
                    System.out.println(item + " is becoming smallest 1.");
                    smallest2 = smallest1;
                    smallest1 = item; //Set our element to be the smallest.
                }
                else { //Otherwise,
                    System.out.println(item + " is becoming smallest 2.");
                    smallest2 = item; //Set our element to be the second smallest.
                }
            }
            //Repeat what we did for finding the smallest elements with the largest elements.
            if (item > largest2){
                if (item > largest1) {
                    System.out.println(item + " is becoming largest 1.");
                    largest2 = largest1;
                    largest1 = item;
                }
                else {
                    System.out.println(item + " is becoming largest 2.");
                    largest2 = item;
                }
            }
        }


        //I think we're getting somewhere, we just need to figure out this last hurdle.
        //The array [2, 3, 4, 1] says the smallest are 1 and 3.
        //In our loop, we check 2, which would pass into smallest1.
        //Then 3, which would pass into smallest2.
        //Then 4, which would be overlooked.
        //Then 1, which would be lesser than 3, then lesser than 2, so it would overwrite 3.
        //Therefore we should reassign the second value to the previous smallest value.
        //for (int item: a){
        //if (item < smallest1){
        //smallest1 = item;
        //}
        //}
        System.out.println("The largest value in the array is; " + largest1 + ". The second largest is; " + largest2);
        System.out.println("The smallest value in the array is; " + smallest1 + ". The second smallest is; " + smallest2);
        double ratio = (largest1 + largest2)/(smallest1 + smallest2);
        System.out.println("The ratio of the two largest divided by the two smallest values is " + ratio);
    }

}
