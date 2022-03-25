import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackBoxTest {
    private final ByteArrayOutputStream outputStreamCaptor_message = new ByteArrayOutputStream();

    // R3: Pen-Up
    @Test
    public void testPenUp(){

        //Test Case: A1
        Main.initialize(5);
        Main.robot.setPenUp(true);
        Main.CommandInput("U",2,new int[2][2]);
        Assertions.assertTrue(Main.robot.isPenUp());
        //Test Case: A2
        Main.initialize(5);
        Main.robot.setPenUp(false);
        Main.CommandInput("U",2,new int[2][2]);
        Assertions.assertTrue(Main.robot.isPenUp());
    }

    // R4: Pen-Down
    @Test
    public void testPenDown(){

        //Test Case: A1
        Main.initialize(5);
        Main.robot.setPenUp(true);
        Main.CommandInput("D",2,new int[2][2]);
        Assertions.assertFalse(Main.robot.isPenUp());
        //Test Case: A2
        Main.initialize(5);
        Main.robot.setPenUp(false);
        Main.CommandInput("D",2,new int[2][2]);
        Assertions.assertFalse(Main.robot.isPenUp());
    }

    // R5: Turn Right
    @Test
    public void testTurnRight(){
        Main.initialize(5);
        //A1
        Assertions.assertEquals(0, Main.robot.getDirection());
        //A3
        Main.CommandInput("R",2,new int[2][2]);
        Assertions.assertEquals(1, Main.robot.getDirection());
        //A2
        Main.CommandInput("R",2,new int[2][2]);
        Assertions.assertEquals(2, Main.robot.getDirection());
        //A4
        Main.CommandInput("R",2,new int[2][2]);
        Assertions.assertEquals(3, Main.robot.getDirection());
        //A1
//        Main.CommandInput("R",2,new int[2][2]);
//        Assertions.assertEquals(0, Main.robot.getDirection());

        }


    @Test
    public void testTurnLeft(){
        Main.initialize(5);
        Main.robot.setDirection(3);
        //A4
        Assertions.assertEquals(3, Main.robot.getDirection());
        //A2
        Main.CommandInput("L",2,new int[2][2]);
        Assertions.assertEquals(2, Main.robot.getDirection());
        //A3
        Main.CommandInput("L",2,new int[2][2]);
        Assertions.assertEquals(1, Main.robot.getDirection());
        //A1
        Main.CommandInput("L",2,new int[2][2]);
        Assertions.assertEquals(0, Main.robot.getDirection());
        //A4
//        Main.CommandInput("L",2,new int[2][2]);
//        Assertions.assertEquals(3, Main.robot.getDirection());



    }

    // R7: Move Forward
    @Test
    public void testMoveForward(){
    //Base Choice Set #1
        //A2,B2,C1,D2
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(0);
        Main.robot.setxPosition(3);
        Main.robot.setyPosition(2);
        Main.moveForward(2,5,Main.getGrid());
        Assertions.assertEquals(3,Main.robot.getxPosition());
        Assertions.assertEquals(4,Main.robot.getyPosition());
        int[][] array = new int[5][5];
        for(int i = 2; i <=4; i++) {
            array[3][i]=1;
        }
        Main.printGrid(Main.getGrid(),5);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A3,B2,C1,D2
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(0);
        Main.robot.setxPosition(4);
        Main.robot.setyPosition(0);
        Main.moveForward(2,5,Main.getGrid());
        Assertions.assertEquals(4,Main.robot.getxPosition());
        Assertions.assertEquals(2,Main.robot.getyPosition());
        array = new int[5][5];
        for(int i = 0; i <= 2; i++) {
            array[4][i]=1;
        }
        Main.printGrid(Main.getGrid(),5);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A2,B1,C1,D2
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(true);
        Main.robot.setDirection(0);
        Main.robot.setxPosition(3);
        Main.robot.setyPosition(2);
        Main.moveForward(2,5,Main.getGrid());
        Assertions.assertEquals(3,Main.robot.getxPosition());
        Assertions.assertEquals(4,Main.robot.getyPosition());
        array = new int[5][5];
        Main.printGrid(Main.getGrid(),5);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A2,B2,C2,D2
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(2);
        Main.robot.setxPosition(3);
        Main.robot.setyPosition(2);
        Main.moveForward(1,5,Main.getGrid());
        Assertions.assertEquals(3,Main.robot.getxPosition());
        Assertions.assertEquals(1,Main.robot.getyPosition());
        array = new int[5][5];
        for(int i = 2; i >=1; i--) {
            array[3][i]=1;
        }
        Main.printGrid(Main.getGrid(),5);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A2,B2,C3,D2
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(1);
        Main.robot.setxPosition(2);
        Main.robot.setyPosition(2);
        Main.moveForward(1,5,Main.getGrid());
        Assertions.assertEquals(3,Main.robot.getxPosition());
        Assertions.assertEquals(2,Main.robot.getyPosition());
        array = new int[5][5];
        for(int i = 2; i <= 3; i++) {
            array[i][2]=1;
        }
        Main.printGrid(Main.getGrid(),5);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A2,B2,C4,D2
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(3);
        Main.robot.setxPosition(3);
        Main.robot.setyPosition(2);
        Main.moveForward(2,5,Main.getGrid());
        Assertions.assertEquals(1,Main.robot.getxPosition());
        Assertions.assertEquals(2,Main.robot.getyPosition());
        array = new int[5][5];
        for(int i = 3; i >=1; i--) {
            array[i][2]=1;
        }
        Main.printGrid(Main.getGrid(),5);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A2,B2,C1,D1
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(0);
        Main.robot.setxPosition(3);
        Main.robot.setyPosition(2);
        Main.moveForward(0,5,Main.getGrid());
        Assertions.assertEquals(3,Main.robot.getxPosition());
        Assertions.assertEquals(2,Main.robot.getyPosition());
        array = new int[5][5];
        for(int i = 2; i <=2; i++) {
            array[3][i]=1;
        }
        Main.printGrid(Main.getGrid(),5);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A2,B2,C1,D3
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(0);
        Main.robot.setxPosition(3);
        Main.robot.setyPosition(2);
        Main.moveForward(2,5,Main.getGrid());
        Assertions.assertEquals(3,Main.robot.getxPosition());
        Assertions.assertEquals(4,Main.robot.getyPosition());
        array = new int[5][5];
        for(int i = 2; i <= 4; i++) {
            array[3][i]=1;
        }
        Main.printGrid(Main.getGrid(),5);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A2,B2,C1,D4
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(0);
        Main.robot.setxPosition(3);
        Main.robot.setyPosition(2);
        System.setOut(new PrintStream(outputStreamCaptor_message));
        Main.moveForward(3,5,Main.getGrid());
        //check 3 prints of floor must be initialized
        String expectedOutput  = "Invalid move (out of bound)\r\n";
        assertEquals(expectedOutput, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
        array = new int[5][5];
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A2,B2,C1,D5
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(false);
        Main.robot.setDirection(0);
        Main.robot.setxPosition(3);
        Main.robot.setyPosition(2);
        System.setOut(new PrintStream(outputStreamCaptor_message));
        Main.moveForward(101,5,Main.getGrid());
        //check 3 prints of floor must be initialized
        expectedOutput  = "Invalid move (out of bound)\r\n";
        assertEquals(expectedOutput, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
        array = new int[5][5];
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
    //Base Choice Set #2
        //A1,B1,C4,D4
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(true);
        Main.robot.setDirection(3);
        Main.robot.setxPosition(0);
        Main.robot.setyPosition(4);
        System.setOut(new PrintStream(outputStreamCaptor_message));
        Main.moveForward(5,5,Main.getGrid());
        expectedOutput  = "Invalid move (out of bound)\r\n";
        assertEquals(expectedOutput, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
        array = new int[5][5];
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A1,B1,C1,D4
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(true);
        Main.robot.setDirection(0);
        Main.robot.setxPosition(0);
        Main.robot.setyPosition(4);
        System.setOut(new PrintStream(outputStreamCaptor_message));
        Main.moveForward(20,5,Main.getGrid());
        expectedOutput  = "Invalid move (out of bound)\r\n";
        assertEquals(expectedOutput, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
        array = new int[5][5];
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A1,B1,C2,D4
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(true);
        Main.robot.setDirection(2);
        Main.robot.setxPosition(0);
        Main.robot.setyPosition(4);
        System.setOut(new PrintStream(outputStreamCaptor_message));
        Main.moveForward(20,5,Main.getGrid());
        expectedOutput  = "Invalid move (out of bound)\r\n";
        assertEquals(expectedOutput, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
        array = new int[5][5];
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A1,B1,C3,D4
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(true);
        Main.robot.setDirection(1);
        Main.robot.setxPosition(0);
        Main.robot.setyPosition(4);
        System.setOut(new PrintStream(outputStreamCaptor_message));
        Main.moveForward(20,5,Main.getGrid());
        expectedOutput  = "Invalid move (out of bound)\r\n";
        assertEquals(expectedOutput, outputStreamCaptor_message.toString());
        outputStreamCaptor_message.reset();
        array = new int[5][5];
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }
        //A1,B1,C4,D1
        Main.initialize(5);
        Main.setGrid(new int [5][5]);
        Main.robot.setPenUp(true);
        Main.robot.setDirection(3);
        Main.robot.setxPosition(0);
        Main.robot.setyPosition(4);
        Main.moveForward(0,5,Main.getGrid());
        Assertions.assertEquals(0,Main.robot.getxPosition());
        Assertions.assertEquals(4,Main.robot.getyPosition());
        array = new int[5][5];
        Main.printGrid(Main.getGrid(),5);
        for(int i = 0; i <= Main.getGrid().length - 1; i++){
            for(int j = 0; j <= Main.getGrid().length - 1; j++){
                assertEquals(array[i][j],  Main.getGrid()[i][j]);
            }
        }

    }
}
