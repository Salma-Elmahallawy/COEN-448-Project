import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.NoSuchElementException;

class WhiteBoxTest {
    private final ByteArrayOutputStream outputStreamCaptor_message = new ByteArrayOutputStream();
    @Test
    void testGridCreation() {

        // Acceptable grid size: Between 0 and 100
        Scanner sc1 = new Scanner("9");
        Main.setGrid(Main.gridCreation(Main.getGrid(), sc1));
        Assertions.assertEquals(9, Main.getGridSize());

        System.setOut(new PrintStream(outputStreamCaptor_message));

        // Invalid grid size: Less than 0
        Scanner sc2 = new Scanner("-1");
        Main.gridCreation(Main.getGrid(), sc2);
        Assertions.assertEquals("Invalid Input. Please enter an integer value greater than 0 and less than a 100\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

        // Invalid grid size: More than 100
        Scanner sc3 = new Scanner("101");
        Main.gridCreation(Main.getGrid(), sc3);
        Assertions.assertEquals("Invalid Input. Please enter an integer value greater than 0 and less than a 100\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

    }

    @Test
    void testGridCreationWithSpace() {
        // Invalid grid size: Empty space
        Scanner sc = new Scanner(" ");
        Assertions.assertThrows(NoSuchElementException.class, () -> { Main.gridCreation(Main.getGrid(), sc);});

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
        // Test Print with Pen Up

        System.setOut(new PrintStream(outputStreamCaptor_message));

        Main.robot.setxPosition(5);
        Main.robot.setyPosition(5);
        Main.robot.setPenUp(true);
        Main.robot.setDirection(1);
        Main.printRobotInfo();

        Assertions.assertEquals("""
                            Current Position: (5,5)\r
                            Pen Position: Up\r
                            Pen Direction: East\r
                            """, outputStreamCaptor_message.toString());

        outputStreamCaptor_message.reset();

        // Test Print with Pen Down
        Main.robot.setxPosition(5);
        Main.robot.setyPosition(5);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(2);
        Main.printRobotInfo();

        Assertions.assertEquals("""
                            Current Position: (5,5)\r
                            Pen Position: Down\r
                            Pen Direction: South\r
                            """, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
    }

    @Test
    void testPrintCommands() {
        String[] allCommands = {"U", "D", "R", "L", "M s OR M0s", "P", "C", "Q", "I s or I0s"};
        System.setOut(new PrintStream(outputStreamCaptor_message));
        Main.PrintCommands(allCommands);

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
    public void testCommandInput() {
        ByteArrayOutputStream outputStreamCaptor_message = new ByteArrayOutputStream();
        Main.initialize(10);
        int[][] array = new int[10][10];
        //t1
        Main.robot.setPenUp(false);
        Main.CommandInput("U", 10, array);
        Assertions.assertTrue(Main.robot.isPenUp());
        Main.CommandInput("D", 10, array);
        Assertions.assertFalse(Main.robot.isPenUp());
        Main.CommandInput("R", 10, array);
        Assertions.assertEquals(1, 1);
        Main.CommandInput("L", 10, array);
        Assertions.assertEquals(0, 0);
        Main.CommandInput("P", 10, array);
        Assertions.assertEquals(0, 0);

        System.setOut(new PrintStream(outputStreamCaptor_message));

        Main.CommandInput("P", 10, array);
        Assertions.assertEquals("""
                ---+---+---+---+---+---+---+---+---+---+---+\r
                9  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                8  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                7  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                6  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                5  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                4  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                3  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                2  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                1  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                0  |   |   |   |   |   |   |   |   |   |   |
                ---+---+---+---+---+---+---+---+---+---+---+\r
                     0   1   2   3   4   5   6   7   8   9   \r
                """, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

        Main.CommandInput("C", 10, array);
        Assertions.assertEquals("""
                Current Position: (0,0)\r
                Pen Position: Down\r
                Pen Direction: North\r
                """, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

        Main.CommandInput("Q", 10, array);
        Assertions.assertEquals("Exiting Program\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();

        Main.CommandInput("", 10, array);
        Assertions.assertEquals("Wrong Command Input\r\n", outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
    }

    @Test
    public void testMoveForward()
    {
    ByteArrayOutputStream outputStreamCaptor_message = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStreamCaptor_message));

      int numOfSpaces = 5;
      int gridSize = 10;
      int[][] grid = new int [gridSize][gridSize];

      //test case: pen up facing north move within border
      Main.initialize(10);
      Main.setGrid(new int [gridSize][gridSize]);
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
      Main.printGrid(Main.getGrid(),5);
      for(int i = 0; i <= Main.getGrid().length - 1; i++){
          for(int j = 0; j <= Main.getGrid().length - 1; j++){
              Assertions.assertEquals(array[i][j],  Main.getGrid()[i][j]);
          }
      }
      outputStreamCaptor_message.reset();
      Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

      //test case: pen down facing north move inside border
       Main.initialize(10);
       Main.setGrid(new int [gridSize][gridSize]);
       Main.robot.setxPosition(0);
      outputStreamCaptor_message.reset();
      Main.robot.setyPosition(0);
      Main.robot.setPenUp(false);
      Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
      Assertions.assertEquals(0, Main.robot.getxPosition());
      Assertions.assertEquals(5, Main.robot.getyPosition());
      Assertions.assertFalse(Main.robot.isPenUp());
        array = new int[10][10];
        for(int i = 5; i >=0; i--) {
            array[0][i]=1;
        }
        Main.printGrid(Main.getGrid(),10);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                Assertions.assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        outputStreamCaptor_message.reset();
        Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

      //test case: pen up facing north move inside border
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
      Main.setGrid(new int [gridSize][gridSize]);
      Main.robot.setxPosition(0);
      outputStreamCaptor_message.reset();
      Main.robot.setyPosition(9);
      Main.robot.setPenUp(false);
      Main.moveForward(numOfSpaces, gridSize, Main.getGrid());
      Assertions.assertEquals(0, Main.robot.getxPosition());
      Assertions.assertEquals(9, Main.robot.getyPosition());
      Assertions.assertFalse(Main.robot.isPenUp());
      Assertions.assertEquals("Invalid move (out of bound)\r\n", outputStreamCaptor_message.toString());

        //test case: pen down facing east move inside border
        Main.initialize(10);
        Main.setGrid(new int [gridSize][gridSize]);
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

        //test case: pen down facing east move inside border
        Main.initialize(10);
        Main.setGrid(new int [gridSize][gridSize]);
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

        //test case: pen down facing south move inside border
        Main.initialize(10);
        Main.setGrid(new int [gridSize][gridSize]);
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

        //test case: pen down facing south move inside border
        Main.initialize(10);
        Main.setGrid(new int [gridSize][gridSize]);
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


        //test case: pen down facing west move inside border
        Main.initialize(10);
        Main.setGrid(new int [gridSize][gridSize]);
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

        //test case: pen down facing west move inside border
        Main.initialize(10);
        Main.setGrid(new int [gridSize][gridSize]);
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
}
