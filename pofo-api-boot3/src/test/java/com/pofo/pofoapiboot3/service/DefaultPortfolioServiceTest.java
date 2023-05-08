package com.pofo.pofoapiboot3.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pofo.pofoapiboot3.entity.PortfolioView;

@SpringBootTest
@AutoConfigureMybatis
class DefaultPortfolioServiceTest {
	
	@Autowired
	private PortfolioService service;

	@Test
	void test() {
		List<PortfolioView> list = service.getViewList(0, "likes", 1, 1);
		System.out.println(list);
	}

}
