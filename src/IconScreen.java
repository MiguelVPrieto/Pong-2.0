import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IconScreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong-2.0");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        String filePath = "src/block.png"; // replace with your absolute path
        ImageIcon Bl_icon = new ImageIcon(filePath);

        if (Bl_icon.getImage() == null) {
            System.err.println("Error loading icon file.");
            return;
        }
        JLabel block = new JLabel();
        // Set the size of the icon
        Bl_icon.setImage(Bl_icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH));
        block.setIcon(Bl_icon);

        // Set the initial position of the block to be in the center of the screen
        int x = (int) (screenSize.getWidth() - 300) / 2;
        int y = (int) (screenSize.getHeight() * 0.9 - 150);w    a
        block.setBounds(x, y, 300, 150); // set bounds instead of location

        frame.addKeyListener(new KeyAdapter() {
            int xTemp = x; // adjust the x position to fit the label
            int yTemp = y;
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        xTemp -= 10;
                        break;
                    case KeyEvent.VK_D:
                        xTemp += 10;
                        break;
                }

                // Check if the block is still within the visible area of the frame
                if (xTemp < 0) {
                    xTemp = 0;
                } else if (xTemp + 300 > frame.getWidth()) {
                    xTemp = frame.getWidth() - 300;
                }

                if (yTemp < 0) {
                    yTemp = 0;
                } else if (yTemp + 150 > frame.getHeight()) {
                    yTemp = frame.getHeight() - 150;
                }

                block.setBounds(xTemp, yTemp, 300, 150); // update bounds
                System.out.println("x: " + xTemp + ", y: " + yTemp);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // set layout to null
        frame.getContentPane().add(block);
        frame.setSize(screenSize.width, screenSize.height); // set size of frame to be the same as the screen size
        frame.setVisible(true);

        // Move the focus to the frame so that key events are received
        frame.requestFocus();
    }
}