package com.kosa.mycompany.fileupload;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kosa.mycompany.common.FileUploadUtil;

@Controller 
public class FileController {
	@RequestMapping(value="/fileupload")
	public String fileupload() 
	{
		return "/fileupload/fileupload";
	}
	
	@RequestMapping(value="/fileupload/save") 
	public String fileuploadsave(MultipartHttpServletRequest multi)
	// MultipartHttpServletRequest ��ü�� getFile�Լ� ȣ��
	// �� �Լ��� ���ε�� ������ ������ ������ ����
	// input type="file" �±��� name �Ӽ��� ������
	{
		MultipartFile file = multi.getFile("file1");
		String path="C:\\web_programming\\spring_workspace1\\myapp2\\src\\main\\webapp\\upload";
		/*File dir = new File(path);
		if( !dir.exists()) // ���丮�� �������� ������
			dir.mkdirs();  // ���丮 ����
		
		try
		// Spring3 : �ӽü����� ������ �̹� �ö�� ����
		// ������ ��Ʈ�� �ٸ� ���
		// "���ο����ϸ�" : ������ ���ϸ� �����ͼ� ������, ������ ���� ������
		{
			file.transferTo(new File(path + "//a.jpg"));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}*/
		
		List<MultipartFile> multiList = new ArrayList<MultipartFile>();
		multiList.add(multi.getFile("file1"));
		
		List<String> fileNameList = new ArrayList<String>();
		fileNameList.add("filename1");
		
		FileUploadUtil.setFilePath(path);
		FileUploadUtil.upload(multiList, fileNameList);
		
		
		return "redirect:/";
	}
}



