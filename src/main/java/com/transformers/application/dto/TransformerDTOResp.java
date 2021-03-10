package com.transformers.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransformerDTOResp {

    private Long transformerID;
    private String transformerName;
    private String transformerTeam;
    private Long strength;
    private Long intelligence;
    private Long speed;
    private Long endurance;
    private Long rank;
    private Long courage;
    private Long firepower;
    private Long skill;

}
