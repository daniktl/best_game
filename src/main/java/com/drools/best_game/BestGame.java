package com.drools.best_game;

import org.apache.log4j.BasicConfigurator;
import org.drools.compiler.compiler.DroolsParserException;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import javax.swing.*;
import java.io.IOException;

public class BestGame {

    private static KieSession session;
    private static KieContainer container;
    private static KieBase k_base;

    public static void main(String[] args) throws DroolsParserException,
            IOException {
        KieServices ks = KieServices.Factory.get();
//        BasicConfigurator.configure();
        container = ks.getKieClasspathContainer();
        k_base = container.getKieBase();
        session = k_base.newKieSession();
        MainFrame.start(session);
        session.addEventListener( new DebugRuleRuntimeEventListener());
        session.addEventListener( new DebugAgendaEventListener());

    }

    public static void askQuestion(Question q){
        System.out.println(q.getQ());
        JFrame frame = new JFrame();
        int answer_idx = JOptionPane.showOptionDialog(
                frame,
                q.getQ(),
                "Answer the question",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                q.getPossibleAnswers(),
                q.getPossibleAnswers()[0]
        );
        FactHandle fh = session.getFactHandle(q);
        q.setAnswer(q.getPossibleAnswers()[answer_idx]);
        session.update(fh, q);
        session.fireAllRules();
    }

    public static class Question {
        private String q;
        private String shortcut;
        private String[] possibleAnswers;
        private String choosedAnswer;

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
    }

//    public static class Answer {
//        private String question;
//        private String answer;
//
//        public Answer(String question, String answer){
//            this.question = question;
//            this.answer = answer;
//        }
//
//        public String getAnswer(){
//            return this.answer;
//        }
//
//        public String getQuestion(){
//            return this.question;
//        }
//    }

}