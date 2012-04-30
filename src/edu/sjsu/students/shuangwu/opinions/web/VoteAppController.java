package edu.sjsu.students.shuangwu.opinions.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import edu.sjsu.students.shuangwu.opinions.domain.Gender;
import edu.sjsu.students.shuangwu.opinions.domain.User;
import edu.sjsu.students.shuangwu.opinions.domain.VoteOption;
import edu.sjsu.students.shuangwu.opinions.domain.VoteTopic;
import edu.sjsu.students.shuangwu.opinions.service.UserService;
import edu.sjsu.students.shuangwu.opinions.service.VoteService;

@Controller
@SessionAttributes("loginUser")
public class VoteAppController {
	private static final Logger LOGGER = Logger
			.getLogger(VoteAppController.class);

	private final VoteService voteService;
	private final UserService userService;

	public VoteAppController(VoteService voteService, UserService userService) {
		this.voteService = voteService;
		this.userService = userService;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView loginHandler() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginHandler(String userKey) {
		ModelAndView mv = new ModelAndView("index");
		User loginUser = userService.getUser(userKey);
		if (loginUser != null) {
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
		return mv;
	}

	@RequestMapping("/index.do")
	public ModelAndView indexHandler() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public ModelAndView registerHandler() {
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public ModelAndView registerHandler(String email, String name, String gender) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setGender(Gender.valueOf(gender));
		User newUser = userService.createUser(user);
		if (newUser != null) { // create ok
			return new ModelAndView("redirect:/login.do?from=new_user");
		} else { // failed
			return new ModelAndView(
					"redirect:/register.do?from=duplicated_user");
		}
	}

	@RequestMapping(value = "/ask.do", method = RequestMethod.GET)
	public ModelAndView askHandler(@ModelAttribute("loginUser") User loginUser) {
		ModelAndView mv = new ModelAndView("ask");
		List<VoteTopic> voteTopics = userService.getUser(loginUser.getEmail())
				.getVoteTopics();
		mv.addObject("voteTopics", voteTopics);
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
		voteService.createVote(v, loginUser.getEmail());
		List<VoteTopic> voteTopics = userService.getUser(loginUser.getEmail())
				.getVoteTopics();
		mv.addObject("voteTopics", voteTopics);
		return mv;
	}

	@RequestMapping("/vote.do")
	public ModelAndView voteHandler() {
		ModelAndView mv = new ModelAndView("vote");
		mv.addObject("visitorName", "Shuang <i>Wu</i>");
		return mv;
	}

	@RequestMapping("/results.do")
	public ModelAndView resultsHandler() {
		ModelAndView mv = new ModelAndView("result");
		mv.addObject("visitorName", "Shuang <i>Wu</i>");
		return mv;
	}

	@RequestMapping("/profile.do")
	public ModelAndView profileHandler() {
		ModelAndView mv = new ModelAndView("profile");
		mv.addObject("visitorName", "Shuang <i>Wu</i>");
		return mv;
	}

	@RequestMapping("/friends.do")
	public ModelAndView friendsHandler() {
		ModelAndView mv = new ModelAndView("friends");
		mv.addObject("visitorName", "Shuang <i>Wu</i>");
		return mv;
	}
}
