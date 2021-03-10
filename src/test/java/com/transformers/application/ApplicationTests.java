package com.transformers.application;

import com.transformers.application.config.Constants;
import com.transformers.application.dto.*;
import com.transformers.application.entity.Transformer;
import com.transformers.application.exception.NoRecordFoundException;
import com.transformers.application.repository.TransformerRepository;
import com.transformers.application.service.TransformerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ApplicationTests {

    @InjectMocks
    TransformerServiceImpl transformerService;

    @Mock
    TransformerRepository transformerRepository;

    /***
     * add transformers
     */
    @Test
    void testAddTransformers() throws Exception {

        //expected mockito
        Transformer transformer = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Soundwave")
                .transformerTeam("A")
                .strength(new Long(1))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .build();

        //input
        TransformerDTOReq transformerDTOReq = TransformerDTOReq.builder()
                .transformerName("Soundwave")
                .transformerTeam("A")
                .strength(new Long(1))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .build();

        Mockito.when(transformerRepository.save(Mockito.any())).thenReturn(transformer);

        Assertions.assertEquals("Transformer Created.", transformerService.addTransformer(transformerDTOReq));
    }

    /***
     * Update transformer
     */
    @Test
    public void testUpdateTransformer() throws Exception {

        //expected
        TransformerDTOResp transformerDTOResp = TransformerDTOResp.builder()
                .transformerID(new Long(1))
                .transformerName("Soundwave")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .skill(new Long(6))
                .build();

        //expected mockito
        Transformer transformer = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Soundwave")
                .transformerTeam("A")
                .strength(new Long(1))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .skill(new Long(5))
                .build();

        //input
        TransformerUpdReq transformerUpdReq = TransformerUpdReq.builder()
                .transformerID(new Long(1))
                .transformerName("Soundwave")
                .transformerTeam("D")
                .strength(new Long(1))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .build();


        Transformer updatedTransformer = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Soundwave")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .skill(new Long(6))
                .build();

        Mockito.when(transformerRepository.findByTransformerID(new Long(1))).thenReturn(transformer);
        Mockito.when(transformerRepository.save(Mockito.any())).thenReturn(updatedTransformer);
        Assertions.assertEquals(transformerDTOResp, transformerService.updateTranformer(transformerUpdReq));
    }

    /***
     * Compute for skill ("Over-all Rating")
     * Skill = Strength + Intelligence + Speed + Endurance + Firepower
     */
    @Test
    public void testGetSkill() {

        TransformerDTOReq skillCalcDTO = TransformerDTOReq.builder()
                .strength(new Long(1))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .firepower(new Long(1))
                .build();

        TransformerUpdReq skillCalcDTO1 = TransformerUpdReq.builder()
                .strength(new Long(1))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .firepower(new Long(1))
                .build();
        Assertions.assertEquals(new Long(5), transformerService.getSkill(skillCalcDTO));
        Assertions.assertEquals(new Long(5), transformerService.getSkill(skillCalcDTO1));
    }

    /***
     * Get Transformers
     */
    @Test
    public void testGetTransformers() throws Exception {

        //expected
        List<TransformerDTOResp> transformerDTOResps = new ArrayList<>();

        TransformerDTOResp transformerDTOResp1 = TransformerDTOResp.builder()
                .transformerID(new Long(1))
                .transformerName("Soundwave")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .skill(new Long(6))
                .build();

        transformerDTOResps.add(transformerDTOResp1);

        TransformerDTOResp transformerDTOResp2 = TransformerDTOResp.builder()
                .transformerID(new Long(1))
                .transformerName("Optimus Prime")
                .transformerTeam("A")
                .strength(new Long(2))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .skill(new Long(6))
                .build();

        transformerDTOResps.add(transformerDTOResp2);

        //expected mockito
        List<Transformer> transformers = new ArrayList<>();
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Soundwave")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .skill(new Long(6))
                .build();
        transformers.add(transformer1);

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Optimus Prime")
                .transformerTeam("A")
                .strength(new Long(2))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .skill(new Long(6))
                .build();
        transformers.add(transformer2);

        Mockito.when(transformerRepository.findAll()).thenReturn(transformers);

        Assertions.assertEquals(transformerDTOResps, transformerService.getTransformers());
    }

    @Test
    public void testDeleteTransformer() {

        //expected mockito
        Transformer transformer = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Soundwave")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(1))
                .speed(new Long(1))
                .endurance(new Long(1))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(1))
                .skill(new Long(6))
                .build();

        Mockito.when(transformerRepository.findByTransformerID(new Long(1))).thenReturn(transformer);
        Assertions.assertEquals("Transformer Destroyed.", transformerService.deleteTransformer(new Long(1)));

    }

    @Test
    public void testTeamBattleWithPredaking3x3() throws Exception {
        String result = "3 battle(s) Winning Team (Decepticons): Predaking, Megatron, Shockwave Survivors from " +
                "the losing team (Autobots): Hubcap, Hound, Mirage";

        TransBattleDTOResp expected = TransBattleDTOResp.builder()
                .result(result)
                .build();

        //mock
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Hubcap")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(4))
                .speed(new Long(4))
                .endurance(new Long(4))
                .rank(new Long(4))
                .courage(new Long(4))
                .firepower(new Long(4))
                .skill(new Long(20))
                .build();

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(2))
                .transformerName("Hound")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(7))
                .speed(new Long(5))
                .endurance(new Long(2))
                .rank(new Long(2))
                .courage(new Long(3))
                .firepower(new Long(3))
                .skill(new Long(21))
                .build();

        Transformer transformer3 = Transformer.builder()
                .transformerID(new Long(3))
                .transformerName("Mirage")
                .transformerTeam("A")
                .strength(new Long(3))
                .intelligence(new Long(5))
                .speed(new Long(4))
                .endurance(new Long(3))
                .rank(new Long(1))
                .courage(new Long(5))
                .firepower(new Long(6))
                .skill(new Long(21))
                .build();

        Transformer transformer4 = Transformer.builder()
                .transformerID(new Long(4))
                .transformerName("Predaking")
                .transformerTeam("D")
                .strength(new Long(9))
                .intelligence(new Long(2))
                .speed(new Long(6))
                .endurance(new Long(7))
                .rank(new Long(5))
                .courage(new Long(6))
                .firepower(new Long(10))
                .skill(new Long(34))
                .build();

        Transformer transformer5 = Transformer.builder()
                .transformerID(new Long(5))
                .transformerName("Megatron")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(4))
                .speed(new Long(3))
                .endurance(new Long(2))
                .rank(new Long(4))
                .courage(new Long(5))
                .firepower(new Long(8))
                .skill(new Long(21))
                .build();

        Transformer transformer6 = Transformer.builder()
                .transformerID(new Long(6))
                .transformerName("Shockwave")
                .transformerTeam("D")
                .strength(new Long(7))
                .intelligence(new Long(3))
                .speed(new Long(4))
                .endurance(new Long(6))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(5))
                .skill(new Long(25))
                .build();

        Mockito.when(transformerRepository.findByTransformerID(new Long(1))).thenReturn(transformer1);
        Mockito.when(transformerRepository.findByTransformerID(new Long(2))).thenReturn(transformer2);
        Mockito.when(transformerRepository.findByTransformerID(new Long(3))).thenReturn(transformer3);
        Mockito.when(transformerRepository.findByTransformerID(new Long(4))).thenReturn(transformer4);
        Mockito.when(transformerRepository.findByTransformerID(new Long(5))).thenReturn(transformer5);
        Mockito.when(transformerRepository.findByTransformerID(new Long(6))).thenReturn(transformer6);

        //input
        List<Long> transformerIds = Arrays.asList(new Long(1),
                new Long(2),
                new Long(3),
                new Long(4),
                new Long(5),
                new Long(6));

        Assertions.assertEquals(expected, transformerService.executeBattle(transformerIds));
    }

    @Test
    public void testTeamBattleWithOptimusPrimeVSPredaking3x3() throws Exception {
        String result = Constants.GAME_OVER;

        TransBattleDTOResp expected = TransBattleDTOResp.builder()
                .result(result)
                .build();

        //mock
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Optimus Prime")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(4))
                .speed(new Long(4))
                .endurance(new Long(4))
                .rank(new Long(4))
                .courage(new Long(4))
                .firepower(new Long(4))
                .skill(new Long(20))
                .build();

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(2))
                .transformerName("Hound")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(7))
                .speed(new Long(5))
                .endurance(new Long(2))
                .rank(new Long(2))
                .courage(new Long(3))
                .firepower(new Long(3))
                .skill(new Long(21))
                .build();

        Transformer transformer3 = Transformer.builder()
                .transformerID(new Long(3))
                .transformerName("Mirage")
                .transformerTeam("A")
                .strength(new Long(3))
                .intelligence(new Long(5))
                .speed(new Long(4))
                .endurance(new Long(3))
                .rank(new Long(1))
                .courage(new Long(5))
                .firepower(new Long(6))
                .skill(new Long(21))
                .build();

        Transformer transformer4 = Transformer.builder()
                .transformerID(new Long(4))
                .transformerName("Predaking")
                .transformerTeam("D")
                .strength(new Long(9))
                .intelligence(new Long(2))
                .speed(new Long(6))
                .endurance(new Long(7))
                .rank(new Long(5))
                .courage(new Long(6))
                .firepower(new Long(10))
                .skill(new Long(34))
                .build();

        Transformer transformer5 = Transformer.builder()
                .transformerID(new Long(5))
                .transformerName("Megatron")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(4))
                .speed(new Long(3))
                .endurance(new Long(2))
                .rank(new Long(4))
                .courage(new Long(5))
                .firepower(new Long(8))
                .skill(new Long(21))
                .build();

        Transformer transformer6 = Transformer.builder()
                .transformerID(new Long(6))
                .transformerName("Shockwave")
                .transformerTeam("D")
                .strength(new Long(7))
                .intelligence(new Long(3))
                .speed(new Long(4))
                .endurance(new Long(6))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(5))
                .skill(new Long(25))
                .build();

        Mockito.when(transformerRepository.findByTransformerID(new Long(1))).thenReturn(transformer1);
        Mockito.when(transformerRepository.findByTransformerID(new Long(2))).thenReturn(transformer2);
        Mockito.when(transformerRepository.findByTransformerID(new Long(3))).thenReturn(transformer3);
        Mockito.when(transformerRepository.findByTransformerID(new Long(4))).thenReturn(transformer4);
        Mockito.when(transformerRepository.findByTransformerID(new Long(5))).thenReturn(transformer5);
        Mockito.when(transformerRepository.findByTransformerID(new Long(6))).thenReturn(transformer6);

        //input
        List<Long> transformerIds = Arrays.asList(new Long(1),
                new Long(2),
                new Long(3),
                new Long(4),
                new Long(5),
                new Long(6));

        Assertions.assertEquals(expected, transformerService.executeBattle(transformerIds));
    }

    @Test
    public void testTeamBattleWithOptimusPrime3x3() throws Exception {
        String result = "3 battle(s) Winning Team (Autobots): Optimus Prime, Hound, Mirage Survivors from " +
                "the losing team (Decepticons): Soundwave, Megatron, Shockwave";

        TransBattleDTOResp expected = TransBattleDTOResp.builder()
                .result(result)
                .build();

        //mock
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Optimus Prime")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(4))
                .speed(new Long(4))
                .endurance(new Long(4))
                .rank(new Long(4))
                .courage(new Long(4))
                .firepower(new Long(4))
                .skill(new Long(20))
                .build();

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(2))
                .transformerName("Hound")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(7))
                .speed(new Long(5))
                .endurance(new Long(2))
                .rank(new Long(2))
                .courage(new Long(3))
                .firepower(new Long(3))
                .skill(new Long(21))
                .build();

        Transformer transformer3 = Transformer.builder()
                .transformerID(new Long(3))
                .transformerName("Mirage")
                .transformerTeam("A")
                .strength(new Long(3))
                .intelligence(new Long(5))
                .speed(new Long(4))
                .endurance(new Long(3))
                .rank(new Long(1))
                .courage(new Long(5))
                .firepower(new Long(6))
                .skill(new Long(21))
                .build();

        Transformer transformer4 = Transformer.builder()
                .transformerID(new Long(4))
                .transformerName("Soundwave")
                .transformerTeam("D")
                .strength(new Long(9))
                .intelligence(new Long(2))
                .speed(new Long(6))
                .endurance(new Long(7))
                .rank(new Long(5))
                .courage(new Long(6))
                .firepower(new Long(10))
                .skill(new Long(34))
                .build();

        Transformer transformer5 = Transformer.builder()
                .transformerID(new Long(5))
                .transformerName("Megatron")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(4))
                .speed(new Long(3))
                .endurance(new Long(2))
                .rank(new Long(4))
                .courage(new Long(5))
                .firepower(new Long(8))
                .skill(new Long(21))
                .build();

        Transformer transformer6 = Transformer.builder()
                .transformerID(new Long(6))
                .transformerName("Shockwave")
                .transformerTeam("D")
                .strength(new Long(7))
                .intelligence(new Long(3))
                .speed(new Long(4))
                .endurance(new Long(6))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(5))
                .skill(new Long(25))
                .build();

        Mockito.when(transformerRepository.findByTransformerID(new Long(1))).thenReturn(transformer1);
        Mockito.when(transformerRepository.findByTransformerID(new Long(2))).thenReturn(transformer2);
        Mockito.when(transformerRepository.findByTransformerID(new Long(3))).thenReturn(transformer3);
        Mockito.when(transformerRepository.findByTransformerID(new Long(4))).thenReturn(transformer4);
        Mockito.when(transformerRepository.findByTransformerID(new Long(5))).thenReturn(transformer5);
        Mockito.when(transformerRepository.findByTransformerID(new Long(6))).thenReturn(transformer6);

        //input
        List<Long> transformerIds = Arrays.asList(new Long(1),
                new Long(2),
                new Long(3),
                new Long(4),
                new Long(5),
                new Long(6));

        Assertions.assertEquals(expected, transformerService.executeBattle(transformerIds));
    }

    @Test
    public void testTeamBattle3x2() throws Exception {
        //expected
        String result = "2 battle(s) Winning Team (Decepticons): Soundwave, Megatron Survivors from " +
                "the losing team (Autobots): Hubcap, Hound";

        TransBattleDTOResp expected = TransBattleDTOResp.builder()
                .result(result)
                .build();

        //mock
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Hubcap")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(4))
                .speed(new Long(4))
                .endurance(new Long(4))
                .rank(new Long(4))
                .courage(new Long(4))
                .firepower(new Long(4))
                .skill(new Long(20))
                .build();

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(2))
                .transformerName("Hound")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(7))
                .speed(new Long(5))
                .endurance(new Long(2))
                .rank(new Long(2))
                .courage(new Long(3))
                .firepower(new Long(3))
                .skill(new Long(21))
                .build();

        Transformer transformer3 = Transformer.builder()
                .transformerID(new Long(3))
                .transformerName("Mirage")
                .transformerTeam("A")
                .strength(new Long(3))
                .intelligence(new Long(5))
                .speed(new Long(4))
                .endurance(new Long(3))
                .rank(new Long(1))
                .courage(new Long(5))
                .firepower(new Long(6))
                .skill(new Long(21))
                .build();

        Transformer transformer4 = Transformer.builder()
                .transformerID(new Long(4))
                .transformerName("Soundwave")
                .transformerTeam("D")
                .strength(new Long(9))
                .intelligence(new Long(2))
                .speed(new Long(6))
                .endurance(new Long(7))
                .rank(new Long(5))
                .courage(new Long(6))
                .firepower(new Long(10))
                .skill(new Long(34))
                .build();

        Transformer transformer5 = Transformer.builder()
                .transformerID(new Long(5))
                .transformerName("Megatron")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(4))
                .speed(new Long(3))
                .endurance(new Long(2))
                .rank(new Long(4))
                .courage(new Long(5))
                .firepower(new Long(8))
                .skill(new Long(21))
                .build();

        Mockito.when(transformerRepository.findByTransformerID(new Long(1))).thenReturn(transformer1);
        Mockito.when(transformerRepository.findByTransformerID(new Long(2))).thenReturn(transformer2);
        Mockito.when(transformerRepository.findByTransformerID(new Long(3))).thenReturn(transformer3);
        Mockito.when(transformerRepository.findByTransformerID(new Long(4))).thenReturn(transformer4);
        Mockito.when(transformerRepository.findByTransformerID(new Long(5))).thenReturn(transformer5);

        //input
        List<Long> transformerIds = Arrays.asList(new Long(1),
                new Long(2),
                new Long(3),
                new Long(4),
                new Long(5));

        Assertions.assertEquals(expected, transformerService.executeBattle(transformerIds));
    }

    @Test
    public void testTeamBattle3x3() throws Exception {

        //expected
        String result = "3 battle(s) Winning Team (Decepticons): Soundwave, Megatron, Shockwave Survivors from " +
                "the losing team (Autobots): Hubcap, Hound, Mirage";

        TransBattleDTOResp expected = TransBattleDTOResp.builder()
                .result(result)
                .build();

        //expected mockito
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Hubcap")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(4))
                .speed(new Long(4))
                .endurance(new Long(4))
                .rank(new Long(4))
                .courage(new Long(4))
                .firepower(new Long(4))
                .skill(new Long(20))
                .build();

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(2))
                .transformerName("Hound")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(7))
                .speed(new Long(5))
                .endurance(new Long(2))
                .rank(new Long(2))
                .courage(new Long(3))
                .firepower(new Long(3))
                .skill(new Long(21))
                .build();

        Transformer transformer3 = Transformer.builder()
                .transformerID(new Long(3))
                .transformerName("Mirage")
                .transformerTeam("A")
                .strength(new Long(3))
                .intelligence(new Long(5))
                .speed(new Long(4))
                .endurance(new Long(3))
                .rank(new Long(1))
                .courage(new Long(5))
                .firepower(new Long(6))
                .skill(new Long(21))
                .build();

        Transformer transformer4 = Transformer.builder()
                .transformerID(new Long(4))
                .transformerName("Soundwave")
                .transformerTeam("D")
                .strength(new Long(9))
                .intelligence(new Long(2))
                .speed(new Long(6))
                .endurance(new Long(7))
                .rank(new Long(5))
                .courage(new Long(6))
                .firepower(new Long(10))
                .skill(new Long(34))
                .build();

        Transformer transformer5 = Transformer.builder()
                .transformerID(new Long(5))
                .transformerName("Megatron")
                .transformerTeam("D")
                .strength(new Long(2))
                .intelligence(new Long(4))
                .speed(new Long(3))
                .endurance(new Long(2))
                .rank(new Long(4))
                .courage(new Long(5))
                .firepower(new Long(8))
                .skill(new Long(21))
                .build();

        Transformer transformer6 = Transformer.builder()
                .transformerID(new Long(6))
                .transformerName("Shockwave")
                .transformerTeam("D")
                .strength(new Long(7))
                .intelligence(new Long(3))
                .speed(new Long(4))
                .endurance(new Long(6))
                .rank(new Long(1))
                .courage(new Long(1))
                .firepower(new Long(5))
                .skill(new Long(25))
                .build();

        Mockito.when(transformerRepository.findByTransformerID(new Long(1))).thenReturn(transformer1);
        Mockito.when(transformerRepository.findByTransformerID(new Long(2))).thenReturn(transformer2);
        Mockito.when(transformerRepository.findByTransformerID(new Long(3))).thenReturn(transformer3);
        Mockito.when(transformerRepository.findByTransformerID(new Long(4))).thenReturn(transformer4);
        Mockito.when(transformerRepository.findByTransformerID(new Long(5))).thenReturn(transformer5);
        Mockito.when(transformerRepository.findByTransformerID(new Long(6))).thenReturn(transformer6);

        //input
        List<Long> transformerIds = Arrays.asList(new Long(1),
                new Long(2),
                new Long(3),
                new Long(4),
                new Long(5),
                new Long(6));

        Assertions.assertEquals(expected, transformerService.executeBattle(transformerIds));

    }

    @Test
    public void testStartBattlewithHighSkills() {
        //expected
        SingleBattleDTOResp expected = SingleBattleDTOResp.builder()
                .autobots(Constants.LOOSER)
                .decepticons(Constants.WINNER)
                .autbotsName("Hubcap")
                .decepticonsName("Soundwave")
                .build();

        //input
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Hubcap")
                .transformerTeam("A")
                .strength(new Long(6))
                .intelligence(new Long(7))
                .speed(new Long(5))
                .endurance(new Long(2))
                .rank(new Long(1))
                .courage(new Long(6))
                .firepower(new Long(3))
                .skill(new Long(20))
                .build();

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Soundwave")
                .transformerTeam("A")
                .strength(new Long(9))
                .intelligence(new Long(7))
                .speed(new Long(5))
                .endurance(new Long(2))
                .rank(new Long(1))
                .courage(new Long(10))
                .firepower(new Long(3))
                .skill(new Long(34))
                .build();

        Assertions.assertEquals(expected, transformerService.startBattle(transformer1, transformer2));

    }

    @Test
    public void testStartBattleHighCourageAndStrengthAutobots() {
        //expected
        SingleBattleDTOResp expected = SingleBattleDTOResp.builder()
                .autobots(Constants.WINNER)
                .decepticons(Constants.LOOSER)
                .autbotsName("Jazz")
                .decepticonsName("Starscream")
                .build();

        //input
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Jazz")
                .transformerTeam("A")
                .strength(new Long(2))
                .intelligence(new Long(3))
                .speed(new Long(2))
                .endurance(new Long(3))
                .rank(new Long(2))
                .courage(new Long(5))
                .firepower(new Long(3))
                .skill(new Long(13))
                .build();

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Starscream")
                .transformerTeam("D")
                .strength(new Long(3))
                .intelligence(new Long(2))
                .speed(new Long(2))
                .endurance(new Long(2))
                .rank(new Long(2))
                .courage(new Long(2))
                .firepower(new Long(2))
                .skill(new Long(11))
                .build();

        Assertions.assertEquals(expected, transformerService.startBattle(transformer1, transformer2));
    }

    @Test
    public void testStartBattleWithHighCourageAndStrengthDecepticons() {
        //expected
        SingleBattleDTOResp expected = SingleBattleDTOResp.builder()
                .autobots(Constants.LOOSER)
                .decepticons(Constants.WINNER)
                .autbotsName("Mirage")
                .decepticonsName("Shockwave")
                .build();

        //input
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Mirage")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(4))
                .speed(new Long(4))
                .endurance(new Long(4))
                .rank(new Long(1))
                .courage(new Long(4))
                .firepower(new Long(4))
                .skill(new Long(20))
                .build();

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(8))
                .transformerName("Shockwave")
                .transformerTeam("D")
                .strength(new Long(9))
                .intelligence(new Long(2))
                .speed(new Long(6))
                .endurance(new Long(7))
                .rank(new Long(5))
                .courage(new Long(6))
                .firepower(new Long(10))
                .skill(new Long(34))
                .build();

        Assertions.assertEquals(expected, transformerService.startBattle(transformer1, transformer2));
    }

    @Test
    public void testStartBattleWhenSkillsAreTie() {
        //expected
        SingleBattleDTOResp expected = SingleBattleDTOResp.builder()
                .autobots(Constants.LOOSER)
                .decepticons(Constants.LOOSER)
                .autbotsName("Hound")
                .decepticonsName("Megatron")
                .build();

        //input
        Transformer transformer1 = Transformer.builder()
                .transformerID(new Long(1))
                .transformerName("Hound")
                .transformerTeam("A")
                .strength(new Long(4))
                .intelligence(new Long(7))
                .speed(new Long(5))
                .endurance(new Long(2))
                .rank(new Long(1))
                .courage(new Long(3))
                .firepower(new Long(3))
                .skill(new Long(20))
                .build();

        Transformer transformer2 = Transformer.builder()
                .transformerID(new Long(4))
                .transformerName("Megatron")
                .transformerTeam("A")
                .strength(new Long(2))
                .intelligence(new Long(4))
                .speed(new Long(3))
                .endurance(new Long(2))
                .rank(new Long(4))
                .courage(new Long(5))
                .firepower(new Long(8))
                .skill(new Long(20))
                .build();

        Assertions.assertEquals(expected, transformerService.startBattle(transformer1, transformer2));
    }

    @Test
    public void testNoRecordsFoundINGetAllTransformers() {
        assertThrows(NoRecordFoundException.class, () -> {
            transformerService.getTransformers();
        });
    }

    @Test
    public void testNoRecordFoundINExecuteBattle() {
        assertThrows(NoRecordFoundException.class, () -> {
            transformerService.executeBattle(new ArrayList(Arrays.asList(new Long(1))));
        });
    }
}
