package actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import common.Parameters;
import common.exception.TrewsError;
import common.exception.TrewsException;
import models.Account;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import services.account.AccountService;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class ParametersRequiredAction extends Action<ParametersRequired> {

    private static final String PARAMETERS_BEARER_FIELD = "bearer";

    private static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";

    private static final String REQUEST_HEADER_BEARER = "Bearer ";

    @Inject
    AccountService accountService;

    @Override
    public CompletionStage<Result> call(Http.Request request) {
        // TODO: Handle throw
        Account bearer = getBearer(request);
        Parameters parameters = getParameters(request, bearer);
        return delegate.call(request.addAttr(ParametersRequired.Attrs.PARAMETERS_TYPED_KEY, parameters));
    }

    private Account getBearer(Http.Request request) {
        boolean bearerRequired = false;
        try {
            bearerRequired = configuration.type().getDeclaredField(PARAMETERS_BEARER_FIELD) != null;
        } catch (Exception e) {
        }

        if (!bearerRequired) {
            return null;
        }

        Optional<String> authHeader =  request.getHeaders().get(REQUEST_HEADER_AUTHORIZATION);
        if (!authHeader.filter(ah -> ah.contains(REQUEST_HEADER_BEARER)).isPresent()) {
            throw new TrewsException(TrewsError.MISSING_TOKEN_ERROR);
        }
        String token = authHeader.map(ah -> ah.replace(REQUEST_HEADER_BEARER, "")).orElse("");

        Account bearer = accountService.verifyToken(token);
        if (bearer == null) {
            throw new TrewsException(TrewsError.INVALID_TOKEN_ERROR);
        }

        return bearer;
    }

    private Parameters getParameters(Http.Request request, Account bearer) {
        JsonNode json;

        if (request.method() == Http.HttpVerbs.GET) {
            json = Json.mapper().convertValue(request.queryString(), JsonNode.class);
        } else {
            json = request.body().asJson();
        }

        if (bearer != null) {
            ((ObjectNode) json).set(PARAMETERS_BEARER_FIELD, Json.toJson(bearer));
        }
        Parameters parameters = Json.fromJson(json, configuration.type());

        // TODO: Check null for every field

        return parameters;
    }

}
