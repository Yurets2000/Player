package player;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerPanel extends JPanel implements Runnable {
    private int dx = 50;
    private int dy = 25;
    private int dy2 = 65;
    private int play;
    private boolean new_sound;
    private Sound s;

    public void newSound() {
        new_sound = true;
    }

    public PlayerPanel(int width, int height) {
        PlayerButton b = new PlayerButton();
        b.setBounds(width / 2 - dx, height / 2 - dy, 100, 100);
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (new_sound) {
                    play = 0;
                    new_sound = false;
                }
                if (play == 0) {
                    EqulizerPanel.play();
                    playSound();
                } else if (play % 2 == 1) {
                    EqulizerPanel.stop();
                    s.stop();
                } else if (play % 2 == 0) {
                    EqulizerPanel.play();
                    s.play();
                }
                play++;
            }
        });
        add(b);
    }

    private void playSound() {
        if (s != null) {
            s.close();
        }
        s = new Sound(Player.getSound());
        s.start();
        s.play();
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        double position;
        double length;
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.fillRoundRect(2 * dx, 2 * dy, getWidth() - 4 * dx, 15, 7, 7);
        if (play > 0) {
            position = (double) s.getLine().getMicrosecondPosition();
            length = (double) s.getLine().getMicrosecondLength();
            if (length <= position) {
                s.close();
                EqulizerPanel.stop();
                s.start();
            }
            g.drawString((int) position / 1000000 + "s", dx, dy2);
            g.drawString((int) length / 1000000 + "s", getWidth() - 2 * dx + 10, dy2);
            g.drawString(Player.getSoundName(), getWidth() / 2 - dx, dy);
            g.setColor(Color.BLUE);
            g.fillRoundRect(2 * dx, 2 * dy, (int) ((getWidth() - 4 * dx) * (position / length)), 15, 7, 7);
        }
    }

    @Override
    public void run() {
        while (true) {
            super.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
