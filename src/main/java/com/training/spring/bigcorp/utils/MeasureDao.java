package com.training.spring.bigcorp.utils;

import com.training.spring.bigcorp.model.Measure;
import com.training.spring.bigcorp.repository.CrudDao;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureDao extends CrudDao<Measure, Long> {

}
