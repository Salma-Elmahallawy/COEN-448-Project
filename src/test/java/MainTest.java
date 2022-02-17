import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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
                    Assertions.assertEquals("""
                            ---+---+---+\r
                            1  |   |   |
                            ---+---+---+\r
                            0  |   |   |
                            ---+---+---+\r
                                 0   1   \r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 1:
                    int[][] startGrid = new int [2][2];
                    startGrid[1][1] = 1;
                    startGrid[0][0] = 1;

                    Main.printGrid(startGrid, 2);
                    Assertions.assertEquals("""
                            ---+---+---+\r
                            1  |   | * |
                            ---+---+---+\r
                            0  | * |   |
                            ---+---+---+\r
                                 0   1   \r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();
                case 2:
                    Main.printGrid(new int[15][15], 15);
                    Assertions.assertEquals("""
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            14 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            13 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            12 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            11 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            10 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            9  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            8  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            7  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            6  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            5  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            4  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            3  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            2  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            1  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                            0  |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\r
                                 0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  \r
                            """, outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 3:
                    int[][] starGrid = new int [11][11];
                    starGrid[1][1] = 1;
                    starGrid[10][10] = 1;

                    Main.printGrid(starGrid, 11);
                    Assertions.assertEquals("""
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            10 |   |   |   |   |   |   |   |   |   |   | * |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            9  |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            8  |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            7  |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            6  |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            5  |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            4  |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            3  |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            2  |   |   |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            1  |   | * |   |   |   |   |   |   |   |   |   |
                            ---+---+---+---+---+---+---+---+---+---+---+---+\r
                            0  |   |   |   |   |   |   |   |   |   |   |   |
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

        //Invalid Inputs
        String input1 = "M";
        String input2 = "M0";
        String input3 = "m0s";
        String input4 = "m1s";
        String input5 = "m01s";

        //Valid Inputs
        String input6 = "M 3";
        String input7 = "M03";

        outputStreamCaptor_message.reset();

        for(int i = 1; i < 8; i++){
            switch (i){
                case 1:
                    //Main.CommandInput_M(input1, 5, new int[5][5]);
                    Assertions.assertThrows(StringIndexOutOfBoundsException.class, ()-> {
                        Main.CommandInput_M(input1, 5, new int [5][5]);
                    });
                    outputStreamCaptor_message.reset();

                case 2:
                    Main.CommandInput_M(input2, 5, new int[5][5]);
                    Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 3:
                    Main.CommandInput_M(input3, 5, new int[5][5]);
                    Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
                    Assertions.assertThrows(NumberFormatException.class, ()-> {
                        Integer.parseInt(input3.substring(2));
                    });
                    outputStreamCaptor_message.reset();

                case 4:
                    Main.CommandInput_M(input4, 5, new int[5][5]);
                    Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
                    Assertions.assertThrows(NumberFormatException.class, ()-> {
                        Integer.parseInt(input4.substring(1));
                    });
                    outputStreamCaptor_message.reset();

                case 5:
                    Main.CommandInput_M(input5, 5, new int[5][5]);
                    Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
                    Assertions.assertThrows(NumberFormatException.class, ()-> {
                        Integer.parseInt(input5.substring(2));
                    });
                    outputStreamCaptor_message.reset();

                case 6:
                    Main.CommandInput_M(input6, 5, new int[5][5]);
                    //mockito
                    outputStreamCaptor_message.reset();

                case 7:
                    Main.CommandInput_M(input7, 5, new int[5][5]);
                    //mockito
                    outputStreamCaptor_message.reset();
            }
        }

    }

    @Test
    public void CommandInput_I() {

        //Invalid Inputs
        String input1 = "I";
        String input2 = "I0";
        String input3 = "i0s";
        String input4 = "i1s";
        String input5 = "i01s";

        //Valid Inputs
        String input6 = "I 3";
        String input7 = "I03";

        outputStreamCaptor_message.reset();

        for (int i = 1; i < 8; i++) {
            switch (i) {
                case 1:
                    Assertions.assertThrows(StringIndexOutOfBoundsException.class, ()-> {
                        Main.CommandInput_I(input1, new int [5][5]);
                    });
                    outputStreamCaptor_message.reset();

                case 2:
                    Main.CommandInput_I(input2, new int[5][5]);
                    Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
                    outputStreamCaptor_message.reset();

                case 3:
                    Main.CommandInput_I(input3, new int[5][5]);
                    Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
                    Assertions.assertThrows(NumberFormatException.class, () -> {
                        Integer.parseInt(input3.substring(2));
                    });
                    outputStreamCaptor_message.reset();

                case 4:
                    Main.CommandInput_I(input4, new int[5][5]);
                    Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
                    Assertions.assertThrows(NumberFormatException.class, () -> {
                        Integer.parseInt(input4.substring(1));
                    });
                    outputStreamCaptor_message.reset();

                case 5:
                    Main.CommandInput_I(input5, new int [5][5]);
                    Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
                    Assertions.assertThrows(NumberFormatException.class, () -> {
                        Integer.parseInt(input5.substring(2));
                    });
                    outputStreamCaptor_message.reset();

                case 6:
                    Main.CommandInput_I(input6, new int[5][5]);
                    //mockito
                    outputStreamCaptor_message.reset();
                case 7:
                    Main.CommandInput_I(input7, new int[5][5]);
                    //mockito
                    outputStreamCaptor_message.reset();
            }
        }
    }
}