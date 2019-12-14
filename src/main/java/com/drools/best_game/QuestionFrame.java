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
    private String answer;
    private String choosedAnswer;

    public QuestionFrame(String q, String[] possibleAnswers){
        this.q = q;
        this.answer = "";

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
                    getSession().fireAllRules();
                    break;
                }
            }
        }
    }

    public String getAnswer() throws InterruptedException{
        return this.answer;
    }
}
