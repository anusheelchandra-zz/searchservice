package com.expertsearch.repository;

import com.expertsearch.TestDataUtils;
import com.expertsearch.entity.Expert;
import com.expertsearch.entity.Status;
import com.expertsearch.model.Constants;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class ExpertRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExpertRepository repository;

    @BeforeEach
    void setup() {
        Expert expert = TestDataUtils.createExpert();
        entityManager.persist(expert);
        entityManager.flush();
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void shouldFindExpertsByName() {
        List<Expert> byName = repository.findByName("test expert");
        Assertions.assertNotNull(byName);
        commonAssert(byName);
    }

    @Test
    void shouldNotFindExpertsByName() {
        List<Expert> byName = repository.findByName("abc");
        Assertions.assertTrue(byName.isEmpty());
    }

    @Test
    void shouldFindExpertsByLanguage() {
        List<Expert> byLanguage = repository.findByLanguage("en", Constants.SORT_ORDER_BY_BOTH);
        Assertions.assertNotNull(byLanguage);
        commonAssert(byLanguage);
    }

    @Test
    void shouldNotFindExpertsByLanguage() {
        List<Expert> byLanguage = repository.findByLanguage("de", Constants.SORT_ORDER_BY_BOTH);
        Assertions.assertTrue(byLanguage.isEmpty());
    }

    @Test
    void shouldFindExpertsByDescriptionContaining() {
        List<Expert> byDescriptionContaining = repository.findByDescriptionContaining("test");
        Assertions.assertNotNull(byDescriptionContaining);
        commonAssert(byDescriptionContaining);
    }

    @Test
    void shouldNotFindExpertsByDescriptionContaining() {
        List<Expert> byDescriptionContaining = repository.findByDescriptionContaining("xyz");
        Assertions.assertTrue(byDescriptionContaining.isEmpty());
    }

    private void commonAssert(List<Expert> expertsByName) {
        Assertions.assertEquals(expertsByName.size(), 1);
        Assertions.assertEquals(expertsByName.get(0).getName(), "test expert");
        Assertions.assertEquals(expertsByName.get(0).getLanguage(), "en");
        Assertions.assertEquals(expertsByName.get(0).getDescription(), "test description");
        Assertions.assertEquals(expertsByName.get(0).getAvailability().getStatus(), Status.ONLINE);
        Assertions.assertEquals(expertsByName.get(0).getAvailability().getPricePerMinute().intValue(), 100);
    }
}