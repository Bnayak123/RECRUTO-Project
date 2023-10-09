package com.tyssSpark.recrutounitservice.dto;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "SPARK_TRAINEE_DATA")
public class TraineeData {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "TRAINEE_NAME")
    private String traineeName;
    @Column(name = "TRAINEE_EMAIL_ID")
    private String traineeEmailId;
    @Column(name ="TRAINEE_MOBILE_NUM")
    private Long traineeMobileNum;
    @Column(name ="TRAINEE_STATUS")
    private String traineeStatus;
    @Column(name = "TRAINEE_YOEXP")
    private Double traineeYearOfExp;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
}
