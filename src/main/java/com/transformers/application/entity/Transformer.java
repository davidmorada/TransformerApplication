package com.transformers.application.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "TRANSFORMER")
public class Transformer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSFORMER_ID")
    private Long transformerID;
    @Column(name = "TRANSFORMER_NAME")
    private String transformerName;
    @Column(name = "TRANSFORMER_TEAM")
    private String transformerTeam;
    @Column(name = "STRENGTH")
    private long strength;
    @Column(name = "INTELLIGENCE")
    private Long intelligence;
    @Column(name = "SPEED")
    private Long speed;
    @Column(name = "ENDURANCE")
    private Long endurance;
    @Column(name = "RANK")
    private Long rank;
    @Column(name = "COURAGE")
    private Long courage;
    @Column(name = "FIREPOWER")
    private Long firepower;
    @Column(name = "SKILL")
    private Long skill;


}
