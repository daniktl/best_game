package com.drools.best_game;

import org.kie.api.runtime.rule.FactHandle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class QuestionFrame extends MainGUI {

    private String chosenAnswer;

    private String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public QuestionFrame(String q, String[] possibleAnswers, String attribute){
        clear_content();

        GroupLayout groupLayout = new GroupLayout(content);
        content.setLayout(groupLayout);
        GroupLayout.ParallelGroup horizontal = groupLayout.createParallelGroup();
        GroupLayout.SequentialGroup vertical = groupLayout.createSequentialGroup();

        groupLayout.setHorizontalGroup(horizontal);
        groupLayout.setVerticalGroup(vertical);

        JPanel title_panel = new JPanel();
        JLabel title = new JLabel("<html><div style=\"width: 450px; word-wrap: break-word\">" +q+ "</div></html>");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 24));
        title.setBorder(new EmptyBorder(10, 10, 0, 0));

        title_panel.add(title);
        horizontal.addComponent(title_panel);
        vertical.addComponent(title_panel);

        JButton nextButton = new JButton("Next");
        nextButton.setEnabled(false);

        ButtonGroup options = new ButtonGroup();
        JPanel buttonsPanel = new JPanel();
        for (String possAn : possibleAnswers){
            JButton option = new JButton(possAn);
            option.addActionListener(actionEvent -> {
                chosenAnswer = possAn;
                for (FactHandle f: getSession().getFactHandles()) {
                    Object sessionObject = getSession().getObject(f);
                    if (sessionObject.getClass().toString().equals("class com.drools.best_game.UserPreferences")) {
                        for (Method method : sessionObject.getClass().getDeclaredMethods()) {
                            if (method.getName().startsWith("set" + capitalizeFirstLetter(attribute))) {
                                try {
                                    method.invoke(sessionObject, chosenAnswer);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                                FactHandle fh = getSession().getFactHandle(sessionObject);
                                getSession().update(fh, sessionObject);
                                getSession().fireAllRules();
                                break;
                            }
                        }
                    }
                }
            });
            options.add(option);
            buttonsPanel.add(option);
        }
        horizontal.addComponent(buttonsPanel);
        vertical.addComponent(buttonsPanel);
    }

}
