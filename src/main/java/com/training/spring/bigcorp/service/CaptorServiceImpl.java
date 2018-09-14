package com.training.spring.bigcorp.service;

import com.training.spring.bigcorp.config.Monitored;
import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.PowerSource;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.service.measure.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CaptorServiceImpl implements CaptorService {

    private PowerSource powerSource;
    private MeasureService fixedMeasureService;
    private MeasureService simulatedMeasureService;
    private MeasureService realMeasureService;
    private CaptorDao captorDao;

    @Autowired
    @Lazy
    public CaptorServiceImpl(@Qualifier("fixedMeasureService") MeasureService testfixedMeasureService,
                             MeasureService simulatedMeasureService,
                             MeasureService realMeasureService,
                             CaptorDao captorDao) {
        this.fixedMeasureService = testfixedMeasureService;
        this.simulatedMeasureService = simulatedMeasureService;
        this.realMeasureService = realMeasureService;
        this.captorDao = captorDao;
    }

    @Override
    @Monitored
    public Set<Captor> findBySite(String siteId) {
        if (siteId == null) {
            return new HashSet<>();
        }
        return  captorDao.findBySiteId(siteId).stream().collect(Collectors.toSet());
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(PowerSource powerSource) {
        this.powerSource = powerSource;
    }
}
