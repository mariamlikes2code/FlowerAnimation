package twoDimage;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlowerDrawing extends JFrame {
    private static final long serialVersionUID = 1L;

    public FlowerDrawing() {
        setTitle("Flower Drawing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        FlowerPanel flowerPanel = new FlowerPanel();
        add(flowerPanel);
        setVisible(true);
    }

    // main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlowerDrawing());
    }

    class FlowerPanel extends JPanel implements ActionListener {

        private static final long serialVersionUID = 1L;
        private int petalSize = 100;
        private int petals = 6;
        private int centerX;
        private int centerY;
        private Timer timer; // Use javax.swing.Timer
        private int angle = 0;

        public FlowerPanel() {
            timer = new Timer(10, this); // Use javax.swing.Timer
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw flower petals
            centerX = getWidth() / 2;
            centerY = getHeight() / 2;
            g2d.setColor(Color.PINK);

            for (int i = 0; i < petals; i++) {
                double petalAngle = angle + i * (360.0 / petals);
                int petalX = (int) (centerX + petalSize * Math.cos(Math.toRadians(petalAngle)));
                int petalY = (int) (centerY + petalSize * Math.sin(Math.toRadians(petalAngle)));
                g2d.fillOval(petalX - petalSize / 2, petalY - petalSize / 2, petalSize, petalSize);
            }

            // Draw flower center
            int centerSize = 125;
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(centerX - centerSize / 2, centerY - centerSize / 2, centerSize, centerSize);

            // Draw stem
            int stemHeight = 180;
            g2d.setColor(Color.GREEN);
            g2d.fillRect(centerX - 5, centerY + centerSize / 2, 10, stemHeight);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            angle++; // Increment angle for rotation
            repaint();
        }
    }
}
