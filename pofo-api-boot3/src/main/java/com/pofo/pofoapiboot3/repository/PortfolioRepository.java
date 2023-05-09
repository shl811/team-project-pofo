package com.pofo.pofoapiboot3.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pofo.pofoapiboot3.entity.PortfolioView;
import com.pofo.pofoapiboot3.entity.WeeklyPopularPortfolioView;

@Mapper
public interface PortfolioRepository {

    // 포트폴리오 리스트
    List<PortfolioView> findViewAll(Integer offset, 
                                    int size, 
                                    String sort, 
                                    Integer collaboration, 
                                    Integer skillId);

    // 이번주 인기 포트폴리오 리스트
    List<WeeklyPopularPortfolioView> findWeeklyPopularViewAll();
}
