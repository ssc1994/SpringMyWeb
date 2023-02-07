package com.ssc.myweb.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssc.myweb.command.TripVO;
import com.ssc.myweb.trip.service.TripService;


@Controller
@RequestMapping("/trip")
public class NoticeController {
	
	@Autowired
	@Qualifier("tripService")
	private TripService tripService;
	
	
	@RequestMapping("/notice_list")
	public String notice_list(Model model) {
		
		/*
		 * service, mapper영역에 getlist함수를 선언
		 * 등록번호를 역순으로 데이터를 조회해서 가지고 나가기
		 * model에 담아서 화면에는 반복문으로 처리
		 * */
		ArrayList<TripVO> list = tripService.getList();
		model.addAttribute("list",list);
		
		return "trip/notice_list";
	}
	
	@RequestMapping("/notice_view")
	public String notice_view() {
		return "trip/notice_view";
	}
	
	@RequestMapping("/notice_write")
	public String notice_write() {
		return "trip/notice_write";
	}

	@RequestMapping("/notice_modify")
	public String notice_modify() {
		return "trip/notice_modify";
	}
	
	//글등록
	@RequestMapping(value="/registForm", method = RequestMethod.POST)
	public String registForm(TripVO vo, RedirectAttributes ra) {
		int result = tripService.noticeRegist(vo);
		String msg = result == 1? "문의사항이 정상 등록 되었습니다":"문의등록에 실패했습니다";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/trip/notice_list";
	}
	
	
}
