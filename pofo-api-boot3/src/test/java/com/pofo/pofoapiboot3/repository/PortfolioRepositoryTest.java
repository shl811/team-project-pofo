package com.pofo.pofoapiboot3.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.pofo.pofoapiboot3.entity.PortfolioView;

@AutoConfigureTestDatabase(replace=Replace.NONE)
@MybatisTest
class PortfolioRepositoryTest {
	
	@Autowired
	private PortfolioRepository repository;

	@Test
	void test() {
		List<PortfolioView> list = repository.findViewAll(0, 15, "likes", 1, 1);
		System.out.println(list);
		/*
		 * [
		 * PortfolioView(nickname=shl811, memberImage=null, likeCount=0, skillIds=1,2,9), 
		 * PortfolioView(nickname=D.BRONZE, memberImage=d.bronze.jpg, likeCount=1, skillIds=1,2,7)
		 * ]
		 */
	}

}
