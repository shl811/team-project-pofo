package com.pofo.pofoapiboot3.entity;

import java.beans.Transient;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityPost {
    private Long id;
    // @json // json으로  전달된 것  이름 매핑 가능함
    // 무시할 수 있는 부분 찾아볼 수 있음
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
