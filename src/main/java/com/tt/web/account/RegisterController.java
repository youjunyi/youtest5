package com.tt.web.account;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tt.entity.Fengcheng;
import com.tt.entity.User;
import com.tt.service.account.AccountService;
import com.tt.service.zhekou.FengchengService;

/**
 * 用户注册的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private FengchengService fcs;
	@RequestMapping(method = RequestMethod.GET)
	public String registerForm(Model model) {
		List<Fengcheng> flist = fcs.getAll();
		System.out.println("laile ren"+flist);
		model.addAttribute("fengcheng",flist);
		return "account/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid User user,Long fengchengs, RedirectAttributes redirectAttributes) {
		
		Fengcheng fencheng = fcs.getFengcheng(fengchengs);
		accountService.registerUser(user,fencheng);
		redirectAttributes.addFlashAttribute("username", user.getLoginName());
		return "redirect:/login";
	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("loginName") String loginName) {
		if (accountService.findUserByLoginName(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}
}
