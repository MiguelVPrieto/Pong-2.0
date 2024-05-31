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
        int x = getX() + xDirection * 5;
        int y = getY() + yDirection * 5;

        // Check if the ball hits the screen border
        if (x <= 0) {
            xDirection = -xDirection;
            x = 0;
        } else if (x + diameter >= getParent().getWidth()) {
            xDirection = -xDirection;
            x = getParent().getWidth() - diameter;
        }
        if (y <= 0) {
            yDirection = -yDirection;
            y = 0;
        } else if (y + diameter >= getParent().getHeight()) {
            // Reset the ball to the center of the screen
            setLocation(getParent().getWidth() / 2 - diameter / 2, getParent().getHeight() / 2 - diameter / 2);
            xDirection = random.nextInt(2) * 2 - 1;
            yDirection = -1;
        }

        // Check if the ball hits a block
        Component component = frame.getContentPane().getComponentAt(x, y);
        if (component instanceof JLabel && ((JLabel) component).getIcon()!= null) {
            JLabel block = (JLabel) component;

            // Change the direction of the ball upwards
            yDirection = -yDirection;

            // Generate a random horizontal direction
            xDirection = random.nextInt(2) * 2 - 1;
        }

        // Update the ball's position
        setLocation(x, y);
    }

    public void addKeyListenerToFrame(JFrame frame) {
        // No need for key listener in Ball class since the ball moves randomly
    }
}