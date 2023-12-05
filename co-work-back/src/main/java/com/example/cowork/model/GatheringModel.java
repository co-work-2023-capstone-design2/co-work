package com.example.cowork.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.sql.Date;

@Entity
@Table(name="gathering")
@Data
public class GatheringModel {
    @Id
    private String gathering_code;
    // @NotBlank
    private String gathering_exterior;  // 외관 이미지 주소
    // @NotBlank
    private String gathering_name;
    // @NotBlank
    private String gathering_owner;
    // @NotBlank
    private Integer gathering_floor;
    private String gathering_explanation;
    private Integer gathering_coord_x;
    private Integer gathering_coord_y;
    // @NotBlank
    private Date gathering_created_at;
    private Date gathering_updated_at;
    private Date gathering_deleted_at;

}
