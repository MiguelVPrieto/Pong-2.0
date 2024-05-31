import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball extends JLabel {
    private int xDirection = 1;
    private int yDirection = 1;
    private int diameter;
    private Random random;
    private Timer timer;
    private JFrame frame;
    private int xDirectionCount = 0;
    private int yDirectionCount = 0;
    private int lastXDirection = 0;
    private int lastYDirection = 0;

    public Ball(JFrame frame, int frameWidth, int frameHeight) {
        this.frame = frame;
        this.diameter = (int) (frameWidth * 0.02 * 1.25); // adjust the size of the ball to be 25% larger
        this.random = new Random();

        // Load the ball image from "src/ball.png"
        ImageIcon ballIcon = new ImageIcon("src/ball.png");
        Image image = ballIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance((int) (diameter * 1.25), (int) (diameter * 1.25), java.awt.Image.SCALE_SMOOTH);  // scale it the smooth way
        ballIcon = new ImageIcon(newimg);  // transform it back
        setIcon(ballIcon);

        setSize((int) (diameter * 1.25), (int) (diameter * 1.25)); // set the size of the JLabel to be the same as the scaled image size
        setLocation(frameWidth / 2 - (int) (diameter * 1.25) / 2, frameHeight / 2 - (int) (diameter * 1.25) / 2);

        timer = new Timer(10, e -> moveBall()); // set the ball's movement speed
        timer.start();

        // Set the initial random direction for the ball
        xDirection = random.nextInt(2) * 2 - 1;
        yDirection = -1; // Set the initial vertical direction upwards
    }

    private void moveBall() {
        int x = getX() + xDirection * 5; // move 1 pixel at a time
        int y = getY() + yDirection * 5; // move 1 pixel at a time

        // Check if the ball hits the screen border
        if (x <= 0 || x + (int) (diameter * 1.25) >= getParent().getWidth()) {
            xDirection *= -1;
            xDirectionCount++;
            if (xDirectionCount > 4 && xDirection == lastXDirection) {
                xDirection = random.nextInt(2) * 2 - 1;
                xDirectionCount = 1;
            }
            lastXDirection = xDirection;
        }
        if (y <= 0) {
            yDirection *= -1;
            yDirectionCount++;
            if (yDirectionCount > 4 && yDirection == lastYDirection) {
                yDirection = random.nextInt(2) * 2 - 1;
                yDirectionCount = 1;
            }
            lastYDirection = yDirection;
        } else if (y + (int) (diameter * 1.25) >= getParent().getHeight()) {
            // Stop the timer and make the ball invisible when it hits the bottom of the screen
            timer.stop();
            setVisible(false);
        }

        // Check if the ball hits a block
        Component component = frame.getContentPane().getComponentAt(x, y);
        if (component instanceof JLabel && ((JLabel) component).getIcon()!= null) {
            JLabel block = (JLabel) component;

            // Change the direction of the ball upwards
            yDirection = -1;

            // Generate a random horizontal direction
            xDirection = random.nextInt(2) * 2 - 1;

            // Update the ball's position to be inside the block
            setLocation(block.getX() - (int) (diameter * 1.25) / 2, block.getY() - (int) (diameter * 1.25));
        }

        setLocation(x, y);
    }

    public void addKeyListenerToFrame(JFrame frame) {
        // No need for key listener in Ball class since the ball moves randomly
    }
}