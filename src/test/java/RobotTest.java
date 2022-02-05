import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    Robot robot = new Robot();

    @Test
    void getxPosition() {

        robot.setxPosition(4);
        Assertions.assertEquals(4, robot.getxPosition());
        Assertions.assertNotEquals(6, robot.getxPosition());
    }

    @Test
    void getyPosition() {

        robot.setyPosition(5);
        Assertions.assertEquals(5, robot.getyPosition());
        Assertions.assertNotEquals(2, robot.getyPosition());
    }

    @Test
    void isPenUp() {

        Robot facing_up_robot = new Robot();
        Robot facing_down_robot = new Robot();

        facing_up_robot.setPenUp(true);
        facing_down_robot.setPenUp(false);

        Assertions.assertTrue(facing_up_robot.isPenUp());
        Assertions.assertFalse(facing_down_robot.isPenUp());
    }

    @Test
    void getNorthDirection() {

        String[] poles = {"North", "East", "South", "West"};
        robot.setDirection(0);
        String north = poles[(((robot.getDirection()%4) + 4) % 4)];

        Assertions.assertEquals(0, robot.getDirection());
        Assertions.assertEquals("North", north);
    }

    @Test
    void getEastDirection() {

        String[] poles = {"North", "East", "South", "West"};
        robot.setDirection(1);
        String east = poles[(((robot.getDirection()%4) + 4) % 4)];

        Assertions.assertEquals(1, robot.getDirection());
        Assertions.assertEquals("East", east);
    }

    @Test
    void getSouthDirection() {

        String[] poles = {"North", "East", "South", "West"};
        robot.setDirection(2);
        String south = poles[(((robot.getDirection()%4) + 4) % 4)];

        Assertions.assertEquals(2, robot.getDirection());
        Assertions.assertEquals("South", south);
    }

    @Test
    void getWestDirection() {

        String[] poles = {"North", "East", "South", "West"};
        robot.setDirection(3);
        String west = poles[(((robot.getDirection()%4) + 4) % 4)];

        Assertions.assertEquals(3, robot.getDirection());
        Assertions.assertEquals("West", west);
    }
}