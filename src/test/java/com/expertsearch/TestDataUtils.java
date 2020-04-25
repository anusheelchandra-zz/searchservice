package com.expertsearch;

import com.expertsearch.entity.Availability;
import com.expertsearch.entity.Expert;
import com.expertsearch.entity.Status;
import com.expertsearch.model.AvailabilityDTO;
import com.expertsearch.model.ExpertDTO;
import java.math.BigDecimal;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataUtils {

    public Expert createExpert() {
        Expert expert = new Expert();
        expert.setName("test expert");
        expert.setDescription("test description");
        expert.setLanguage("en");

        Availability availability = Availability.builder()
            .pricePerMinute(new BigDecimal(100))
            .status(Status.ONLINE)
            .expert(expert)
            .build();

        expert.setAvailability(availability);
        return expert;
    }

    public ExpertDTO createExpertDTO() {
        ExpertDTO expertDTO = new ExpertDTO();
        expertDTO.setName("test expert");
        expertDTO.setDescription("test description");
        expertDTO.setLanguage("en");

        AvailabilityDTO availabilityDTO = AvailabilityDTO.builder()
            .pricePerMinute(new BigDecimal(100))
            .status(Status.ONLINE)
            .build();

        expertDTO.setAvailability(availabilityDTO);
        return expertDTO;
    }

    public List<ExpertDTO> createExpertDTOs() {
        ExpertDTO expert1 = new ExpertDTO();
        expert1.setName("abc");
        expert1.setDescription("abc description");
        expert1.setLanguage("en");

        ExpertDTO expert2 = new ExpertDTO();
        expert2.setName("def");
        expert2.setDescription("def description");
        expert2.setLanguage("en");

        ExpertDTO expert3 = new ExpertDTO();
        expert3.setName("efg");
        expert3.setDescription("efg description");
        expert3.setLanguage("en");

        ExpertDTO expert4 = new ExpertDTO();
        expert4.setName("hij");
        expert4.setDescription("hij description");
        expert4.setLanguage("de");

        ExpertDTO expert5 = new ExpertDTO();
        expert5.setName("klm");
        expert5.setDescription("klm description");
        expert5.setLanguage("de");

        ExpertDTO expert6 = new ExpertDTO();
        expert6.setName("mno");
        expert6.setDescription("mno description");
        expert6.setLanguage("de");

        AvailabilityDTO availability1 = AvailabilityDTO.builder()
            .pricePerMinute(new BigDecimal(100))
            .status(Status.ONLINE)
            .build();

        AvailabilityDTO availability2 = AvailabilityDTO.builder()
            .pricePerMinute(new BigDecimal(200))
            .status(Status.BUSY)
            .build();

        AvailabilityDTO availability3 = AvailabilityDTO.builder()
            .pricePerMinute(new BigDecimal(300))
            .status(Status.OFFLINE)
            .build();

        AvailabilityDTO availability4 = AvailabilityDTO.builder()
            .pricePerMinute(new BigDecimal(400))
            .status(Status.ONLINE)
            .build();

        AvailabilityDTO availability5 = AvailabilityDTO.builder()
            .pricePerMinute(new BigDecimal(500))
            .status(Status.ONLINE)
            .build();

        AvailabilityDTO availability6 = AvailabilityDTO.builder()
            .pricePerMinute(new BigDecimal(600))
            .status(Status.OFFLINE)
            .build();

        expert1.setAvailability(availability1);
        expert2.setAvailability(availability2);
        expert3.setAvailability(availability3);
        expert4.setAvailability(availability4);
        expert5.setAvailability(availability5);
        expert6.setAvailability(availability6);

        return List.of(expert1, expert2, expert3, expert4, expert5, expert6);
    }
}