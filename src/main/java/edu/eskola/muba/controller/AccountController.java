package edu.eskola.muba.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.eskola.muba.config.AppConfig;
import edu.eskola.muba.user.entity.User;
import edu.eskola.muba.user.service.UserService;

@Controller
@RequestMapping("account")
@SessionAttributes("sessUser")
public class AccountController {
	
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfig.class);
	UserService userService = context.getBean(UserService.class);
	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public ModelAndView accountPage(@RequestParam("oldPass")String oldPass, @RequestParam("newPass")String newPass, @RequestParam("reNewPass")String reNewPass, HttpServletRequest request, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute("sessUser");
		
		if(user!=null) checkOldPass(modelAndView, user, oldPass, newPass, reNewPass);
		else redir.addFlashAttribute("warning", "login.warning");
		
		return modelAndView;
	}

	private void checkOldPass(ModelAndView modelAndView, User user, String oldPass, String newPass, String reNewPass) {
		modelAndView.setViewName("account");
		if(user.getPassword().equals(oldPass)) checkNewPass(modelAndView, user, newPass, reNewPass);
		else modelAndView.addObject("error", "password.error");
	}

	private void checkNewPass(ModelAndView modelAndView, User user, String newPass, String reNewPass) {
		if(newPass.equals(reNewPass)) changePass(modelAndView, user, newPass);
		else modelAndView.addObject("error", "newPass.error");
	}

	private void changePass(ModelAndView modelAndView, User user, String newPass) {
		if(userService.changePass(newPass, user.getUserId()) == 1) modelAndView.addObject("success", "password.success");
		else modelAndView.addObject("error", "passDb.error");
	}

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView accountPage(HttpServletRequest request, RedirectAttributes redir) {
		String retPage = "redirect:/login/home.html";
		User user = (User) request.getSession().getAttribute("sessUser");
		ModelAndView modelAndView = new ModelAndView();
		
		if(user!=null) retPage="account";
		else redir.addFlashAttribute("warning", "login.warning");
		
		modelAndView.setViewName(retPage);
		return modelAndView;
	}
}
