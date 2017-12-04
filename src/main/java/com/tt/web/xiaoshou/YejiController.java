package com.tt.web.xiaoshou;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;
import com.tt.entity.Liucheng;
import com.tt.entity.User;
import com.tt.service.account.AccountService;
import com.tt.service.account.ShiroDbRealm.ShiroUser;
import com.tt.service.zhekou.LiuchengService;

@Controller
@RequestMapping(value="/yeji")
public class YejiController {
	@Autowired
	private LiuchengService lcs;
	@Autowired
	private AccountService accountService;
	private static final int PAGE_SIZE = 20;
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("czsj", "时间");
	}
	
	@RequestMapping()
	public String yeji(@RequestParam(value="sortType",defaultValue="auto") String sortType,
			@RequestParam(value="page",defaultValue="1")int pageNumber,Model model,ServletRequest request,HttpServletRequest hrequest) throws ParseException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Long userid= getCurrentUserId();
		User user = accountService.getUser(userid);
		List<Liucheng> liucheng = lcs.getLiuchengalldan(searchParams,user);

	//	Page<Liucheng> liucheng = lcs.getLiuchengr(sortType,searchParams,pageNumber,PAGE_SIZE,user);
		model.addAttribute("liucheng",liucheng);
	model.addAttribute("sortType", sortType);
	model.addAttribute("sortTypes", sortTypes);
	model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
	System.out.println("map"+searchParams.toString());
		return "xiaoshou/yejiList";
		
	}
	
	
	private Long  getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
