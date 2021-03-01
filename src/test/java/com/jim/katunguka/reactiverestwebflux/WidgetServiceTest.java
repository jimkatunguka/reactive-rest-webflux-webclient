package com.jim.katunguka.reactiverestwebflux;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jim.katunguka.reactiverestwebflux.model.Widget;
import com.jim.katunguka.reactiverestwebflux.service.WidgetService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WidgetServiceTest {
	private static MockWebServer mockWebServer;
	private static WidgetService widgetService;

	@BeforeAll
	static void beforeAll() throws IOException {
		mockWebServer = new MockWebServer();
		mockWebServer.start();
	}

	@BeforeEach
	void init(){
		widgetService = new WidgetService(WebClient.builder().build(), mockWebServer.url("/widgets/2").url());
	}

	@AfterAll
	static  void afterAll() throws IOException {
		mockWebServer.shutdown();
	}

	@Test
	void widgetServiceCallIsSuccessful() throws Exception {

		mockWebServer.enqueue(
				new MockResponse()
				.setResponseCode(200)
				.setBody(defaultResponseString())
				.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		);

		String expected = "my second";
		String actual = widgetService.getDescription(2);

		//assertTrue(widgetService.getDescription(2).equals("my second"));
		assertEquals(actual, expected);
		assertEquals("GET", mockWebServer.takeRequest().getMethod());
	}

	@Test
	void testGetMethod() throws Exception {
		Widget widget = new Widget(1, "my widget", "my number one widget", 1);
		mockWebServer.enqueue(new MockResponse()
				.setResponseCode(200)
		.setBody(new ObjectMapper().writeValueAsString(widget))
		.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

		String expected = "my number one widget";
		String actual = widgetService.getDescription(1);

		assertEquals(expected, actual);
		assertEquals("GET", mockWebServer.takeRequest().getMethod());
		//assertEquals("http://localhost:8087/widgets/1", mockWebServer.takeRequest().getPath());
	}

	private Widget defaultRequest(){
		return new Widget(2, "second","my second", 22);
	}

	private String defaultResponseString(){
		return "{\n" +
				"    \"id\": 2,\n" +
				"    \"name\": \"second\",\n" +
				"    \"description\": \"my second\",\n" +
				"    \"version_number\": 22\n" +
				"}";
	}
}
