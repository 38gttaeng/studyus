package com.studyus.meeting.store;

import com.studyus.meeting.domain.Meeting;

public interface MeetingStore {
	/**
	 * 데이터베이스에 Meeting을 insert한 후 meetingNo를 반환합니다.
	 * @param meeting
	 * @return meetingNo
	 * @throws Exception
	 */
	public int insertOne(Meeting meeting) throws Exception;
	public Meeting selectCurrentOneByStudyNo(int studyNo) throws Exception;
}
