package xyz.itwill.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.SpaceDAO;
import xyz.itwill.dto.Space;
import xyz.itwill.dto.SpaceHost;
import xyz.itwill.service.SpaceService;

@Controller
@RequiredArgsConstructor
public class SpaceDetailController {	    
    private final SpaceService spaceService;  

	 	
    /**
     * 상세 페이지 요청 처리
     * @param sno
     * @param model
     * @return
     */
    @RequestMapping(value = "/spaces", method = RequestMethod.GET)
    public String showDetailPage2(@RequestParam int sno,Model model) {
    	Space spaces = spaceService.getSpaceBySnoTest(sno);
    	model.addAttribute("spaces",spaces);
	    return "space/space_detailpage";
    }
		
}

