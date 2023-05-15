package com.pofo.pofoapiboot3.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.pofo.pofoapiboot3.entity.CommunityPost;

@AutoConfigureTestDatabase(replace=Replace.NONE)
@MybatisTest
class CommunityPostRepositoryTest {
	
	@Autowired
	private CommunityPostRepository repository;

	@Test
	void test() {
		
		
		CommunityPost post = new CommunityPost();
		post.setMemberId(2L);
		post.setTitle("놀이터같은 사이트를 만들고 싶어요");
		post.setLocationType(1);
		post.setLocationInfo("스타벅스 서강대점");
		post.setPeriod("5월 26일 ~ 6월 25일");
		post.setTeamSize(6);
		post.setThumbnail(null);
		
		int result = repository.insert(2L, "놀이터같은 사이트를 만들고 싶어요", 1, "스타벅스 서강대점", "5월 26일 ~ 6월 25일", 6, null);
		
		System.out.println(result);
	}

}
