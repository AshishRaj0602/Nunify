import java.util.HashMap;
import java.util.Scanner;

public class RC_Robots {

    static class Robot {
        int x;
        int y;
        char direction;

        public Robot(int x, int y, char direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    public static Robot moveRobot(Robot robot, String instructions,int xMax, int yMax) {
        HashMap<Character, int[]> directions = new HashMap<>();
        directions.put('N', new int[]{0, 1});
        directions.put('E', new int[]{1, 0});
        directions.put('S', new int[]{0, -1});
        directions.put('W', new int[]{-1, 0});

        HashMap<Character, Character> leftTurns = new HashMap<>();
        leftTurns.put('N', 'W');
        leftTurns.put('E', 'N');
        leftTurns.put('S', 'E');
        leftTurns.put('W', 'S');

        HashMap<Character, Character> rightTurns = new HashMap<>();
        rightTurns.put('N', 'E');
        rightTurns.put('E', 'S');
        rightTurns.put('S', 'W');
        rightTurns.put('W', 'N');

        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'L') {
                robot.direction = leftTurns.get(robot.direction);
            } else if (instruction == 'R') {
                robot.direction = rightTurns.get(robot.direction);
            } else if (instruction == 'M') {
                int[] dir = directions.get(robot.direction);
                int newX = robot.x + dir[0];
                int newY = robot.y + dir[1];

                if (newX >= 0 && newX <= xMax && newY >= 0 && newY <= yMax) {
                    robot.x = newX;
                    robot.y = newY;
                }
            }
        }

        return robot;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int xMax = scanner.nextInt();
        int yMax = scanner.nextInt();
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            char direction = scanner.next().charAt(0);
            scanner.nextLine();

            String instructions = scanner.nextLine();

            Robot robot = new Robot(x, y, direction);
            robot = moveRobot(robot, instructions,xMax,yMax);
            if (robot.x >= 0 && robot.x <= xMax && robot.y >= 0 && robot.y <= yMax) {
                System.out.println(robot.x + " " + robot.y + " " + robot.direction);
            } else {
                System.out.println("Invalid final position for the robot");
            }
        }

        scanner.close();
    }
}
