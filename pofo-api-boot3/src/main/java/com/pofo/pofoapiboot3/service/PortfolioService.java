package com.pofo.pofoapiboot3.service;

import java.util.List;

import com.pofo.pofoapiboot3.entity.PortfolioView;

public interface PortfolioService {

    //List<PortfolioView> getViewList(Integer page, String sort, Integer collaboration);

    List<PortfolioView> getViewList(Integer page, String sort, Integer collaboration, Integer skillId);
    
}
