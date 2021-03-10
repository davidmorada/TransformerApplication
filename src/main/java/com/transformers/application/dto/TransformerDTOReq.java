package com.transformers.application.dto;

import com.transformers.application.validator.ValidateNames;
import com.transformers.application.validator.ValidateScores;
import com.transformers.application.validator.ValidateTeams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransformerDTOReq implements Serializable {

    @ValidateNames
    private String transformerName;
    @ValidateTeams
    private String transformerTeam;
    @ValidateScores
    private Long strength;
    @ValidateScores
    private Long intelligence;
    @ValidateScores
    private Long speed;
    @ValidateScores
    private Long endurance;
    @ValidateScores
    private Long rank;
    @ValidateScores
    private Long courage;
    @ValidateScores
    private Long firepower;
}
