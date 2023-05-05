package com.pofo.pofoapiboot3.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pofo.pofoapiboot3.entity.PortfolioView;

@Mapper
public interface PortfolioRepository {

    // 인덱스 페이지 포트폴리오 리스트
    List<PortfolioView> findViewAll(
                                    Integer offset, 
                                    Integer size);
}
