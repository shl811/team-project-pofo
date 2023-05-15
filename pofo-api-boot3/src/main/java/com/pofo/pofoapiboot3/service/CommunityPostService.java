package com.pofo.pofoapiboot3.service;

import com.pofo.pofoapiboot3.entity.CommunityPost;

public interface CommunityPostService {

    boolean register(CommunityPost post);

    void register(Long memberId, String title, Integer locationType, String locationInfo, String period,
            Integer teamSize, String thumbnail);
    
}
