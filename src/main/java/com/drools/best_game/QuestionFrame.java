package com.drools.best_game;

import org.kie.api.runtime.rule.FactHandle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class QuestionFrame extends MainGUI {

    private String q;
    private String[] possibleAnswers;
    private String answer;
    private String choosedAnswer;

    public QuestionFrame(String q, String[] possibleAnswers){
        this.q = q;
        this.possibleAnswers = possibleAnswers;
        this.answer = "";

        clear_content();

        GroupLayout groupLayout = new GroupLayout(content);
        content.setLayout(groupLayout);
        GroupLayout.ParallelGroup horizontal = groupLayout.createParallelGroup();
        GroupLayout.SequentialGroup vertical = groupLayout.createSequentialGroup();



        groupLayout.setHorizontalGroup(horizontal);
        groupLayout.setVerticalGroup(vertical);

        JPanel title_panel = new JPanel();
        JLabel title = new JLabel(q);
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
            option.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    choosedAnswer = possAn;
                    try {
                        updateAnswer();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
            options.add(option);
            buttonsPanel.add(option);
        }
        horizontal.addComponent(buttonsPanel);
        vertical.addComponent(buttonsPanel);

//        System.out.println("here");

//        JPanel description_panel = new JPanel();
//        JLabel description = new JLabel("Answer for a couple of question to find the best matching.");
//        description.setFont(new Font(description.getFont().getName(), Font.PLAIN, 14));
//        description_panel.add(description);
//        horizontal.addComponent(description_panel);
//        vertical.addComponent(description_panel);

//
//        JPanel btn_panel = new JPanel();
////        btn_panel.setSize(600, 50);
//
//        nextButton.setActionCommand("next");
//        nextButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                if (actionEvent.getActionCommand().equals("next")) {
//                    answer = choosedAnswer;
//
//                }
//            }
//        });
//
//
////        nextButton.setLayout(null);
////        nextButton.setBounds(200, 200, 100, 50);
//        btn_panel.add(nextButton);
//
//        horizontal.addComponent(btn_panel);
//        vertical.addComponent(btn_panel);
    }

    public void updateAnswer() throws InvocationTargetException, IllegalAccessException {
        Object target = null;
        boolean ok = false;
        for (FactHandle f: getSession().getFactHandles()){
            Object sessionObject = getSession().getObject(f);
            for (Method method: sessionObject.getClass().getDeclaredMethods()){
                if (method.getName().startsWith("getQ")) {
                    Object value = method.invoke(sessionObject);
                    if (value != null) {
                        if (value.equals(this.q)){
                            target = sessionObject;
                            ok = true;
                            break;
                        }
                    }
                }
            }
            if (ok){break;}
        }
        if (target != null){
            for (Method method: target.getClass().getDeclaredMethods()){
                if (method.getName().startsWith("setAnswer")) {
                    method.invoke(target, choosedAnswer);
                    FactHandle fh = getSession().getFactHandle(target);
                    getSession().update(fh, target);
                    break;
                }
            }
        }
    }

    public String getAnswer() throws InterruptedException{
        return this.answer;
    }
}
