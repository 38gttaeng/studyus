package com.studyus.member.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

//import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.studyus.enrollment.domain.Enrollment;
//import com.studyus.member.domain.MailUtils;
import com.studyus.member.domain.Member;
//import com.studyus.member.domain.TempKey;
import com.studyus.member.store.MemberStore;
import com.studyus.review.domain.Review;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	public MemberStore store;

//	@Autowired
//	private JavaMailSender mailSender;

	@Override
	public Member loginMember(Member member) {
		Member mOne = store.selectOneMem(member);
		return mOne;
	}

	@Override
	public Member selectOneById(String mbId) {
		Member mOne = store.selectOneById(mbId);
		return mOne;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class})
	public int registerMember(Member member) {
		int result = store.insertMember(member);

//		String authKey = new TempKey().getKey(8, false);
//		member.setAuthKey(authKey);
//		store.updateAuthKey(member);
//		
//		if(result > 0) {
//			try {
//				// mail 작성 관련기능
//				MailUtils sendMail = new MailUtils(mailSender);
//				sendMail.setSubject("[StudyUs] 회원가입 이메일 인증");
//				sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
//						.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
//						.append("<a href='http://localhost:9999/member/joinConfirm?mbId=")
//						.append(member.getMbId())
//						.append("&mbEmail=")
//						.append(member.getMbEmail())
//						.append("&authKey=")
//						.append(member.getAuthKey())
//						.append("' target='_blenk'>이메일 인증 확인</a>").toString());
//				sendMail.setFrom("38gttaeng@gmail.com", "StudyUs");
//				sendMail.setTo(member.getMbEmail());
//				sendMail.send();
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return result;
	}

	@Override
	public void updateAuthKey(Member member) {
		store.updateAuthKey(member);
	}
	
	@Override
	public void updateKeyByEmail(Member member) {
		store.updateKeyByEmail(member);
	}
	
	@Override
	public Member checkAuthKey(String mbId) {
		Member checkKey = store.checkAuthKey(mbId);
		return checkKey;
	}
	
	@Override
	public Member checkKeyByEmail(String mbEmail) {
		Member checkKey = store.checkKeyByEmail(mbEmail);
		return checkKey;
	}
	
	@Override
	public int updateMbStatus(Member member) {
		int result = store.updateMbStatus(member);
		return result;
	}

	@Override
	public int registerNaverMem(Member member) {
		int result = store.insertNaverMem(member);
		return result;
	}

	@Override
	public int modifyMember(Member member) {
		int result = store.updateMember(member);
		return result;
	}

	@Override
	public int removeMember(String mbId) {
		int result = store.deleteMember(mbId);
		return result;
	}

	@Override
	public int checkIdDup(String mbId) {
		return store.checkIdDup(mbId);
	}

	@Override
	public int checkNickDup(String mbNickname) {
		return store.checkNickDup(mbNickname);
	}
	
	@Override
	public int checkEmailDup(String mbEmail) {
		return store.checkEmailDup(mbEmail);
	}

	@Override
	public Member findMemId(Member member) {
//		String authKey = new TempKey().getKey(8, false);
//		member.setAuthKey(authKey);
//		store.updateKeyByEmail(member);

		Member mOne = store.findMemId(member);
		
//		if(mOne != null) {
//			try {
//				// mail 작성 관련기능
//				MailUtils sendMail = new MailUtils(mailSender);
//				sendMail.setSubject("[StudyUs] 아이디 찾기 인증번호");
//				sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
//						.append("<p>아래의 인증번호를 확인창에 입력해주세요.</p>")
//						.append("<h3>")
//						.append(member.getAuthKey())
//						.append("</h3>").toString());
//				sendMail.setFrom("38gttaeng@gmail.com", "StudyUs");
//				sendMail.setTo(member.getMbEmail());
//				sendMail.send();
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return mOne;
	}

	@Override
	public Member findMemPw(Member member) {
//		String authKey = new TempKey().getKey(8, false);
//		member.setAuthKey(authKey);
//		store.updateAuthKey(member);
		
		Member mOne = store.findMemPw(member);

//		if(mOne != null) {
//			try {
//				// mail 작성 관련기능
//				MailUtils sendMail = new MailUtils(mailSender);
//				sendMail.setSubject("[StudyUs] 비밀번호 찾기 인증번호");
//				sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
//						.append("<p>아래의 인증번호를 확인창에 입력해주세요.</p>")
//						.append("<h3>")
//						.append(member.getAuthKey())
//						.append("</h3>").toString());
//				sendMail.setFrom("38gttaeng@gmail.com", "StudyUs");
//				sendMail.setTo(member.getMbEmail());
//				sendMail.send();
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return mOne;
	}

	@Override
	public ArrayList<Enrollment> myStudyList(int mbNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Review> myReviewList(int mbNo) {
		ArrayList<Review> myReviewList = store.myReviewList(mbNo);
		return myReviewList;
	}

	@Override
	public ArrayList<Member> printAllByStudyNo(int studyNo) {
		return store.selectAllEnrolled(studyNo);
	}

	@Override
	public ArrayList<Member> printAllAssign(int grNo) {
		return store.selectAllAssign(grNo);
	}
	
	@Override
	public ArrayList<Member> printAll() {
		return store.selectList();
	}

}
