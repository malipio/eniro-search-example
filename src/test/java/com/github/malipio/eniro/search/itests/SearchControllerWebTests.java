package com.github.malipio.eniro.search.itests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.malipio.eniro.search.EniroSearchExampleApplication;

/**
 * A Basic Spring MVC Test for Search Controller
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = EniroSearchExampleApplication.class)
public class SearchControllerWebTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void shouldGetHome() throws Exception {
		this.mockMvc.perform(get("/enirotest")).andExpect(status().isOk());
	}

	@Test
	public void shouldPostSearchForm() throws Exception {
		this.mockMvc.perform(post("/enirotest")
				.param("searchWords", "a,b,c")
				.param("regexpFilter", "."))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldPostSearchFormWithInvalidData() throws Exception {
		this.mockMvc.perform(post("/enirotest")
				.param("searchWords", "")
				.param("regexpFilter", ""))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldPostSearchFormWithInvalidRegexp() throws Exception {
		this.mockMvc.perform(post("/enirotest")
				.param("searchWords", "aaa")
				.param("regexpFilter", "[0-"))
				.andExpect(status().isOk());
	}

}
