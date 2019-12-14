package com.drools.best_game;

import org.kie.api.runtime.KieSession;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGUI {
    private static JFrame frame;
    private static KieSession session;
    protected static JPanel content;

    public static void showFrame() {
        frame.pack();
        frame.setSize(600, 400);
        frame.setLocation(400, 300);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                session.dispose();
            }
        });
        StartFrame startFrame = new StartFrame();
        startFrame.show();
    }

    public static void start(KieSession k_session) {
        session = k_session;
        frame = new JFrame("Choose the perfect board game");
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        showFrame();
    }

    public void show() {
        frame.setContentPane(content);
        frame.validate();
        frame.repaint();
    }

    public void close() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public void clear_content(){
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
    }

    public static JFrame getFrame(){
        return frame;
    }

    public static KieSession getSession() {
        return session;
    }
}
