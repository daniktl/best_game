package com.drools.best_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.drools.compiler.compiler.DroolsParserException;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import javax.swing.*;

public class BestGame {
    public static void main(String[] args) throws DroolsParserException,
            IOException {
        BestGame bgTest = new BestGame();
        bgTest.executeHelloWorldRules();
    }

    public void executeHelloWorldRules() throws IOException,
            DroolsParserException {
        KieServices ks = KieServices.Factory.get();
        BasicConfigurator.configure();
        Logger.getLogger(BestGame.class).setLevel(Level.INFO);
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession session = kContainer.newKieSession("ksession-rules");
//        BestGame bg = new BestGame();
//        bg.setPrintMessage("Hello World");
//        session.insert(bg);
        session.fireAllRules();
    }

    public static class Question {
        private String q;
        private String shortcut;
        private String[] possibleAnswers;
        private String choosedAnswer = null;

        public Question(String q, String shortcut, String[] possibleAnswers, String choosedAnswer) {
            this.q = q;
            this.shortcut = shortcut;
            this.possibleAnswers = possibleAnswers;
            this.choosedAnswer = choosedAnswer;
        }

        public String getQ() {
            return this.q;
        }

        public String getShortcut() {
            return this.shortcut;
        }

        public String[] getPossibleAnswers() {
            return this.possibleAnswers;
        }

        public String getAnswer() {
            return this.choosedAnswer;
        }

        public void setAnswer(String answer) {
            this.choosedAnswer = answer;
        }

        public void ask(){
            JFrame frame = new JFrame();
            int answer_idx = JOptionPane.showOptionDialog(frame,
                    getQ(),
                    "Answer the question",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    getPossibleAnswers(),
                    getPossibleAnswers()[0]
            );
            this.choosedAnswer = this.possibleAnswers[answer_idx];
        }
    }

    public static class MainFrame {
        public boolean started = false;
        private JFrame frame;

        public MainFrame() {
            this.frame = new JFrame();
            this.frame.setTitle("Find best game");
            frame.setSize(600, 400);
            frame.setLocation(200, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setResizable(true);
            JPanel panel = new JPanel();
            frame.add(panel);
            JLabel label1 = new JLabel("Choose the perfect board game");
            label1.setLocation(200, 100);
            panel.add(label1);
            JButton start = new JButton("Start quest");
            start.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    started = true;
                    frame.dispose();
                }
            });
            panel.add(start);
            frame.pack();
        }

        public boolean getStarted(){
            return this.started;
        }
    }
}