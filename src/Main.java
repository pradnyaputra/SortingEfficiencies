import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args){
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

        selectionSort(unsorted);

    }


    public static long selectionSort(int[] arr){
        long startTime = System.nanoTime();
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
        long endTime = System.nanoTime();
        long runtime = endTime - startTime;

        return runtime;
    }

    //The following functions are helper functions

    public static int[] createArray(int n){
        int[] newArr = new int[n];
        Random rand = new Random();

        for(int i=0; i<n; i++){
            newArr[i] = rand.nextInt(n*2);
        }
        return newArr;
    }

    public static boolean isInteger(String n){
        try
        {
            // checking valid integer using parseInt() method
            Integer.parseInt(n);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    //This function is used only in debugging
    public static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print("["+arr[i]+"]");
        }
        System.out.println("");
    }
}
