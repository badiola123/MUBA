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

/**
 * This class catches all the requests directed to /account and handles the required actions to carry the password change process.
 *
 * @author MUBA team
 * @version Final version
 * @see The Controller annotation is used to specify that this class is a controller that handles requests
 * @see The RequestMapping annotation is used to map the methods to a path and specify the requet method (GET or POST)
 * @see The SessionAttributes annotation is used to specify the attributes with session scope that are used
 */

@Controller
@RequestMapping("account")
@SessionAttributes("sessUser")
public class AccountController {
	
	AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(AppConfig.class);
	UserService userService = context.getBean(UserService.class);
	
	/**
	 * Catches the request for /account/manage with POST method which is requested to process a password change by the user
	 * 
	 * @param oldPass The old password entered by the user to verify the identity
	 * @param newPass The new password entered by the user which will replace the current password
	 * @param reNewPass The new password retyped by the user to confirm it
	 * @param request Used to check if a user attribute exists which shows that someone is logged in
	 * @param redir Attributes that need to be passed between redirects are added to it
	 * @return An instance of ModelAndView containing the view
	 */
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public ModelAndView accountPage(@RequestParam("oldPass")String oldPass, @RequestParam("newPass")String newPass, @RequestParam("reNewPass")String reNewPass, HttpServletRequest request, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login/home.html");
		User user = (User) request.getSession().getAttribute("sessUser");
		
		if(user!=null) checkOldPass(modelAndView, user, oldPass, newPass, reNewPass);
		else redir.addFlashAttribute("warning", "login.warning");
		
		return modelAndView;
	}

	/**
	 * Checks if the old password inserted with the user equals the current password of the logged in user
	 * If they are, the function the check if the inserted new passwords are equal is called
	 * Otherwise, an error attribute is added to display an alert later
	 * 
	 * @param modelAndView Instance created in the caller function to manage the view to be loaded with its attributes
	 * @param user Current user whose password is changed
	 * @param oldPass The old password entered by the user to verify the identity
	 * @param newPass The new password entered by the user which will replace the current password
	 * @param reNewPass The new password retyped by the user to confirm it
	 */
	private void checkOldPass(ModelAndView modelAndView, User user, String oldPass, String newPass, String reNewPass) {
		modelAndView.setViewName("account");
		if(user.getPassword().equals(oldPass)) checkNewPass(modelAndView, user, newPass, reNewPass);
		else modelAndView.addObject("error", "password.error");
	}

	/**
	 * Checks if both new password inputs are the same
	 * If they are, the function to change the password and update the database is called
	 * Otherwise, an error attribute is added to display an alert later
	 * 
	 * @param modelAndView Instance of ModelAndView to mnage the attributes that will later be used to display alerts
	 * @param user Current user whose password is changed
	 * @param newPass The new password entered by the user which will replace the current password
	 * @param reNewPass The new password retyped by the user to confirm it
	 */
	private void checkNewPass(ModelAndView modelAndView, User user, String newPass, String reNewPass) {
		if(newPass.equals(reNewPass)) changePass(modelAndView, user, newPass);
		else modelAndView.addObject("error", "newPass.error");
	}

	/**
	 * Updates the database with the new password
	 * If an error occurs, it adds an attribute which will later be used to display an alert
	 * 
	 * @param modelAndView Instance of ModelAndView to mnage the attributes that will later be used to display alerts
	 * @param user Current user whose password is changed
	 * @param newPass The new password entered by the user which will replace the current password
	 */
	private void changePass(ModelAndView modelAndView, User user, String newPass) {
		if(userService.changePass(newPass, user.getUserId()) == 1) modelAndView.addObject("success", "password.success");
		else modelAndView.addObject("error", "passDb.error");
	}

	/**
	 * Mapped to /account/manage with GET method.
	 * It is called to access the register page or when, once in the register page, a language change is requested.
	 * In the last case, the same page is loaded but in another language
	 * 
	 * @param request Used to check if a user attribute exists which shows that someone is logged in
	 * @param redir Attributes that need to be passed between redirects are added to it
	 * @return
	 */
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
