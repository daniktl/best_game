package com.drools.best_game;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class BestGame {

    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer container = ks.getKieClasspathContainer();
        KieBase k_base = container.getKieBase();
        KieSession session = k_base.newKieSession();
        MainGUI.start(session);
        session.addEventListener(new DebugRuleRuntimeEventListener());
        session.addEventListener(new DebugAgendaEventListener());
    }
}