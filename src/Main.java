/*Program made by: I GEDE PRADNYA SADHU PUTRA

This program generates a random array with the user-entered value as its length.
Then, it will sort this array using various different sorting algorithms and print out the times of each algorithm.

The goal of this algorithm is to help users understand which algorithms are more efficient based on size of the array.
 */

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        long startTime;
        long endTime;
        boolean cont = false;
        int[] unsorted;
        String value;
        int n;
        do {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter the length of the list you would like to sort: ");
            value = input.nextLine();

            //Validates that the input is an integer
            if (isInteger(value)) {
                cont = true;
            }
            else{
                System.out.println("Please enter a valid integer value");
            }
        }while(!cont);

        n = Integer.parseInt(value);
        unsorted = createArray(n);

        System.out.print("processing selection sort... ");
        startTime = System.nanoTime();
        selectionSort(unsorted);
        endTime = System.nanoTime();
        System.out.println("\t\t\tCOMPLETED: "+(endTime-startTime)/1000+" microsecond(s)");

        unsorted = createArray(n);

        System.out.print("processing bubble sort... ");
        startTime = System.nanoTime();
        bubbleSort(unsorted);
        endTime = System.nanoTime();
        System.out.println("\t\t\t\tCOMPLETED: "+(endTime-startTime)/1000+" microsecond(s)");

        unsorted = createArray(n);

        System.out.print("processing merge sort (RECURSIVE)... ");
        startTime = System.nanoTime();
        mergeSort(unsorted,n);
        endTime = System.nanoTime();
        System.out.println("\tCOMPLETED: "+(endTime-startTime)/1000+" microsecond(s)");
    }


    static void selectionSort(int[] arr){
        int lowest;
        int lowestIndex=0;
        int temp;

        for(int i=0;i<arr.length;i++){
            lowest =  999999999;
            for( int j=0+i;j<arr.length;j++){
                if(arr[j]<lowest){
                    lowest = arr[j];
                    lowestIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = lowest;
            arr[lowestIndex] = temp;
        }
    }

    //The following functions are helper functions

    static void bubbleSort(int[] arr){
        int temp;

        for(int i=0; i<arr.length-1;i++) {
            for(int j=0; j<arr.length-i-1;j++){
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void mergeSort(int[] arr, int length){
        if(length<2){
            return;
        }

        int mid=length/2;
        int[] left = new int[mid];
        int[] right = new int [length-mid];

        for (int i=0; i<mid;i++){
            left[i] = arr[i];
        }
        for (int i=0; i<length-mid;i++){
            right[i] = arr[i+mid];
        }

        mergeSort(left, mid);
        mergeSort(right, length-mid);

        merge(left,right,arr,mid,length-mid);
    }

    static void merge(int[] left, int[] right, int[] arr, int leftLength, int rightLength){
        int lIndex=0;
        int rIndex=0;

        int i=0;
        while(lIndex<leftLength && rIndex<rightLength){
            if(left[lIndex]<right[rIndex]){
                arr[i++] = left[lIndex++];
            }
            else{
                arr[i++] = right[rIndex++];
            }
        }
        while(lIndex<leftLength){
            arr[i++] = left[lIndex++];
        }
        while(rIndex<rightLength){
            arr[i++] = right[rIndex++];
        }
    }

    static int[] createArray(int n){
        int[] newArr = new int[n];
        Random rand = new Random();

        for(int i=0; i<n; i++){
            newArr[i] = rand.nextInt(n*2);
        }
        return newArr;
    }

    //Validates whether the value is both an integer and greater than 1
    static boolean isInteger(String n){
        try
        {
            // checking valid integer using parseInt() method
            if(Integer.parseInt(n)>1){
                return true;
            }
            return false;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    //This function is used only in debugging
    static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print("["+arr[i]+"]");
        }
        System.out.println("");
    }
}
