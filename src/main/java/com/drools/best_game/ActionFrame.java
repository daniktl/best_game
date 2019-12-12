package com.drools.best_game;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class ActionFrame {
    private JFrame main;
    protected JPanel content;

    public ActionFrame(JFrame main) {
        this.main = main;
        this.content = new JPanel();
    }

    public void show() {
        main.setContentPane(content);
        main.validate();
        main.repaint();
    }

    public void close() {
        main.dispatchEvent(new WindowEvent(main, WindowEvent.WINDOW_CLOSED));
    }
}
