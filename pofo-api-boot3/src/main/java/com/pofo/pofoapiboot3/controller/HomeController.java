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
import com.pofo.pofoapiboot3.service.PortfolioService;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PortfolioService portfolioService;

    // 2차
    // 디폴트 포트폴리오 리스트[프로그래밍언어(전체), 정렬방식(최신순), 협업여부(전체)]
    // 정렬방식(최신순), 협업여부(전체)만 성공한 상태
    @GetMapping("index")
    public ResponseEntity<Map<String, Object>> index(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "sort", defaultValue = "최신순") String sort,
        @RequestParam(name = "collaboration", required = false) Integer collaboration
    ){
        // 포트폴리오 리스트
        List<PortfolioView> portfolioViewList = portfolioService.getViewList(page, sort, collaboration);

        Map<String, Object> dto = new HashMap<>();
        dto.put("result", portfolioViewList);

        return new ResponseEntity<Map<String,Object>>(dto, HttpStatus.OK);
    }
    
}
