package com.example.cowork.vo;

import com.example.cowork.model.GatheringModel;
import lombok.Data;

import java.sql.Date;

@Data
public class GatheringVO {

    private String gathering_code;
    private String gathering_exterior;  // 외관 이미지 주소
    private String gathering_name;
    private String gathering_owner;
    private Integer gathering_floor;
    private String gathering_explanation;
    private Integer gathering_coord_x;
    private Integer gathering_coord_y;
    private Date gathering_created_at;
    private Date gathering_updated_at;
    private Date gathering_deleted_at;
}
