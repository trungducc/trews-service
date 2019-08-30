package common;

import common.exception.TrewsError;
import common.exception.TrewsException;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import java.util.concurrent.CompletionStage;

public class Response<T> {

    private T data;

    private TrewsError error;

    public Response(T data, Throwable exception) {
        this.data = data;

        if (exception == null) {
            return;
        }

        if (exception instanceof TrewsException) {
            this.error = ((TrewsException) exception).getError();
        } else {
            error = TrewsError.INTERNAL_SERVER_ERROR;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TrewsError getError() {
        return error;
    }

    public void setError(TrewsError error) {
        this.error = error;
    }

    public static <T> CompletionStage<Result> make(CompletionStage<T> completionStage) {
        return completionStage.handle((result, exception) -> {
            return Results.ok(Json.toJson(new Response<T>(result, exception)));
        });
    }

}
