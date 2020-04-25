package com.expertsearch.model;

import com.expertsearch.entity.Status;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityDTO {

    @ApiModelProperty(notes = "Expert status", required =true)
    private Status status;

    @ApiModelProperty(notes = "Expert pricePerMinute", required =true)
    private BigDecimal pricePerMinute;
}
