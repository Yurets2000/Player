package player;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayerButton extends Component implements MouseListener {

    private BufferedImage im;
    private boolean isClicked;

    public PlayerButton() {
        super();
        ClassLoader cldr = this.getClass().getClassLoader();
        URL icon = cldr.getResource("images/button.png");
        try {
            im = ImageIO.read(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        addMouseListener(this);
    }

    public Dimension getPrefferedSize() {
        return new Dimension(100, 100);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPrefferedSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPrefferedSize();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isClicked) {
            g.drawImage(im, 0, 0, this);
        } else {
            g.drawImage(im, 3, 2, this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        isClicked = !isClicked;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isClicked = !isClicked;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}