package com.tt.web.jinhuo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tt.entity.Liucheng;
import com.tt.entity.Shangpin;
import com.tt.entity.User;
import com.tt.service.account.AccountService;
import com.tt.service.account.ShiroDbRealm.ShiroUser;
import com.tt.service.zhekou.LiuchengService;
import com.tt.service.zhekou.ShangpinService;

@Controller
@RequestMapping(value = "/jinhuo")
public class JinhuoController {
	@Autowired
	private ShangpinService sps;
	@Autowired
	private LiuchengService lcs;
	
	@Autowired
	private AccountService accountService;
	private static final int PAGE_SIZE = 10;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("czsj", "时间");
	}
	
	@RequestMapping(value = "update/{id}",method = RequestMethod.GET)
	public String jinhuo(@PathVariable("id") Long id,Model model){
		System.out.println("update");
		model.addAttribute("shangpin",sps.getShangpinNoe(id));
		model.addAttribute("liucheng",new Liucheng());
		model.addAttribute("action","update");
		return "jinhuo/jinhuo";
		
	}
	@RequestMapping(value ="update",method = RequestMethod.POST)
	public String jinhuotj(@Valid @ModelAttribute("preloadShangpin") Shangpin shangpin,@Valid Liucheng newliucheng,  RedirectAttributes redirectAttributes){
		System.out.println("lai le mei you ");
		//String longName= getCurrentUserId();
		Long userid= getCurrentUserId();
		User user = accountService.getUser(userid);
		shangpin.setKcsl(shangpin.getKcsl()+newliucheng.getJysl());
		newliucheng.setShangpin(shangpin);
		newliucheng.setType(2);
		Date date = new Date();
		newliucheng.setCzsj(date);
		newliucheng.setUser(user);
		Liucheng liucheng  = new Liucheng();
		liucheng.setBz(newliucheng.getBz());
		liucheng.setCzsj(newliucheng.getCzsj());
		liucheng.setUser(newliucheng.getUser());
		liucheng.setJyje(newliucheng.getJyje());
		liucheng.setJysl(newliucheng.getJysl());
		liucheng.setShangpin(newliucheng.getShangpin());
		liucheng.setType(newliucheng.getType());
		sps.save(shangpin);
		lcs.save(liucheng);
		redirectAttributes.addFlashAttribute("message", "进货成功");
		return "redirect:/shangpin/";
		
	}
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value="sortType",defaultValue="auto") String sortType,
			@RequestParam(value="page",defaultValue="1")int pageNumber,Model model,ServletRequest request) throws ParseException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		System.out.println("zm "+searchParams.toString());
		Integer type = 2;
		Integer zt = null;
		//Long userid= getCurrentUserId();
		//User user = accountService.getUser(userid);
		Page<Liucheng> liucheng = lcs.getLiuchengall1(type,searchParams, pageNumber, PAGE_SIZE, sortType,zt);
	//	List<Map> tongji = lcs.getTongji(type);
		//for(Map m: tongji){
	//		System.out.println("我去"+m.keySet().toString());
		//}
		//System.out.println("zm "+tongji.get(0).get("JYSL"));
		model.addAttribute("liucheng", liucheng);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
	//	model.addAttribute("tongji",tongji);
		return "jinhuo/jinhuoList";
		
	}
	@ModelAttribute("preloadShangpin")
	public Shangpin getTask(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return sps.getShangpinNoe(id);
		}
		return null;
	}
	private Long  getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
