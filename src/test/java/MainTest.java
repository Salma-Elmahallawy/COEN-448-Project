import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        String input = "";
        int[][] grid = new int[0][];

        for (int i = 0; i < 2; i++){
            if (i == 0)
                input = "5";
            else
                input = "notaNumber";

            try{
                Scanner scanner = new Scanner(input);
                int gridSize = scanner.nextInt();
                if(gridSize > 0)
                    grid = new int[gridSize][gridSize];

                Assertions.assertEquals(5, gridSize);
                Assertions.assertEquals(5, grid.length);
                Assertions.assertEquals(5, grid[0].length);
            }
            catch (InputMismatchException e){
                outputStreamCaptor_message.reset();
                System.out.println("Invalid Input. Please enter an integer value greater than 0");
                Assertions.assertEquals("Invalid Input. Please enter an integer value greater than 0", outputStreamCaptor_message.toString().trim() );
                Assertions.assertThrows(InputMismatchException.class, ()-> {
                    Scanner scanner = new Scanner("not a number");
                    scanner.nextInt();
                });
                Assertions.assertEquals("notaNumber", input);
            }
        }
    }

    @Test
    public void initialize() {
        Robot robot = new Robot();
        robot.setxPosition(0);
        robot.setyPosition(0);
        robot.setDirection(0);
        robot.setPenUp(true);

        Assertions.assertEquals(robot.getxPosition(), 0);
        Assertions.assertEquals(robot.getyPosition(), 0);
        Assertions.assertEquals(robot.getDirection(), 0);
        Assertions.assertTrue(robot.isPenUp());
    }

    @Test
    public void printRobotInfo() {

        Robot robot = new Robot();
        String[] robotDirection = {"North", "East", "South", "West"};
        robot.setyPosition(3);
        robot.setxPosition(4);

        outputStreamCaptor_message.reset();
        System.out.println("Current Position: (" + robot.getxPosition() + "," + robot.getyPosition() + ")");
        Assertions.assertEquals("Current Position: (" + 4 + "," + 3 + ")", outputStreamCaptor_message.toString().trim());

        for(int i = 0; i < 8; i++) {
            switch (i){
                case 0:
                    robot.setPenUp(true);
                    robot.setDirection(0);
                    if (robot.isPenUp()) {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Up");
                        Assertions.assertEquals("Pen Position: Up", outputStreamCaptor_message.toString().trim());
                    } else {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Down");
                        Assertions.assertEquals("Pen Position: Down", outputStreamCaptor_message.toString().trim());
                    }

                    outputStreamCaptor_message.reset();
                    System.out.println("Pen Direction: " + robotDirection[((robot.getDirection()%4) + 4) % 4]);
                    Assertions.assertEquals("Pen Direction: " + "North", outputStreamCaptor_message.toString().trim());

                case 1:
                    robot.setPenUp(true);
                    robot.setDirection(1);
                    if (robot.isPenUp()) {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Up");
                        Assertions.assertEquals("Pen Position: Up", outputStreamCaptor_message.toString().trim());
                    } else {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Down");
                        Assertions.assertEquals("Pen Position: Down", outputStreamCaptor_message.toString().trim());
                    }

                    outputStreamCaptor_message.reset();
                    System.out.println("Pen Direction: " + robotDirection[((robot.getDirection()%4) + 4) % 4]);
                    Assertions.assertEquals("Pen Direction: " + "East", outputStreamCaptor_message.toString().trim());

                case 2:
                    robot.setPenUp(true);
                    robot.setDirection(2);
                    if (robot.isPenUp()) {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Up");
                        Assertions.assertEquals("Pen Position: Up", outputStreamCaptor_message.toString().trim());
                    } else {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Down");
                        Assertions.assertEquals("Pen Position: Down", outputStreamCaptor_message.toString().trim());
                    }

                    outputStreamCaptor_message.reset();
                    System.out.println("Pen Direction: " + robotDirection[((robot.getDirection()%4) + 4) % 4]);
                    Assertions.assertEquals("Pen Direction: " + "South", outputStreamCaptor_message.toString().trim());

                case 3:
                    robot.setPenUp(true);
                    robot.setDirection(3);
                    if (robot.isPenUp()) {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Up");
                        Assertions.assertEquals("Pen Position: Up", outputStreamCaptor_message.toString().trim());
                    } else {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Down");
                        Assertions.assertEquals("Pen Position: Down", outputStreamCaptor_message.toString().trim());
                    }

                    outputStreamCaptor_message.reset();
                    System.out.println("Pen Direction: " + robotDirection[((robot.getDirection()%4) + 4) % 4]);
                    Assertions.assertEquals("Pen Direction: " + "West", outputStreamCaptor_message.toString().trim());

                case 4:
                    robot.setPenUp(false);
                    robot.setDirection(0);
                    if (robot.isPenUp()) {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Up");
                        Assertions.assertEquals("Pen Position: Up", outputStreamCaptor_message.toString().trim());
                    } else {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Down");
                        Assertions.assertEquals("Pen Position: Down", outputStreamCaptor_message.toString().trim());
                    }

                    outputStreamCaptor_message.reset();
                    System.out.println("Pen Direction: " + robotDirection[((robot.getDirection()%4) + 4) % 4]);
                    Assertions.assertEquals("Pen Direction: " + "North", outputStreamCaptor_message.toString().trim());

                case 5:
                    robot.setPenUp(false);
                    robot.setDirection(1);
                    if (robot.isPenUp()) {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Up");
                        Assertions.assertEquals("Pen Position: Up", outputStreamCaptor_message.toString().trim());
                    } else {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Down");
                        Assertions.assertEquals("Pen Position: Down", outputStreamCaptor_message.toString().trim());
                    }

                    outputStreamCaptor_message.reset();
                    System.out.println("Pen Direction: " + robotDirection[((robot.getDirection()%4) + 4) % 4]);
                    Assertions.assertEquals("Pen Direction: " + "East", outputStreamCaptor_message.toString().trim());

                case 6:
                    robot.setPenUp(false);
                    robot.setDirection(2);
                    if (robot.isPenUp()) {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Up");
                        Assertions.assertEquals("Pen Position: Up", outputStreamCaptor_message.toString().trim());
                    } else {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Down");
                        Assertions.assertEquals("Pen Position: Down", outputStreamCaptor_message.toString().trim());
                    }

                    outputStreamCaptor_message.reset();
                    System.out.println("Pen Direction: " + robotDirection[((robot.getDirection()%4) + 4) % 4]);
                    Assertions.assertEquals("Pen Direction: " + "South", outputStreamCaptor_message.toString().trim());

                case 7:
                    robot.setPenUp(false);
                    robot.setDirection(3);
                    if (robot.isPenUp()) {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Up");
                        Assertions.assertEquals("Pen Position: Up", outputStreamCaptor_message.toString().trim());
                    } else {
                        outputStreamCaptor_message.reset();
                        System.out.println("Pen Position: Down");
                        Assertions.assertEquals("Pen Position: Down", outputStreamCaptor_message.toString().trim());
                    }

                    outputStreamCaptor_message.reset();
                    System.out.println("Pen Direction: " + robotDirection[((robot.getDirection()%4) + 4) % 4]);
                    Assertions.assertEquals("Pen Direction: " + "West", outputStreamCaptor_message.toString().trim());
            }
        }
    }

    @Test
    public void printGrid() {
        int gridSize = 2;
        int[][] grid = new int [gridSize][gridSize];

        int ytemp = gridSize - 1 , xtemp = 0;

        outputStreamCaptor_message.reset();

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

        Assertions.assertEquals("---+---+---+\r\n" +
                "1  |   |   |\r\n" +
                "---+---+---+\r\n" +
                "0  |   |   |\r\n" +
                "---+---+---+\r\n" +
                "     0   1", outputStreamCaptor_message.toString().trim());
    }

    @Test
    public void moveForward_North() {

        Robot robot = new Robot();
        int numOfSpaces = 3;
        int gridSize = 5;
        int[][] grid = new int [gridSize][gridSize];

        for(int n = 0; n < 4; n++) {
            robot.setxPosition(0);
            switch(n){
                case 0:
                    outputStreamCaptor_message.reset();
                    robot.setyPosition(0);
                    robot.setPenUp(true);
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

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(3, robot.getyPosition());
                    Assertions.assertTrue(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 1:
                    outputStreamCaptor_message.reset();
                    robot.setyPosition(0);
                    robot.setPenUp(false);
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

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(3, robot.getyPosition());
                    Assertions.assertFalse(robot.isPenUp());
                    Assertions.assertEquals(1, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 2:
                    outputStreamCaptor_message.reset();
                    robot.setyPosition(4);
                    robot.setPenUp(true);
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

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(4, robot.getyPosition());
                    Assertions.assertTrue(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());

                case 3:
                    outputStreamCaptor_message.reset();
                    robot.setyPosition(4);
                    robot.setPenUp(false);
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

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(4, robot.getyPosition());
                    Assertions.assertFalse(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());
            }
        }
    }

    @Test
    public void moveForward_East() {

        Robot robot = new Robot();
        int numOfSpaces = 3;
        int gridSize = 5;
        int[][] grid = new int [gridSize][gridSize];

        for(int n = 0; n < 4; n++) {
            robot.setyPosition(0);
            switch(n){
                case 0:
                    outputStreamCaptor_message.reset();
                    robot.setxPosition(0);
                    robot.setPenUp(true);
                    if((robot.getxPosition() + numOfSpaces) < gridSize) {
                        if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                            for(int i = 0; i <= numOfSpaces; i++) {
                                grid[robot.getxPosition() + i][robot.getyPosition()] = 1;
                            }
                            robot.setxPosition(robot.getxPosition() + numOfSpaces);
                        }else{ // robot moves normally without tracing
                            robot.setxPosition(robot.getxPosition() + numOfSpaces);
                        }
                    }else {
                        System.out.println("Invalid move (out of bound)");
                    }

                    Assertions.assertEquals(3, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertTrue(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 1:
                    outputStreamCaptor_message.reset();
                    robot.setxPosition(0);
                    robot.setPenUp(false);
                    if((robot.getxPosition() + numOfSpaces) < gridSize) {
                        if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                            for(int i = 0; i <= numOfSpaces; i++) {
                                grid[robot.getxPosition() + i][robot.getyPosition()] = 1;
                            }
                            robot.setxPosition(robot.getxPosition() + numOfSpaces);
                        }else{ // robot moves normally without tracing
                            robot.setxPosition(robot.getxPosition() + numOfSpaces);
                        }
                    }else {
                        System.out.println("Invalid move (out of bound)");
                    }

                    Assertions.assertEquals(3, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertFalse(robot.isPenUp());
                    Assertions.assertEquals(1, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 2:
                    outputStreamCaptor_message.reset();
                    robot.setxPosition(4);
                    robot.setPenUp(true);
                    if((robot.getxPosition() + numOfSpaces) < gridSize) {
                        if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                            for(int i = 0; i <= numOfSpaces; i++) {
                                grid[robot.getxPosition() + i][robot.getyPosition()] = 1;
                            }
                            robot.setxPosition(robot.getxPosition() + numOfSpaces);
                        }else{ // robot moves normally without tracing
                            robot.setxPosition(robot.getxPosition() + numOfSpaces);
                        }
                    }else {
                        System.out.println("Invalid move (out of bound)");
                    }

                    Assertions.assertEquals(4, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertTrue(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());

                case 3:
                    outputStreamCaptor_message.reset();
                    robot.setxPosition(4);
                    robot.setPenUp(false);
                    if((robot.getxPosition() + numOfSpaces) < gridSize) {
                        if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                            for(int i = 0; i <= numOfSpaces; i++) {
                                grid[robot.getxPosition() + i][robot.getyPosition()] = 1;
                            }
                            robot.setxPosition(robot.getxPosition() + numOfSpaces);
                        }else{ // robot moves normally without tracing
                            robot.setxPosition(robot.getxPosition() + numOfSpaces);
                        }
                    }else {
                        System.out.println("Invalid move (out of bound)");
                    }

                    Assertions.assertEquals(4, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertFalse(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());
            }
        }
    }

    @Test
    public void moveForward_South(){
        Robot robot = new Robot();
        int numOfSpaces = 3;
        int gridSize = 5;
        int[][] grid = new int [gridSize][gridSize];

        for(int n = 0; n < 4; n++) {
            robot.setxPosition(0);
            switch(n){
                case 0:
                    outputStreamCaptor_message.reset();
                    robot.setyPosition(4);
                    robot.setPenUp(true);
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

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(1, robot.getyPosition());
                    Assertions.assertTrue(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 1:


                    robot.setyPosition(4);
                    robot.setPenUp(false);
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

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(1, robot.getyPosition());
                    Assertions.assertFalse(robot.isPenUp());
                    Assertions.assertEquals(1, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 2:
                    outputStreamCaptor_message.reset();
                    robot.setyPosition(0);
                    robot.setPenUp(true);
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

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertTrue(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());

                case 3:
                    outputStreamCaptor_message.reset();
                    robot.setyPosition(0);
                    robot.setPenUp(false);
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

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertFalse(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());
            }
        }
    }

    @Test
    public void moveForward_West() {

        Robot robot = new Robot();
        int numOfSpaces = 3;
        int gridSize = 5;
        int[][] grid = new int [gridSize][gridSize];

        for(int n = 0; n < 4; n++) {
            robot.setyPosition(0);
            switch(n){
                case 0:
                    outputStreamCaptor_message.reset();
                    robot.setxPosition(4);
                    robot.setPenUp(true);
                    if((robot.getxPosition() - numOfSpaces) >= 0) {
                        if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                            for(int i = 0; i <= numOfSpaces; i++) {
                                grid[robot.getxPosition() - i][robot.getyPosition()] = 1;
                            }
                            robot.setxPosition(robot.getxPosition() - numOfSpaces);
                        }else{ // robot moves normally without tracing
                            robot.setxPosition(robot.getxPosition() - numOfSpaces);
                        }
                    }else {
                        System.out.println("Invalid move (out of bound)");
                    }

                    Assertions.assertEquals(1, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertTrue(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 1:
                    outputStreamCaptor_message.reset();
                    robot.setxPosition(4);
                    robot.setPenUp(false);
                    if((robot.getxPosition() - numOfSpaces) >= 0) {
                        if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                            for(int i = 0; i <= numOfSpaces; i++) {
                                grid[robot.getxPosition() - i][robot.getyPosition()] = 1;
                            }
                            robot.setxPosition(robot.getxPosition() - numOfSpaces);
                        }else{ // robot moves normally without tracing
                            robot.setxPosition(robot.getxPosition() - numOfSpaces);
                        }
                    }else {
                        System.out.println("Invalid move (out of bound)");
                    }

                    Assertions.assertEquals(1, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertFalse(robot.isPenUp());
                    Assertions.assertEquals(1, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("", outputStreamCaptor_message.toString().trim());

                case 2:
                    outputStreamCaptor_message.reset();
                    robot.setxPosition(0);
                    robot.setPenUp(true);
                    if((robot.getxPosition() - numOfSpaces) >= 0) {
                        if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                            for(int i = 0; i <= numOfSpaces; i++) {
                                grid[robot.getxPosition() - i][robot.getyPosition()] = 1;
                            }
                            robot.setxPosition(robot.getxPosition() - numOfSpaces);
                        }else{ // robot moves normally without tracing
                            robot.setxPosition(robot.getxPosition() - numOfSpaces);
                        }
                    }else {
                        System.out.println("Invalid move (out of bound)");
                    }

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertTrue(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());

                case 3:
                    outputStreamCaptor_message.reset();
                    robot.setxPosition(0);
                    robot.setPenUp(false);
                    if((robot.getxPosition() - numOfSpaces) >= 0) {
                        if(!robot.isPenUp()){ // robot traces its movement by changing 0 to 1 on the grid
                            for(int i = 0; i <= numOfSpaces; i++) {
                                grid[robot.getxPosition() - i][robot.getyPosition()] = 1;
                            }
                            robot.setxPosition(robot.getxPosition() - numOfSpaces);
                        }else{ // robot moves normally without tracing
                            robot.setxPosition(robot.getxPosition() - numOfSpaces);
                        }
                    }else {
                        System.out.println("Invalid move (out of bound)");
                    }

                    Assertions.assertEquals(0, robot.getxPosition());
                    Assertions.assertEquals(0, robot.getyPosition());
                    Assertions.assertFalse(robot.isPenUp());
                    Assertions.assertEquals(0, grid[robot.getxPosition()][robot.getyPosition()]);
                    Assertions.assertEquals("Invalid move (out of bound)", outputStreamCaptor_message.toString().trim());
            }
        }
    }

    @Test
    public void CommandInput_U() {

        Robot robot = new Robot();
        String input = "U";
        Scanner scanner = new Scanner(input);

        String U_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();

        if (U_Input.equalsIgnoreCase("U")) {
            robot.setPenUp(true);
            Assertions.assertTrue(robot.isPenUp());
        }
    }

    @Test
    public void CommandInput_D() {

        Robot robot = new Robot();
        String input = "D";
        Scanner scanner = new Scanner(input);

        String D_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();

        if (D_Input.equalsIgnoreCase("D")) {
            robot.setPenUp(false);
            Assertions.assertFalse(robot.isPenUp());
        }
    }

    @Test
    public void CommandInput_R() {

        String[] poles = {"North", "East", "South", "West"};
        Robot robot = new Robot();
        String input = "R";
        Scanner scanner = new Scanner(input);

        String R_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();
        if (R_Input.equalsIgnoreCase("R")) {
            for (int i = 0; i < 4; i++) {
                String pole_direction = "";
                robot.setDirection(i);
                int direction = robot.getDirection();
                if (direction == 0) {
                    robot.setDirection(robot.getDirection() + 1);
                    pole_direction = poles[(((robot.getDirection() % 4) + 4) % 4)];
                    Assertions.assertEquals(1, robot.getDirection());
                    Assertions.assertEquals("East", pole_direction);
                } else if (direction == 1) {
                    robot.setDirection(robot.getDirection() + 1);
                    pole_direction = poles[(((robot.getDirection() % 4) + 4) % 4)];
                    Assertions.assertEquals(2, robot.getDirection());
                    Assertions.assertEquals("South", pole_direction);
                } else if (direction == 2) {
                    robot.setDirection(robot.getDirection() + 1);
                    pole_direction = poles[(((robot.getDirection() % 4) + 4) % 4)];
                    Assertions.assertEquals(3, robot.getDirection());
                    Assertions.assertEquals("West", pole_direction);

                } else if (direction == 3) {
                    robot.setDirection(robot.getDirection() + 1);
                    pole_direction = poles[(((robot.getDirection() % 4) + 4) % 4)];
                    Assertions.assertEquals(4, robot.getDirection());
                    Assertions.assertEquals("North", pole_direction);
                }
            }
        }
    }

    @Test
    public void CommandInput_L() {

        Robot robot = new Robot();
        String input = "L";
        Scanner scanner = new Scanner(input);
        String[] poles = {"North", "East", "South", "West"};

        String L_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();
        if(L_Input.equalsIgnoreCase("L")){
            for (int i = 3; i >= 0; i--) {
                String pole_direction = "";
                robot.setDirection(i);
                int direction = robot.getDirection();
                if (direction == 3){
                    robot.setDirection(robot.getDirection() - 1);
                    pole_direction = poles[(((robot.getDirection()%4) + 4) % 4)];
                    Assertions.assertEquals(2, robot.getDirection());
                    Assertions.assertEquals("South", pole_direction);
                }
                else if (direction == 2){
                    robot.setDirection(robot.getDirection() - 1);
                    pole_direction = poles[(((robot.getDirection()%4) + 4) % 4)];
                    Assertions.assertEquals(1, robot.getDirection());
                    Assertions.assertEquals("East", pole_direction);
                }
                else if (direction == 1){
                    robot.setDirection(robot.getDirection() - 1);
                    pole_direction = poles[(((robot.getDirection()%4) + 4) % 4)];
                    Assertions.assertEquals(0, robot.getDirection());
                    Assertions.assertEquals("North", pole_direction);
                }
                else if (direction == 0){
                    robot.setDirection(robot.getDirection() - 1);
                    pole_direction = poles[(((robot.getDirection()%4) + 4) % 4)];
                    Assertions.assertEquals(-1, robot.getDirection());
                    Assertions.assertEquals("West", pole_direction);
                }
            }
        }
    }

    @Test
    public void CommandInput_P(){

        Main main = new Main();
        String input = "P";
        int gridSize = 3;
        int[][] grid = new int [gridSize][gridSize];

        Scanner scanner = new Scanner(input);

        String P_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();
        if (P_Input.equalsIgnoreCase("P")){
            main.printGrid(grid, gridSize);
        }

        Assertions.assertEquals(3, gridSize);
        Assertions.assertEquals(3, grid.length);
        Assertions.assertEquals(3, grid[0].length);
        Assertions.assertInstanceOf(main.getClass(), main);
    }

    @Test
    public void CommandInput_C(){
        Main main = new Main();
        String input = "C";
        boolean function_called = false;
        Scanner scanner = new Scanner(input);

        String C_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();
        if(C_Input.equalsIgnoreCase("C")){
            main.printRobotInfo();
            function_called = true;
        }
        Assertions.assertEquals("C", C_Input);
        Assertions.assertTrue(function_called);
    }

    @Test
    public void CommandInput_Q(){
        String input = "Q";

        Scanner scanner = new Scanner(input);

        String Q_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();
        if(Q_Input.equalsIgnoreCase("Q")){
            outputStreamCaptor_message.reset();
            System.out.println("Exiting Program");
            Assertions.assertEquals("Exiting Program", outputStreamCaptor_message.toString().trim());
        }
    }

    @Test
    public void PrintInvalidCommand(){
        String[] commands = {"U" , "D" , "R" , "L" , "M s OR M0s" , "P" , "C" , "Q" , "I s or I0s"};

        outputStreamCaptor_message.reset();
        System.out.println("Invalid Command. Please use one of the following commands:");
        Assertions.assertEquals("Invalid Command. Please use one of the following commands:", outputStreamCaptor_message.toString().trim());

        outputStreamCaptor_message.reset();
        for(int i = 0 ; i < commands.length ; i++){
            System.out.println(commands[i]);
        }
        Assertions.assertEquals("U" +
                "\nD" +
                "\nR" +
                "\nL" +
                "\nM s OR M0s" +
                "\nP" +
                "\nC" +
                "\nQ" +
                "\nI s or I0s", outputStreamCaptor_message.toString().trim());
    }

    @Test
    public void CommandInput_M(){
        Robot robot = new  Robot();
        Main main = new Main();

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

        if((M_Input3.equalsIgnoreCase("M 3")) && (Character.isDigit(input3.charAt(1))) && (input3.charAt(1) > '0')){

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
                robot.setyPosition(robot.getyPosition() + Integer.parseInt(input3.substring(1)));
            }
            Assertions.assertEquals(0, robot.getxPosition());
            Assertions.assertEquals(3, robot.getyPosition());
            Assertions.assertEquals(0, robot.getDirection());
            Assertions.assertFalse(robot.isPenUp());
            Assertions.assertTrue(function_called);
        }
    }

    @Test
    public void CommandInput_I(){
        Robot robot = new  Robot();
        Main main = new Main();

        String input1 = "I";
        String input2 = "I0";
        String input3 = "I3";
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
    }
}