package com.kosa.mycompany.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/member/write")
	public String write()
	{
		return "/member/member_write";
	}
	@RequestMapping(value="/member/save")
	public String save(MemberDto dto)
	{
		service.insert(dto);
		return "redirect:/"; //home���� �̵�
	}
	
	
	@ResponseBody // jsp�̵��� �ƴ϶�, json ������ �����͸� ��� 
	@RequestMapping(value="/member/idcheck")
	public Map<String, String> idcheck(MemberDto dto)
	{
		// return "��������" - viewResolver�� �������� jspȣ�� 
		
		Map<String, String> map = new HashMap<String, String>();
		
		if(service.isDuplicate(dto))
			map.put("result", "fail");
		else
			map.put("result", "success");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/logon_proc")
	public Map<String, String> logon_proc(MemberDto dto, HttpServletRequest req)
	{
		Map<String, String> map = new HashMap<String, String>();
		MemberDto resultDto = service.logon(dto);
		if(resultDto == null) // �����Ͱ� ����
		{
			map.put("result", "1");
			map.put("message", "�ش� ���̵� �������� �ʽ��ϴ�");
			return map;
		}
		
		if(!resultDto.getPassword().equals(dto.getPassword()))
		{
			map.put("result", "1");
			map.put("message", "�н����尡 ��ġ���� �ʽ��ϴ�");
			return map;
		}
		
		HttpSession session = req.getSession(); // ���� ��ü ��������
		// ������ ���� �����ϴ� ����ڸ��� �ϳ��� ��ü �ڵ� ����
		session.setAttribute("user_id", resultDto.getUser_id());
		session.setAttribute("user_name", resultDto.getUser_name());
		session.setAttribute("email", resultDto.getEmail());
		map.put("result", "0");
		map.put("message", "�α��εǾ����ϴ�");
		return map;
	}
	
	@RequestMapping(value="/member/logon")
	public String logon()
	{
		return "/member/logon";
	}
	
	@RequestMapping(value="/member/findid")
	public String findid()
	{
		return "/member/findid";
	}
	@ResponseBody
	@RequestMapping(value="/member/findid_proc")
	public Map<String, String> findid_proc(MemberDto dto)
	{
		Map<String, String> map = new HashMap<String, String>();
		MemberDto resultDto = service.findid(dto);
		if(resultDto == null) // �����Ͱ� ����
		{
			map.put("result", "1");
			map.put("message", "�ش� ���̵� �������� �ʽ��ϴ�");
			return map;
		}
		if(resultDto.getUser_name().equals(dto.getUser_name()))
		{
			map.put("result", "0");
			map.put("message", "���̵�� "+ resultDto.getUser_id() + "�Դϴ�");
			return map;
		}
		return map;
	}
	
	
	@RequestMapping(value="/member/findpassword")
	public String findpassword()
	{
		return "/member/findpassword";
	}
	@ResponseBody
	@RequestMapping(value="/member/findpassword_proc")
	public Map<String, String> findpassword_proc(MemberDto dto)
	{
		Map<String, String> map = new HashMap<String, String>();
		MemberDto resultDto = service.findpassword(dto);
		if(resultDto == null) // �����Ͱ� ����
		{
			map.put("result", "1");
			map.put("message", "�ش� ���̵� �������� �ʽ��ϴ�");
			return map;
		}
		
		if(resultDto.getUser_id().equals(dto.getUser_id()))
		{
			map.put("result", "0");
			map.put("message", "��й�ȣ�� "+ resultDto.getPassword() + "�Դϴ�");
			return map;
		}
		
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/member/list_data")
	public Map<String, Object> list_data(MemberDto dto)
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCnt", service.getTotalCnt(dto));
		map.put("data", service.getList(dto));
		return map;
	}
	
	@RequestMapping(value="/member/member_list")
	public String list(Model model, MemberDto dto)
	{
		model.addAttribute("totalCnt", service.getTotalCnt(dto));
		return "member/member_list";
	}
}
	
