package com.pofo.pofoapiboot3.service;

import com.pofo.pofoapiboot3.entity.RecruitmentPost;

public interface RecruitmentPostService {

    void register(RecruitmentPost post);

    void register(Long memberId, String title, Integer locationType, String locationInfo, String period,
            Integer teamSize, String thumbnail);
    
}
