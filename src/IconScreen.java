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
        int blockWidth = (int) (screenSize.getWidth() * 0.17);
        int blockHeight = (int) (screenSize.getHeight() * 0.1);
        Bl_icon.setImage(Bl_icon.getImage().getScaledInstance(blockWidth, blockHeight, Image.SCALE_SMOOTH));
        block.setIcon(Bl_icon);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // set layout to null
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // set frame to fullscreen
        frame.setVisible(true);

        // Get the frame's size after it's been maximized
        Dimension frameSize = frame.getSize();
        Insets insets = frame.getInsets();

        // Set the initial position of the block to be in the middle horizontally and 90% down
        int x = (int) ((frameSize.getWidth() - blockWidth) / 2.0);
        int y = (int) (frameSize.getHeight() * 0.9 - blockHeight);
        block.setBounds(x, y, blockWidth, blockHeight); // set bounds instead of location

        frame.getContentPane().add(block);

        Ball ball = new Ball(frame, frame.getWidth(), frame.getHeight());
        frame.getContentPane().add(ball);

        frame.addKeyListener(new KeyAdapter() {
            int xTemp = x; // adjust the x position to fit the label
            int yTemp = y;
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        xTemp -= 12;
                        break;
                    case KeyEvent.VK_D:
                        xTemp += 12;
                        break;
                }

                // Check if the block is still within the visible area of the frame
                if (xTemp < 0) {
                    xTemp = 0;
                } else if (xTemp + blockWidth > frame.getWidth() - insets.right) {
                    xTemp = frame.getWidth() - insets.right - blockWidth;
                }

                if (yTemp < insets.top) {
                    yTemp = insets.top;
                } else if (yTemp + blockHeight > frame.getHeight() - insets.bottom) {
                    yTemp = frame.getHeight() - insets.bottom - blockHeight;
                }

                block.setBounds(xTemp, yTemp, blockWidth, blockHeight); // update bounds
                System.out.println("x: " + xTemp + ", y: " + yTemp);
            }
        });

        // Move the focus to the frame so that key events are received
        frame.requestFocus();
    }
}