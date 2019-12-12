package com.drools.best_game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends ActionFrame {
    public StartFrame(JFrame main) {
        super(main);

        JButton startButton = new JButton("Start");
        startButton.setActionCommand("start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.getSession().fireAllRules();
            }
        });
        content.add(startButton);
    }
}
