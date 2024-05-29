import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IconScreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong-2.0");
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
        block.setBounds(830, 750, 300, 150); // set bounds instead of location

        frame.addKeyListener(new KeyAdapter() {
            int x = 830; // adjust the x position to fit the label
            int y = 750;
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        x -= 10;
                        break;
                    case KeyEvent.VK_D:
                        x += 10;
                        break;
                }

                // Check if the block is still within the visible area of the frame
                if (x < 0) {
                    x = 0;
                } else if (x + 800 > frame.getWidth()) {
                    x = frame.getWidth() - 300;
                }

                if (y < 0) {
                    y = 0;
                } else if (y + 40 > frame.getHeight()) {
                    y = frame.getHeight() - 150;
                }

                block.setBounds(x, y, 300, 150); // update bounds
                System.out.println("x: " + x + ", y: " + y);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // set layout to null
        frame.getContentPane().add(block);
        frame.setSize(1200, 900); // set size of frame to be larger than the block's position
        frame.setVisible(true);

        // Move the focus to the frame so that key events are received
        frame.requestFocus();
    }
}