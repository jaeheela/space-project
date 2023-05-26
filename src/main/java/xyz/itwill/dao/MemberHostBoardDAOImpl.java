package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Question;
import xyz.itwill.dto.Review;
import xyz.itwill.dto.SelectMember;
import xyz.itwill.dto.Space;
import xyz.itwill.mapper.NoticeMapper;
import xyz.itwill.mapper.MemberHostBoardMapper;

@Repository
@RequiredArgsConstructor
public class MemberHostBoardDAOImpl implements MemberHostBoardDAO {
    
    private final SqlSession sqlSession;

    

	@Override
	public int insertReview(Review review) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).insertReview(review);
	}

	@Override
	public int updateReview(Review review) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).updateReview(review);
	}

	@Override
	public int deleteReview(int rNo) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).deleteReview(rNo);
	}

	@Override
	public int selectReviewCount(String rMid) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectReviewCount(rMid);
	}

	@Override
	public Review selectReview(int rNo) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectReview(rNo);
	}

	@Override
	public List<SelectMember> selectReviewList(Map<String, Object> map) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectReviewList(map); 
	}

	@Override
	public List<SelectMember> SelectReserveList(Map<String, Object> map) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).SelectReserveList(map);
	}

	@Override
	public int deleteReserve(int pNo) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).deleteReserve(pNo);
	}

	@Override
	public int selectReserveCount(String pMid) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectReserveCount(pMid);
	}

	@Override
	public List<SelectMember> SelectQuestionList(Map<String, Object> map) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).SelectQuestionList(map);
	}

	@Override
	public int deleteQuestion(int qNo) {
	
		return sqlSession.getMapper(MemberHostBoardMapper.class).deleteQuestion(qNo);
	}

	@Override
	public int selectQuestionCount(String qMid) {
	
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectQuestionCount(qMid);
	}

	@Override
	public int selectHostReviewCount() {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectHostReviewCount();
	}
	
	
	@Override
	public int selectHostQuestionCount() {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectHostQuestionCount();
	}

	@Override
	public int selectHostReserveCount() {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectHostReserveCount();
	}
	
	/*
	@Override
	public int selectHostQuestionCount(String qHid) {
		
		return sqlSession.getMapper(ReviewMapper.class).selectHostQuestionCount(qHid);
	}

	@Override
	public int selectHostReserveCount(String pHid) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ReviewMapper.class).selectHostReviewCount(pHid);
	}
	*/
	@Override
	public List<SelectMember> selectHostQuestionList(Map<String, Object> map) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectHostQuestionList(map);
	}

	@Override
	public List<SelectMember> selectHostReserveList(Map<String, Object> map) {
	
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectHostReserveList(map);
	}

	@Override
	public List<SelectMember> selectHostReviewList(Map<String, Object> map) {
		
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectHostReviewList(map);
	}
	@Override
	public int insertQuestion(Question question) {
		return sqlSession.getMapper(MemberHostBoardMapper.class).insertQuestion(question);
	}

	@Override
	public int updateQuestion(Question restBoard) {
		return sqlSession.getMapper(MemberHostBoardMapper.class).updateQuestion(restBoard);
	}


	@Override
	public int selectSpaceCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectSpaceCount(params);
	}

	@Override
	public List<Space> selectSpaceList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectSpaceList(map);
	}

	@Override
	public List<Space> selectSpaceSno(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemberHostBoardMapper.class).selectSpaceSno(params);
	}

}