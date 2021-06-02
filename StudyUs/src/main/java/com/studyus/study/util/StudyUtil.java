package com.studyus.study.util;

import java.util.ArrayList;
import java.util.Calendar;

import com.studyus.study.domain.Study;

public class StudyUtil {
	
	/**
	 * 오늘(서버 시간 기준)이 스터디 모임일인지 확인합니다.
	 * @param study
	 * @return
	 * 오늘이 모임일이면 true, 아니라면 false를 반환합니다.
	 */
	public static boolean isMeetingDay (Study study) {
		// 스터디 모임 요일을 리스트화
		ArrayList<Integer> meetingDayList = new ArrayList<Integer>();
		if (study.getSunday() == 1) {
			meetingDayList.add(1);
		}
		if (study.getMonday() == 1) {
			meetingDayList.add(2);
		}
		if (study.getTuesday() == 1) {
			meetingDayList.add(3);
		}
		if (study.getWednesday() == 1) {
			meetingDayList.add(4);
		}
		if (study.getThursday() == 1) {
			meetingDayList.add(5);
		}
		if (study.getFriday() == 1) {
			meetingDayList.add(6);
		}
		if (study.getSaturday() == 1) {
			meetingDayList.add(7);
		}
		
		// 오늘 요일을 int값으로 가져옴. 일 = 1, 월 = 2, 화 = 3 ... 토 = 7 
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		// 스터디 모임일 리스트에 오늘 요일값이 있는지 확인
		return meetingDayList.contains(dayOfWeek);
	}
}
