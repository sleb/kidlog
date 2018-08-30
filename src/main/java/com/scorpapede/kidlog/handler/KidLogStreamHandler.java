package com.scorpapede.kidlog.handler;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.scorpapede.kidlog.guice.SkillModule;
import com.scorpapede.kidlog.store.guice.LogStoreModule;

public class KidLogStreamHandler extends SkillStreamHandler {
    public KidLogStreamHandler() {
        super(getSkill());
    }

    private static Skill getSkill() {
        return Guice.createInjector(
            new SkillModule(),
            new LogStoreModule()
        ).getInstance(Skill.class);
    }
}
