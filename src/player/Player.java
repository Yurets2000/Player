package player;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.swing.border.BevelBorder;
import static javax.swing.JFileChooser.FILES_ONLY;
import static javax.swing.border.BevelBorder.RAISED;

public class Player extends JFrame {

    private static String name;
    private static String shortname;

    public Player() {
        setLayout(null);
        EqulizerPanel equlizerPanel = new EqulizerPanel();
        equlizerPanel.setOpaque(true);
        equlizerPanel.setBackground(Color.BLACK);
        equlizerPanel.setBounds(0, 0, 900, 400);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Choose the sound");
        JMenuItem menuItem = new JMenuItem("Open file");
        menu.add(menuItem);
        menuBar.add(menu);
        PlayerPanel playerPanel = new PlayerPanel(900, 250);
        menuItem.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(FILES_ONLY);
            chooser.addChoosableFileFilter(new PlayerFileFilter());
            int state = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (file != null && state == JFileChooser.APPROVE_OPTION) {
                if (file.getName().endsWith(".wav")) {
                    System.out.println(file.getPath());
                    name = file.getAbsolutePath();
                    shortname = file.getName();
                    playerPanel.newSound();
                }
            }
        });
        playerPanel.setBounds(0, 400, 900, 250);
        playerPanel.setOpaque(true);
        playerPanel.setBorder(new BevelBorder(RAISED));
        playerPanel.setBackground(Color.DARK_GRAY);
        playerPanel.setLayout(null);
        Thread t = new Thread(equlizerPanel);
        t.start();
        Thread t2 = new Thread(playerPanel);
        t2.start();
        setJMenuBar(menuBar);
        add(equlizerPanel);
        add(playerPanel);
    }

    public static String getSound() {
        return name;
    }

    public static String getSoundName() {
        return shortname;
    }

    public static void main(String[] args) {
        Player player = new Player();
        player.setTitle("MyRealTeck");
        player.setBounds(50, 50, 900, 650);
        player.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        player.setResizable(false);
        player.setVisible(true);
    }

}
