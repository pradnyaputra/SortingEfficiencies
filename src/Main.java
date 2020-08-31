/*Program made by: I GEDE PRADNYA SADHU PUTRA

This program showcases a custom sorting algorithm I made (Packet Sort), comparing it directly against other popular sorting algorithms.

This program generates a random array with the user-entered value as its length, and then
sort this array with my custom algorithm and various other sorting algorithms.
Then, it will print out the times of each algorithm.

Packet sorting is best used with larger array sizes due to its recursive methods combines with insertion sort(>=500)
 */

import java.lang.Math;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        long startTime;
        long endTime;
        boolean pass = false;
        int[] unsorted;
        int[] unsortedSaved;
        int length=0;
        int[] sortTimes = new int[5];

        do {

            System.out.println("Enter the length of the list you would like to sort: ");

            //Validates that the input is greater than 1 and is an integer before continuing
            length = isValid();
            if(length>1){
                pass=true;
            }
            else{
                System.out.println("Invalid integer...");
                System.out.println("");
            }

        }while(!pass);

        //Calls function to generate random array
        unsorted = createRandomArray(length);

        //Saving randomly generated array as separate entity to ensure ability to restore the same random array for other algorithms
        unsortedSaved = new int[unsorted.length];
        restoreRandomArray(unsortedSaved, unsorted);

        //BEGINS PROCESSING PACKET SORT
        System.out.print("PROCESSING PACKET SORT (custom algorithm)... ");
        startTime = System.nanoTime();
        int packetSortArray[] = packetSort(unsorted, new int[]{});
        endTime = System.nanoTime();
        int packetSortTime = (int)((endTime-startTime)/1000); //Gets the system time at both the start and end of sorting, and then calculates difference to get sorting time
        sortTimes[0] = packetSortTime;
        System.out.println("COMPLETED");
        //FINISHES PROCESSING PACKET SORT

        //Restores the now sorted array back to the original random array
        System.out.println("");
        restoreRandomArray(unsorted, unsortedSaved);

        //BEGINS PROCESSING SELECTION SORT
        System.out.print("PROCESSING SELECTION SORT... ");
        startTime = System.nanoTime();
        selectionSort(unsorted);
        endTime = System.nanoTime();
        int selectionSortTime = (int)((endTime-startTime)/1000); //Gets the system time at both the start and end of sorting, and then calculates difference to get sorting time
        sortTimes[1] = selectionSortTime;
        System.out.println("COMPLETED");
        //FINISHES PROCESSING SELECTION SORT

        //Restores the now sorted array back to the original random array
        System.out.println("");
        restoreRandomArray(unsorted, unsortedSaved);

        //BEGINS PROCESSING BUBBLE SORT
        System.out.print("PROCESSING BUBBLE SORT... ");
        startTime = System.nanoTime();
        bubbleSort(unsorted);
        endTime = System.nanoTime();
        int bubbleSortTime = (int)((endTime-startTime)/1000); //Gets the system time at both the start and end of sorting, and then calculates difference to get sorting time
        sortTimes[2] = bubbleSortTime;
        System.out.println("COMPLETED");
        //FINISHES PROCESSING BUBBLE SORT

        //Restores the now sorted array back to the original random array
        System.out.println("");
        restoreRandomArray(unsorted, unsortedSaved);

        //BEGINS PROCESSING MERGE SORT
        System.out.print("PROCESSING MERGE SORT ... ");
        startTime = System.nanoTime();
        mergeSort(unsorted,length);
        endTime = System.nanoTime();
        int mergeSortTime = (int)((endTime-startTime)/1000); //Gets the system time at both the start and end of sorting, and then calculates difference to get sorting time
        sortTimes[3] = mergeSortTime;
        System.out.println("COMPLETED");
        //FINISHES PROCESSING MERGE SORT

        //Restores the now sorted array back to the original random array
        System.out.println("");
        restoreRandomArray(unsorted, unsortedSaved);

        //BEGINS PROCESSING INSERTION SORT
        System.out.print("PROCESSING INSERTION SORT... ");
        startTime = System.nanoTime();
        insertionSort(unsorted);
        endTime = System.nanoTime();
        int insertionSortTime = (int)((endTime-startTime)/1000); //Gets the system time at both the start and end of sorting, and then calculates difference to get sorting time
        sortTimes[4] = insertionSortTime;
        System.out.println("COMPLETED");
        //FINISHES PROCESSING INSERTION SORT

        //Begins ranking the different sorting algorithms based on their times
        System.out.println("");
        System.out.println("SORTING ALGORITHMS FROM FASTEST TO SLOWEST:");
        System.out.println("-------------------------------------------");
        insertionSort(sortTimes);
        boolean packetPrinted = false;
        boolean selectionPrinted = false;
        boolean bubblePrinted = false;
        boolean mergePrinted = false;
        boolean insertionPrinted = false;

        for(int i=0;i<sortTimes.length;i++){
            if(sortTimes[i]==packetSortTime && packetPrinted==false){
                System.out.println((i+1)+") PACKET SORT: "+sortTimes[i]+" microseconds");
                packetPrinted=true;
            }
            else if(sortTimes[i]==selectionSortTime && selectionPrinted==false){
                System.out.println((i+1)+") SELECTION SORT: "+sortTimes[i]+" microseconds");
                selectionPrinted=true;
            }
            else if(sortTimes[i]==bubbleSortTime && bubblePrinted==false){
                System.out.println((i+1)+") BUBBLE SORT: "+sortTimes[i]+" microseconds");
                bubblePrinted=true;
            }
            else if(sortTimes[i]==mergeSortTime && mergePrinted==false){
                System.out.println((i+1)+") MERGE SORT: "+sortTimes[i]+" microseconds");
                mergePrinted=true;
            }
            else if(sortTimes[i]==insertionSortTime && insertionPrinted==false){
                System.out.println((i+1)+") INSERTION SORT: "+sortTimes[i]+" microseconds");
                insertionPrinted=true;
            }
        }
    }

    static int[] packetSort(int[] arr, int[] sorted) {
        long mode;
        int lSize=0;
        int rSize=0;
        int split=0;
        long total=0;
        double diff=999999999;
        int lNextFree=0;
        int rNextFree=0;


        if(arr.length<=2){
            sorted = packetSortMerge(sorted, arr);
            return sorted;
        }

        int same=0;
        for(int i=0;i<arr.length;i++){
            if(arr[0]==arr[i]){
                same++;
            }
        }
        if(same==arr.length){
            sorted = packetSortMerge(sorted, arr);
            return sorted;
        }

        for(int i=0;i<arr.length;i++){
            total = total + arr[i];

        }

        double length = arr.length;
        mode = total/arr.length;

        for(int i=0;i<arr.length;i++){
            if(Math.abs(mode-arr[i])<diff) {
                diff = Math.abs(mode - arr[i]);
                split = arr[i];
            }
        }

        for(int i=0;i<arr.length;i++){
            if(arr[i]<split){
                lSize++;
            }
            else{
                rSize++;
            }
        }

        int[] lArr = new int[lSize];
        int[] rArr = new int[rSize];

        for(int i=0;i<arr.length;i++){
            if(arr[i]<split){
                lArr[lNextFree]=arr[i];
                lNextFree++;
            }
            else{
                rArr[rNextFree]=arr[i];
                rNextFree++;
            }
        }

        if(lArr.length==0 || rArr.length==0){
            sorted = packetSortMerge(sorted, arr);
            return sorted;
        }

        sorted = packetSort(lArr, sorted);
        sorted = packetSort(rArr, sorted);

        return sorted;
    }

    static int[] packetSortMerge(int[] oldSorted, int[] packet){
        /*
        take sorted(oldSorted) list
        take list to add to sorted(packet)
        sort list to add to sorted(packet)
        add to sorted list(oldSorted)
        return sorted list(newSorted)
        */

        int key;
        int prev;
        for (int i = 1; i < packet.length; i++) {
            key = packet[i];
            prev = i - 1;

            while (prev >= 0 && packet[prev] > key) {
                packet[prev + 1] = packet[prev];
                prev--;
            }
            packet[prev + 1] = key;
        }

        int[] newSorted = new int[oldSorted.length+packet.length];
        System.arraycopy(oldSorted, 0, newSorted, 0, oldSorted.length);
        System.arraycopy(packet, 0, newSorted, oldSorted.length, packet.length);

        return newSorted;
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

        mergeSortMerge(left,right,arr,mid,length-mid);
    }

    static void mergeSortMerge(int[] left, int[] right, int[] arr, int leftLength, int rightLength){
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

    static void insertionSort(int[] arr){
        int key;
        int prev;
        for (int i=1;i<arr.length;i++){
            key=arr[i];
            prev=i-1;

            while(prev>=0 && arr[prev]>key){
                arr[prev+1]=arr[prev];
                prev--;
            }
            arr[prev+1]=key;
        }
    }

    //validates user input ensuring an integer
    static int isValid(){
        int n;
        Scanner input = new Scanner(System.in);

        try
        {
            n = input.nextInt();
            return n;
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    static int[] createRandomArray(int n){
        int[] newArr = new int[n];
        int max;
        Random rand = new Random();
        if(n>=499999999){
            max=999999999;
        }
        else{
            max=n*2;
        }
        for(int i=0; i<n; i++){
            newArr[i] = rand.nextInt(max);
        }
        return newArr;
    }

    static void restoreRandomArray(int[] arr1, int[] arr2){
        for(int i=0;i<arr1.length;i++){
            arr1[i]=arr2[i];
        }
    }

    //THE FOLLOWING FUNCTIONS ARE USED ONLY IN DEBUGGING

    //This function validates whether an array is properly sorted in ascending order or not
    static boolean isSorted(int[] arr){
        for(int i=1;i<arr.length;i++){
            if(arr[i]<arr[i-1]){
                return false;
            }
        }
        return true;
    }

    //This function prints a given array
    static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print("["+arr[i]+"]");
        }
        System.out.println("");
    }
}
