package com.expertsearch.mapper;

import com.expertsearch.entity.Availability;
import com.expertsearch.entity.Expert;
import com.expertsearch.model.AvailabilityDTO;
import com.expertsearch.model.ExpertDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExpertMapper {

    public Expert expertDTOToExpert(ExpertDTO expertDTO) {
        Expert expert =  new Expert();
        expert.setName(expertDTO.getName().toLowerCase());
        expert.setDescription(expertDTO.getDescription().toLowerCase());
        expert.setLanguage(expertDTO.getLanguage().toLowerCase());

        Availability availability = Availability.builder()
            .pricePerMinute(expertDTO.getAvailability().getPricePerMinute())
            .status(expertDTO.getAvailability().getStatus())
            .expert(expert)
            .build();

        expert.setAvailability(availability);
        return expert;
    }

    public ExpertDTO expertToExpertDTO(Expert expert) {
        ExpertDTO expertDTO =  new ExpertDTO();
        expertDTO.setName(expert.getName().toLowerCase());
        expertDTO.setDescription(expert.getDescription().toLowerCase());
        expertDTO.setLanguage(expert.getLanguage().toLowerCase());

        AvailabilityDTO availabilityDTO = AvailabilityDTO.builder()
            .pricePerMinute(expert.getAvailability().getPricePerMinute())
            .status(expert.getAvailability().getStatus())
            .build();

        expertDTO.setAvailability(availabilityDTO);
        return expertDTO;
    }

}


