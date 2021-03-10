package com.transformers.application.service;

import com.transformers.application.config.Constants;
import com.transformers.application.dto.*;
import com.transformers.application.entity.Transformer;
import com.transformers.application.exception.NoRecordFoundException;
import com.transformers.application.repository.TransformerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class TransformerServiceImpl implements TransformerService {

    private static final Logger logger = LoggerFactory.getLogger(TransformerServiceImpl.class);

    TransformerRepository transformerRepository;

    @Autowired
    public TransformerServiceImpl(TransformerRepository transformerRepository) {
        this.transformerRepository = transformerRepository;
    }

    /***
     * create transformer
     * @param transformerDTOReq
     * @return
     * @throws Exception
     */
    @Override
    public String addTransformer(TransformerDTOReq transformerDTOReq) throws Exception {
        try {
            transformerRepository.save(Transformer.builder()
                    .transformerName(transformerDTOReq.getTransformerName())
                    .transformerTeam(transformerDTOReq.getTransformerTeam())
                    .strength(transformerDTOReq.getStrength())
                    .intelligence(transformerDTOReq.getIntelligence())
                    .speed(transformerDTOReq.getSpeed())
                    .endurance(transformerDTOReq.getEndurance())
                    .rank(transformerDTOReq.getRank())
                    .courage(transformerDTOReq.getCourage())
                    .firepower(transformerDTOReq.getFirepower())
                    .skill(getSkill(transformerDTOReq))
                    .build());

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }
        return Constants.TRANSFORMER_CREATED;
    }

    /***
     * update transformer
     * @param transformerUpdReq
     * @return
     * @throws Exception
     */
    @Override
    public TransformerDTOResp updateTranformer(TransformerUpdReq transformerUpdReq) throws Exception {

        Transformer updatedTransformer = null;

        try {
            Transformer transformer = transformerRepository.findByTransformerID(transformerUpdReq.getTransformerID());

            if (Objects.isNull(transformer)) throw new NoRecordFoundException(Constants.NO_RECORDS_FOUND);

            Transformer updateTransformer = Transformer.builder()
                    .transformerName(transformerUpdReq.getTransformerName())
                    .transformerTeam(transformerUpdReq.getTransformerTeam())
                    .transformerID(transformer.getTransformerID())
                    .strength(transformerUpdReq.getStrength())
                    .intelligence(transformerUpdReq.getIntelligence())
                    .speed(transformerUpdReq.getSpeed())
                    .endurance(transformerUpdReq.getEndurance())
                    .rank(transformerUpdReq.getRank())
                    .courage(transformerUpdReq.getCourage())
                    .firepower(transformerUpdReq.getFirepower())
                    .skill(getSkill(transformerUpdReq))
                    .build();

            updatedTransformer = transformerRepository.save(updateTransformer);

        } catch (NoRecordFoundException e) {
            throw new NoRecordFoundException(Constants.NO_RECORD_FOUND_MESSAGE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }

        return TransformerDTOResp.builder()
                .transformerID(updatedTransformer.getTransformerID())
                .transformerName(updatedTransformer.getTransformerName())
                .transformerTeam(updatedTransformer.getTransformerTeam())
                .strength(updatedTransformer.getStrength())
                .intelligence(updatedTransformer.getIntelligence())
                .speed(updatedTransformer.getSpeed())
                .endurance(updatedTransformer.getEndurance())
                .rank(updatedTransformer.getRank())
                .courage(updatedTransformer.getCourage())
                .firepower(updatedTransformer.getFirepower())
                .skill(updatedTransformer.getSkill())
                .build();
    }

    /***
     * get all transformers
     * @return
     * @throws Exception
     */
    @Override
    public List<TransformerDTOResp> getTransformers() throws Exception {

        List<TransformerDTOResp> transformerDTOResps = new ArrayList<>();

        try {
            List<Transformer> transformers = (List<Transformer>) transformerRepository.findAll();

            if ((Objects.isNull(transformers)) || transformers.isEmpty())
                throw new NoRecordFoundException(Constants.NO_RECORDS_FOUND);

            for (Transformer transformer : transformers) {
                TransformerDTOResp transformerDTOResp = TransformerDTOResp.builder()
                        .transformerID(transformer.getTransformerID())
                        .transformerName(transformer.getTransformerName())
                        .transformerTeam(transformer.getTransformerTeam())
                        .strength(transformer.getStrength())
                        .intelligence(transformer.getIntelligence())
                        .speed(transformer.getSpeed())
                        .endurance(transformer.getEndurance())
                        .rank(transformer.getRank())
                        .courage(transformer.getCourage())
                        .firepower(transformer.getFirepower())
                        .skill(transformer.getSkill())
                        .build();
                transformerDTOResps.add(transformerDTOResp);
            }

        } catch (NoRecordFoundException e) {
            logger.error(e.getMessage());
            throw new NoRecordFoundException(Constants.NO_RECORDS_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }

        return transformerDTOResps;
    }

    /***
     * delete transformer
     * @param transformerId
     * @return
     */
    @Override
    public String deleteTransformer(Long transformerId) {

        try {
            Transformer transformer = transformerRepository.findByTransformerID(transformerId);
            transformerRepository.delete(transformer);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "Transformer Destroyed.";
    }

    /***
     *
     * @param transformerIds
     * @return
     */
    @Override
    public TransBattleDTOResp executeBattle(List<Long> transformerIds) throws Exception {

        Integer noOfBattles = 0;
        Integer aTotalWin = 0;
        Integer dTotalWin = 0;
        MissingTransformerDTO missingTransformerDTO = null;
        List<String> aNames = new ArrayList<>();
        List<String> dNames = new ArrayList<>();
        TransBattleDTOResp transBattleDTOResp = null;
        //The transformers are split into two teams based on if they are Autobots or Decepticons
        List<Transformer> autobots = new ArrayList<>();
        List<Transformer> decepticons = new ArrayList<>();
        List<SingleBattleDTOResp> singleBattleDTOResps = new ArrayList<>();

        try {
            for (Long transformerID : transformerIds) {
                Transformer transformer = transformerRepository.findByTransformerID(transformerID);

                if (Objects.isNull(transformer)){
                    missingTransformerDTO = MissingTransformerDTO.builder().transformerId(transformerID.toString()).build();
                    throw new NoRecordFoundException(missingTransformerDTO.getTransformerId());
                }

                if (transformer.getTransformerTeam().equals("A")) {
                    autobots.add(transformer);
                } else
                    decepticons.add(transformer);
            }

            //The teams should be sorted by rank
            autobots.sort(Comparator.comparing(Transformer::getRank).reversed());
            decepticons.sort(Comparator.comparing(Transformer::getRank).reversed());


            for (int x = 0; x < autobots.size(); x++) {
                for (int y = x; y < decepticons.size(); y++) {
                    if ((Objects.nonNull(autobots.get(x)) && (Objects.nonNull(decepticons.get(y))))) {
                        logger.info("autobots: {} vs decepticons: {}", autobots.get(x).getTransformerName(),
                                decepticons.get(y).getTransformerName());
                        SingleBattleDTOResp singleBattleDTOResp = startBattle(autobots.get(x), decepticons.get(y));
                        //GAME OVER
                        if (singleBattleDTOResp.getAutobots().equals(Constants.WINNER) &&
                                (singleBattleDTOResp.getDecepticons().equals(Constants.WINNER))) {
                            return TransBattleDTOResp.builder()
                                    .result(Constants.GAME_OVER)
                                    .build();
                        }

                        singleBattleDTOResps.add(singleBattleDTOResp);
                        noOfBattles++;
                        break;
                    }
                }
            }

            for (SingleBattleDTOResp singleBattleDTOResp : singleBattleDTOResps) {
                aNames.add(singleBattleDTOResp.getAutbotsName());
                dNames.add(singleBattleDTOResp.getDecepticonsName());
                if (singleBattleDTOResp.getAutobots() == 1) {
                    aTotalWin++;
                } else {
                    dTotalWin++;
                }
            }

            //The team who eliminated the largest number of the opposing team is the winner
            String winningTeam = (aTotalWin > dTotalWin) ? Constants.AUTOBOTS : Constants.DECEPTICONS;
            String lossingTeam = (aTotalWin < dTotalWin) ? Constants.AUTOBOTS : Constants.DECEPTICONS;
            String result = (aTotalWin == dTotalWin) ? " Transformers Destroyed." : noOfBattles + " battle(s) Winning Team ("
                    .concat(winningTeam).concat("): ")
                    .concat((winningTeam.equals(Constants.AUTOBOTS)) ? aNames.toString() : dNames.toString())
                    .concat(" Survivors from the losing team (".concat(lossingTeam).concat("): ")
                            .concat((lossingTeam.equals(Constants.AUTOBOTS)) ? aNames.toString() : dNames.toString()));
            transBattleDTOResp = TransBattleDTOResp.builder()
                    .result(result.replaceAll("\\[", "").replaceAll("\\]", ""))
                    .build();

        } catch (NoRecordFoundException e) {
            logger.error(e.getMessage());
            throw new NoRecordFoundException(missingTransformerDTO.getTransformerId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }

        return transBattleDTOResp;
    }

    /***
     * start battle
     * @param autobots
     * @param decepticons
     * @return
     */
    @Override
    public SingleBattleDTOResp startBattle(Transformer autobots, Transformer decepticons) {

        String output = "";
        SingleBattleDTOResp singleBattleDTOResp = null;

        //In the event either of the above face each other (or a duplicate of each other), the game immediately ends with all competitors destroyed
        if (((autobots.getTransformerName().equals(Constants.OP)) && (decepticons.getTransformerName().equals(Constants.PREDAKING))) ||
                ((autobots.getTransformerName().equals(Constants.OP)) && (decepticons.getTransformerName().equals(Constants.OP))) ||
                ((autobots.getTransformerName().equals(Constants.PREDAKING)) && (decepticons.getTransformerName().equals(Constants.PREDAKING)))) {
            logger.info("GAME OVER");
            return SingleBattleDTOResp.builder()
                    .autobots(Constants.WINNER)
                    .decepticons(Constants.WINNER)
                    .autbotsName(autobots.getTransformerName())
                    .decepticonsName(decepticons.getTransformerName())
                    .build();
        }

        //Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria
        if (autobots.getTransformerName().equals(Constants.OP)) {
            logger.info("autobots win.");
            return SingleBattleDTOResp.builder()
                    .autobots(Constants.WINNER)
                    .decepticons(Constants.LOOSER)
                    .autbotsName(autobots.getTransformerName())
                    .decepticonsName(decepticons.getTransformerName())
                    .build();
        }

        //Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria
        if (decepticons.getTransformerName().equals(Constants.PREDAKING)) {
            logger.info("decepticon wins");
            return SingleBattleDTOResp.builder()
                    .autobots(Constants.LOOSER)
                    .decepticons(Constants.WINNER)
                    .autbotsName(autobots.getTransformerName())
                    .decepticonsName(decepticons.getTransformerName())
                    .build();
        }

        //If any fighter is down 4 or more points of courage and 3 or more points of strength compared to their opponent,
        // the opponent automatically wins the face-off regardless of overall rating (opponent has ran away)
        if (((autobots.getCourage() < 4) && (decepticons.getCourage() > 4)) && (decepticons.getStrength() > 2)) {
                logger.info("decepticon wins");
                return SingleBattleDTOResp.builder()
                        .autobots(Constants.LOOSER)
                        .decepticons(Constants.WINNER)
                        .autbotsName(autobots.getTransformerName())
                        .decepticonsName(decepticons.getTransformerName())
                        .build();
        } else if (((decepticons.getCourage() < 4) && (autobots.getCourage() > 4)) && (autobots.getStrength() > 2)) {
            logger.info("autobots win.");
            return SingleBattleDTOResp.builder()
                    .autobots(Constants.WINNER)
                    .decepticons(Constants.LOOSER)
                    .autbotsName(autobots.getTransformerName())
                    .decepticonsName(decepticons.getTransformerName())
                    .build();
        }

        //Otherwise, if one of the fighters is 3 or more points of skill above their opponent, they win the fight regardless of overall rating
        if (autobots.getSkill() > decepticons.getSkill()) {
            logger.info("autobots win.");
            return SingleBattleDTOResp.builder()
                    .autobots(Constants.WINNER)
                    .decepticons(Constants.LOOSER)
                    .autbotsName(autobots.getTransformerName())
                    .decepticonsName(decepticons.getTransformerName())
                    .build();
        } else if (autobots.getSkill() < decepticons.getSkill()) {
            logger.info("decepticon win.");
            return SingleBattleDTOResp.builder()
                    .autobots(Constants.LOOSER)
                    .decepticons(Constants.WINNER)
                    .autbotsName(autobots.getTransformerName())
                    .decepticonsName(decepticons.getTransformerName())
                    .build();
        } else {
            //In the event of a tie, both Transformers are considered destroyed
            logger.info("Transformers destroyed");
            singleBattleDTOResp = SingleBattleDTOResp.builder()
                    .autobots(Constants.LOOSER)
                    .decepticons(Constants.LOOSER)
                    .autbotsName(autobots.getTransformerName())
                    .decepticonsName(decepticons.getTransformerName())
                    .build();
        }

        return singleBattleDTOResp;
    }

    /***
     * calculate skill for updated transformer
     * @param skillCalcDTO
     * @return
     */
    public Long getSkill(TransformerUpdReq skillCalcDTO) {
        return skillCalcDTO.getStrength() +
                skillCalcDTO.getIntelligence() +
                skillCalcDTO.getSpeed() +
                skillCalcDTO.getEndurance() +
                skillCalcDTO.getFirepower();
    }

    /***
     *  calculate skill for created transformer
     * @param skillCalcDTO
     * @return
     */
    public Long getSkill(TransformerDTOReq skillCalcDTO) {
        return skillCalcDTO.getStrength() +
                skillCalcDTO.getIntelligence() +
                skillCalcDTO.getSpeed() +
                skillCalcDTO.getEndurance() +
                skillCalcDTO.getFirepower();
    }
}
