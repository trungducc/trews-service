package controllers;

import actions.ParametersRequired;
import common.Response;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.trews.CreateTrewsParameters;
import services.trews.ListTrewsParameters;
import services.trews.TrewsService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class TrewsController extends Controller {

    private TrewsService trewsService;

    @Inject
    TrewsController(TrewsService trewsService) {
        this.trewsService = trewsService;
    }

    @ParametersRequired(type = CreateTrewsParameters.class)
    public CompletionStage<Result> create(Http.Request request) {
        CreateTrewsParameters parameters = (CreateTrewsParameters) request.attrs().get(ParametersRequired.Attrs.PARAMETERS_TYPED_KEY);
        return Response.make(trewsService.create(parameters));
    }

    @ParametersRequired(type = ListTrewsParameters.class)
    public CompletionStage<Result> list(Http.Request request) {
        ListTrewsParameters parameters = (ListTrewsParameters) request.attrs().get(ParametersRequired.Attrs.PARAMETERS_TYPED_KEY);
        return Response.make(trewsService.list(parameters));
    }

}
