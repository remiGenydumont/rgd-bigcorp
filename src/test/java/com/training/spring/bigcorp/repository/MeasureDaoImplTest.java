package com.training.spring.bigcorp.repository;

import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Measure;
import com.training.spring.bigcorp.model.RealCaptor;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.utils.MeasureDao;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class MeasureDaoImplTest {
    @Autowired
    private MeasureDao measureDao;

    @Test
    public void findById() {
        Optional<Measure> measure = measureDao.findById(-1L);
        Assertions.assertThat(measure).isNotEmpty();
        measure.ifPresent(measure1 -> {
            Assertions.assertThat(measure1.getId()).isEqualTo(-1L);
            Assertions.assertThat(measure1.getInstant()).isEqualTo(Instant.parse("2018-08-09T11:00:00.000Z"));
            Assertions.assertThat(measure1.getValueInWatt()).isEqualTo(1_000_000);
            Assertions.assertThat(measure1.getCaptor().getName()).isEqualTo("Eolienne");
            Assertions.assertThat(measure1.getCaptor().getSite().getName()).isEqualTo("Bigcorp Lyon");
        });

    }

    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        Optional<Measure> measure = measureDao.findById(-1000L);
        Assertions.assertThat(measure).isNull();
    }

    @Test
    public void findAll() {
        List<Measure> measures = measureDao.findAll();
        Assertions.assertThat(measures).hasSize(10);
    }

    @Test
    public void create() {
        Captor captor = new RealCaptor("Eolienne", new Site("site"));
        captor.setId("c1");
        Assertions.assertThat(measureDao.findAll()).hasSize(10);
        measureDao.save(new Measure(Instant.now(), 2_333_666, captor));
        Assertions.assertThat(measureDao.findAll()).hasSize(11);
    }

    @Test
    public void update() {
        Optional<Measure> optionalMeasure = measureDao.findById(-1L);
        Assertions.assertThat(optionalMeasure).isNotEmpty();
        optionalMeasure.ifPresent(measure -> {
            Assertions.assertThat(measure.getValueInWatt()).isEqualTo(1_000_000);
            measure.setValueInWatt(2_333_666);
            measureDao.save(measure);
        });
        optionalMeasure = measureDao.findById(-1L);
        Assertions.assertThat(optionalMeasure).isNotEmpty();
        optionalMeasure.ifPresent(measure -> {
            Assertions.assertThat(measure.getValueInWatt()).isEqualTo(2_333_666);
        });

    }

    @Test
    public void delete() {
        Assertions.assertThat(measureDao.findAll()).hasSize(10);
        Optional<Measure> optionalMeasure = measureDao.findById(-1L);
        Assertions.assertThat(optionalMeasure).isNotEmpty();
        optionalMeasure.ifPresent(measure -> {
            measureDao.delete(measure);
        });
        Assertions.assertThat(measureDao.findAll()).hasSize(9);
    }
}
