package com.pofo.pofoapiboot3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pofo.pofoapiboot3.entity.RecruitmentPost;
import com.pofo.pofoapiboot3.repository.RecruitmentPostRepository;

@Service
public class DefaultRecruitmentPostService implements RecruitmentPostService {

    @Autowired
    private RecruitmentPostRepository repository;

    @Override
    public void register(RecruitmentPost post) {
        repository.insert(post);
    }

    @Override
    public void register(Long memberId, String title, Integer locationType, String locationInfo, String period,
            Integer teamSize, String thumbnail) {
        repository.insert(memberId, title, locationType, locationInfo, period, teamSize, thumbnail);
    }
    
}
