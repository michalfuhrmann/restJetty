package com.word.learner.service.impl;


import com.google.inject.AbstractModule;
import com.word.learner.service.api.ILearningService;
import com.word.learner.service.api.IRegistrationService;
import com.word.learner.service.api.IUserService;
import com.word.learner.service.api.IWordRecordService;

public class ServiceRegistryModule extends AbstractModule{
    @Override
    protected void configure() {

        System.out.println("elo");
        bind(ILearningService.class).to(LearningService.class);
        bind(IWordRecordService.class).to(WordRecordService.class);
        bind(IUserService.class).to(UserService.class);
        bind(IRegistrationService.class).to(RegistrationService.class);
    }
}
