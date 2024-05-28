import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Toolkit;

public class IconScreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Icon Screen");
        String filePath = "src/block.png"; // replace with your absolute path
        ImageIcon Bl_icon = new ImageIcon(filePath);

        if (Bl_icon.getImage() == null) {
            System.err.println("Error loading icon file.");
            return;
        }

        // Scale the image to be twice as large
        Image scaledBlock = Bl_icon.getImage().getScaledInstance(Bl_icon.getIconWidth() * 2, Bl_icon.getIconHeight() * 2, Image.SCALE_DEFAULT);
        Bl_icon = new ImageIcon(scaledBlock);

        JLabel block = new JLabel(Bl_icon);
        block.setBounds(910, 750, Bl_icon.getIconWidth(), Bl_icon.getIconHeight()); // set bounds instead of location

        block.addKeyListener(new KeyAdapter() {
            int x = 910;
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
                } else if (x + Bl_icon.getIconWidth() > frame.getWidth()) {
                    x = frame.getWidth() - Bl_icon.getIconWidth();
                }

                block.setBounds(x, y, Bl_icon.getIconWidth(), Bl_icon.getIconHeight()); // update bounds
                System.out.println("x: " + x + ", y: " + y);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // set layout to null
        frame.getContentPane().add(block);
        frame.setSize(1200, 900); // set size of frame to be larger than the block's position
        frame.setVisible(true);

        // Move the focus to the label so that key events are received
        block.requestFocus();
    }
}