package com.pofo.pofoapiboot3.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pofo.pofoapiboot3.entity.CommunityPost;
import com.pofo.pofoapiboot3.service.CommunityPostService;

@RestController("memberCommunityPostController")
@RequestMapping("/member/community-post")
public class CommunityPostController {
    
    @Autowired
    private CommunityPostService service;

    // 팀 모집 게시글 등록
    // @PostMapping("/register")
    // public String register(Long memberId,
    //                         String title,
    //                         Integer locationType,
    //                         String locationInfo,
    //                         String period,
    //                         Integer teamSize,
    //                         String thumbnail){
        
    //     // service.register(post);
    //     System.out.println("hi");
    //     System.out.println(memberId + title + locationType + locationInfo + period + teamSize + thumbnail);
    //     service.register(memberId, title, locationType, locationInfo, period, teamSize, thumbnail);

    //     return "ok";
    // }

    @PostMapping("/register")
    public boolean register(@RequestBody CommunityPost post){ // 객체로 받아오기
        
        System.out.println("post: " + post);
        boolean result = service.register(post);

        return result;
    }
}
