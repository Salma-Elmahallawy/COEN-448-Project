
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Robot robot = new Robot();
    private static final String[] robotDirection = {"North" , "East" , "South" , "West"};
    private static int gridSize;

    public static void main(String[] args) {

        String[] commands = {"U" , "D" , "R" , "L" , "M s OR M0s" , "P" , "C" , "Q" , "I s or I0s"};
        String[] compareCommands = {"U" , "D" , "R" , "L" , "P" , "C" , "Q" };
        Scanner sc = new Scanner(System.in);

        int[][] grid = new int[0][];

        System.out.println("Enter the size of the square floor: ");
        while(true){
            grid = gridCreation(grid, sc);

            if(gridSize > 0)
                break;
        }

        sc.nextLine(); // needed to clear the line from the nextInt

        //infinite while loop to continuously ask for commands
        while(true){

            String input;
            System.out.println("Enter a command");
            input = sc.nextLine().replaceAll("\\s+","").toUpperCase();

            // Removes all spaces and changes all input letters to uppercase then checks with the list of commands available
            if(Arrays.asList(compareCommands).contains(input)){
                CommandInput(input, gridSize, grid);
                if (input.equalsIgnoreCase("Q")) {
                    break;
                }

            }else if((input.length() >= 2) && (input.charAt(0) == 'M')){ // moves the robot in the grid s spaces
                CommandInput_M(input, gridSize, grid);

            }else if((input.length() >= 2) && (input.charAt(0) == 'I')){ // moves the robot in the grid s spaces
                grid = CommandInput_I(input, grid);

            }else{ // loop again if the user input is an invalid command
                PrintCommands(commands);
            }
        }
    }


    public static int[][] gridCreation(int[][] grid, Scanner sc){
        try{
            gridSize = sc.nextInt();
            if(gridSize > 0){
                return initialize(gridSize);
            }else
                throw new InputMismatchException();
        }catch(InputMismatchException e){
            System.out.println("Invalid Input. Please enter an integer value greater than 0");
            sc.next();
        }
        return grid;
    }

    // creates and initializes the grid to zeros, robot to position (0,0) , facing north , and pen up ( called at command I n )
    public static int[][] initialize(int gridSize){
        robot.setxPosition(0);
        robot.setyPosition(0);
        robot.setDirection(0);
        robot.setPenUp(true);

        return new int[gridSize][gridSize];
    }

    // Printing the information about the robot direction, position, and pen position ( called at command C )
    public static void printRobotInfo(){
        System.out.println("Current Position: (" + robot.getxPosition() + "," + robot.getyPosition() + ")");
        if(robot.isPenUp())
            System.out.println("Pen Position: Up");
        else
            System.out.println("Pen Position: Down");

        System.out.println("Pen Direction: " + robotDirection[((robot.getDirection()%4) + 4) % 4]);
    }

    // Prints the square floor ( called at command P )
    public static void printGrid(int[][] grid, int gridSize){

        int ytemp = gridSize - 1 , xtemp = 0;

        System.out.print("---+");
        for(int j = 0 ; j < gridSize ; j++){
            System.out.print("---+");
        }
        System.out.println();

        for(int i = gridSize - 1 ; i >= 0 ; i--){
            if(ytemp > 9){
                System.out.print(ytemp-- + " ");
            }else{
                System.out.print(ytemp-- + "  ");
            }

            for(int j = 0 ; j < gridSize ; j++){
                if(grid[j][i] == 1)
                    System.out.printf("%-2s%-2s", "|", "*");
                else
                    System.out.printf("%-2s%-2s", "|", " ");

            }
            System.out.print("|\n---+");
            for(int j = 0 ; j < gridSize ; j++){
                System.out.print("---+");
            }
            System.out.println();
        }
        System.out.print("     ");
        for(int i = 0 ; i < gridSize ; i++){
            if(i > 9){
                System.out.print(xtemp++ + "  ");
            }else{
                System.out.print(xtemp++ + "   ");
            }
        }
        System.out.println();
    }

    public static void moveForward(int numOfSpaces , int gridSize , int[][] grid){

        switch(((robot.getDirection()%4) + 4) % 4){
            case 0: // facing north -> so we add to the yPosition

                if((robot.getyPosition() + numOfSpaces) < gridSize) {
                    if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                        for(int i = 0; i <= numOfSpaces; i++) {
                            grid[robot.getxPosition()][robot.getyPosition() + i] = 1;
                        }
                        robot.setyPosition(robot.getyPosition() + numOfSpaces);
                    }else{ // robot moves normally without tracing
                        robot.setyPosition(robot.getyPosition() + numOfSpaces);
                    }
                }else {
                    System.out.println("Invalid move (out of bound)");
                }
                break;

            case 1: // facing east -> so we add to the xPosition

                if((robot.getxPosition() + numOfSpaces) < gridSize) {
                    if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                        for(int i = 0; i <= numOfSpaces; i++) {
                            grid[robot.getxPosition() + i][robot.getyPosition()] = 1;
                        }
                        robot.setxPosition(robot.getxPosition() + numOfSpaces);
                    }else{ // robot moves normally without tracing
                        robot.setxPosition(robot.getxPosition() + numOfSpaces);
                    }
                }else{
                    System.out.println("Invalid move (out of bound)");
                }
                break;


            case 2:// facing south -> so we subtract from the yPosition

                if((robot.getyPosition() - numOfSpaces) >= 0) {
                    if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                        for(int i = 0; i <= numOfSpaces; i++) {
                            grid[robot.getxPosition()][robot.getyPosition() - i] = 1;
                        }
                        robot.setyPosition(robot.getyPosition() - numOfSpaces);
                    }else{ // robot moves normally without tracing
                        robot.setyPosition(robot.getyPosition() - numOfSpaces);
                    }
                }else {
                    System.out.println("Invalid move (out of bound)");
                }
                break;


            case 3:// facing west -> so we subtract from the xPosition

                if((robot.getxPosition() - numOfSpaces) >= 0) {
                    if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                        for(int i = 0; i <= numOfSpaces; i++) {
                            grid[robot.getxPosition() - i][robot.getyPosition()] = 1;
                        }
                        robot.setxPosition(robot.getxPosition() - numOfSpaces);
                    }else{ // robot moves normally without tracing
                        robot.setxPosition(robot.getxPosition() - numOfSpaces);
                    }
                }else{
                    System.out.println("Invalid move (out of bound)");
                }
                break;
        }
    }

    public static void PrintCommands(String[] commands){
        System.out.println("Invalid Command. Please use one of the following commands:");
        for(int i = 0 ; i < commands.length ; i++){
            System.out.println(commands[i]);
        }
    }

    public static void CommandInput_M(String input, int gridSize, int[][] grid) {
        // when the input is M0s
        if((Character.isDigit(input.charAt(1))) && (input.charAt(1) == '0')){
            try{
                if((Character.isDigit(input.charAt(2)) && (input.charAt(2) > '0'))){

                    moveForward(Integer.parseInt(input.substring(2)) , gridSize , grid);
                }else
                    System.out.println("Invalid Input. Please enter an positive integer value");
            }catch(NumberFormatException | StringIndexOutOfBoundsException e){
                System.out.println("Invalid Input. Please enter an positive integer value");
            }

        }else if((Character.isDigit(input.charAt(1))) && (input.charAt(1) > '0')){ // when the input is M s

            try{
                moveForward(Integer.parseInt(input.substring(1)) , gridSize , grid);

            }catch (NumberFormatException e){
                System.out.println("Invalid Input. Please enter an positive integer value");
            }
        }else{
            System.out.println("Invalid Input. Please enter an positive integer value");
        }
    }

    public static int[][] CommandInput_I(String input, int[][] grid){
        // when the input is M0s
        int newSize;
        if((Character.isDigit(input.charAt(1))) && (input.charAt(1) == '0')){
            try{
                if((Character.isDigit(input.charAt(2)) && (input.charAt(2) > '0'))){
                    newSize = Integer.parseInt(input.substring(2));
                    gridSize = newSize;
                    return initialize(newSize);
                }else
                    System.out.println("Invalid Input. Please enter an positive integer value");
            }catch(NumberFormatException | StringIndexOutOfBoundsException e){
                System.out.println("Invalid Input. Please enter an positive integer value");
            }

        }else if((Character.isDigit(input.charAt(1))) && (input.charAt(1) > '0')){ // when the input is M s

            try{
                gridSize = Integer.parseInt(input.substring(1));
                grid = initialize(gridSize);

            }catch (NumberFormatException e){
                System.out.println("Invalid Input. Please enter an positive integer value");
            }
        }else{
            System.out.println("Invalid Input. Please enter an positive integer value");
        }
    return grid;
    }

    public static void CommandInput(String input, int gridSize , int[][] grid){
        if(input.equalsIgnoreCase("U")){ // puts the position of the pen up

            robot.setPenUp(true);

        }else if(input.equalsIgnoreCase("D")) { // puts the position of the pen down

            robot.setPenUp(false);

        }else if(input.equalsIgnoreCase("R")){ // turn the robot direction to the right

            robot.setDirection(robot.getDirection() + 1);

        }else if(input.equalsIgnoreCase("L")){ // turn the robot direction to the left

            robot.setDirection(robot.getDirection() - 1);

        }else if(input.equalsIgnoreCase("P")){ // prints out the grid into console

            printGrid(grid , gridSize);

        }else if(input.equalsIgnoreCase("C")){ // prints out the Robot information

            printRobotInfo();

        }else if(input.equalsIgnoreCase("Q")){ // Quits the program

            System.out.println("Exiting Program");
        }
    }
}
