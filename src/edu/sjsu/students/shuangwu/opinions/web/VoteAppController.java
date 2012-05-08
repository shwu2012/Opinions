package edu.sjsu.students.shuangwu.opinions.web;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import edu.sjsu.students.shuangwu.opinions.domain.Gender;
import edu.sjsu.students.shuangwu.opinions.domain.LoginType;
import edu.sjsu.students.shuangwu.opinions.domain.User;
import edu.sjsu.students.shuangwu.opinions.domain.VoteAction;
import edu.sjsu.students.shuangwu.opinions.domain.VoteOption;
import edu.sjsu.students.shuangwu.opinions.domain.VoteStatus;
import edu.sjsu.students.shuangwu.opinions.domain.VoteTopic;
import edu.sjsu.students.shuangwu.opinions.service.UserService;
import edu.sjsu.students.shuangwu.opinions.service.VoteService;
import edu.sjsu.students.shuangwu.opinions.web.json.FacebookUserInfo;

@Controller
@SessionAttributes("loginUser")
public class VoteAppController {
	private static final Logger LOGGER = Logger
			.getLogger(VoteAppController.class);

	private VoteService voteService;
	private UserService userService;
	private OAuthService facebookOAuthService;
	private String fbApiKey;
	private String fbApiSecret;
	private String fbLoginCallbackUrl;

	@PostConstruct
	public void init() {
		LOGGER.info("Building FB-oauth service...");
		LOGGER.info("fbApiKey: " + fbApiKey);
		LOGGER.info("fbApiSecret: " + fbApiSecret);
		LOGGER.info("fbLoginCallbackUrl: " + fbLoginCallbackUrl);
		facebookOAuthService = new ServiceBuilder().provider(FacebookApi.class)
				.apiKey(fbApiKey).apiSecret(fbApiSecret)
				.callback(fbLoginCallbackUrl).build();
		LOGGER.info("FB-oauth service is set up.");
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		LOGGER.info("Building WebDataBinder...");
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(
				CustomBooleanEditor.VALUE_TRUE,
				CustomBooleanEditor.VALUE_FALSE, true));
		LOGGER.info("WebDataBinder is set up");
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginHandler(String returnUrl, Boolean autoLogin,
			ModelMap modelMap) {
		// Token requestToken = oAuthService.getRequestToken();
		// facebook doesn't require a request-token
		String fbLoginUrl = facebookOAuthService.getAuthorizationUrl(null);
		// set required facebook permissions to post on walls
		fbLoginUrl += "&scope=publish_stream";
		if (!Strings.isNullOrEmpty(returnUrl)) {
			// append the return-url as the "state" parameter so that we can get
			// this parameter in the facebook-callback-handler
			fbLoginUrl += ("&state=" + URLEncoder.encode(returnUrl));
		}
		LOGGER.info("authUrl=" + fbLoginUrl);
		if ((autoLogin != null) && autoLogin.booleanValue()) {
			return "redirect:" + fbLoginUrl;
		} else {
			modelMap.addAttribute("fbLoginUrl", fbLoginUrl);
			return "login";
		}
	}

	/**
	 * The callback URL to get the code assigned from Facebook
	 * 
	 * @param code
	 *            The code which we will use to exchange for the access-token
	 * @return
	 */
	@RequestMapping(value = "/facebookLogin.do", method = RequestMethod.GET)
	public String facebookLoginHandler(String code, String state,
			ModelMap modelMap) {
		LOGGER.info("code from facebook: " + code);
		Verifier verifier = new Verifier(code);
		Token accessToken = facebookOAuthService.getAccessToken(null, verifier);
		LOGGER.info("get fb user secret: " + accessToken.getSecret());
		LOGGER.info("get fb user token: " + accessToken.getToken());
		// get logged-in fb user's information
		OAuthRequest request = new OAuthRequest(Verb.GET,
				"https://graph.facebook.com/me");
		facebookOAuthService.signRequest(accessToken, request);
		Response response = request.send();
		String responseContent = response.getBody();
		Gson gson = new Gson();
		FacebookUserInfo fbUserInfo = gson.fromJson(responseContent,
				FacebookUserInfo.class);

		User user = new User();
		user.setUserId(LoginType.FACEBOOK.makeUserKey(fbUserInfo.getId()));
		user.setExternalId(fbUserInfo.getId());
		user.setLoginType(LoginType.FACEBOOK);
		user.setName(fbUserInfo.getName());
		user.setGender(Gender.valueOf(fbUserInfo.getGender().toUpperCase()));
		user.setAccessToken(accessToken.getToken());

		User loginUser = userService.getOrCreateUser(user);
		modelMap.addAttribute("loginUser", loginUser); // add to session
		if (!Strings.isNullOrEmpty(state)) {
			LOGGER.info("get returnUrl from fb: " + state);
			return "redirect:" + state;
		} else {
			return "redirect:/question.do";
		}
	}

