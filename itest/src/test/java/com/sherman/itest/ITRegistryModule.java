package com.sherman.itest;

import com.google.inject.AbstractModule;

import javax.persistence.EntityManager;

public class ITRegistryModule extends AbstractModule {
    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE
            = new ThreadLocal<EntityManager>();

    @Override
    protected void configure() {
        bind(EntityManager.class);
    }


}
