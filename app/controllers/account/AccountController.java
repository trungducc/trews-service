package controllers.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import common.Response;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.AccountService;

import java.util.concurrent.CompletionStage;

public class AccountController extends Controller {

	private AccountService accountService;

	@Inject
	AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	public CompletionStage<Result> signUp(Http.Request request) {
		// TODO: Refactor.
		//  Ideas:
		//  - Use other type of Request which has parameters as a property
		//  - Common method to extract parameters object from body
		JsonNode bodyJson = request.body().asJson();
		SignUpParameters parameters = Json.fromJson(bodyJson, SignUpParameters.class);

		// TODO: Use common method to jsonify, add context.
		return Response.make(accountService.signUp(parameters));
	}

	public CompletionStage<Result> signIn(Http.Request request) {
		// TODO: Refactor
		JsonNode bodyJson = request.body().asJson();
		SignInParameters parameters = Json.fromJson(bodyJson, SignInParameters.class);

		return Response.make(accountService.signIn(parameters));
	}

}
