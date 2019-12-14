package com.drools.best_game;
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

    public static void main(String[] args) throws DroolsParserException,
            IOException {
        KieServices ks = KieServices.Factory.get();
        KieContainer container = ks.getKieClasspathContainer();
        KieBase k_base = container.getKieBase();
        session = k_base.newKieSession();
        MainGUI.start(session);
        session.addEventListener( new DebugRuleRuntimeEventListener());
        session.addEventListener( new DebugAgendaEventListener());

    }

    public static void askQuestion(Question q){
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

    public static class Answer {
        private String question;
        private String answer;

        public Answer(String question, String answer){
            this.question = question;
            this.answer = answer;
        }

        public String getAnswer(){
            return this.answer;
        }

        public String getQuestion(){
            return this.question;
        }
    }

//    public static class TmpAnswer {
//        private String shortcut;
//        private String answer;
//
//        public TmpAnswer(String shortcut, String answer){
//            shortcut = shortcut;
//            answer = answer;
//        }
//
//        public String getShortcut() {
//            return shortcut;
//        }
//
//        public String getAnswer() {
//            return answer;
//        }
//    }

}