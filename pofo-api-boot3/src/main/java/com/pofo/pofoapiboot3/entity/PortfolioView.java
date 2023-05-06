package com.pofo.pofoapiboot3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PortfolioView extends Portfolio {
    private String nickname;
    private String memberImage;
    private Long likeCount;
}
