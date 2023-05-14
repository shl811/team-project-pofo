package com.pofo.pofoapiboot3.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentPost {
    private Long id;
    private Long memberId; 
    private String title;
    private Integer locationType;
    private String locationInfo; 
    private String period; 
    private Integer teamSize;
    private String thumbnail;
    private Date regDate;
    private Date deleteDate;
    private Long hit;
}
