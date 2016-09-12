package com.daniel.blog.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorRestController {
	@RequestMapping(method = RequestMethod.GET, value = "/error", produces = MediaType.TEXT_HTML_VALUE) 
    public ResponseEntity<String> getErrorPage(){
		StringBuilder builder = new StringBuilder();
		builder
		.append("<html>")
		.append("<head></head>")
		.append("<body>")
		.append("<strong>Requested Page is not found!</strong>")
		.append("<br><br>")
		.append("<a href=\"/index.html\">Go to Home Page</a>")
		.append("</body>")
		.append("</html>");
        return new ResponseEntity<String>(builder.toString(), HttpStatus.OK);
	}
}
