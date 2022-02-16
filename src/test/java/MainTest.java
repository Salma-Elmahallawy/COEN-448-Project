import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final PrintStream standardOut_message = System.out;
    private final PrintStream standardErr_message = System.out;
    private final ByteArrayOutputStream outputStreamCaptor_message = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStreamCaptor_message = new ByteArrayOutputStream();


    @BeforeEach
    public void setUpStream(){
        System.setOut(new PrintStream(outputStreamCaptor_message));
        System.setErr(new PrintStream(errorStreamCaptor_message));
    }

    @AfterEach
    public void restoreStream(){
        System.setOut(standardOut_message);
        System.setErr(standardErr_message);
    }

    @Test
    public void gridCreation(){

        for (int i = 0; i < 3; i++){
            outputStreamCaptor_message.reset();
            switch (i){
                case 0:
                    Scanner sc = new Scanner("5");
                    Main.setGrid(Main.gridCreation(Main.getGrid(), sc));
                    Assertions.assertEquals(5, Main.getGridSize());
                    Assertions.assertEquals(5, Main.getGrid().length);
                    Assertions.assertEquals(5, Main.getGrid()[0].length);

                case 1:
                    Scanner sc2 = new Scanner("wrong input");
                    Main.gridCreation(Main.getGrid(), sc2);
                    Assertions.assertEquals("Invalid Input. Please enter an integer value greater than 0\r\n", outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 2:
                    Scanner sc3 = new Scanner("0");
                    Main.gridCreation(Main.getGrid(), sc3);
                    Assertions.assertEquals("Invalid Input. Please enter an integer value greater than 0\r\n", outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

            }
        }
    }

    @Test
    public void initialize() {
        Main.initialize(5);

        Assertions.assertEquals(Main.robot.getxPosition(), 0);
        Assertions.assertEquals(Main.robot.getyPosition(), 0);
        Assertions.assertEquals(Main.robot.getDirection(), 0);
        Assertions.assertTrue(Main.robot.isPenUp());
    }

    @Test
    public void printRobotInfo() {

        Main.robot.setyPosition(3);
        Main.robot.setxPosition(4);

        outputStreamCaptor_message.reset();

        for(int i = 0; i < 8; i++) {
            switch (i){
                case 0:
                    Main.robot.setPenUp(true);
                    Main.robot.setDirection(0);
                    Main.printRobotInfo();
                    Assertions.assertEquals("""
                            Current Position: (4,3)\r
                            Pen Position: Up\r
                            Pen Direction: North\r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 1:
                    Main.robot.setPenUp(true);
                    Main.robot.setDirection(1);
                    Main.printRobotInfo();
                    Assertions.assertEquals("""
                            Current Position: (4,3)\r
                            Pen Position: Up\r
                            Pen Direction: East\r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 2:
                    Main.robot.setPenUp(true);
                    Main.robot.setDirection(2);
                    Main.printRobotInfo();
                    Assertions.assertEquals("""
                            Current Position: (4,3)\r
                            Pen Position: Up\r
                            Pen Direction: South\r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 3:
                    Main.robot.setPenUp(true);
                    Main.robot.setDirection(3);
                    Main.printRobotInfo();
                    Assertions.assertEquals("""
                            Current Position: (4,3)\r
                            Pen Position: Up\r
                            Pen Direction: West\r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 4:
                    Main.robot.setPenUp(false);
                    Main.robot.setDirection(0);
                    Main.printRobotInfo();
                    Assertions.assertEquals("""
                            Current Position: (4,3)\r
                            Pen Position: Down\r
                            Pen Direction: North\r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 5:
                    Main.robot.setPenUp(false);
                    Main.robot.setDirection(1);
                    Main.printRobotInfo();
                    Assertions.assertEquals("""
                            Current Position: (4,3)\r
                            Pen Position: Down\r
                            Pen Direction: East\r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 6:
                    Main.robot.setPenUp(false);
                    Main.robot.setDirection(2);
                    Main.printRobotInfo();
                    Assertions.assertEquals("""
                            Current Position: (4,3)\r
                            Pen Position: Down\r
                            Pen Direction: South\r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 7:
                    Main.robot.setPenUp(false);
                    Main.robot.setDirection(3);
                    Main.printRobotInfo();
                    Assertions.assertEquals("""
                            Current Position: (4,3)\r
                            Pen Position: Down\r
                            Pen Direction: West\r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();
            }
        }
    }

    @Test
    public void printGrid() throws IOException {

        outputStreamCaptor_message.reset();

        for(int i = 0; i < 3; i++){
            switch (i){
                case 0:
                    Main.printGrid(new int[2][2], 2);
                    Assertions.assertEquals(bs.toByteArray(), outputStreamCaptor_message.toByteArray());
                    outputStreamCaptor_message.reset();


                case 1:
                    Main.printGrid(new int[15][15], 15);
                    Assertions.assertEquals("""
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            14 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            13 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            12 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            11 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            10 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            9  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            8  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            7  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            6  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            5  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            4  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            3  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            2  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            1  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            0  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                                 0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  \r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 2:
                    int[][] starGrid = new int [11][11];
                    starGrid[1][1] = 1;
                    starGrid[10][10] = 1;

                    Main.printGrid(starGrid, 11);
                    Assertions.assertEquals("""
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            10 |   |   |   |   |   |   |   |   |   |   | * |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            9  |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            8  |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            7  |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            6  |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            5  |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            4  |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            3  |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            2  |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            1  |   | * |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            0  |   |   |   |   |   |   |   |   |   |   |   |\r
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                                 0   1   2   3   4   5   6   7   8   9   10  \r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();
            }
        }
    }

    @Test
    public void moveForward_North() {

        int numOfSpaces = 3;
        int gridSize = 5;
        int[][] grid = new int [gridSize][gridSize];
        Main.robot.setDirection(0);

        for(int n = 0; n < 4; n++) {
            Main.robot.setxPosition(0);
            switch(n){
                case 0:
                    outputStreamCaptor_message.reset();
                    Main.robot.setyPosition(0);
                    Main.robot.setPenUp(true);

                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(3, Main.robot.getyPosition());
                    Assertions.assertTrue(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 1:
                    outputStreamCaptor_message.reset();
                    Main.robot.setyPosition(0);
                    Main.robot.setPenUp(false);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(3, Main.robot.getyPosition());
                    Assertions.assertFalse(Main.robot.isPenUp());
                    Assertions.assertEquals(1, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 2:
                    outputStreamCaptor_message.reset();
                    Main.robot.setyPosition(4);
                    Main.robot.setPenUp(true);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(4, Main.robot.getyPosition());
                    Assertions.assertTrue(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());

                case 3:
                    outputStreamCaptor_message.reset();
                    Main.robot.setyPosition(4);
                    Main.robot.setPenUp(false);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(4, Main.robot.getyPosition());
                    Assertions.assertFalse(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());
            }
        }
    }

    @Test
    public void moveForward_East() {

        int numOfSpaces = 3;
        int gridSize = 5;
        int[][] grid = new int [gridSize][gridSize];
        Main.robot.setDirection(1);

        for(int n = 0; n < 4; n++) {
            Main.robot.setyPosition(0);
            switch(n){
                case 0:
                    outputStreamCaptor_message.reset();
                    Main.robot.setxPosition(0);
                    Main.robot.setPenUp(true);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(3, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertTrue(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 1:
                    outputStreamCaptor_message.reset();
                    Main.robot.setxPosition(0);
                    Main.robot.setPenUp(false);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(3, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertFalse(Main.robot.isPenUp());
                    Assertions.assertEquals(1, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 2:
                    outputStreamCaptor_message.reset();
                    Main.robot.setxPosition(4);
                    Main.robot.setPenUp(true);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(4, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertTrue(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());

                case 3:
                    outputStreamCaptor_message.reset();
                    Main.robot.setxPosition(4);
                    Main.robot.setPenUp(false);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(4, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertFalse(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());
            }
        }
    }

    @Test
    public void moveForward_South(){

        int numOfSpaces = 3;
        int gridSize = 5;
        int[][] grid = new int [gridSize][gridSize];
        Main.robot.setDirection(2);

        for(int n = 0; n < 4; n++) {
            Main.robot.setxPosition(0);
            switch(n){
                case 0:
                    outputStreamCaptor_message.reset();
                    Main.robot.setyPosition(4);
                    Main.robot.setPenUp(true);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(1, Main.robot.getyPosition());
                    Assertions.assertTrue(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 1:
                    outputStreamCaptor_message.reset();
                    Main.robot.setyPosition(4);
                    Main.robot.setPenUp(false);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(1, Main.robot.getyPosition());
                    Assertions.assertFalse(Main.robot.isPenUp());
                    Assertions.assertEquals(1, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 2:
                    outputStreamCaptor_message.reset();
                    Main.robot.setyPosition(0);
                    Main.robot.setPenUp(true);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertTrue(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());

                case 3:
                    outputStreamCaptor_message.reset();
                    Main.robot.setyPosition(0);
                    Main.robot.setPenUp(false);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertFalse(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());
            }
        }
    }

    @Test
    public void moveForward_West() {

        int numOfSpaces = 3;
        int gridSize = 5;
        int[][] grid = new int [gridSize][gridSize];
        Main.robot.setDirection(3);

        for(int n = 0; n < 4; n++) {
            Main.robot.setyPosition(0);
            switch(n){
                case 0:
                    outputStreamCaptor_message.reset();
                    Main.robot.setxPosition(4);
                    Main.robot.setPenUp(true);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(1, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertTrue(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 1:
                    outputStreamCaptor_message.reset();
                    Main.robot.setxPosition(4);
                    Main.robot.setPenUp(false);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(1, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertFalse(Main.robot.isPenUp());
                    Assertions.assertEquals(1, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 2:
                    outputStreamCaptor_message.reset();
                    Main.robot.setxPosition(0);
                    Main.robot.setPenUp(true);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertTrue(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());

                case 3:
                    outputStreamCaptor_message.reset();
                    Main.robot.setxPosition(0);
                    Main.robot.setPenUp(false);
                    Main.moveForward(numOfSpaces, gridSize, grid);

                    Assertions.assertEquals(0, Main.robot.getxPosition());
                    Assertions.assertEquals(0, Main.robot.getyPosition());
                    Assertions.assertFalse(Main.robot.isPenUp());
                    Assertions.assertEquals(0, grid[Main.robot.getxPosition()][Main.robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());
            }
        }
    }

    @Test
    public void CommandInput_U() {

        String input = "U";
        Main.CommandInput(input, 5, new int[5][5]);
        Assertions.assertTrue(Main.robot.isPenUp());
    }

    @Test
    public void CommandInput_D() {

        String input = "D";
        Main.CommandInput(input, 5, new int[5][5]);
        Assertions.assertFalse(Main.robot.isPenUp());
    }

    @Test
    public void CommandInput_R() {

        String input = "R";

        for (int i = 0; i < 4; i++) {
            Main.robot.setDirection(i);
            switch (i){
                case 0:
                    Main.CommandInput(input, 5, new int[5][5]);
                    Assertions.assertEquals(1, Main.robot.getDirection());

                case 1:
                    Main.CommandInput(input, 5, new int[5][5]);
                    Assertions.assertEquals(2, Main.robot.getDirection());

                case 2:
                    Main.CommandInput(input, 5, new int[5][5]);
                    Assertions.assertEquals(3, Main.robot.getDirection());

                case 3:
                    Main.CommandInput(input, 5, new int[5][5]);
                    Assertions.assertEquals(4, Main.robot.getDirection());
            }
        }
    }

    @Test
    public void CommandInput_L() {

        String input = "L";

        for (int i = 3; i >= 0; i--) {
            Main.robot.setDirection(i);
            switch (i){
                case 3:
                    Main.CommandInput(input, 5, new int[5][5]);
                    Assertions.assertEquals(2, Main.robot.getDirection());

                case 2:
                    Main.CommandInput(input, 5, new int[5][5]);
                    Assertions.assertEquals(1, Main.robot.getDirection());

                case 1:
                    Main.CommandInput(input, 5, new int[5][5]);
                    Assertions.assertEquals(0, Main.robot.getDirection());

                case 0:
                    Main.CommandInput(input, 5, new int[5][5]);
                    Assertions.assertEquals(-1, Main.robot.getDirection());
            }
        }
    }

    @Test
    public void CommandInput_P(){

//        Main main = new Main();
//        String input = "P";
//        int gridSize = 3;
//        int[][] grid = new int [gridSize][gridSize];
//
//        Scanner scanner = new Scanner(input);
//
//        String P_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();
//        if (P_Input.equalsIgnoreCase("P")){
//            main.printGrid(grid, gridSize);
//        }
//
//        Assertions.assertEquals(3, gridSize);
//        Assertions.assertEquals(3, grid.length);
//        Assertions.assertEquals(3, grid[0].length);
//        Assertions.assertInstanceOf(main.getClass(), main);
        String input = "P";
        Main.CommandInput(input, 5, new int [5][5]);
        //mockito
    }

    @Test
    public void CommandInput_C(){

        String input = "C";

        Main.CommandInput(input, 5, new int[5][5]);
//        boolean function_called = false;
//        Scanner scanner = new Scanner(input);
//
//        String C_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();
//        if(C_Input.equalsIgnoreCase("C")){
//            main.printRobotInfo();
//            function_called = true;
//        }
//        Assertions.assertEquals("C", C_Input);
//        Assertions.assertTrue(function_called);
    }

    @Test
    public void CommandInput_Q(){
        String input = "Q";
        Main.CommandInput(input, 5, new int [5][5]);


//        Scanner scanner = new Scanner(input);
//
//        String Q_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();
//        if(Q_Input.equalsIgnoreCase("Q")){
//            outputStreamCaptor_message.reset();
//            System.out.println("Exiting Program");
//            Assertions.assertEquals("Exiting Program", outputStreamCaptor_message.toString().trim());
//        }
    }

    @Test
    public void PrintInvalidCommand() {
        String[] commands = {"U", "D", "R", "L", "M s OR M0s", "P", "C", "Q", "I s or I0s"};
        outputStreamCaptor_message.reset();
        Main.PrintCommands(commands);
        Assertions.assertEquals("""
                Invalid Command. Please use one of the following commands:\r
                U\r
                D\r
                R\r
                L\r
                M s OR M0s\r
                P\r
                C\r
                Q\r
                I s or I0s\r
                """, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
    }

    @Test
    public void CommandInput_M(){

        String input1 = "M";
        String input2 = "M0";
        String input3 = "M 3";
        String input4 = "M03";

        int gridSize = 6;
        int[][] grid = new int[gridSize][gridSize];
        boolean function_called = false;

        robot.setxPosition(0);
        robot.setyPosition(0);
        robot.setDirection(0);
        robot.setPenUp(false);

        Scanner scanner1 = new Scanner(input1);
        Scanner scanner2 = new Scanner(input2);
        Scanner scanner3 = new Scanner(input3);
        Scanner scanner4 = new Scanner(input4);

        String M_Input1 = scanner1.nextLine().replaceAll("\\s+","").toUpperCase();
        String M_Input2 = scanner2.nextLine().replaceAll("\\s+","").toUpperCase();
        String M_Input3 = scanner3.nextLine().replaceAll("\\s+","").toUpperCase();
        String M_Input4 = scanner4.nextLine().replaceAll("\\s+","").toUpperCase();

        outputStreamCaptor_message.reset();
        if(M_Input1.equalsIgnoreCase("M")){
            System.out.println("Invalid Input. Please enter an positive integer value");
            Assertions.assertEquals("Invalid Input. Please enter an positive integer value", outputStreamCaptor_message.toString().trim());
            Assertions.assertEquals(0, robot.getxPosition());
            Assertions.assertEquals(0, robot.getyPosition());
            Assertions.assertEquals(0, robot.getDirection());
            Assertions.assertFalse(robot.isPenUp());
        }

        outputStreamCaptor_message.reset();
        if((M_Input2.equalsIgnoreCase("M0")) && (Character.isDigit(input2.charAt(1))) && (input2.charAt(1) == '0')){
            System.out.println("Invalid Input. Please enter an positive integer value");
            Assertions.assertEquals("Invalid Input. Please enter an positive integer value", outputStreamCaptor_message.toString().trim());
            Assertions.assertEquals(0, robot.getxPosition());
            Assertions.assertEquals(0, robot.getyPosition());
            Assertions.assertEquals(0, robot.getDirection());
            Assertions.assertFalse(robot.isPenUp());
        }

        if((M_Input3.equalsIgnoreCase("M3")) && (Character.isDigit(input3.charAt(1))) && (input3.charAt(1) > '0')){

            main.moveForward(Integer.parseInt(input3.substring(1)), gridSize, grid);
            function_called = true;
            if(function_called) {
                robot.setyPosition(0);
                robot.setyPosition(robot.getyPosition() + Integer.parseInt(input3.substring(1)));
            }
            Assertions.assertEquals(0, robot.getxPosition());
            Assertions.assertEquals(3, robot.getyPosition());
            Assertions.assertEquals(0, robot.getDirection());
            Assertions.assertFalse(robot.isPenUp());
            Assertions.assertTrue(function_called);
        }

        if((M_Input4.equalsIgnoreCase("M03")) && ((Character.isDigit(input4.charAt(1))) && (input4.charAt(1) == '0')) && (Character.isDigit(input4.charAt(2))) && (input4.charAt(2) > '0')){
            main.moveForward(Integer.parseInt(input4.substring(1)), gridSize, grid);
            function_called = true;
            if(function_called){
                robot.setyPosition(0);
                robot.setyPosition(robot.getyPosition() + Integer.parseInt(input4.substring(1)));
            }
            Assertions.assertEquals(0, robot.getxPosition());
            Assertions.assertEquals(3, robot.getyPosition());
            Assertions.assertEquals(0, robot.getDirection());
            Assertions.assertFalse(robot.isPenUp());
            Assertions.assertTrue(function_called);
        }
    }

    @Test
    public void CommandInput_I() {

        String input1 = "I";
        String input2 = "I0";
        String input3 = "I 3";
        String input4 = "I03";
        int gridSize = 6;
        int[][] grid = new int[gridSize][gridSize];
        boolean function_called = false;

        robot.setxPosition(2);
        robot.setyPosition(1);
        robot.setDirection(2);
        robot.setPenUp(false);

        Scanner scanner1 = new Scanner(input1);
        Scanner scanner2 = new Scanner(input2);
        Scanner scanner3 = new Scanner(input3);
        Scanner scanner4 = new Scanner(input4);

        String I_Input1 = scanner1.nextLine().replaceAll("\\s+","").toUpperCase();
        String I_Input2 = scanner2.nextLine().replaceAll("\\s+","").toUpperCase();
        String I_Input3 = scanner3.nextLine().replaceAll("\\s+","").toUpperCase();
        String I_Input4 = scanner4.nextLine().replaceAll("\\s+","").toUpperCase();

        outputStreamCaptor_message.reset();
        if(I_Input1.equalsIgnoreCase("I")){
            System.out.println("Invalid Input. Please enter an positive integer value");
            Assertions.assertEquals("Invalid Input. Please enter an positive integer value", outputStreamCaptor_message.toString().trim());
            Assertions.assertEquals(6, gridSize);
            Assertions.assertEquals(6, grid.length);
            Assertions.assertEquals(6, grid[0].length);
            Assertions.assertEquals(2, robot.getxPosition());
            Assertions.assertEquals(1, robot.getyPosition());
            Assertions.assertEquals(2, robot.getDirection());
            Assertions.assertFalse(robot.isPenUp());

        }

        outputStreamCaptor_message.reset();
        if((I_Input2.equalsIgnoreCase("I0")) && (Character.isDigit(input2.charAt(1))) && (input2.charAt(1) == '0')){
            System.out.println("Invalid Input. Please enter an positive integer value");
            Assertions.assertEquals("Invalid Input. Please enter an positive integer value", outputStreamCaptor_message.toString().trim());
            Assertions.assertEquals(6, gridSize);
            Assertions.assertEquals(6, grid.length);
            Assertions.assertEquals(6, grid[0].length);
            Assertions.assertEquals(2, robot.getxPosition());
            Assertions.assertEquals(1, robot.getyPosition());
            Assertions.assertEquals(2, robot.getDirection());
            Assertions.assertFalse(robot.isPenUp());
        }

        if((I_Input3.equalsIgnoreCase("I3")) && (Character.isDigit(input3.charAt(1))) && (input3.charAt(1) > '0')){
            gridSize = Integer.parseInt(input3.substring(1));
            grid = main.initialize(gridSize);
            function_called = true;

            if(function_called){
                robot.setxPosition(0);
                robot.setyPosition(0);
                robot.setDirection(0);
                robot.setPenUp(true);
            }
            Assertions.assertEquals(3, gridSize);
            Assertions.assertEquals(3, grid.length);
            Assertions.assertEquals(3, grid[0].length);
            Assertions.assertEquals(0, robot.getxPosition());
            Assertions.assertEquals(0, robot.getyPosition());
            Assertions.assertEquals(0, robot.getDirection());
            Assertions.assertTrue(robot.isPenUp());
            Assertions.assertTrue(function_called);

        }

        if((I_Input4.equalsIgnoreCase("I03")) && ((Character.isDigit(input4.charAt(1))) && (input4.charAt(1) == '0')) && (Character.isDigit(input4.charAt(2))) && (input4.charAt(2) > '0')){
            gridSize = Integer.parseInt(input4.substring(1));
            grid = main.initialize(gridSize);
            function_called = true;

            if(function_called){
                robot.setxPosition(0);
                robot.setyPosition(0);
                robot.setDirection(0);
                robot.setPenUp(true);
            }
            Assertions.assertEquals(3, gridSize);
            Assertions.assertEquals(3, grid.length);
            Assertions.assertEquals(3, grid[0].length);
            Assertions.assertEquals(0, robot.getxPosition());
            Assertions.assertEquals(0, robot.getyPosition());
            Assertions.assertEquals(0, robot.getDirection());
            Assertions.assertTrue(robot.isPenUp());
            Assertions.assertTrue(function_called);
        }
    }
}