package com.scorpapede.kidlog.guice;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.scorpapede.kidlog.config.Config;
import com.scorpapede.kidlog.handler.guice.Launch;
import com.scorpapede.kidlog.handler.guice.LogActivity;

import javax.inject.Inject;
import javax.inject.Provider;

public class KidLogSkillProvider implements Provider<Skill> {
    private final RequestHandler launchRequestHandler;
    private final RequestHandler logActivityRequestHandler;
    private final Config config;

    @Override
    public Skill get() {
        return Skills.standard()
            .addRequestHandlers(
                launchRequestHandler,
                logActivityRequestHandler
            )
            //.withSkillId(config.getSkillId())
            .build();
    }

    @Inject
    public KidLogSkillProvider(
        @Launch RequestHandler launchRequestHandler,
        @LogActivity RequestHandler logActivityRequestHandler,
        Config config
    ) {
        this.launchRequestHandler = launchRequestHandler;
        this.logActivityRequestHandler = logActivityRequestHandler;
        this.config = config;
    }
}
