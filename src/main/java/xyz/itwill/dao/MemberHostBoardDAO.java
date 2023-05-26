package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Question;
import xyz.itwill.dto.Review;
import xyz.itwill.dto.SelectMember;
import xyz.itwill.dto.Space;
import xyz.itwill.dto.Review;

public interface MemberHostBoardDAO {
	
	
	//Member Review
	 int insertReview(Review review);
	 int updateReview(Review review);
	 int deleteReview(int rNo); 
	 int selectReviewCount(String rMid);
	 Review selectReview(int rNo);
	 List<SelectMember> selectReviewList(Map<String, Object> map);
	
	
	//Member Reserve
	List<SelectMember> SelectReserveList(Map<String, Object> map);
	int deleteReserve(int pNo);
	int selectReserveCount(String pMid);

	
	//Member Question
	List<SelectMember> SelectQuestionList(Map<String, Object> map);
	int deleteQuestion(int qNo);
	int selectQuestionCount(String qMid);
	int insertQuestion(Question question);
	int updateQuestion(Question question);
	


	//Host Reserve
	int selectHostReserveCount();
	List<SelectMember> selectHostReserveList(Map<String, Object> map);

	//Host Review
	int selectHostReviewCount();
	List<SelectMember> selectHostReviewList(Map<String, Object> map);
	
	//Host Question
	int selectHostQuestionCount();
	List<SelectMember> selectHostQuestionList(Map<String, Object> map);
	
	// Filter 
	List<Space> selectSpaceSno(Map<String, Object> params);
	int selectSpaceCount(Map<String, Object> params);
	List<Space> selectSpaceList(Map<String, Object> map);
	    
}
