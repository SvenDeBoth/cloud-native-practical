package com.ezgroceries.shoppinglist;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.context.support.TestExecutionEvent;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import java.util.UUID;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@SpringJUnitConfig
@ComponentScan({ "com.ezgroceries", "config:" })
public class MockMvcTests {

	@Autowired
	private MockMvc mockMvc;


	@Test

	//@WithMockUser(username = "John",password = "John",roles = {"ADMIN"})
	//@WithMockUser(value="John")
	//@WithUserDetails("John")
	public void getCocktailListTest() throws Exception {
		int expectedNumberOfCocktails = 7;

		this.mockMvc //
				.perform(get("/cocktails?search=Russian").with(httpBasic("John","John")) //
						.accept(MediaType.parseMediaType("application/json"))) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(expectedNumberOfCocktails));
	}


	@Test
	public void should_CreateShoppingList_When_ValidRequest() throws Exception {

		mockMvc.perform(post("/shopping-lists").with(httpBasic("John","John"))
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\"name\": \"Elise's birthday\"}")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Elise's birthday"))
				.andExpect(jsonPath("$.shoppingListID").exists() )
				.andExpect(jsonPath("$.cocktails").exists() );
	}
// add coctails
@Test
//@WithUserDetails("John")
public void should_AddCocktails_When_ValidRequest() throws Exception {
	MvcResult result = mockMvc.perform(post("/shopping-lists").with(httpBasic("John","John"))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("{\"name\": \"Elise's birthday\"}")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated())
			.andReturn();

	String response = result.getResponse().getContentAsString();
	UUID newShoppingList = UUID.fromString(JsonPath.parse(response).read("$.shoppingListID").toString());
	System.out.println(newShoppingList);
			//		.andDo(MvcResult -> UUID newShopplingList = JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
	mockMvc.perform(post("/shopping-lists/"+ newShoppingList + "/cocktails").with(httpBasic("John","John"))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("[{\"cocktailId\":\"8209cc09-0dde-4964-af41-d6b80e1070fe\"},{\"cocktailId\":\"29e407d1-9084-4735-9d1f-8543cb4c4704\"}]")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$[0].cocktailId").exists())
			.andExpect(jsonPath("$[1].cocktailId").exists())
	.andExpect(jsonPath("$[0].cocktailId").isNotEmpty())
			.andExpect(jsonPath("$.name").doesNotExist());
}
// get a shopping list
@Test
public void should_GetIngedientsList_When_ValidRequest() throws Exception {
	MvcResult result = mockMvc.perform(post("/shopping-lists").with(httpBasic("John","John"))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("{\"name\": \"Elise's birthday\"}")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated())
			.andReturn();

	String response = result.getResponse().getContentAsString();
	UUID newShoppingList = UUID.fromString(JsonPath.parse(response).read("$.shoppingListID").toString());
	System.out.println(newShoppingList);
	//		.andDo(MvcResult -> UUID newShopplingList = JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
	mockMvc.perform(post("/shopping-lists/"+ newShoppingList + "/cocktails").with(httpBasic("John","John"))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("[{\"cocktailId\":\"29e407d1-9084-4735-9d1f-8543cb4c4704\"},{\"cocktailId\":\"ef486220-73f5-4e18-9a4d-36d6f2d6ca7f\"}]")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$[0].cocktailId").exists())
			.andExpect(jsonPath("$[1].cocktailId").exists())
			.andExpect(jsonPath("$[0].cocktailId").isNotEmpty())
			.andExpect(jsonPath("$.name").doesNotExist());
	mockMvc.perform(get("/shopping-lists/"+ newShoppingList ).with(httpBasic("John","John"))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
				//	.content("[{\"cocktailId\":\"e43e7792-92ac-4727-980b-33dd3508c86e\"},{\"cocktailId\":\"59ed7313-630b-4a75-9078-d36d27ddadf1\"}]")
					.accept(MediaType.APPLICATION_JSON_VALUE).secure(true))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.cocktailIngredients.*", hasSize(3)))
			.andExpect(jsonPath("$.shoppingListName").isNotEmpty())
			.andExpect(jsonPath("$.shoppingListID").isNotEmpty())
			.andExpect(jsonPath("$.name").doesNotExist());
}
	@Test
	public void should_GetAllIngedientsList_When_ValidRequest() throws Exception {
		MvcResult result = mockMvc.perform(post("/shopping-lists").with(httpBasic("John","John"))
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\"name\": \"Elise's birthday\"}")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andReturn();

		String response = result.getResponse().getContentAsString();
		UUID newShoppingList = UUID.fromString(JsonPath.parse(response).read("$.shoppingListID").toString());
		System.out.println(newShoppingList);
		//		.andDo(MvcResult -> UUID newShopplingList = JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
		mockMvc.perform(post("/shopping-lists/"+ newShoppingList + "/cocktails").with(httpBasic("John","John"))
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("[{\"cocktailId\":\"8209cc09-0dde-4964-af41-d6b80e1070fe\"},{\"cocktailId\":\"ef486220-73f5-4e18-9a4d-36d6f2d6ca7f\"}]")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$[0].cocktailId").exists())
				.andExpect(jsonPath("$[1].cocktailId").exists())
				.andExpect(jsonPath("$[0].cocktailId").isNotEmpty())
				.andExpect(jsonPath("$.name").doesNotExist());
		MvcResult result2 = mockMvc.perform(post("/shopping-lists").with(httpBasic("John","John"))
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\"name\": \"Sven's birthday\"}")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andReturn();

		String response2 = result2.getResponse().getContentAsString();
		UUID newShoppingList2 = UUID.fromString(JsonPath.parse(response2).read("$.shoppingListID").toString());
		System.out.println(newShoppingList);
		//		.andDo(MvcResult -> UUID newShopplingList = JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
		mockMvc.perform(post("/shopping-lists/"+ newShoppingList2 + "/cocktails").with(httpBasic("John","John"))
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("[{\"cocktailId\":\"8209cc09-0dde-4964-af41-d6b80e1070fe\"},{\"cocktailId\":\"5f7f02fe-092c-444f-b7e4-efc426a8c5d4\"}]")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$[0].cocktailId").exists())
				.andExpect(jsonPath("$[1].cocktailId").exists())
				.andExpect(jsonPath("$[0].cocktailId").isNotEmpty())
				.andExpect(jsonPath("$.name").doesNotExist());



		mockMvc.perform(get("/shopping-lists").with(httpBasic("John","John"))
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						//	.content("[{\"cocktailId\":\"23b3d85a-3928-41c0-a533-6538a71e17c4\"},{\"cocktailId\":\"d615ec78-fe93-467b-8d26-5d26d8eab073\"}]")
						.accept(MediaType.APPLICATION_JSON_VALUE).secure(true))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].cocktailIngredients.*", hasSize(3)))
				.andExpect(jsonPath("$[0].shoppingListName").isNotEmpty())
				.andExpect(jsonPath("$[0].shoppingListID").isNotEmpty())
				.andExpect(jsonPath("$[1].cocktailIngredients.*", hasSize(0)))
				.andExpect(jsonPath("$[1].shoppingListName").isNotEmpty())
				.andExpect(jsonPath("$[1].shoppingListID").isNotEmpty())
				.andExpect(jsonPath("$.name").doesNotExist());
	}


}
