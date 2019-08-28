package common.exception;

public class TrewsException extends RuntimeException {

    private TrewsError error;

    public TrewsException(TrewsError error) {
        super(error.getMessage());
        this.error = error;
    }

    public TrewsError getError() {
        return error;
    }

    public void setError(TrewsError error) {
        this.error = error;
    }

}
