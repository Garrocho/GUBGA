package com.wicht.java7.swing;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GriddedTranslucentWindow extends JFrame {
    public GriddedTranslucentWindow() {
        super("Java 7 Per Pixel Translucency");

        setSize(300, 320);

        getContentPane().setLayout(new GridLayout(4, 4));

        for(int i = 0; i <= 16; i++){
            add(new AlphaPanel(255 - i * 12));
        }

        setBackground(new Color(0, 0, 0, 0));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

                if (ge.getDefaultScreenDevice().isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT)) {
                    new GriddedTranslucentWindow();
                }
            }
        });
    }

    private class AlphaPanel extends JPanel {
        private AlphaPanel(int alpha) {
            super();

            setBackground(new Color(0, 0, 255, alpha));
        }
    }
}