package com.volkangurbuz.repositories;

import com.volkangurbuz.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {
  // add datajpa test that will bring up an embedded database and configure for us
  // autowired spring is going to do dependency injection on your integration test here.
  @Autowired UnitOfMeasureRepository unitOfMeasureRepository;

  @BeforeEach
  void setUp() {}

  @Test
  void findByDescription() {
    Optional<UnitOfMeasure> ueamOptioanl = unitOfMeasureRepository.findByDescription("Teaspoon");
    assertEquals("Teaspoon", ueamOptioanl.get().getDescription());
  }

  // second test will take 6 ms because the spring context was strill available.
  @Test
  void findByDescriptionCup() {
    Optional<UnitOfMeasure> ueamOptioanl = unitOfMeasureRepository.findByDescription("Cup");
    assertEquals("Cup", ueamOptioanl.get().getDescription());
  }
}
