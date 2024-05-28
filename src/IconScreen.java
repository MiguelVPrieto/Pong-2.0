import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IconScreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Icon Screen");
        String filePath = "src/block.png"; // replace with your absolute path
        ImageIcon Bl_icon = new ImageIcon(filePath);

        if (Bl_icon.getImage() == null) {
            System.err.println("Error loading icon file.");
            return;
        }

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