	@RequestMapping("/logout.do")
	public ModelAndView logoutHandler(
			@ModelAttribute("loginUser") User loginUser, SessionStatus status) {
		ModelAndView mv = new ModelAndView("logout");
		mv.addObject("logoutUserName", loginUser.getName());
		status.setComplete(); // clean the session
		mv.addObject("loginUser", null);
		mv.addObject("fbApiKey", fbApiKey);
		mv.addObject("loginUrl", "/login.do");
		return mv;
	}

	@RequestMapping(value = "/ask.do", method = RequestMethod.GET)
	public ModelAndView askHandler(@ModelAttribute("loginUser") User loginUser) {
		ModelAndView mv = new ModelAndView("ask");
		return mv;
	}

	@RequestMapping(value = "/ask.do", method = RequestMethod.POST)
	public ModelAndView askHandler(@ModelAttribute("loginUser") User loginUser,
			String title, String description, String imageBlobId,
			String[] options) {
		ModelAndView mv = new ModelAndView("ask_done");
		VoteTopic v = new VoteTopic();
		v.setText(title);
		v.setDescription(description);
		v.setImageBlobId(imageBlobId);
		List<VoteOption> optionList = Lists.newArrayList();
		for (String optionText : options) {
			if (!optionText.trim().equals("")) {
				VoteOption voteOption = new VoteOption();
				voteOption.setText(optionText);
				optionList.add(voteOption);
			}
		}
		v.setOptions(optionList);
		v.setStatus(VoteStatus.ACTIVE);
		v = voteService.createVoteTopic(v, loginUser.getUserId());
		mv.addObject("voteTopic", v);

		if (v != null) {
			OAuthRequest request = new OAuthRequest(Verb.POST,
					"https://graph.facebook.com/me/feed");
			request.addBodyParameter("message",
					"http://localhost:8888/question.do?id=" + v.getEncodedKey());
			facebookOAuthService
					.signRequest(new Token(loginUser.getAccessToken(),
							fbApiSecret), request);
			request.send();
		}
		return mv;
	}

	@RequestMapping("/results.do")
	public ModelAndView resultsHandler(
			@ModelAttribute("loginUser") User loginUser) {
		ModelAndView mv = new ModelAndView("results");
		List<VoteTopic> voteTopics = voteService
				.getVoteTopicsByInitiator(loginUser.getUserId());
		mv.addObject("voteTopics", voteTopics);
		return mv;
	}

	@RequestMapping(value = "/vote.do", method = RequestMethod.POST)
	public String voteHandler(@ModelAttribute("loginUser") User loginUser,
			String id, String[] optionIds, ModelMap modelMap) {
		LOGGER.info("vote-topic-id=" + id);
		LOGGER.info("vote-option-ids=" + Arrays.toString(optionIds));
		if (optionIds != null) {
			for (String voteOptionEncodedId : optionIds) {
				VoteAction voteAction = new VoteAction();
				voteAction.setCreateTimestamp(new Date().getTime());
				voteService.createVoteAction(voteAction, voteOptionEncodedId);
			}
		}
		modelMap.addAttribute("voteTopicEncodedKey", id);
		return "vote_done";
	}

	@RequestMapping(value = "/question.do", method = RequestMethod.GET)
	public String questionHandler(String id, Boolean view, ModelMap modelMap) {
		if (Strings.isNullOrEmpty(id)) {
			VoteTopic voteTopic = voteService.getRandomVoteTopic();
			modelMap.addAttribute("voteTopic", voteTopic);
		} else {
			VoteTopic voteTopic = voteService.getVoteTopicById(id);
			modelMap.addAttribute("voteTopic", voteTopic);
			modelMap.addAttribute("readonly",
					(view != null) && (view.booleanValue()));
		}
		return "question";
	}

	@RequestMapping("/facebookAppCanvas.do")
	public String facebookAppCanvasHandler(Boolean redirect, ModelMap modelMap) {
		modelMap.addAttribute("autoRedirect",
				(redirect != null) && redirect.booleanValue());
		return "facebookAppCanvas";
	}

	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setFbApiKey(String fbApiKey) {
		this.fbApiKey = fbApiKey;
	}

	public void setFbApiSecret(String fbApiSecret) {
		this.fbApiSecret = fbApiSecret;
	}

	public void setFbLoginCallbackUrl(String fbLoginCallbackUrl) {
		this.fbLoginCallbackUrl = fbLoginCallbackUrl;
	}
}
