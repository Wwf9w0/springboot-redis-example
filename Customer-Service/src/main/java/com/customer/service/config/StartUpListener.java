package com.customer.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StartUpListener {

    @Autowired
    private CacheManager cacheManager;


    public final void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("On Application Event is OK");
        cacheManager.getCacheNames().parallelStream().forEach(n -> {
            log.info(n);
        });
    }
}
