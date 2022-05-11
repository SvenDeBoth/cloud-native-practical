package com.ezgroceries.shoppinglist;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.UUID;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc

@ComponentScan({ "com.ezgroceries", "config:" })
public class MockMvcTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void getCocktailListTest() throws Exception {
		int expectedNumberOfAccounts = 2;
		
		this.mockMvc //
				.perform(get("/cocktails?search=Russian") //
						.accept(MediaType.parseMediaType("application/json"))) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(expectedNumberOfAccounts));
	}


	@Test
	public void should_CreateShoppingList_When_ValidRequest() throws Exception {

		mockMvc.perform(post("/shopping-lists")
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
public void should_AddCocktails_When_ValidRequest() throws Exception {
	MvcResult result = mockMvc.perform(post("/shopping-lists")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("{\"name\": \"Elise's birthday\"}")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated())
			.andReturn();

	String response = result.getResponse().getContentAsString();
	UUID newShoppingList = UUID.fromString(JsonPath.parse(response).read("$.shoppingListID").toString());
	System.out.println(newShoppingList);
			//		.andDo(MvcResult -> UUID newShopplingList = JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
	mockMvc.perform(post("/shopping-lists/"+ newShoppingList + "/cocktails")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("[{\"cocktailId\":\"23b3d85a-3928-41c0-a533-6538a71e17c4\"},{\"cocktailId\":\"d615ec78-fe93-467b-8d26-5d26d8eab073\"}]")
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
	MvcResult result = mockMvc.perform(post("/shopping-lists")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("{\"name\": \"Elise's birthday\"}")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated())
			.andReturn();

	String response = result.getResponse().getContentAsString();
	UUID newShoppingList = UUID.fromString(JsonPath.parse(response).read("$.shoppingListID").toString());
	System.out.println(newShoppingList);
	//		.andDo(MvcResult -> UUID newShopplingList = JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
	mockMvc.perform(post("/shopping-lists/"+ newShoppingList + "/cocktails")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content("[{\"cocktailId\":\"23b3d85a-3928-41c0-a533-6538a71e17c4\"},{\"cocktailId\":\"d615ec78-fe93-467b-8d26-5d26d8eab073\"}]")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$[0].cocktailId").exists())
			.andExpect(jsonPath("$[1].cocktailId").exists())
			.andExpect(jsonPath("$[0].cocktailId").isNotEmpty())
			.andExpect(jsonPath("$.name").doesNotExist());
	mockMvc.perform(get("/shopping-lists/"+ newShoppingList )
					.contentType(MediaType.APPLICATION_JSON_VALUE)
				//	.content("[{\"cocktailId\":\"23b3d85a-3928-41c0-a533-6538a71e17c4\"},{\"cocktailId\":\"d615ec78-fe93-467b-8d26-5d26d8eab073\"}]")
					.accept(MediaType.APPLICATION_JSON_VALUE).secure(true))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.cocktailIngredients.*", hasSize(8)))
			.andExpect(jsonPath("$.shoppingListName").isNotEmpty())
			.andExpect(jsonPath("$.shoppingListID").isNotEmpty())
			.andExpect(jsonPath("$.name").doesNotExist());
}
	@Test
	public void should_GetAllIngedientsList_When_ValidRequest() throws Exception {
		MvcResult result = mockMvc.perform(post("/shopping-lists")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\"name\": \"Elise's birthday\"}")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andReturn();

		String response = result.getResponse().getContentAsString();
		UUID newShoppingList = UUID.fromString(JsonPath.parse(response).read("$.shoppingListID").toString());
		System.out.println(newShoppingList);
		//		.andDo(MvcResult -> UUID newShopplingList = JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
		mockMvc.perform(post("/shopping-lists/"+ newShoppingList + "/cocktails")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("[{\"cocktailId\":\"23b3d85a-3928-41c0-a533-6538a71e17c4\"},{\"cocktailId\":\"d615ec78-fe93-467b-8d26-5d26d8eab073\"}]")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$[0].cocktailId").exists())
				.andExpect(jsonPath("$[1].cocktailId").exists())
				.andExpect(jsonPath("$[0].cocktailId").isNotEmpty())
				.andExpect(jsonPath("$.name").doesNotExist());
		MvcResult result2 = mockMvc.perform(post("/shopping-lists")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\"name\": \"Sven's birthday\"}")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andReturn();

		String response2 = result2.getResponse().getContentAsString();
		UUID newShoppingList2 = UUID.fromString(JsonPath.parse(response2).read("$.shoppingListID").toString());
		System.out.println(newShoppingList);
		//		.andDo(MvcResult -> UUID newShopplingList = JsonPath.read(result.getResponse().getContentAsString(), "$.id"))
		mockMvc.perform(post("/shopping-lists/"+ newShoppingList2 + "/cocktails")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("[{\"cocktailId\":\"23b3d85a-3928-41c0-a533-6538a71e17c4\"},{\"cocktailId\":\"d615ec78-fe93-467b-8d26-5d26d8eab073\"}]")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$[0].cocktailId").exists())
				.andExpect(jsonPath("$[1].cocktailId").exists())
				.andExpect(jsonPath("$[0].cocktailId").isNotEmpty())
				.andExpect(jsonPath("$.name").doesNotExist());



		mockMvc.perform(get("/shopping-lists")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						//	.content("[{\"cocktailId\":\"23b3d85a-3928-41c0-a533-6538a71e17c4\"},{\"cocktailId\":\"d615ec78-fe93-467b-8d26-5d26d8eab073\"}]")
						.accept(MediaType.APPLICATION_JSON_VALUE).secure(true))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].cocktailIngredients.*", hasSize(8)))
				.andExpect(jsonPath("$[0].shoppingListName").isNotEmpty())
				.andExpect(jsonPath("$[0].shoppingListID").isNotEmpty())
				.andExpect(jsonPath("$[1].cocktailIngredients.*", hasSize(8)))
				.andExpect(jsonPath("$[1].shoppingListName").isNotEmpty())
				.andExpect(jsonPath("$[1].shoppingListID").isNotEmpty())
				.andExpect(jsonPath("$.name").doesNotExist());
	}


}
