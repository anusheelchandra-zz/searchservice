package com.expertsearch.mapper;

import com.expertsearch.TestDataUtils;
import com.expertsearch.entity.Expert;
import com.expertsearch.model.ExpertDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExpertMapperTest {

    @Test
    void shouldMapExpertDTOToExpert() {
        Expert expert = ExpertMapper.expertDTOToExpert(TestDataUtils.createExpertDTO());
        assertNotNull(expert);
    }

    @Test
    void shouldMapExpertToExpertDTO() {
        ExpertDTO expertDTO = ExpertMapper.expertToExpertDTO(TestDataUtils.createExpert());
        assertNotNull(expertDTO);
    }

}