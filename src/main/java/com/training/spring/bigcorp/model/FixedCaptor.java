package com.training.spring.bigcorp.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FIXED")
public class FixedCaptor extends Captor {
    @Column
    private Integer defaultPowerInWatt;

    public FixedCaptor(String name, Site site, Integer defaultPowerInWatt) {
        super(name, site);
        this.defaultPowerInWatt = defaultPowerInWatt;
    }

    public FixedCaptor() {
    }

    public Integer getDefaultPowerInWatt() {
        return defaultPowerInWatt;
    }

    public void setDefaultPowerInWatt(Integer defaultPowerInWatt) {
        this.defaultPowerInWatt = defaultPowerInWatt;
    }
}
