package com.pofo.pofoapiboot3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pofo.pofoapiboot3.entity.PortfolioView;
import com.pofo.pofoapiboot3.entity.WeeklyPopularPortfolioView;
import com.pofo.pofoapiboot3.repository.PortfolioRepository;

import ch.qos.logback.core.joran.conditional.ElseAction;

@Service
public class DefaultPortfolioService implements PortfolioService {

    @Autowired
    private PortfolioRepository repository;

    // 디폴트 포트폴리오 리스트
    @Override
    public List<PortfolioView> getViewList(Integer page, String sort, Integer collaboration, Integer skillId) {
    	
        int size = 15; // 포트폴리오를 한 번에 15개씩 가져옴
        return repository.findViewAll(page, size, sort, collaboration, skillId);
    }

    // 검색된 포트폴리오 리스트
    @Override
    public List<PortfolioView> getViewListByQuery(Integer page, String sort, Integer collaboration, String query) {
        int size = 15; // 포트폴리오를 한 번에 15개씩 가져옴

        // query와 관련 스택이 있는지 확인함
        int[] skillIds = repository.findSkillIdsByQuery(query);

        return repository.findViewAllByQuery(page, size, sort, collaboration, query, skillIds);
    }
    
    // 이번주 인기 포트폴리오 리스트
    @Override
    public List<WeeklyPopularPortfolioView> getWeeklyPopularViewList() {

        return repository.findWeeklyPopularViewAll();
    }


    
}
