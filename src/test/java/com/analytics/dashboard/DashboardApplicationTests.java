package com.analytics.dashboard;

import com.analytics.dashboard.util.SparkUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class DashboardApplicationTests {

	@MockBean
	private SparkUtils sparkUtils;

	@Test
	void contextLoads() {
	}

}
