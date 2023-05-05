package com.pofo.pofoapiboot3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioView extends Portfolio {
    private String nickname;
    private String memberImage;
    private Long likeCount;
}
