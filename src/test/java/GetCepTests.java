import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

import java.io.InputStream;

class GetCepTests {
	InputStream schema = getClass().getClassLoader().getResourceAsStream("viacep.json");

	@BeforeEach
	void setUp() throws Exception{
		RestAssured.baseURI = "https://viacep.com.br/ws";
	}

	@Tag("Sucesso")
	@Test
	void getCepValidoCompletoTest() {
		String cep = "36026040";
		int statusCodeEsperado = HttpStatus.SC_OK;



		Response response = given()
					.when()
					.get(cep + "/json")
					.then()
					.statusCode(statusCodeEsperado)
					.contentType("application/json")
				.assertThat()
					.body(JsonSchemaValidator.matchesJsonSchema(schema))
					.extract()
					.response();

		String bodyCep = response.jsonPath().getString("cep");
		String logradouro = response.jsonPath().getString("logradouro");
		String complemento = response.jsonPath().getString("complemento");
		String bairro = response.jsonPath().getString("bairro");
		String localidade = response.jsonPath().getString("localidade");
		String uf = response.jsonPath().getString("uf");
		String ibge = response.jsonPath().getString("ibge");
		String gia = response.jsonPath().getString("gia");
		String ddd = response.jsonPath().getString("ddd");
		String siafi = response.jsonPath().getString("siafi");

		assertEquals(cep.substring(0,5) + "-" + cep.substring(5), bodyCep);
		assertEquals("Rua Rodrigo Lemos de Paula", logradouro);
		assertEquals("", complemento);
		assertEquals("Santa Cecília", bairro);
		assertEquals("Juiz de Fora", localidade);
		assertEquals("MG", uf);
		assertEquals("3136702", ibge);
		assertEquals("", gia);
		assertEquals("32", ddd);
		assertEquals("4733", siafi);
	}

	@Tag("Sucesso")
	@Test
	void getCepValidoIncompletoTest() {
		String cep = "36788000";
		int statusCodeEsperado = HttpStatus.SC_OK;

		Response response = given()
				.when()
				.get(cep + "/json")
				.then()
				.statusCode(statusCodeEsperado)
				.contentType("application/json")
			.assertThat()
				.body(JsonSchemaValidator.matchesJsonSchema(schema))
				.extract()
				.response();

		String bodyCep = response.jsonPath().getString("cep");
		String logradouro = response.jsonPath().getString("logradouro");
		String complemento = response.jsonPath().getString("complemento");
		String bairro = response.jsonPath().getString("bairro");
		String localidade = response.jsonPath().getString("localidade");
		String uf = response.jsonPath().getString("uf");
		String ibge = response.jsonPath().getString("ibge");
		String gia = response.jsonPath().getString("gia");
		String ddd = response.jsonPath().getString("ddd");
		String siafi = response.jsonPath().getString("siafi");

		assertEquals(cep.substring(0,5) + "-" + cep.substring(5), bodyCep);
		assertEquals("", logradouro);
		assertEquals("", complemento);
		assertEquals("", bairro);
		assertEquals("Itamarati de Minas", localidade);
		assertEquals("MG", uf);
		assertEquals("3132602", ibge);
		assertEquals("", gia);
		assertEquals("32", ddd);
		assertEquals("4651", siafi);
	}

	@Tag("Excecao")
	@Test
	void getCepInexistenteTest() {
		String cep = "00000000";
		int statusCodeEsperado = HttpStatus.SC_OK;

		Response response = given()
				.when()
				.get(cep + "/json")
				.then()
				.statusCode(statusCodeEsperado)
				.contentType("application/json")
				.extract()
				.response();

		Boolean erro = response.jsonPath().getBoolean("erro");

		assertEquals(true, erro);
	}

	@Tag("Excecao")
	@Test
	void getCepInvalidoTest() {
		String cep = "0000000";
		int statusCodeEsperado = HttpStatus.SC_BAD_REQUEST;

		given()
				.when()
				.get(cep + "/json")
				.then()
				.statusCode(statusCodeEsperado)
				.contentType("text/html; charset=utf-8");
	}
}
