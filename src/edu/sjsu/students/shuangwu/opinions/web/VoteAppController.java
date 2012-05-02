package edu.sjsu.students.shuangwu.opinions.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import edu.sjsu.students.shuangwu.opinions.domain.User;
import edu.sjsu.students.shuangwu.opinions.domain.VoteOption;
import edu.sjsu.students.shuangwu.opinions.domain.VoteStatus;
import edu.sjsu.students.shuangwu.opinions.domain.VoteTopic;
import edu.sjsu.students.shuangwu.opinions.service.UserService;
import edu.sjsu.students.shuangwu.opinions.service.VoteService;
import edu.sjsu.students.shuangwu.opinions.validator.UserValidator;

@Controller
@SessionAttributes("loginUser")
public class VoteAppController {
	private static final Logger LOGGER = Logger
			.getLogger(VoteAppController.class);

	private final VoteService voteService;
	private final UserService userService;
	private final UserValidator userValidator;

	public VoteAppController(VoteService voteService, UserService userService,
			UserValidator userValidator) {
		this.voteService = voteService;
		this.userService = userService;
		this.userValidator = userValidator;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView loginHandler() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginHandler(String userKey) {
		ModelAndView mv = null;
		User loginUser = userService.getUser(userKey);
		if (loginUser != null) {
			mv = new ModelAndView("redirect:/vote.do");
			mv.addObject("loginUser", loginUser); // add to session
		} else {
			mv = new ModelAndView("redirect:/login.do?no_such_user");
		}
		return mv;
	}

	@RequestMapping("/logout.do")
	public ModelAndView logoutHandler(
			@ModelAttribute("loginUser") User loginUser, SessionStatus status) {
		ModelAndView mv = new ModelAndView("logout");
		mv.addObject("logoutUserName", loginUser.getName());
		status.setComplete(); // clean the session
		mv.addObject("loginUser", null);
		return mv;
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public ModelAndView registerHandler() {
		ModelAndView mv = new ModelAndView("register");
		User newUser = new User();
		mv.addObject("newUser", newUser);
		return mv;
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String registerHandler(@ModelAttribute("newUser") User newUser,
			BindingResult result) {
		userValidator.validate(newUser, result);
		if (result.hasErrors()) {
			LOGGER.info("new user has errors!");
			return "register";
		}
		if (userService.createUser(newUser) != null) { // create ok
			return "redirect:/login.do?from=new_user";
		} else { // failed
			return "redirect:/register.do?from=duplicated_user";
		}
	}

	@RequestMapping(value = "/ask.do", method = RequestMethod.GET)
	public ModelAndView askHandler(@ModelAttribute("loginUser") User loginUser) {
		ModelAndView mv = new ModelAndView("ask");
		return mv;
	}

	@RequestMapping(value = "/ask.do", method = RequestMethod.POST)
	public ModelAndView askHandler(@ModelAttribute("loginUser") User loginUser,
			String title, String description, String... options) {
		ModelAndView mv = new ModelAndView("ask_done");
		VoteTopic v = new VoteTopic();
		v.setText(title);
		v.setDescription(description);
		List<VoteOption> optionList = Lists.newArrayList();
		for (String optionText : options) {
			if (!optionText.trim().equals("")) {
				VoteOption voteOption = new VoteOption();
				voteOption.setText(optionText);
				optionList.add(voteOption);
			}
		}
		v.setOptions(optionList);
		v.setFriendOnly(false);
		v.setStatus(VoteStatus.ACTIVE);
		voteService.createVote(v, loginUser.getEmail());
		List<VoteTopic> voteTopics = userService.getUser(loginUser.getEmail())
				.getVoteTopics();
		mv.addObject("voteTopics", voteTopics);
		return mv;
	}

	@RequestMapping("/vote.do")
	public ModelAndView voteHandler() {
		ModelAndView mv = new ModelAndView("vote");
		// get random vote
		return mv;
	}

	@RequestMapping("/results.do")
	public ModelAndView resultsHandler(
			@ModelAttribute("loginUser") User loginUser) {
		ModelAndView mv = new ModelAndView("results");
		List<VoteTopic> voteTopics = voteService
				.getVoteTopicsByInitiator(loginUser.getEmail());
		mv.addObject("voteTopics", voteTopics);
		return mv;
	}

	@RequestMapping("/profile.do")
	public ModelAndView profileHandler() {
		ModelAndView mv = new ModelAndView("profile");
		return mv;
	}

	@RequestMapping("/friends.do")
	public ModelAndView friendsHandler() {
		ModelAndView mv = new ModelAndView("friends");
		return mv;
	}
}
