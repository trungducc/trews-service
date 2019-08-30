package services.account;

import common.CompletionStageHelper;
import common.exception.TrewsError;
import daos.AccountDAO;
import io.jsonwebtoken.*;
import models.Account;
import org.mindrot.jbcrypt.BCrypt;
import play.libs.Json;

import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AccountService {

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private static final String SECRET_KEY = "Trews";

    private AccountDAO accountDAO;

    @Inject
    AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public CompletionStage<SignUpResult> signUp(SignUpParameters parameters) {
        if (accountDAO.usernameExists(parameters.getUsername())) {
            return CompletionStageHelper.exceptionalFuture(TrewsError.USERNAME_EXISTS);
        }

        Account newAccount = new Account(parameters);
        accountDAO.create(newAccount);
        return CompletableFuture.completedFuture(new SignUpResult(newAccount));
    }

    public CompletionStage<SignInResult> signIn(SignInParameters parameters) {
        Account account = accountDAO.findByUsername(parameters.getUsername());
        if (account == null || !BCrypt.checkpw(parameters.getPassword(), account.getHashedPassword())) {
            return CompletionStageHelper.exceptionalFuture(TrewsError.AUTHENTICATION_ERROR);
        }

        String token = generateToken(account);
        SignInResult result = new SignInResult(token);
        return CompletableFuture.completedFuture(result);
    }

    public String generateToken(Account account) {
        Map<String, Object> claims = Json.mapper().convertValue(account, Map.class);
        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(SIGNATURE_ALGORITHM, SECRET_KEY);
        return builder.compact();
    }

    public Account verifyToken(String token) {
        JwtParser parser = Jwts.parser().setSigningKey(SECRET_KEY);
        Claims claims = parser.parseClaimsJws(token).getBody();

        Account account = null;
        try {
            account = Json.mapper().convertValue(claims, Account.class);
        } catch (Exception e) {
            return null;
        }

        if (account != null && account.equals(accountDAO.findById(account.getId()))) {
            return account;
        }
        return null;
    }

}
