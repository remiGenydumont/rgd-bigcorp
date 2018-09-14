package com.training.spring.bigcorp.service;


import com.training.spring.bigcorp.config.Monitored;
import com.training.spring.bigcorp.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class SiteServiceImpl implements SiteService {

    private CaptorService captorService;
    private ResourceLoader resourceLoader;
    private final static Logger logger = LoggerFactory.getLogger(SiteService.class);

    @Autowired
    public SiteServiceImpl(CaptorService captorService, ResourceLoader resourceLoader) {
        logger.info("Init SiteServiceImpl :{}",this);
        this.captorService = captorService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    @Monitored
    public Site findById(String siteId) {
        System.out.println("Appel de findById :" + this);
        if (siteId == null) {
            return null;
        }
        Site site = new Site("Florange");
        site.setId(siteId);
        site.setCaptors(captorService.findBySite(siteId));
        return site;
    }

}
