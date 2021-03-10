package com.transformers.application.dto;

import com.transformers.application.config.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MissingTransformerDTO {

    private String transformerId;

    public String getTransformerId() {
        return "Transfromer Id: ".concat(transformerId).concat(", ")
                .concat(Constants.NO_RECORD_FOUND_MESSAGE);
    }
}
