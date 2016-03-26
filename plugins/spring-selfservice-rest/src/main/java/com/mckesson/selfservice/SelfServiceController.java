package com.mckesson.selfservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mckesson.ldap.Person;
import com.mckesson.ldap.PersonDao;
import com.utils.MailService;


//			-------------------------------------------------------
//			POST
//			https://access.stage.iam.mckesson.com/idm/mhs2/endpoint/restore?_action=securityQuestionsForUserName
//			
//			REQUEST
//			HEADER: uid, email
//			
//			RESPONSE
//			HEADER: result - true or false, error, securityQuestions
//			
//			-------------------------------------------------------
//			POST
//			https://access.stage.iam.mckesson.com/idm/mhs2/endpoint/restore?_action=checkSecurityAnswersForUserName
//			
//			REQUEST
//			HEADER: uid, email, securityAnswers
//			
//			RESPONSE
//			HEADER: result - true or false, error
//			
//			-------------------------------------------------------
//			POST
//			https://access.stage.iam.mckesson.com/idm/mhs2/endpoint/restore?_action=restoreUserId
//			
//			REQUEST
//			HEADER: email, givenName
//			
//			RESPONSE
//			HEADER: result - true or false, error

@Component
@RestController
public class SelfServiceController {
	// Actions
	public static final String ACTION_SECURITY_QUESTIONS_FOR_USER_NAME = "securityQuestionsForUserName";
	public static final String ACTION_CHECK_SECURITY_ANSWERS_FOR_USER_NAME = "checkSecurityAnswersForUserName";
	public static final String ACTION_RESTORE_USER_ID = "restoreUserId";
	
	// Header fields
	public static final String HEADER_RESULT = "result";
	public static final String HEADER_ERROR = "error";
	public static final String HEADER_UID = "uid";
	public static final String HEADER_EMAIL = "email";
	public static final String HEADER_GIVEN_NAME = "givenName";
	public static final String HEADER_SECURITY_QUESTIONS = "securityQuestions";
	public static final String HEADER_SECURITY_ANSWERS = "securityAnswers";
	
	private PersonDao userService;
	private MailService mailService;
	private SecurityQuestionsAccess mySQLService;
	
	@Autowired
	public SelfServiceController(MailService mailService, PersonDao userService, SecurityQuestionsAccess mySQLAccess){
		this.mailService = mailService;
		this.userService = userService;
		this.mySQLService = mySQLAccess;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/idm/mhs2/endpoint/restore", produces = "application/json") 
    public @ResponseBody List<String> responseOnAction(@RequestParam("_action") String action, HttpServletRequest request, HttpServletResponse response){
		if(action.equalsIgnoreCase(ACTION_SECURITY_QUESTIONS_FOR_USER_NAME)){
			String userId = request.getHeader(HEADER_UID);
			String email = request.getHeader(HEADER_EMAIL);
			
			Person user = userService.findByUserId(userId);
			if(user != null){
			
				// TODO validate userId and email here
			
				List<String> questions = mySQLService.getSecurityQuestions(userId, email);
				if(questions != null){
					response.setStatus(200);
					return questions;
				}
			}
			response.setStatus(500);
			return Collections.EMPTY_LIST;
		}else if(action.equalsIgnoreCase(ACTION_CHECK_SECURITY_ANSWERS_FOR_USER_NAME)){
			String userId = request.getHeader(HEADER_UID);
			String email = request.getHeader(HEADER_EMAIL);
			Person user = userService.findByUserId(userId);
			if(user == null){
				response.setStatus(500);
				return Collections.EMPTY_LIST;
			}
			
			ArrayList<String> answers = new ArrayList<String>();
			for(int index=1; index < 4; index++){
				String answer = request.getHeader("answer"+index);
				if(answer != null){
					answers.add(answer);
				}
			}
			if(answers.size() != 3){
				response.setStatus(500);
				return Collections.EMPTY_LIST;
			}
			
			// TODO validate userId, email and securityAnswers here
			
			boolean result = mySQLService.checkSecurityAnswers(userId, email, answers);
			
			if(result){
				String newPassword = PasswordGenerator.randomString(10);
				
				// changing password in LDAP
				
				user.setPassword(newPassword);
				userService.update(user);
				
				mailService.sendMail("testJavaMail2000@gmail.com", email, "SECURE: McKesson Identity Management Portal - your password was reset",
			        	user.getFullName()+",\n\n"+
			        	"Your McKesson Identity Management Portal password has been successfully reset. Your new password is: "+newPassword+"\n"+
			        	"Please navigate to McKesson Identity Management Portal URL https://access.stage.iam.mckesson.com/idm/mhs2/ui/ using your browser of choice, and log in using new password. You will be prompted to change the password upon successful login – please follow the instructions provided by the site. After you’ve changed your password McKesson Identity Management Portal you will be able to access McKesson applications.\n"+
			        	"If you need to make any changes, please use URL https://access.stage.iam.mckesson.com/idm/mhs2/ui/ or contact your System Administrator.\n"+
			        	"Confidentiality Notice: This email message, including any attachments, is for the sole use of the intended recipients and may contain confidential and/or privileged information. Any unauthorized review, use, disclosure or distribution is prohibited. If you are not the intended recipient, please contact the sender by reply email, delete this message and destroy copies thereof.\n"
				);
				
				response.setStatus(200);
				return Collections.EMPTY_LIST;
			}else{
				response.setStatus(500);
				return Collections.EMPTY_LIST;
			}
		}else if(action.equalsIgnoreCase(ACTION_RESTORE_USER_ID)){
			String email = request.getHeader(HEADER_EMAIL);
			String givenName = request.getHeader(HEADER_GIVEN_NAME);
			
			String userId = mySQLService.getUserId(email, givenName);
			if(userId != null){
				Person user = userService.findByUserId(userId);
				if(user != null){
					
					mailService.sendMail("testJavaMail2000@gmail.com", email, "SECURE: McKesson Identity Management Portal - your User ID",
							user.getFullName()+",\n\n"+
			        		"Your McKesson Identity Management Portal User ID is: "+userId+"\n"+
			        		"Confidentiality Notice: This email message, including any attachments, is for the sole use of the intended recipients and may contain confidential and/or privileged information. Any unauthorized review, use, disclosure or distribution is prohibited. If you are not the intended recipient, please contact the sender by reply email, delete this message and destroy copies thereof."
					);
				
					response.setStatus(200);
					return Collections.EMPTY_LIST;
				}
			}
			response.setStatus(500);
			return Collections.EMPTY_LIST;
		}

        return Collections.EMPTY_LIST;
    }
	
//	@RequestMapping("/spring-selfservice-rest")
//    public String greeting() {
//		StringBuffer buffer = new StringBuffer();
//		buffer.append("<html>");
//		buffer.append("<body>");
//		buffer.append("<h1>Welcome to McKesson</h1>");
//		buffer.append("<h2>Security Questions:</h2>");
//		
//		buffer.append("<ul>");
//		List<String> questions = SecretQuestions.getSecretAllQuestions();
//		for(String question : questions){
//			buffer.append("<li>"+question+"</li>");
//		}
//		buffer.append("</ul>");
//		
//		buffer.append("</body>");
//		buffer.append("</html>");
//		
//		return buffer.toString();
//    }
}
