package com.pofo.pofoapiboot3.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pofo.pofoapiboot3.entity.RecruitmentPost;
import com.pofo.pofoapiboot3.service.RecruitmentPostService;

@RestController("memberRecruitmentPostController")
@RequestMapping("/member/recruitmentPost")
public class RecruitmentPostController {
    
    @Autowired
    private RecruitmentPostService service;

    // 팀 모집 게시글 등록
    @PostMapping("/register")
    public String register(Long memberId,
                            String title,
                            Integer locationType,
                            String locationInfo,
                            String period,
                            Integer teamSize,
                            String thumbnail){
        
        // service.register(post);
        service.register(memberId, title, locationType, locationInfo, period, teamSize, thumbnail);
                                
        return null;
    }
}
