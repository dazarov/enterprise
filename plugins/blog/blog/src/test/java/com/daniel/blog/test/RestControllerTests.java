package com.daniel.blog.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.daniel.blog.dao.PostDAO;
import com.daniel.blog.model.Post;
import com.daniel.blog.test.configs.AOPTestConfig;
import com.daniel.blog.test.configs.MockDAOTestConfig;
import com.daniel.blog.test.configs.WebTestConfig;
import com.daniel.blog.test.model.PostBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MockDAOTestConfig.class, WebTestConfig.class, AOPTestConfig.class})
@WebAppConfiguration
public class RestControllerTests {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private PostDAO postDAOMock;
	
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
    public void testPostCreation() throws Exception {
        Post post1 = new PostBuilder()
        		.id(1L)
                .description("description")
                .subject("subject")
                .body("body")
                .build();
        Post post2 = new PostBuilder()
        		.id(2L)
                .description("description")
                .subject("subject")
                .body("body")
                .build();
        
        Mockito.when(postDAOMock.list(0,10)).thenReturn(Arrays.asList(post1,post2));
 
 
        mockMvc.perform(get("/posts?_start=0&_number=10"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
	        .andExpect(jsonPath("$", Matchers.hasSize(2)))
	        .andExpect(jsonPath("$[0].id", Matchers.is(1)))
	        .andExpect(jsonPath("$[0].description", Matchers.is("description")))
	        .andExpect(jsonPath("$[0].body", Matchers.is("body")))
	        .andExpect(jsonPath("$[0].subject", Matchers.is("subject")))
			.andExpect(jsonPath("$[1].id", Matchers.is(2)))
			.andExpect(jsonPath("$[1].description", Matchers.is("description")))
			.andExpect(jsonPath("$[1].body", Matchers.is("body")))
			.andExpect(jsonPath("$[1].subject", Matchers.is("subject")));

 
    }
}
