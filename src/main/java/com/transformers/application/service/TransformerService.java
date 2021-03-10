package com.transformers.application.service;

import com.transformers.application.dto.*;
import com.transformers.application.entity.Transformer;

import java.util.List;

public interface TransformerService {

    String addTransformer(TransformerDTOReq transformerDTOReq) throws Exception;

    TransformerDTOResp updateTranformer(TransformerUpdReq transformerUpdReq) throws Exception;

    List<TransformerDTOResp> getTransformers() throws Exception;

    String deleteTransformer(Long transformerId);

    TransBattleDTOResp executeBattle(List<Long> transformerIds) throws Exception;

    SingleBattleDTOResp startBattle(Transformer autobots, Transformer decepticons);
}
