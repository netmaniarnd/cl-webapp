package com.checklod.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoingStatusTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		GoingStatus goingStatus = GoingStatus.STAT_2;
		System.out.println(goingStatus.toString());
		System.out.println(goingStatus.getLabel());
		assertEquals("배송중", goingStatus.getLabel());
		GoingStatus goingStatus2 = GoingStatus.get("2");
		System.out.println(goingStatus2.toString());
		System.out.println(goingStatus2.getLabel());
		assertEquals("배송중", goingStatus2.getLabel());
	}

}
