package com.drools.best_game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartFrame extends MainGUI {
    public StartFrame() {

        GroupLayout groupLayout = new GroupLayout(content);
        content.setLayout(groupLayout);
        GroupLayout.ParallelGroup horizontal = groupLayout.createParallelGroup();
        GroupLayout.SequentialGroup vertical = groupLayout.createSequentialGroup();

        groupLayout.setHorizontalGroup(horizontal);
        groupLayout.setVerticalGroup(vertical);

        JPanel title_panel = new JPanel();
        JLabel title = new JLabel("Are you looking for the perfect board game?");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        title.setBorder(new EmptyBorder(10, 10, 0, 0));

        title_panel.add(title);
        horizontal.addComponent(title_panel);
        vertical.addComponent(title_panel);

        JPanel description_panel = new JPanel();
        JLabel description = new JLabel("Answer for a couple of question to find the best matching.");
        description.setFont(new Font(description.getFont().getName(), Font.PLAIN, 14));
        description_panel.add(description);
        horizontal.addComponent(description_panel);
        vertical.addComponent(description_panel);


        JPanel btn_panel = new JPanel();
        btn_panel.setSize(600, 50);

        JButton startButton = new JButton("Start");
        startButton.setActionCommand("start");
        startButton.setLayout(null);
        startButton.setBounds(200, 200, 100, 50);
        btn_panel.add(startButton);

        horizontal.addComponent(btn_panel);
        vertical.addComponent(btn_panel);

        startButton.addActionListener(actionEvent -> MainGUI.getSession().fireAllRules());

        JPanel copyright_bottom = new JPanel();
        JLabel copyright_l = new JLabel("<html>Created by Daniil Martsich & Marcin Odor<br>Poznań, 2019</html>", SwingConstants.CENTER);

        copyright_bottom.add(copyright_l);

        horizontal.addComponent(copyright_bottom);
        vertical.addComponent(copyright_bottom);

    }
}
