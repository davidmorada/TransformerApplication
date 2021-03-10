package com.transformers.application.controller;

import com.transformers.application.dto.TransBattleDTOResp;
import com.transformers.application.dto.TransformerDTOReq;
import com.transformers.application.dto.TransformerDTOResp;
import com.transformers.application.dto.TransformerUpdReq;
import com.transformers.application.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class TransformerController {

    TransformerService transformerService;

    @Autowired
    public TransformerController(TransformerService transformerService) {
        this.transformerService = transformerService;
    }

    /***
     * get all transformers
     * @return
     */
    @GetMapping("/v1/transformers")
    public ResponseEntity<List<TransformerDTOResp>> getTransformers() throws Exception{
        return new ResponseEntity(transformerService.getTransformers(), HttpStatus.OK);
    }

    /***
     * create trasformer
     * @param transformerDTOReq
     * @return
     */
    @PostMapping("/v1/transformers/transformer")
    public ResponseEntity<String> addTransformer(@Valid @RequestBody TransformerDTOReq transformerDTOReq) throws Exception {
        return new ResponseEntity<>(transformerService.addTransformer(transformerDTOReq), HttpStatus.CREATED);
    }

    /***
     *
     * @param transformerUpdReq
     * @return
     */
    @PutMapping("/v1/transformers/transformer")
    public ResponseEntity<String> updateTransformer(@RequestBody TransformerUpdReq transformerUpdReq) throws Exception {
        return new ResponseEntity(transformerService.updateTranformer(transformerUpdReq), HttpStatus.ACCEPTED);
    }

    /***
     *
     * @param transformerID
     * @return
     */
    @DeleteMapping("/v1/transformers/transformer/{transformerID}")
    public ResponseEntity<String> deleteTransformer(@PathVariable(name = "transformerID") Long transformerID) {
        return new ResponseEntity(transformerService.deleteTransformer(transformerID), HttpStatus.OK);
    }

    /***
     * Begin Transformers Battle
     * @param transformerIds
     * @return
     * @throws Exception
     */
    @PostMapping("/v1/transformers")
    public ResponseEntity<List<TransBattleDTOResp>> beginBattle(@Valid @RequestBody List<Long> transformerIds) throws Exception{
        return new ResponseEntity(transformerService.executeBattle(transformerIds),HttpStatus.OK);
    }
}
