package bujny.atlas.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bujny.atlas.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bujny.atlas.user.entity.User;
import bujny.atlas.user.service.UserService;

@Controller
@SessionAttributes("sessUser")
@RequestMapping("login")
public class LoginController {
	
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfig.class);
	UserService userService = context.getBean(UserService.class);
	private static final String WARNING_ALERT = "warning";

	@GetMapping(value = "/home")
	public String homePage() {
		return "login";
	}

	@PostMapping(value = "/login")
	public ModelAndView checkUser(@RequestParam("username")String username, @RequestParam("password")String password) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		User user = userService.checkUser(username, password);
		if(user != null) {
			modelAndView.addObject("sessUser", user);
			modelAndView.addObject("success", "login.success");
		}
		else {
			modelAndView.addObject("error", "login.error");
			modelAndView.setViewName("login");
		}
		
		return modelAndView;
	}

	@GetMapping(value = "/logout")
	public ModelAndView logoutUser(SessionStatus status) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/loggedOut.html");
		status.setComplete();
		return modelAndView;
		}

	@GetMapping(value = "/loggedOut")
	public ModelAndView loggedOut() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		modelAndView.addObject("success", "logout.success");
		return modelAndView;
	}

	@GetMapping(value = "registerForm")
    public ModelAndView registerForm () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

	@PostMapping(value = "/register")
	public ModelAndView register(@RequestParam("username")String username, @RequestParam("password")String password) {
		ModelAndView modelAndView = new ModelAndView();
		if(userService.checkUsername(username)!=-1) {
		    modelAndView.setViewName("register");
		    modelAndView.addObject("error","Nazwa użytkownika zajęta.");
        }
		else {
		    modelAndView.setViewName("login");
		    modelAndView.addObject("success","Zarejestrowano nowego użytkownika.");
		    User user = new User(username,password);
		    userService.add(user);
        }
		return modelAndView;
	}

	private void validData(ModelAndView modelAndView, RedirectAttributes redir, String[] regUserInfo) {
		int resultCheckUsername = checkUsername(modelAndView, regUserInfo);

		if(resultCheckUsername == -1) {
			User user = new User(regUserInfo[0], regUserInfo[1]);
			userService.add(user);
			redir.addFlashAttribute("info", "register.success");
		}
		else modelAndView.setViewName("register");
	}

	private int checkUsername(ModelAndView modelAndView, String[] regUserInfo) {
		int result = userService.checkUsername(regUserInfo[0]);
		if(result != -1) modelAndView.addObject("error", "register.usernameError");
		return result;
	}
}
