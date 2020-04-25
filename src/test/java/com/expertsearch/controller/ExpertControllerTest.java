package com.expertsearch.controller;

import com.expertsearch.TestDataUtils;
import com.expertsearch.model.Constants;
import com.expertsearch.model.ExpertDTO;
import com.expertsearch.model.SortMode;
import com.expertsearch.service.ExpertService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static java.util.Comparator.comparing;

@WebMvcTest(ExpertController.class)
class ExpertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpertService expertService;

    @Test
    void shouldFindAllExperts() throws Exception {
        List<ExpertDTO> expertDTOs = Collections.singletonList(TestDataUtils.createExpertDTO());
        BDDMockito.given(expertService.findAll()).willReturn(expertDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.FIND_ALL_PATH)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",
                Matchers.is(expertDTOs.get(0).getName())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].description",
                Matchers.is(expertDTOs.get(0).getDescription())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].language",
                Matchers.is(expertDTOs.get(0).getLanguage())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].availability.status",
                Matchers.is(expertDTOs.get(0).getAvailability().getStatus().name())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].availability.pricePerMinute",
                Matchers.is(expertDTOs.get(0).getAvailability().getPricePerMinute().intValue())));
    }

    @Test
    void shouldNotFindAllExperts() throws Exception {
        BDDMockito.given(expertService.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.FIND_ALL_PATH)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFindExpertByName() throws Exception {
        List<ExpertDTO> expertDTOs = Collections.singletonList(TestDataUtils.createExpertDTO());
        BDDMockito.given(expertService.findByName(ArgumentMatchers.anyString())).willReturn(expertDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.FIND_BY_NAME_PATH)
            .param("name", "test expert")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(expertDTOs.get(0).getName())));
    }

    @Test
    void shouldNotFindExpertByName() throws Exception {
        BDDMockito.given(expertService.findByName(ArgumentMatchers.anyString())).willReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.FIND_BY_NAME_PATH)
            .param("name", "def")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFindExpertByDescription() throws Exception {
        List<ExpertDTO> expertDTOs = Collections.singletonList(TestDataUtils.createExpertDTO());
        BDDMockito.given(expertService.findByDescriptionContaining(ArgumentMatchers.anyString())).willReturn(expertDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.FIND_BY_DESC_PATH)
            .param("description", "test")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].description",
                Matchers.is(expertDTOs.get(0).getDescription())));
    }

    @Test
    void shouldNotFindExpertByDescription() throws Exception {
        BDDMockito.given(expertService.findByDescriptionContaining(ArgumentMatchers.anyString()))
            .willReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.FIND_BY_DESC_PATH)
            .param("description", "abc")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldSearchExpertByLanguageWithDefaultSorting() throws Exception {
        List<ExpertDTO> expertDTOs = TestDataUtils.createExpertDTOs().stream()
            .filter(expertDTO -> expertDTO.getLanguage().equals("en"))
            .sorted(comparing(expertDTO -> expertDTO.getAvailability().getPricePerMinute()))
            .collect(Collectors.toList());

        BDDMockito.given(expertService
            .getExpertByLanguage(ArgumentMatchers.anyString(), ArgumentMatchers.any(SortMode.class),
                ArgumentMatchers.anyString()))
            .willReturn(expertDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.SEARCH_BY_OPTIONS_PATH)
            .param("language", "en")
            .param("sortBy", "PRICE")
            .param("direction", "")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].language",
                Matchers.is(expertDTOs.get(0).getLanguage())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].availability.pricePerMinute",
                Matchers.is(expertDTOs.get(0).getAvailability().getPricePerMinute().intValue())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].language",
                Matchers.is(expertDTOs.get(1).getLanguage())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].availability.pricePerMinute",
                Matchers.is(expertDTOs.get(1).getAvailability().getPricePerMinute().intValue())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].language",
                Matchers.is(expertDTOs.get(2).getLanguage())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].availability.pricePerMinute",
                Matchers.is(expertDTOs.get(2).getAvailability().getPricePerMinute().intValue())));
    }

    @Test
    void shouldSearchExpertByLanguageWithDscSorting() throws Exception {
        List<ExpertDTO> expertDTOs = TestDataUtils.createExpertDTOs().stream()
            .filter(expertDTO -> expertDTO.getLanguage().equals("en"))
            .sorted(Collections.reverseOrder(comparing(expertDTO -> expertDTO.getAvailability().getPricePerMinute())))
            .collect(Collectors.toList());

        BDDMockito.given(expertService
            .getExpertByLanguage(ArgumentMatchers.anyString(), ArgumentMatchers.any(SortMode.class),
                ArgumentMatchers.anyString()))
            .willReturn(expertDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.SEARCH_BY_OPTIONS_PATH)
            .param("language", "en")
            .param("sortBy", "PRICE")
            .param("direction", "asc")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].language",
                Matchers.is(expertDTOs.get(0).getLanguage())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].availability.pricePerMinute",
                Matchers.is(expertDTOs.get(0).getAvailability().getPricePerMinute().intValue())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].language",
                Matchers.is(expertDTOs.get(1).getLanguage())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].availability.pricePerMinute",
                Matchers.is(expertDTOs.get(1).getAvailability().getPricePerMinute().intValue())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].language",
                Matchers.is(expertDTOs.get(2).getLanguage())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[2].availability.pricePerMinute",
                Matchers.is(expertDTOs.get(2).getAvailability().getPricePerMinute().intValue())));
    }

    @Test
    void shouldNotSearchExpertByLanguage() throws Exception {
        BDDMockito.given(expertService
            .getExpertByLanguage(ArgumentMatchers.anyString(), ArgumentMatchers.any(SortMode.class),
                ArgumentMatchers.anyString()))
            .willReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.SEARCH_BY_OPTIONS_PATH)
            .param("language", "en")
            .param("sortBy", "PRICE")
            .param("direction", "asc")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }
}