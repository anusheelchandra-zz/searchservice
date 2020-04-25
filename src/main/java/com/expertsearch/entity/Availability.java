package com.expertsearch.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@ToString(exclude = "expert")
@AllArgsConstructor
@NoArgsConstructor
public class Availability implements Serializable {

    @Id
    private long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private BigDecimal pricePerMinute;

    @OneToOne
    @MapsId
    private Expert expert;
}
