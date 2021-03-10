package com.transformers.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SingleBattleDTOResp {

    private Long autobots;
    private Long decepticons;
    private String autbotsName;
    private String decepticonsName;
    private String status;
}
