package com.tt.web.chushou;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.apache.shiro.SecurityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;



import com.google.common.collect.Maps;
import com.tt.entity.Liucheng;
import com.tt.entity.Renyuan;
import com.tt.entity.Shangpin;
import com.tt.entity.Type;
import com.tt.entity.User;
import com.tt.entity.Zhekou;
import com.tt.service.account.AccountService;
import com.tt.service.account.ShiroDbRealm.ShiroUser;
import com.tt.service.zhekou.LiuchengService;
import com.tt.service.zhekou.RenyuanService;
import com.tt.service.zhekou.ShangpinService;
import com.tt.service.zhekou.TypeService;
import com.tt.service.zhekou.ZhekouService;

@Controller
@RequestMapping(value = "/chushou")
public class ChushouController {
		@Autowired
		private ShangpinService sps;
		@Autowired
		private ZhekouService zks;
		@Autowired
		private RenyuanService rys;
		@Autowired
		private LiuchengService lcs;
		@Autowired
		private AccountService accountService;
		@Autowired
		private TypeService ts;
		private static final int PAGE_SIZE = 20;
		private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
		static {
			sortTypes.put("auto", "自动");
			sortTypes.put("czsj", "时间");
		}
		
		@RequestMapping()
		public String chishoulist(@RequestParam(value="sortType",defaultValue="auto") String sortType,
				@RequestParam(value="page",defaultValue="1")int pageNumber,Model model,ServletRequest request,HttpServletRequest hrequest){
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
			String name = hrequest.getParameter("name");
			System.out.println("skuk"+name);
			List<Type> type = ts.getAll();
			Page<Shangpin> shangpin = sps.getShangpin(searchParams, pageNumber, PAGE_SIZE, sortType,name);
			model.addAttribute("type",type);	
			model.addAttribute("shangpin",shangpin);
			return "chushou/chushoucl";
			
		}
		@RequestMapping(value="chushoua/{id}",method = RequestMethod.GET)
		public String chushou(@PathVariable("id") Long id,Model model){
			//model.addAttribute("zhekou",zhekou());
			//model.addAttribute("renyuan",renyuan());
			model.addAttribute("shangpin",sps.getShangpinNoe(id));
			model.addAttribute("liucheng",new Liucheng());
			model.addAttribute("action","save");
			return "chushou/chushou";
		}
		@RequestMapping(value="save",method = RequestMethod.POST )
		public String save(@Valid Liucheng newliucheng,@Valid Long sid,@Valid Long renyuans,RedirectAttributes redirectAttributes){
			System.out.println("shaha"+newliucheng.getJyje());
			Long userid= getCurrentUserId();
			User user = accountService.getUser(userid);
			Shangpin shangpin = sps.getShangpinNoe(sid);
			//Renyuan renyuan = rys.getRenyuanNoe(renyuans);
			newliucheng.setShangpin(shangpin);
			newliucheng.setUser(user);
		//	newliucheng.setUserName(longName);
			//newliucheng.setRenyuan(renyuan);
			newliucheng.setJyje(newliucheng.getJyje()*newliucheng.getJysl());
			Double ss = shangpin.getCb()*newliucheng.getJysl();
			newliucheng.setLr(newliucheng.getJyje() - ss );
			Date date = new Date();
			newliucheng.setCzsj(date);
			newliucheng.setType(1);
			newliucheng.setZt(1);
			shangpin.setKcsl(shangpin.getKcsl() - newliucheng.getJysl());
			lcs.save(newliucheng);
			sps.save(shangpin);
			redirectAttributes.addFlashAttribute("message", "成功出售商品"+newliucheng.getJysl()+"件，金额为"+newliucheng.getJyje()+"元");

			return "redirect:/chushou/list/";
			
		}
		@RequestMapping(value="list")
		public String list(@RequestParam(value="sortType",defaultValue="auto") String sortType,
				@RequestParam(value="page",defaultValue="1")int pageNumber,Model model,ServletRequest request ) throws ParseException{
			System.out.println("ni shi  shui");
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
				System.out.println("map"+searchParams.toString());
			//Long userId = getCurrentUserId();
				Integer type =1;
				Integer zt = 1;
				//Long userid= getCurrentUserId();
				//User user = accountService.getUser(userid);

			Page<Liucheng> liucheng = lcs.getLiuchengall1(type,searchParams, pageNumber, PAGE_SIZE, sortType,zt);
			model.addAttribute("liucheng", liucheng);
			model.addAttribute("sortType", sortType);
			model.addAttribute("sortTypes", sortTypes);
			// 将搜索条件编码成字符串，用于排序，分页的URL
			model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
			return "chushou/chushouList";
			
		}
		@RequestMapping(value="buquan")
		@ResponseBody
		public String buquan(HttpServletRequest request,HttpServletResponse response) {
			System.out.println("行不");
			String wid = request.getParameter("wid");
			Shangpin shangpin = sps.getShangpinw(wid);
			System.out.println("能行吗"+wid);	
			System.out.println(shangpin.getName());
				JSONObject jobj = null;
				jobj = new JSONObject();
				try {
					jobj.put("id", shangpin.getId());
					jobj.put("wid", shangpin.getWid());
					jobj.put("name", shangpin.getName());
					jobj.put("type", shangpin.getType());
					jobj.put("spxh", shangpin.getSpxh());
					jobj.put("ghs", shangpin.getGhs());
					jobj.put("mtj", shangpin.getMtj());
					jobj.put("zdj", shangpin.getZdj());
				} catch (Exception e) {	
					e.printStackTrace();
				}
			return jobj.toString();	
		}
		public List<Zhekou> zhekou(){
			return  zks.getZkall();
			
		}
		public List<Renyuan> renyuan(){
			return rys.getRenAll();
		}	
		
		private Long  getCurrentUserId() {
			ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			return user.id;
		}
		@RequestMapping(value="shouye")
		public ModelAndView shuye(){
			ModelAndView mav = new ModelAndView("account/insdex");
			return mav;
		}
		
}
