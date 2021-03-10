package com.transformers.application.config;

import com.transformers.application.dto.TransformerDTOReq;
import com.transformers.application.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultTransformers implements CommandLineRunner {

    TransformerService transformerService;

    @Autowired
    public DefaultTransformers(TransformerService transformerService) {
        this.transformerService = transformerService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<TransformerDTOReq> transformers = new ArrayList<>();

        TransformerDTOReq transformerDTOReq1 =
                TransformerDTOReq.builder()
                        .transformerName("Hubcap")
                        .transformerTeam("A")
                        .strength(new Long(4))
                        .intelligence(new Long(4))
                        .speed(new Long(4))
                        .endurance(new Long(4))
                        .rank(new Long(4))
                        .courage(new Long(4))
                        .firepower(new Long(4))
                        .build();

        transformers.add(transformerDTOReq1);

        TransformerDTOReq transformerDTOReq2 =
                TransformerDTOReq.builder()
                        .transformerName("Hound")
                        .transformerTeam("A")
                        .strength(new Long(1))
                        .intelligence(new Long(4))
                        .speed(new Long(5))
                        .endurance(new Long(2))
                        .rank(new Long(2))
                        .courage(new Long(3))
                        .firepower(new Long(3))
                        .build();

        transformers.add(transformerDTOReq2);

        TransformerDTOReq transformerDTOReq3 =
                TransformerDTOReq.builder()
                        .transformerName("Mirage")
                        .transformerTeam("A")
                        .strength(new Long(3))
                        .intelligence(new Long(5))
                        .speed(new Long(4))
                        .endurance(new Long(3))
                        .rank(new Long(1))
                        .courage(new Long(5))
                        .firepower(new Long(6))
                        .build();

        transformers.add(transformerDTOReq3);

        TransformerDTOReq transformerDTOReq4 =
                TransformerDTOReq.builder()
                        .transformerName("Soundwave")
                        .transformerTeam("D")
                        .strength(new Long(9))
                        .intelligence(new Long(2))
                        .speed(new Long(6))
                        .endurance(new Long(7))
                        .rank(new Long(5))
                        .courage(new Long(6))
                        .firepower(new Long(10))
                        .build();

        transformers.add(transformerDTOReq4);

        TransformerDTOReq transformerDTOReq5 =
                TransformerDTOReq.builder()
                        .transformerName("Megatron")
                        .transformerTeam("D")
                        .strength(new Long(2))
                        .intelligence(new Long(4))
                        .speed(new Long(3))
                        .endurance(new Long(2))
                        .rank(new Long(4))
                        .courage(new Long(5))
                        .firepower(new Long(8))
                        .build();

        transformers.add(transformerDTOReq5);

        TransformerDTOReq transformerDTOReq6 =
                TransformerDTOReq.builder()
                        .transformerName("Shockwave")
                        .transformerTeam("D")
                        .strength(new Long(7))
                        .intelligence(new Long(3))
                        .speed(new Long(4))
                        .endurance(new Long(6))
                        .rank(new Long(1))
                        .courage(new Long(1))
                        .firepower(new Long(5))
                        .build();

        transformers.add(transformerDTOReq6);

        for(TransformerDTOReq transformerDTOReq : transformers){
            transformerService.addTransformer(transformerDTOReq);
        }

    }
}
