/*Program made by: I GEDE PRADNYA SADHU PUTRA

This program generates a random array with the user-entered value as its length.
Then, it will sort this array using various different sorting algorithms and print out the times of each algorithm.

The goal of this algorithm is to help users understand which algorithms are more efficient based on size of the array.
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

        }while(!pass);

        //Calls function to generate random array
        unsorted = createRandomArray(length);

        //Saving randomly generated array as separate entity to ensure ability to restore the same random array for other algorithms
        unsortedSaved = new int[unsorted.length];
        restoreRandomArray(unsortedSaved, unsorted);

        //Begins processing and timing my custom sorting algorithm packet sort
        System.out.print("processing packet sort... ");
        startTime = System.nanoTime();
        int packetSortArray[] = packetSort(unsorted, new int[]{});
        endTime = System.nanoTime();
        int packetSortTime = (int)((endTime-startTime)/1000);
        sortTimes[0] = packetSortTime;
        System.out.println("\t\tCOMPLETED: "+packetSortTime+" microsecond(s)");

        System.out.println("");
        restoreRandomArray(unsorted, unsortedSaved);

        System.out.print("processing selection sort... ");
        startTime = System.nanoTime();
        selectionSort(unsorted);
        endTime = System.nanoTime();
        int selectionSortTime = (int)((endTime-startTime)/1000);
        sortTimes[1] = selectionSortTime;
        System.out.println("\tCOMPLETED: "+selectionSortTime+" microsecond(s)");

        System.out.println("");
        restoreRandomArray(unsorted, unsortedSaved);

        System.out.print("processing bubble sort... ");
        startTime = System.nanoTime();
        bubbleSort(unsorted);
        endTime = System.nanoTime();
        int bubbleSortTime = (int)((endTime-startTime)/1000);
        sortTimes[2] = bubbleSortTime;
        System.out.println("\t\tCOMPLETED: "+bubbleSortTime+" microsecond(s)");

        System.out.println("");
        restoreRandomArray(unsorted, unsortedSaved);

        System.out.print("processing merge sort ... ");
        startTime = System.nanoTime();
        mergeSort(unsorted,length);
        endTime = System.nanoTime();
        int mergeSortTime = (int)((endTime-startTime)/1000);
        sortTimes[3] = mergeSortTime;
        System.out.println("\t\tCOMPLETED: "+mergeSortTime+" microsecond(s)");

        System.out.println("");
        restoreRandomArray(unsorted, unsortedSaved);

        System.out.print("processing insertion sort... ");
        startTime = System.nanoTime();
        insertionSort(unsorted);
        endTime = System.nanoTime();
        int insertionSortTime = (int)((endTime-startTime)/1000);
        sortTimes[4] = insertionSortTime;
        System.out.println("\tCOMPLETED: "+insertionSortTime+" microsecond(s)");

        //Begins ranking the different sorting algorithms based on their times
        insertionSort(sortTimes);
        boolean packetPrinted = false;
        boolean selectionPrinted = false;
        boolean bubblePrinted = false;
        boolean mergePrinted = false;
        boolean insertionPrinted = false;

        for(int i=0;i<sortTimes.length;i++){
            if(sortTimes[i]==packetSortTime && packetPrinted==false){
                System.out.println((i+1)+") Packet Sort: "+sortTimes[i]+" microseconds");
                packetPrinted=true;
            }
            else if(sortTimes[i]==selectionSortTime && selectionPrinted==false){
                System.out.println((i+1)+") Selection Sort: "+sortTimes[i]+" microseconds");
                selectionPrinted=true;
            }
            else if(sortTimes[i]==bubbleSortTime && bubblePrinted==false){
                System.out.println((i+1)+") Bubble Sort: "+sortTimes[i]+" microseconds");
                bubblePrinted=true;
            }
            else if(sortTimes[i]==mergeSortTime && mergePrinted==false){
                System.out.println((i+1)+") Merge Sort: "+sortTimes[i]+" microseconds");
                mergePrinted=true;
            }
            else if(sortTimes[i]==insertionSortTime && insertionPrinted==false){
                System.out.println((i+1)+") Insertion Sort: "+sortTimes[i]+" microseconds");
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
