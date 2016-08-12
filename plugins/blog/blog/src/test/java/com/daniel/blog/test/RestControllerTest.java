package com.daniel.blog.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.daniel.blog.test.model.PostBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MockDAOTestConfiguration.class, WebTestConfiguration.class})
@WebAppConfiguration
public class RestControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	//@Autowired
	//PostsRestController postController;
	
	@Autowired
	private PostDAO postDAOMock;
	
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		//MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
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
        
        when(postDAOMock.list(0,10)).thenReturn(Arrays.asList(post1,post2));
 
 
        mockMvc.perform(get("/posts?_start=0&_number=10"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
	        .andExpect(jsonPath("$", hasSize(2)))
	        .andExpect(jsonPath("$[0].id", is(1)))
	        .andExpect(jsonPath("$[0].description", is("description")))
	        .andExpect(jsonPath("$[0].body", is("body")))
	        .andExpect(jsonPath("$[0].subject", is("subject")))
			.andExpect(jsonPath("$[1].id", is(2)))
			.andExpect(jsonPath("$[1].description", is("description")))
			.andExpect(jsonPath("$[1].body", is("body")))
			.andExpect(jsonPath("$[1].subject", is("subject")));

 
    }
}
