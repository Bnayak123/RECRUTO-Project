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
@Table(name = "SPARK_HR_UNIT")
public class HrUnitData {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "HR_TRAINEE_OFFCIAL_ID")
    private String traineeOfficialId;
    @Column(name = "HR_TRAINEE_ONBOARDING_DATE")
    private LocalDate traineeOnBoardingDate;
    @Column(name = "HR_NAME")
    private String hrName;
    @Column(name = "HR_TRAINEE_DOB")
    private LocalDate traineeDOB;
    @Column(name = "HR_TRAINEE_FATHER_NAME")
    private String traineeFatherName;
    @Column(name = "HR_TRAINEE_LOCATION")
    private String traineeLocation;
    @Column(name = "HR_TRAINEE_OFFCIAL_MAIL_ID")
    private String traineeOfficialMailId;
    @Column(name = "HR_TRAINEE_PERMANENT_ADDRESS")
    private String traineePermanentAddress;
    @Column(name = "HR_TRAINEE_JOINING")
    private String traineeJoiningFormalities;
    @Column(name = "HR_TRAINEE_LOI")
    private String traineeLOIFormalities;
    @Column(name = "HR_TRAINEE_NDA")
    private String traineeNDAFormalities;
    @Column(name = "HR_TRAINEE_MARKS_CARD")
    private String traineeMarksCardDetails;
    @Column(name = "HR_TRAINEE_BIOMETRIC_STATUS")
    private String traineeBioMetricStatus;
    @Column(name = "HR_TRAINEE_ICARDS")
    private String traineeICards;
    @Column(name = "HR_TRAINEE_REMARKS")
    private String traineeRemarks;
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
