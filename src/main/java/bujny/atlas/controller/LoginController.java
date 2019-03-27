package bujny.atlas.controller;
import bujny.atlas.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import bujny.atlas.user.entity.User;
import bujny.atlas.user.service.UserService;

@Controller
@SessionAttributes("sessUser")
@RequestMapping("login")
public class LoginController {
	
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfig.class);
	UserService userService = context.getBean(UserService.class);

	@GetMapping(value = "/start")
	public String startPage() {
		return "login";
	}

	@PostMapping(value = "/login")
	public ModelAndView checkUser(@RequestParam("username")String username, @RequestParam("password")String password) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.checkUser(username, password);
		if(user != null) {
			modelAndView.addObject("sessUser", user);
			if(user.isAdmin()) {
				modelAndView.setViewName("allUsers");
			}
			else {
				modelAndView.setViewName("myRobaks");
			}
		}
		else {
			modelAndView.addObject("error", "login.error");
			modelAndView.setViewName("login");
		}
		
		return modelAndView;
	}

	@PostMapping(value = "/logout")
	public ModelAndView logoutUser() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("sessUser", null);
		modelAndView.setViewName("login");
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

}
