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
@Table(name = "SPARK_BUSINESS_UNIT")
public class BusinessUnitData {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "BU_EDF_INITIATED")
    private String edfInitiated;
    @Column(name = "BU_DEPLOYEMENT_DATE")
    private LocalDate deployementDate;
    @Column(name = "BU_CLIENT_NAME")
    private String clientName;
    @Column(name = "BU_BUSINESS_UNIT")
    private String businessUnitName;
    @Column(name = "BU_REPORTING_MANAGER")
    private String reportingManagerName;
    @Column(name = "BU_COMMENTS")
    private String comments;
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
