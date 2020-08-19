import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        boolean cont = false;

        while(!cont) {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter the length of the list you would like to sort: ");
            String value = input.nextLine();

            //Validates that the input is an integer
            if (isInteger(value)) {
                cont = true;
            }
            else{
                System.out.println("Please enter an integer value");
            }
        }
    }


    public static double 

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
}
