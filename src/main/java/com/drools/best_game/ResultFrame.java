package com.drools.best_game;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFrame extends MainGUI {
    public ResultFrame(String result) {

        clear_content();
        GroupLayout groupLayout = new GroupLayout(content);
        content.setLayout(groupLayout);
        GroupLayout.ParallelGroup horizontal = groupLayout.createParallelGroup();
        GroupLayout.SequentialGroup vertical = groupLayout.createSequentialGroup();

        groupLayout.setHorizontalGroup(horizontal);
        groupLayout.setVerticalGroup(vertical);

        JPanel title_panel = new JPanel();
        title_panel.setLayout(new GridLayout(3, 3, 10, 1));
        JLabel title = new JLabel("So the best matching for you is...");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        title.setBorder(new EmptyBorder(0, 10, 0, 0));

        title_panel.add(title);
        horizontal.addComponent(title_panel);
        vertical.addComponent(title_panel);

        JPanel middle_p = new JPanel();
        JLabel result_l = new JLabel(result);
        result_l.setFont(new Font(result_l.getFont().getName(), Font.PLAIN, 20));

        middle_p.add(result_l);
        horizontal.addComponent(middle_p);
        vertical.addComponent(middle_p);

        JPanel bottom_p = new JPanel();

        JButton closeButton = new JButton("Close");
        closeButton.setActionCommand("close");

        bottom_p.add(closeButton);
        horizontal.addComponent(bottom_p);
        vertical.addComponent(bottom_p);

        horizontal.addComponent(bottom_p);
        vertical.addComponent(bottom_p);

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getActionCommand().equals("close")) {
                    close();
                }
            }
        });
    }
}
