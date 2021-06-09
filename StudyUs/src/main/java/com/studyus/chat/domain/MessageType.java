package com.studyus.chat.domain;

public enum MessageType {
	OPEN, 
	SEND, 
	CLOSE,
	/**
	 * 기존에 있던 사용자명을 새로 입장한 사용자에게 보낼 때 사용하는 타입입니다.
	 * EXISTING 타입으로 보낸 메세지는 채팅창에 출력되지 않으며, 참여중인 사람들 목록에 사용자명만 추가됩니다.
	 */
	EXISTING 
}
