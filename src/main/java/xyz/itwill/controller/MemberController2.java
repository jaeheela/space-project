package xyz.itwill.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.MemberHostBoardDAO;
import xyz.itwill.dto.Faq;
import xyz.itwill.dto.Host;
import xyz.itwill.dto.Member;
import xyz.itwill.dto.Notice;
import xyz.itwill.dto.Question;
import xyz.itwill.dto.Review;
import xyz.itwill.dto.SelectMember;
import xyz.itwill.dto.Space;
import xyz.itwill.exception.BoardNotFoundException;
import xyz.itwill.exception.MemberNotFoundException;
import xyz.itwill.exception.SpaceNotFoundException;
import xyz.itwill.service.MemberService;
import xyz.itwill.service.MemberHostBoardService;
import xyz.itwill.util.Pager;


@Controller
@RequiredArgsConstructor
public class MemberController2 {
	
	private final MemberHostBoardService memberHostBoardService;
	private final MemberHostBoardDAO memberHostBoardDao;
	
		
		//member write,update,delete 처리.
			@RequestMapping(value="/member_review_write", method = RequestMethod.GET)
			public String find2(HttpSession session,Model model)  {
				Member loginMember = (Member)session.getAttribute("loginMember");
				model.addAttribute("mId",loginMember.getMId());
				return "member/member_review_write";
			}
		
		//입력값 작성 후 게시글 등록 클릭했을경우 삽입 후 notice페이지로 이동
			@RequestMapping(value = "member_review_write", method = RequestMethod.POST)
			public String noticewrite(@ModelAttribute Review review) {
				memberHostBoardService.addReview(review);
				return "redirect:/member_review";
			}

			
			//member write,update,delete 처리.
			@RequestMapping(value="/member_qna_write", method = RequestMethod.GET)
			public String qnawrite(HttpSession session,Model model)  {
				Member loginMember = (Member)session.getAttribute("loginMember");
				model.addAttribute("mId",loginMember.getMId());
				return "member/member_qna_write";
			}
			
			//입력값 작성 후 게시글 등록 클릭했을경우 삽입 후 notice페이지로 이동
			@RequestMapping(value = "member_qna_write", method = RequestMethod.POST)
			public String qnawrite(@ModelAttribute Question question) {
				memberHostBoardService.addQuestion(question);
				return "redirect:/member_qna";
			}
	
			
			//선택된 게시글을 전달받아 해당 게시글을 삭제 처리
			@RequestMapping(value ="member_review_delete/{rNo}" , method = RequestMethod.DELETE)
			@ResponseBody
			public String faqremove(@PathVariable int rNo) throws BoardNotFoundException {
				memberHostBoardService.removeReview(rNo);
				return "success";
			}
			
			
			
			
				
					//notice 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "member_reviewList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> ReviewList(@RequestParam(defaultValue = "1") int pageNum,HttpSession session) {
						Member loginMember=(Member)session.getAttribute("loginMember");
						int totalQuestion=memberHostBoardService.getReviewCount(loginMember.getMId());
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						
						List<SelectMember> reviewList=memberHostBoardService.getReviewList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("reviewList", reviewList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					
					
					
					//notice 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "member_reserveList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> ReserveList(@RequestParam(defaultValue = "1") int pageNum,HttpSession session) {
						Member loginMember=(Member)session.getAttribute("loginMember");
						int totalQuestion=memberHostBoardService.getReserveCount(loginMember.getMId());
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						
						List<SelectMember> reserveList=memberHostBoardService.getReserveList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("reserveList", reserveList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					
					//space 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					@RequestMapping(value = "member_spaceList", method = RequestMethod.POST)
					@ResponseBody
					public Map<String, Object> SpaceList(@RequestParam(defaultValue = "1") int pageNum,@RequestParam Map<String, Object> params) {	
						
						
						//필터를 통해 전달받은 모든 parameter를 params Map객체에 저장.
						int totalQuestion=memberHostBoardDao.selectSpaceCount(params);
						int pageSize=6;
						int blockSize=5;
						
						//페이징 처리를 위한 객체 생성.
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						
						if(totalQuestion != 0) {
							//sNo를 받아와서 출력하기 위한 객체 생성.
							List<Space> sNo=memberHostBoardDao.selectSpaceSno(params);
							//List<Integer> sNoList = new ArrayList<>();
							
							for (Space space : sNo) {
						        sNoList.add(space.getSNo());
						    }
							//SQL 매퍼에 적용하기 위해 pageMap객체 생성 후 입력값 전달.
							Map<String, Object> pageMap=new HashMap<String, Object>();
							pageMap.put("startRow", pager.getStartRow());
							pageMap.put("endRow", pager.getEndRow());
							pageMap.put("sNoList", sNoList);
							pageMap.put("sort", (String)params.get("sort"));
							
							List<Space> spaceList=memberHostBoardDao.selectSpaceList(pageMap);
							
							Map<String, Object> resultMap=new HashMap<String, Object>();
							resultMap.put("spaceList", spaceList);
							resultMap.put("pager", pager); 
							return resultMap;
											}
						
						
						else {
					        Map<String, Object> resultMap = new HashMap<String, Object>();
					        resultMap.put("spaceList", new ArrayList<Space>()); // 빈 리스트 추가
					        resultMap.put("pager", pager);
					        return resultMap;
					    }
						
					}
					
					
					//notice 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "member_questionList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> QuestionList(@RequestParam(defaultValue = "1") int pageNum,HttpSession session) {
						Member loginMember=(Member)session.getAttribute("loginMember");
						int totalQuestion=memberHostBoardService.getQuestionCount(loginMember.getMId());
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						
						List<SelectMember> questionList=memberHostBoardService.getQuestionList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("questionList", questionList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					
					
					
					//notice 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "host_reviewList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> ReviewList2(@RequestParam(defaultValue = "1") int pageNum) {
					
						int totalQuestion=memberHostBoardDao.selectHostReviewCount();
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						
						List<SelectMember> reviewList=memberHostBoardDao.selectHostReviewList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("reviewList", reviewList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					
					
					//notice 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "host_reserveList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> ReserveList2(@RequestParam(defaultValue = "1") int pageNum) {
						
						int totalQuestion=memberHostBoardDao.selectHostReserveCount();
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						
						List<SelectMember> reserveList=memberHostBoardDao.selectHostReserveList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("reserveList", reserveList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					
					
					//notice 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "host_questionList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> QuestionList2(@RequestParam(defaultValue = "1") int pageNum) {
						
						int totalQuestion=memberHostBoardDao.selectHostReviewCount();
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						
						List<SelectMember> questionList=memberHostBoardDao.selectHostQuestionList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("questionList", questionList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					
				
}
