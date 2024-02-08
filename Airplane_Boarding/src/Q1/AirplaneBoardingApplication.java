package Q1; //Package name Q1

import java.util.InputMismatchException; //import InputMismatchException for user-friendlyness
import java.util.Map; //import of map class to use maps
import java.util.Scanner; //import scanner to get values inputted from the users
import java.util.TreeMap; //import treeMap class for use of a treeMap for sorting of Key values

public class AirplaneBoardingApplication { //start of main class

    public static Scanner scanner = new Scanner(System.in); //creating a public scanner which can be used in all static methods

    public static void main(String[] args) { //start of main method

        // Creating a treeMap which will allow for automatic sorting of the key values in numerical order
        Map<Integer, String> seatMap = new TreeMap<>();
        //Created with Integer and String parameters for the passengers seat and name

        System.out.println("Welcome to the Airplane Boarding and Disembarking Application:"); //for user-friendlyness

        while (true) { //creating an infinite while loop so user can continously use application till they exit
            System.out.println("\nWhat option would you like?"); //giving user their options
            System.out.println("a: Board a Passenger"); //option a to board a passenger
            System.out.println("b: Disembark a Passenger"); //option b to disembark a passenger
            System.out.println("c: View Passengers"); //option c to view the passengers in the plane
            System.out.println("d: Exit"); //option d to exit

            try {//start of try catch exception so no mismatch inputs and for user-friendlyness

                char choice = scanner.next().charAt(0); //creating a char variable to be the users input

                if (choice == 'a' || choice == 'A') { //choice a works with lower case and uppercase letters.
                    boardPassenger(seatMap); //calling boardPassenger method with seatMap parameter
                }  //end of if statement for option a

                else if (choice == 'b' || choice == 'B') { //choice b, works with lowercase and uppercase
                    disembarkPassenger(seatMap); //calling disembarkPassenger method with seatMap parameter
                }//end of if statement for option b

                else if (choice == 'c' || choice == 'C') { //option c, works with upper and lower case
                    printSeatMap(seatMap);//calling printSortedSeatMap method with seatMap Parameter
                } //end of if statement for option c

                else if (choice == 'd' || choice == 'D'){
                    System.out.println("\nThank You for Using This Application!");
                    break;} //Last else if statement to break application if user inputs d

                else { //else statement if input is not listed so user can reinput value
                    System.out.println("\nInvalid input. Please enter a, b, c, or d"); //states that input was invalid and gives options to input
                }//end of else statement

            } catch (InputMismatchException ex) { //catch inputMismatchException so code does not break and for user-friendlyness
                System.out.println("Invalid input. Please enter a, b, c, or d"); //states that input was invalid and gives options to input
                scanner.nextLine(); // Clear the invalid input from the scanner
            } //end of catch exception

        }//end of infinite while loop

    }//end of main method

    /*Rationale for using a treeMap
    * In real life, a full economy plane operates in a way that whoever comes first in line comes first on the plane
    * and when the plane lands, people can get out as they wish, they can disembark either first or last, or somewhere in the middle
    * That is why I decided to use a treeMap so that a passenger can be boarded on their seat, but any passenger on the plane can leave
    * first, which gives the uncertainty. As planes are not a FIFO process or LIFO process, this is why I used a tree map*/

