package com.studyus.meeting.utils;

import java.util.Calendar;

import com.studyus.study.domain.Study;

public class MeetingUtils {
	
	/**
	 * 오늘이 해당 스터디의 모임이 있는 요일인지 확인합니다.
	 * @param study
	 * @return
	 * 오늘이 모임 요일이 맞으면 true, 아니면 false.
	 */
	public static boolean isMeetingTime (Study study) {
		boolean result = false;
		
		// 오늘 요일 가져오기
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK);
		
		switch (dayOfWeek) {
		case 1:
			if (study.getSunday() == 1)
				result = true;
			break;
		case 2:
			if (study.getMonday() == 1)
				result = true;
			break;
		case 3:
			if (study.getTuesday() == 1)
				result = true;
			break;
		case 4:
			if (study.getWednesday() == 1)
				result = true;
			break;
		case 5:
			if (study.getThursday() == 1)
				result = true;
			break;
		case 6:
			if (study.getFriday() == 1)
				result = true;
			break;
		case 7:
			if (study.getSaturday() == 1)
				result = true;
			break;
		}
		
		return result;
	}
}
