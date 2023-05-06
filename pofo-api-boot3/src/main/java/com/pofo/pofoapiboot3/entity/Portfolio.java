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
public class Portfolio {
    private Long id;
    private Long memberId;
    private String title;
    private Long hit;
    private Date regDate;
    private Date deleteDate;
    private Date awardDate;
    private String thumbnail;
    private Long likes;
    private Integer collaboration;
}