        public static void boardPassenger(Map<Integer, String> seatMap) { //start of boardPassenger method with seatMap parameter
        System.out.print("Enter the seat number for boarding: "); //prompts user to input the seat number
        int seatNumber = 0; //setting int seatNumber equal to 0

        while (true) {//start of infinite while loop with try-catch exception inside for exception handling and so code does not break
            try { //start of try statement
                seatNumber = scanner.nextInt();//setting users value to seatNumber
                break;// Break out of the loop if a valid integer is entered
            } catch (InputMismatchException e) { //catch inputMismatchException and ensure it is handled
                System.out.print("Invalid input! Please enter an integer: "); //statement to be printed out stating user needs to input an integer
                scanner.nextLine();// Clear the buffer to prevent an infinite loop of exceptions
            } //end of catch exception
        }//end of infinite while loop

        if (seatNumber < 1) System.out.println("\nThis seat does not exist.");
        else if (seatMap.containsKey(seatNumber)) { //start of if statement if the seat is occupied already or not
            System.out.println("\nSorry, the seat is already occupied."); //print out if seat is already occupied
        } //end of if statement

        else {//start of else statement if seat is not occupied
            System.out.print("Enter the passenger name: "); //prompt user to enter the passengers name
            String passengerName = scanner.next();//creating a string equal to the users input

            if (seatMap.containsValue(passengerName)) { //check if passenger is already on the plane
                System.out.println("This passenger is already on the plane."); //print out if passenger is already on plane
            } //end of if statement
            else { //if passenger is not on plane
                seatMap.put(seatNumber, passengerName); //placing the seatNumber as the key and passengerName as the value in the treeMap
                System.out.println("\nPassenger " + passengerName + " boarded successfully on seat " + seatNumber); //Print out that the boarding was successful
            }//end of statement if passenger is not on plane
        }//end of else statement if seat is occupied or not
        printSeatMap(seatMap); //print out the passengers on the plane and their respective seats
    }// end of boardPassenger method.

        public static void disembarkPassenger (Map < Integer, String > seatMap){ //start of disembarkPassenger method with seatMap parameter

        if (seatMap.isEmpty()){ //check if the plane is empty
            System.out.println("\nThe Plane is Empty!"); //print out this statement if the plane has no passengers
        }//end of if statement

        else { //start of else statement
            System.out.print("Enter the seat number for disembarking: "); //prompt user to enter seat number

            int seatNumber = 0; //creating int seatNumber equal to 0

            while (true) { //start of infinite while loop with try-catch inside so exceptions can be handled and code does not break
                //For user friendlyness

                try { //start of try-block
                    seatNumber = scanner.nextInt(); //setting the seatNumber equal to users input
                    break; // Break out of the loop if a valid integer is entered
                } catch (InputMismatchException e) { //catch the inputMismatchException if input is not an integer
                    System.out.print("Invalid input! Please enter an integer: "); //statement to input an integer
                    scanner.nextLine();// Clear the buffer to prevent an infinite loop of exceptions
                } //end of catch block
            } //end of infinite while loop

            if (seatMap.containsKey(seatNumber)) { //if statement checks if the inputted number is on the plane
                String passengerName = seatMap.remove(seatNumber); //disembark the passenger requested by the user
                System.out.println("\nPassenger " + passengerName + " disembarked from seat " + seatNumber); //print out that disembarking was successful
            }// end of if statement
            else { //start of else statement
                System.out.println("No passenger found on seat " + seatNumber); //print that no passenger was found on the seat value inputted
            } //end else statement

            printSeatMap(seatMap); //print out the passengers on the plane
        }// end of else statement (else statement if plane is empty or not)
    }//end of disembarkPassenger method

        public static void printSeatMap(Map < Integer, String > seatMap) {//start of printSeatMap method which prints the passengers currently on the plane
        if (seatMap.isEmpty()) { //check if the plane is empty
            System.out.println("\nThe Plane is Empty!"); //print out if plane is empty
        }// end of if statement

        else {//if plane is not empty

            System.out.println("\nPassengers on the Plane: "); //formatting
            for (Map.Entry<Integer, String> entry : seatMap.entrySet()) {//enhanced for loop to check all keys in the treeMap
                Integer seat = entry.getKey(); //get the seat number
                String name = entry.getValue();//get the passenger name at that seat number
                System.out.println("Seat: " + seat + ", Name: " + name); //print the values as Seat: seat, Name: name
            } //end of enhanced for loop
        } //end of statement if plane is not empty
    }// end of printSeatMap method

}//end of main class



