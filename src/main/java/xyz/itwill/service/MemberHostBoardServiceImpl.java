package xyz.itwill.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.itwill.dao.MemberHostBoardDAO;
import xyz.itwill.dao.SpaceDAO;
import xyz.itwill.dto.Question;
import xyz.itwill.dto.Review;
import xyz.itwill.dto.SelectMember;
import xyz.itwill.dto.Space;
import xyz.itwill.exception.BoardNotFoundException;

@Service
public class MemberHostBoardServiceImpl implements MemberHostBoardService {

    @Autowired
    private MemberHostBoardDAO reviewDAO;
    
  

	@Override
	public void addReview(Review review) {
		reviewDAO.insertReview(review);
		
	}

	@Override
	public void modifyReview(Review review) throws BoardNotFoundException {
		reviewDAO.updateReview(review);
		
	}

	@Override
	public void removeReview(int rNo) throws BoardNotFoundException {
		reviewDAO.deleteReview(rNo);
		
	}


	@Override
	public int getReviewCount(String rMid) {
		
		return reviewDAO.selectReviewCount(rMid);
	}

	@Override
	public Review getReview(int rNo) throws BoardNotFoundException {
		if(reviewDAO.selectReview(rNo)==null) {
			throw new BoardNotFoundException("해당 게시글을 찾을 수 없습니다.");
			}
			return reviewDAO.selectReview(rNo);
	}
	/*
	@Override
	public List<Review> getReviewList(Map<String, Object> map) {
		return reviewDAO.selectReviewList(map);
	}
	*/
	@Override
	public List<SelectMember> getReviewList(Map<String, Object> map) {
		return reviewDAO.selectReviewList(map);
	}

	@Override
	public List<SelectMember> getReserveList(Map<String, Object> map) {
		
		return reviewDAO.SelectReserveList(map);
	}

	@Override
	public int getReserveCount(String pMid) {
		
		return reviewDAO.selectReserveCount(pMid);
	}

	@Override
	public List<SelectMember> getQuestionList(Map<String, Object> map) {
		
		return reviewDAO.SelectQuestionList(map);
	}

	@Override
	public int getQuestionCount(String qMid) {
	
		return reviewDAO.selectQuestionCount(qMid);
	}
	
	@Transactional
	@Override
	public void addQuestion(Question question) {
		reviewDAO.insertQuestion(question);
	}

	@Transactional
	@Override
	public void modifyQuestion(Question question) {
		/*
		if(restBoardDAO.selectRestBoard(restBoard.getNum())==null) {
			throw new Exception("해당 게시글을 찾을 수 없습니다.");
		}
		*/
		reviewDAO.updateQuestion(question);
	}

	
	
	
  
}