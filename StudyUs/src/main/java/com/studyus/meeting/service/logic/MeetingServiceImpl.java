package com.studyus.meeting.service.logic;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyus.meeting.domain.Meeting;
import com.studyus.meeting.service.MeetingService;
import com.studyus.meeting.store.MeetingStore;

@Service
public class MeetingServiceImpl implements MeetingService {
	
	@Autowired
	MeetingStore store;

	@Override
	public int insertOne(Meeting meeting) throws Exception {
		return store.insertOne(meeting);
	}

	@Override
	public Meeting printCurrentOneByStudyNo(int studyNo) throws Exception {
		return store.selectCurrentOneByStudyNo(studyNo);
	}

	@Override
	public ArrayList<Meeting> printAttMember(Meeting meeting) {
		return store.printAttMember(meeting);
	}

}
