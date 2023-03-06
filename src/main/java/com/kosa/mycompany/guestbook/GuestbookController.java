package com.kosa.mycompany.guestbook;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestbookController {
	@Resource(name="guestbookService")
	GuestbookService service;
	
	@RequestMapping(value="/guestbook/list")
	public String getList(GuestbookDto dto, Model model)
	{
		
		List<GuestbookDto> list = service.getList(dto);
		for(int i=0; i<list.size(); i++)
		{
			System.out.println(list.get(i).getTitle());
		}
		
		model.addAttribute("list", service.getList(dto));
		model.addAttribute("totalCnt", service.getTotalCnt(dto));
		return "guestbook/list";
	}
	
	@RequestMapping(value="/guestbook/view")
	public String getView(GuestbookDto dto, Model model)
	{
		
		GuestbookDto resultDto = service.getView(dto);
		model.addAttribute("dto", resultDto);
		return "guestbook/view";
	}
	
	@RequestMapping(value="/guestbook/write")
	public String write(Model model)
	{
		return "guestbook/write";
	}
	
	@RequestMapping(value="/guestbook/save")
	public String save(GuestbookDto dto, Model model)
	{
		// service�� �̿��� DB�� ���
		service.insert(dto);
		return "redirect:/guestbook/list";
		// redirect �ϴ� ���� : request��ü�� ������ �����
		// ���� jsp�� �̵��ϴ°� �ƴ϶� guestbook/list url�� �̵��ÿ� redirect�� �ؾ���
		// ����ϰ��� getList() �Լ��� ȣ���ؾ��ϴµ�, �߰��� �����ӿ�ũ�� �ϴ� ���� ���Ƽ� �Լ��� ���� ȣ������ ����
		// redirect : ȣ���� url�� ���
	}
	
}
