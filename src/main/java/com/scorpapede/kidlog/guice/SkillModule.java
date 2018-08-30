package com.scorpapede.kidlog.guice;

import com.amazon.ask.Skill;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.google.inject.AbstractModule;
import com.scorpapede.kidlog.handler.LaunchRequestHandler;
import com.scorpapede.kidlog.handler.LogActivityHandler;
import com.scorpapede.kidlog.handler.guice.Launch;
import com.scorpapede.kidlog.handler.guice.LogActivity;

public class SkillModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Skill.class).toProvider(KidLogSkillProvider.class);
        bind(RequestHandler.class).annotatedWith(Launch.class).to(LaunchRequestHandler.class);
        bind(RequestHandler.class).annotatedWith(LogActivity.class).to(LogActivityHandler.class);
    }
}
