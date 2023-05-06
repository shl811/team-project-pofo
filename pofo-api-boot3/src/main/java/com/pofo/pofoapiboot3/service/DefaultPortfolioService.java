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

    @Override
    public List<PortfolioView> getViewList(Integer page, String sort, Integer collaboration) {
        // 포트폴리오를 한 번에 15개씩 가져옴
        int size = 15;
        return repository.findViewAll(page, size, sort, collaboration);
    }
    
}
