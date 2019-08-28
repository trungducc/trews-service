package common;

import common.exception.TrewsError;
import common.exception.TrewsException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class CompletionStageHelper {

    public static <T> CompletionStage<T> exceptionalFuture(TrewsError error) {
        CompletableFuture<T> exceptionalFuture = new CompletableFuture<>();
        exceptionalFuture.completeExceptionally(new TrewsException(error));
        return exceptionalFuture;
    }

}
