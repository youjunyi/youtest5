package com.tt.web.zhekou;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;
import com.tt.entity.Zhekou;
import com.tt.service.account.ShiroDbRealm.ShiroUser;
import com.tt.service.zhekou.ZhekouService;

@Controller
@RequestMapping(value = "/zhekou")
public class ZhekouController {
	
	@Autowired
	private ZhekouService zks;
	//public String 
	private static final int PAGE_SIZE = 10;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("name", "名称");
	}
	@RequestMapping(value="create",method = RequestMethod.GET)
	public String zhekou(Model model) {
		System.out.println("laile ");
		model.addAttribute("zhekou", new Zhekou());
		model.addAttribute("action", "create");
		return "test/test";
	}
	@RequestMapping(value="create",method = RequestMethod.POST)
	public String zhekoutj(@Valid Zhekou newZhekou, RedirectAttributes redirectAttributes){
		System.out.println("wo lai le");
		System.out.println(newZhekou.getBz());
		zks.savezhekou(newZhekou);
		redirectAttributes.addFlashAttribute("message", "创建任务成功");

		return "redirect:/zhekou/";
		
	}
	@RequestMapping(value="")
	public String list(@RequestParam(value="sortType",defaultValue="auto") String sortType,
			@RequestParam(value="page",defaultValue="1")int pageNumber,Model model,ServletRequest request ){
		System.out.println("ni shi  shui");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Long userId = getCurrentUserId();
		Page<Zhekou> zhekou = zks.getUserZhekou(userId, searchParams, pageNumber, PAGE_SIZE, sortType);

		model.addAttribute("zhekou", zhekou);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "test/testList";
		
	}
	@RequestMapping(value="update/{id}",method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id,Model model){
		System.out.println("update");
		model.addAttribute("zhekou", zks.getZhekou(id));
		model.addAttribute("action", "update");
		return "test/test";
		
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadZhekou") Zhekou zhekou, RedirectAttributes redirectAttributes) {
		zks.savezhekou(zhekou);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/zhekou/";
	}
	@RequestMapping(value = "delete/{id}",method= RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		zks.delete(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/zhekou/";
		
	}
	/**
	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preloadZhekou")
	public Zhekou getTask(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return zks.getZhekou(id);
		}
		return null;
	}
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
		
		//return null;
		
	//}
	
}
