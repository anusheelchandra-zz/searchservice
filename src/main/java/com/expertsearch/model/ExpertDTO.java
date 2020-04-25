package com.expertsearch.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExpertDTO {

    @ApiModelProperty(notes = "Expert name", required =true)
    private String name;

    @ApiModelProperty(notes = "Expert description", required =true)
    private String description;

    @ApiModelProperty(notes = "Expert language", required =true)
    private String language;

    @ApiModelProperty(notes = "Expert availability", required =true)
    private AvailabilityDTO availability;
}
