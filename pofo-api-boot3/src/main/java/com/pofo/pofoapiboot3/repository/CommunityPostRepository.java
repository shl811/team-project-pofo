package com.pofo.pofoapiboot3.repository;

import org.apache.ibatis.annotations.Mapper;

import com.pofo.pofoapiboot3.entity.CommunityPost;

@Mapper
public interface CommunityPostRepository {

	int insert(CommunityPost post);

    int insert(Long memberId, String title, Integer locationType, String locationInfo, String period, Integer teamSize,
            String thumbnail);
}
