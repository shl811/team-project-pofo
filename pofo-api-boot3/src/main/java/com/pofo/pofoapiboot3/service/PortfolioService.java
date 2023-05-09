package com.pofo.pofoapiboot3.service;

import java.util.List;

import com.pofo.pofoapiboot3.entity.PortfolioView;
import com.pofo.pofoapiboot3.entity.WeeklyPopularPortfolioView;

public interface PortfolioService {

    // 포트폴리오 리스트
    List<PortfolioView> getViewList(Integer page, String sort, Integer collaboration, Integer skillId);

    // 이번주 인기 포트폴리오 리스트
    List<WeeklyPopularPortfolioView> getWeeklyPopularViewList();
    
}
