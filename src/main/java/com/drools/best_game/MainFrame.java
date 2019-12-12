package com.drools.best_game;

import org.kie.api.runtime.KieSession;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame {
    private static JFrame frame;
    private static KieSession session;

    public static void showFrame() {
        frame.setSize(600, 400);
        frame.setLocation(200, 200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                session.dispose();
            }
        });
        session.fireAllRules();
    }

    public static void start(KieSession k_session) {
        session = k_session;
        frame = new JFrame("Choose the perfect board game");
        showFrame();
    }


    public static JFrame getFrame(){
        return frame;
    }

    public static KieSession getSession() {
        return session;
    }
}
