package player;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class EqulizerPanel extends JPanel implements Runnable {

    private static boolean begin = false;

    public static void play() {
        begin = true;
    }

    public static void stop() {
        begin = false;
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.rotate(Math.PI, getWidth() / 2, getHeight() / 2);
        double height;
        for (int i = 0; i < getWidth(); i += 10) {
            if (begin) {
                height = Math.random() * (getHeight() - 30);
            } else {
                height = 20;
            }
            g.setColor(new Color(((i) / 4) % 255, 0, 100));
            g.fill(new Rectangle2D.Double(i, 0, 10, height + 20));
            g.setColor(Color.BLACK);
            g.draw(new Line2D.Double(i, 0, i, height + 20));
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                super.repaint();
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
