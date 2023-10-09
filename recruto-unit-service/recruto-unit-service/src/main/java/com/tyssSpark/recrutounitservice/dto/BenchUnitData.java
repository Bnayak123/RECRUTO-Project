package com.tyssSpark.recrutounitservice.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "SPARK_BENCH_UNIT")
public class BenchUnitData {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer benchId;
    @Column(name ="BEN_TRAINEE_MONTH")
    private LocalDate benMonth;
    @Column(name ="BEN_TRAINEE_SOURCE" )
    private String benSource;
    @Column (name = "BEN_TRAINEE_GENDER")
    private String benGender;
    @Column(name = "BEN_TRAINEE_BENCH")
    private String benBench;
    @Column(name = "BEN_TRAINEE_BATCH")
    private String benBatch;
    @Column(name = "BEN_TRAINEE_TRAINING_DURATION")
    private Integer benTraineeDuration;
    @Column(name="BEN_TRAINEE_SKILL_SET")
    private String benSkillSet;
    @Column(name="BEN_TRAINEE_CTC")
    private String benCtc;
    @Column(name = "BEN_TRAINEE_REVISED_CTC")
    private String benRevicedCtc;
    @Column(name = "BEN_TRAINEE_REMARKS")
    private String benRemarks;
    @OneToOne
    @JoinColumn(name = "SPARK_TRAINEE_DATA_ID")
    private TraineeData traineeData;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
}
