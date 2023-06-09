package sawon.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SawonController {
	
	@Autowired
	SawonDaoInter dao;
	
	//사원폼
	@GetMapping("/sawon/form")
	public String form()
	{
		return "sawon/addform";
	}
	
	@GetMapping("sawon/list") //인덱스
	public ModelAndView list(@RequestParam(required = false) String title,
			@RequestParam(required = false) String search)
	{
		ModelAndView model = new ModelAndView();
		
		//전체개수
		int totalCount = dao.getTotalCount();
		
		System.out.println(title + "," + search);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("search", search);
		
		//전체회원리스트
		List<SawonDto> totalList = dao.getAllDatas(map);
		
		model.addObject("totalCount", totalCount); //totalCount를 받아서 이름을 totalCount로 해줌
		model.addObject("totalList", totalList);
		model.setViewName("sawon/sawonlist"); //폴더명 파일명
		return model; //모델은 모델을 리턴
	}
	
	@PostMapping("/sawon/insert")
	public String inesert(@ModelAttribute SawonDto dto, 
			@RequestParam MultipartFile upload, // suvlet xml 확인 upload
			HttpSession session) 
	{
		String path = session.getServletContext().getRealPath("WEB-INF/image");
		System.out.println(path);
		
		//dto에 담을 변수
		String photoname;
		
		//사진선택을 안했을 경우 no, 했을 경우
		if(upload.getOriginalFilename().equals(""))
			photoname = "no";
		else {
			String fName = upload.getOriginalFilename();
			photoname = fName; //이미지 이름 들어감
			
			//업로드
			try {
				upload.transferTo(new File(path + "\\" + photoname));
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//dto의 photo에 업로드한 photoname 넣어주기
		dto.setPhoto(photoname);
		
		//insert
		dao.insertSawon(dto);
		return "redirect:list";
	}
	
	//수정폼
	@GetMapping("/sawon/updateform")
	public String uform(@RequestParam String num, Model model)
	{
		
		SawonDto dto = dao.getData(num);
		model.addAttribute("dto", dto);
		return "sawon/updateform";
	}
	
	//이미지 포함 모든 필드 모두 수정
	/*
	 * @PostMapping("/sawon/update") public String update(@ModelAttribute SawonDto
	 * dto,
	 * 
	 * @RequestParam MultipartFile upload, // suvlet xml 확인 upload HttpSession
	 * session) { String path =
	 * session.getServletContext().getRealPath("WEB-INF/image");
	 * System.out.println(path);
	 * 
	 * //dto에 담을 변수 String photoname = "";
	 * 
	 * photoname = upload.getOriginalFilename();
	 * 
	 * //업로드 try { upload.transferTo(new File(path + "\\" + photoname));
	 * 
	 * } catch (IllegalStateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } } //dto의 photo에 업로드한 photoname 넣어주기
	 * dto.setPhoto(photoname);
	 * 
	 * //insert dao.updateSawon(dto); return "redirect:list"; }
	 */
	
	@PostMapping("/sawon/update")
	public String update(@ModelAttribute SawonDto dto, 
			@RequestParam MultipartFile upload, // suvlet xml 확인 upload
			HttpSession session) 
	{
		String path = session.getServletContext().getRealPath("WEB-INF/image");
		System.out.println(path);
		
		//dto에 담을 변수
		String photoname;
		
		//사진선택을 안했을 경우 null, 했을 경우
		if(upload.getOriginalFilename().equals(""))
			photoname = null;
		else {
			String fName = upload.getOriginalFilename();
			photoname = fName; //이미지 이름 들어감
			
			//업로드
			try {
				upload.transferTo(new File(path + "\\" + photoname));
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//dto의 photo에 업로드한 photoname 넣어주기
		dto.setPhoto(photoname);
		
		//update
		dao.updateSawon(dto);
		return "redirect:list";
	}
	
	@GetMapping("/sawon/delete")
	public String deleteform(@RequestParam String num, HttpSession session)
	{
		
		String path = session.getServletContext().getRealPath("WEB-INF/image");
		String photo = dao.getData(num).getPhoto();
		
		File file = new File(path + "\\" + photo);
		if(file.exists())
			file.delete();
		dao.deleteSawon(num);
		return "redirect:list";
		
		
	}
	
	
}
