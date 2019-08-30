package controllers;

import actions.ParametersRequired;
import com.google.inject.Inject;
import common.Response;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.account.AccountService;
import services.account.SignInParameters;
import services.account.SignUpParameters;

import java.util.concurrent.CompletionStage;

public class AccountController extends Controller {

    private AccountService accountService;

    @Inject
    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ParametersRequired(type = SignUpParameters.class)
    public CompletionStage<Result> signUp(Http.Request request) {
        // TODO: Refactor
        SignUpParameters parameters = (SignUpParameters) request.attrs().get(ParametersRequired.Attrs.PARAMETERS_TYPED_KEY);
        return Response.make(accountService.signUp(parameters));
    }

    @ParametersRequired(type = SignInParameters.class)
    public CompletionStage<Result> signIn(Http.Request request) {
        // TODO: Refactor
        SignInParameters parameters = (SignInParameters) request.attrs().get(ParametersRequired.Attrs.PARAMETERS_TYPED_KEY);
        return Response.make(accountService.signIn(parameters));
    }

}
