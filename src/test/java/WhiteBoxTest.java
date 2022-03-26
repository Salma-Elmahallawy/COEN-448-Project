//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WhiteBoxTest {
    private final ByteArrayOutputStream outputStreamCaptor_message = new ByteArrayOutputStream();

    WhiteBoxTest() {
    }

    @Test
    void testGridCreation() {
        Scanner sc1 = new Scanner("9");
        Main.setGrid(Main.gridCreation(Main.getGrid(), sc1));
        Assertions.assertEquals(9, Main.getGridSize());
        System.setOut(new PrintStream(this.outputStreamCaptor_message));
        Scanner sc2 = new Scanner("-1");
        Main.gridCreation(Main.getGrid(), sc2);
        Assertions.assertEquals("Invalid Input. Please enter an integer value greater than 0 and less than a 100\r\n", this.outputStreamCaptor_message.toString());
        this.outputStreamCaptor_message.reset();
        Scanner sc3 = new Scanner("101");
        Main.gridCreation(Main.getGrid(), sc3);
        Assertions.assertEquals("Invalid Input. Please enter an integer value greater than 0 and less than a 100\r\n", this.outputStreamCaptor_message.toString());
        this.outputStreamCaptor_message.reset();
    }

    @Test
    void testGridCreationWithSpace() {
        Scanner sc = new Scanner(" ");
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Main.gridCreation(Main.getGrid(), sc);
        });
    }

    @Test
    void testInitialize() {
        Main.initialize(9);
        Assertions.assertEquals(Main.robot.getxPosition(), 0);
        Assertions.assertEquals(Main.robot.getyPosition(), 0);
        Assertions.assertEquals(Main.robot.getDirection(), 0);
        Assertions.assertEquals(Main.robot.isPenUp(), true);
    }

    @Test
    void testPrintRobotInfo() {
        System.setOut(new PrintStream(this.outputStreamCaptor_message));
        Main.robot.setxPosition(5);
        Main.robot.setyPosition(5);
        Main.robot.setPenUp(true);
        Main.robot.setDirection(1);
        Main.printRobotInfo();
        Assertions.assertEquals("Current Position: (5,5)\r\nPen Position: Up\r\nPen Direction: East\r\n", this.outputStreamCaptor_message.toString());
        this.outputStreamCaptor_message.reset();
        Main.robot.setxPosition(5);
        Main.robot.setyPosition(5);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(2);
        Main.printRobotInfo();
        Assertions.assertEquals("Current Position: (5,5)\r\nPen Position: Down\r\nPen Direction: South\r\n", this.outputStreamCaptor_message.toString());
        this.outputStreamCaptor_message.reset();
    }

    @Test
    void testPrintCommands() {
        String[] allCommands = new String[]{"U", "D", "R", "L", "M s OR M0s", "P", "C", "Q", "I s or I0s"};
        System.setOut(new PrintStream(this.outputStreamCaptor_message));
        Main.PrintCommands(allCommands);
        Assertions.assertEquals("Invalid Command. Please use one of the following commands:\r\nU\r\nD\r\nR\r\nL\r\nM s OR M0s\r\nP\r\nC\r\nQ\r\nI s or I0s\r\n", this.outputStreamCaptor_message.toString());
        this.outputStreamCaptor_message.reset();
    }

    @Test
    public void testCommandInput() {
        ByteArrayOutputStream outputStreamCaptor_message = new ByteArrayOutputStream();
        Main.initialize(10);
        int[][] array = new int[10][10];
        Main.robot.setPenUp(false);
        Main.CommandInput("U", 10, array);
        Assertions.assertTrue(Main.robot.isPenUp());
        Main.CommandInput("D", 10, array);
        Assertions.assertFalse(Main.robot.isPenUp());
        Main.CommandInput("R", 10, array);
        Assertions.assertEquals(1, Main.robot.getDirection());
        Main.CommandInput("L", 10, array);
        Assertions.assertEquals(0, Main.robot.getDirection());
        System.setOut(new PrintStream(outputStreamCaptor_message));
        Main.CommandInput("P", 10, array);
        Assertions.assertEquals("---+---+---+---+---+---+---+---+---+---+---+\r\n9  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n8  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n7  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n6  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n5  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n4  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n3  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n2  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n1  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n0  |   |   |   |   |   |   |   |   |   |   |\n---+---+---+---+---+---+---+---+---+---+---+\r\n     0   1   2   3   4   5   6   7   8   9   \r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
        Main.CommandInput("C", 10, array);
        Assertions.assertEquals("Current Position: (0,0)\r\nPen Position: Down\r\nPen Direction: North\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
        Main.CommandInput("Q", 10, array);
        Assertions.assertEquals("Exiting Program\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
        Main.CommandInput("", 10, array);
        Assertions.assertEquals("Wrong Command Input\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
    }

    @Test
    public void testMoveForward() {
        ByteArrayOutputStream outputStreamCaptor_message = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor_message));
        int numOfSpaces = 5;
        int gridSize = 10;
        int[][] grid = new int[gridSize][gridSize];
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        Main.robot.setDirection(0);
        Main.robot.setxPosition(0);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(0);
        Main.robot.setPenUp(true);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertTrue(Main.robot.isPenUp());
        int[][] array = new int[10][10];
        Main.printGrid(Main.getGrid(), 5);

        int i;
        int j;
        for(i = 0; i <= Main.getGrid().length - 1; ++i) {
            for(j = 0; j <= Main.getGrid().length - 1; ++j) {
                Assertions.assertEquals(array[i][j], Main.getGrid()[i][j]);
            }
        }

        outputStreamCaptor_message.reset();
        Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        Main.robot.setxPosition(0);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(0);
        Main.robot.setPenUp(false);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertFalse(Main.robot.isPenUp());
        array = new int[10][10];

        for(i = 5; i >= 0; --i) {
            array[0][i] = 1;
        }

        Main.printGrid(Main.getGrid(), 10);

        for(i = 0; i <= Main.getGrid().length - 1; ++i) {
            for(j = 0; j <= Main.getGrid().length - 1; ++j) {
                Assertions.assertEquals(array[i][j], Main.getGrid()[i][j]);
            }
        }

        outputStreamCaptor_message.reset();
        Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());
        Main.robot.setxPosition(0);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(9);
        Main.robot.setPenUp(true);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals(9, Main.robot.getyPosition());
        Assertions.assertTrue(Main.robot.isPenUp());
        Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        Main.robot.setxPosition(0);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(9);
        Main.robot.setPenUp(false);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals(9, Main.robot.getyPosition());
        Assertions.assertFalse(Main.robot.isPenUp());
        Assertions.assertEquals("Invalid move (out of bound)\r\n", outputStreamCaptor_message.toString());
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        Main.robot.setDirection(1);
        Main.robot.setxPosition(0);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(0);
        Main.robot.setPenUp(false);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(5, Main.robot.getxPosition());
        Assertions.assertEquals(0, Main.robot.getyPosition());
        Assertions.assertFalse(Main.robot.isPenUp());
        Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        Main.robot.setDirection(1);
        Main.robot.setxPosition(9);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(0);
        Main.robot.setPenUp(false);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(9, Main.robot.getxPosition());
        Assertions.assertEquals(0, Main.robot.getyPosition());
        Assertions.assertFalse(Main.robot.isPenUp());
        Assertions.assertEquals("Invalid move (out of bound)\r\n", outputStreamCaptor_message.toString());
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        Main.robot.setDirection(2);
        Main.robot.setxPosition(0);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(9);
        Main.robot.setPenUp(false);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals(4, Main.robot.getyPosition());
        Assertions.assertFalse(Main.robot.isPenUp());
        Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        Main.robot.setDirection(2);
        Main.robot.setxPosition(0);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(2);
        Main.robot.setPenUp(false);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals(2, Main.robot.getyPosition());
        Assertions.assertFalse(Main.robot.isPenUp());
        Assertions.assertEquals("Invalid move (out of bound)\r\n", outputStreamCaptor_message.toString());
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        Main.robot.setDirection(3);
        Main.robot.setxPosition(5);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(0);
        Main.robot.setPenUp(false);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals(0, Main.robot.getyPosition());
        Assertions.assertFalse(Main.robot.isPenUp());
        Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        Main.robot.setDirection(3);
        Main.robot.setxPosition(3);
        outputStreamCaptor_message.reset();
        Main.robot.setyPosition(0);
        Main.robot.setPenUp(true);
        Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
        Assertions.assertEquals(3, Main.robot.getxPosition());
        Assertions.assertEquals(0, Main.robot.getyPosition());
        Assertions.assertTrue(Main.robot.isPenUp());
        Assertions.assertEquals("Invalid move (out of bound)\r\n", outputStreamCaptor_message.toString());
    }

    @Test
    public void testCommandInput_M() {

        int gridSize = 10;
        Main.initialize(10);
        Main.setGrid(new int[gridSize][gridSize]);
        ByteArrayOutputStream outputStreamCaptor_message = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor_message));

        //line 1-2-3- 4-end
        //t3
        Main.CommandInput_M("M05", gridSize, Main.getGrid());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertEquals(0, Main.robot.getxPosition());

        //1-2-3-5..not in specified format of reqs
        //t5
        Main.CommandInput_M("M005", gridSize, Main.getGrid());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

        //1-2-3-5 ...isDigit(2)=False
        //t4
        Main.CommandInput_M("M0F", gridSize, Main.getGrid());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

        //1-2-6-7-end
        //t2
        Main.initialize(10);
        Main.CommandInput_M("M5", gridSize, Main.getGrid());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertEquals(0, Main.robot.getxPosition());

        // 1-2-6-8-end
        //t1
        Main.CommandInput_M("ML",gridSize,  Main.getGrid());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

        //F causes it to throw number out of bounds exception 1-2-6-7-9
        //t7
        Main.CommandInput_M("M5F",gridSize,  Main.getGrid());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

        //F causes number format exception from ParseInt exception 1-2-3-4-9
        //t8
        Main.CommandInput_M("M05F",gridSize,  Main.getGrid());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

        //index out of bounds of exception lines 1-2-9
        //t6
        Main.setGrid(new int[gridSize][gridSize]);
        Main.CommandInput_M("M", gridSize, Main.getGrid());
        Assertions.assertEquals(5, Main.robot.getyPosition());
        Assertions.assertEquals(0, Main.robot.getxPosition());
        Assertions.assertEquals("Invalid Input. Please enter an positive integer value\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
    }
}
