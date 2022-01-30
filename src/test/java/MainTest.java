import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

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

    }

    @Test
    public void printGrid() {

    }

    @Test
    public void moveForward() {
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
        }

        Assertions.assertFalse(robot.isPenUp());
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

        String U_Input = scanner.nextLine().replaceAll("\\s+","").toUpperCase();

        int i = 3;
        while (0 <= i && i < 4){
            robot.setDirection(i);
            int direction = robot.getDirection();
            if (direction == 1) {
                if (U_Input.equalsIgnoreCase("L")) {
                    robot.setDirection(robot.getDirection() - 1);
                    String north = poles[(((robot.getDirection()%4) + 4) % 4)];

                    Assertions.assertEquals(0, robot.getDirection());
                    Assertions.assertEquals("North", north);
                }
            } else if (direction == 2) {
                if (U_Input.equalsIgnoreCase("L")) {
                    robot.setDirection(robot.getDirection() - 1);
                    String east = poles[(((robot.getDirection()%4) + 4) % 4)];

                    Assertions.assertEquals(1, robot.getDirection());
                    Assertions.assertEquals("East", east);
                }
            } else if (direction == 3) {
                if (U_Input.equalsIgnoreCase("L")) {
                    robot.setDirection(robot.getDirection() - 1);
                    String south = poles[(((robot.getDirection()%4) + 4) % 4)];

                    Assertions.assertEquals(2, robot.getDirection());
                    Assertions.assertEquals("South", south);
                }
            } else if (direction == 0){
                if (U_Input.equalsIgnoreCase("L")) {
                    robot.setDirection(3);
                    String west = poles[(((robot.getDirection()%4) + 4) % 4)];

                    Assertions.assertEquals(3, robot.getDirection());
                    Assertions.assertEquals("West", west);
                }

            }
            i--;
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
        if (P_Input.equalsIgnoreCase("R")){
            main.printGrid(grid, gridSize);
        }

        Assertions.assertInstanceOf(main.getClass(), main);
    }
}