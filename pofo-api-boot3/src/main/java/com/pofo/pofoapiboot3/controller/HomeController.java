package com.pofo.pofoapiboot3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pofo.pofoapiboot3.entity.PortfolioView;
import com.pofo.pofoapiboot3.entity.WeeklyPopularPortfolioView;
import com.pofo.pofoapiboot3.service.PortfolioService;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PortfolioService portfolioService;

    // 4차
    // 디폴트 포트폴리오 리스트[프로그래밍언어(전체), 정렬방식(최신순), 협업여부(전체)]
    @GetMapping("index")
    public ResponseEntity<Map<String, Object>> index(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "sort", defaultValue = "latest") String sort,  // 정렬방법
        @RequestParam(name = "collaboration", required = false) Integer collaboration, // 협업여부
        @RequestParam(name = "language", required = false) Integer skillId // 프로그래밍언어
    ){
        // 포트폴리오 리스트
        List<PortfolioView> portfolioViewList = portfolioService.getViewList(page, sort, collaboration, skillId);

        // 이번주 인기 포트폴리오 리스트
        List<WeeklyPopularPortfolioView> weeklyPopularPortfolioViewList = portfolioService.getWeeklyPopularViewList();

        Map<String, Object> dto = new HashMap<>();
        dto.put("list", portfolioViewList);
        dto.put("weeklyPopularList", weeklyPopularPortfolioViewList);

        return new ResponseEntity<Map<String,Object>>(dto, HttpStatus.OK);
    }
    
}
