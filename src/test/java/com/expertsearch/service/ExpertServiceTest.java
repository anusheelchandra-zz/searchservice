package com.expertsearch.service;

import com.expertsearch.TestDataUtils;
import com.expertsearch.entity.Status;
import com.expertsearch.model.Constants;
import com.expertsearch.model.ExpertDTO;
import com.expertsearch.model.SortMode;
import com.expertsearch.repository.ExpertRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ExpertServiceTest {

    @Mock
    private ExpertRepository repository;

    @InjectMocks
    private ExpertService expertService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(TestDataUtils.createExpert()));

        Mockito.when(repository.findByName("test expert"))
            .thenReturn(Collections.singletonList(TestDataUtils.createExpert()));

        Mockito.when(repository.findByDescriptionContaining("test"))
            .thenReturn(Collections.singletonList(TestDataUtils.createExpert()));

        Mockito.when( repository.findByLanguage("en", Constants.SORT_ORDER_BY_BOTH))
            .thenReturn(Collections.singletonList(TestDataUtils.createExpert()));
    }

    @Test
    void shouldFetchAllExperts() {
        List<ExpertDTO> expertDTOS = expertService.findAll();
        Assertions.assertNotNull(expertDTOS);
        commonAssert(expertDTOS);
    }

    @Test
    void shouldNotFetchAllExperts() {
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());
        List<ExpertDTO> expertDTOS = expertService.findAll();
        Assertions.assertTrue(expertDTOS.isEmpty());
    }

    @Test
    void shouldFetchExpertsByName() {
        List<ExpertDTO> expertsByName = expertService.findByName("test expert");
        Assertions.assertNotNull(expertsByName);
        commonAssert(expertsByName);
    }

    @Test
    void shouldNotFetchExpertsByName() {
        List<ExpertDTO> expertsByName = expertService.findByName("abc");
        Assertions.assertTrue(expertsByName.isEmpty());
    }

    @Test
    void shouldFetchExpertsByDescription() {
        List<ExpertDTO> byDescriptionContaining = expertService.findByDescriptionContaining("test");
        Assertions.assertNotNull(byDescriptionContaining);
        commonAssert(byDescriptionContaining);
    }

    @Test
    void shouldNotFetchExpertsByDescription() {
        List<ExpertDTO> expertByLanguage = expertService.findByDescriptionContaining("xyz");
        Assertions.assertTrue(expertByLanguage.isEmpty());

    }

    @Test
    void shouldFetchExpertsByLanguage() {
        List<ExpertDTO> expertByLanguage = expertService
                                            .getExpertByLanguage("en", SortMode.BOTH, Constants.DIRECTION_ASC);
        Assertions.assertNotNull(expertByLanguage);
        commonAssert(expertByLanguage);

    }

    @Test
    void shouldNotFetchExpertsByLanguage() {
        List<ExpertDTO> expertByLanguage = expertService
                                            .getExpertByLanguage("de", SortMode.BOTH, Constants.DIRECTION_ASC);
        Assertions.assertTrue(expertByLanguage.isEmpty());

    }

    private void commonAssert(List<ExpertDTO> expertsByName) {
        Assertions.assertEquals(expertsByName.size(), 1);
        Assertions.assertEquals(expertsByName.get(0).getName(), "test expert");
        Assertions.assertEquals(expertsByName.get(0).getLanguage(), "en");
        Assertions.assertEquals(expertsByName.get(0).getDescription(), "test description");
        Assertions.assertEquals(expertsByName.get(0).getAvailability().getStatus(), Status.ONLINE);
        Assertions.assertEquals(expertsByName.get(0).getAvailability().getPricePerMinute().intValue(), 100);
    }
}