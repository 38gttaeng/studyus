package com.studyus.chat.store.logic;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.studyus.chat.domain.ChatChannel;

@Component
public class ChatChannelStore {
	
	/*
	 * key: 스터디 url
	 * value: url에 해당하는 스터디의 ChatChannel
	 */
	private Map<String, ChatChannel> map;
	
	@PostConstruct
	private void init() {
		map = new LinkedHashMap<>();
	}
	
	public ChatChannel getChanelByUrl(String url) {
		ChatChannel channel = map.get(url);
		return channel;
	}
	
	/**
	 * 해당 url의 채널이 존재하면 참가하고, 없으면 생성한 후 참가
	 * @param session
	 * url에 참가할 session
	 * @param url
	 * @param nickname
	 * session 사용자의 닉네임
	 * 스터디 url
	 * @return
	 * 참가한 채널
	 */
	public ChatChannel joinOrCreateChannel (WebSocketSession session, String url, String nickname) {
		ChatChannel channel = null;
		channel = map.get(url);
		if (channel == null) {
			channel = ChatChannel.create(url);
			channel.addSession(session, nickname);
			map.put(url, channel);
			return channel;
		} else {
			channel.addSession(session, nickname);
			return channel;
		}
	}
	
	/**
	 * url로부터 session을 제거, 마지막 session일 경우 ChatChannel 제거
	 * @param session
	 * url로부터 제거할 session
	 * @param url
	 * 스터디 url
	 * @return 
	 * ChatChannel or null
	 * session이 채널의 마지막 session이었다면 null, 다른 session이 존재한다면 ChatChannel
	 */
	public ChatChannel leaveOrRemoveChannel (WebSocketSession session, String url) {
		try {
			ChatChannel channel = map.get(url);
			channel.removeSession(session);
			if (channel.getAllSessions() == null) {
				map.remove(url);
				return null;
			} else {
				return channel;
			}
		} catch (NullPointerException omg) {
			omg.printStackTrace();
		}
		return null;
	}

	/**
	 * 세션이 연결되어 있던 채널을 찾은 후 해당 채널에 연결된 사용자들에게 연결 종료를 알림
	 * @param session
	 */
	public ChatChannel findChannelWithSession (WebSocketSession session) {
		ChatChannel channel = null;
		for (ChatChannel c : map.values()) {
			if (c.getSessions().containsKey(session))
				return c;
		}
		
		return channel;
	}
	
}
