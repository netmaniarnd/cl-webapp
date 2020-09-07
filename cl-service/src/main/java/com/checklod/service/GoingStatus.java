package com.checklod.service;

public enum GoingStatus {

	STAT_1(1),
	STAT_2(2),
	STAT_3(3),
	STAT_4(4),
	STAT_5(5);

	private int status;

	GoingStatus(int status) {
		this.status = status;
	}

	String getLabel() {
		switch(status) {
		case 1:
			return "배송시작";
		case 2:
			return "배송중";
		case 3:
			return "배송완료";
		case 4:
			return "서명완료";
		case 5:
			return "PDF완료";
		}
		return null;
	}

	static GoingStatus get(String status) {
		// TODO Auto-generated method stub
		switch(status) {
		case "1":
			return STAT_1;
		case "2":
			return STAT_2;
		case "3":
			return STAT_3;
		case "4":
			return STAT_4;
		case "5":
			return STAT_5;
		}
		return null;
	}
}
