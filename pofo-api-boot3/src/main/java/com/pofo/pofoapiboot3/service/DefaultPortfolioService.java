package com.pofo.pofoapiboot3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pofo.pofoapiboot3.entity.PortfolioView;
import com.pofo.pofoapiboot3.repository.PortfolioRepository;

@Service
public class DefaultPortfolioService implements PortfolioService {

    @Autowired
    private PortfolioRepository repository;

    // 인덱스 페이지 포트폴리오 리스트
    @Override
    public List<PortfolioView> getViewList(int page) {
        // 포트폴리오를 한 번에 15개씩 가져옴
        int size = 15;
        List<PortfolioView> portfolioViewList = repository.findViewAll(page, size);

        return portfolioViewList;
    }
    
}
