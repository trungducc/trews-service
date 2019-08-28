package services;

import common.CompletionStageHelper;
import common.exception.TrewsError;
import controllers.account.SignInParameters;
import controllers.account.SignInResult;
import controllers.account.SignUpParameters;
import controllers.account.SignUpResult;
import daos.AccountDAO;
import models.Account;
import org.mindrot.jbcrypt.BCrypt;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AccountService {

    private JPAApi jpaApi;

    private AccountDAO accountDAO;

    @Inject
    AccountService(JPAApi jpaApi, AccountDAO accountDAO) {
        this.jpaApi = jpaApi;
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
        return CompletableFuture.completedFuture(new SignInResult(account));
    }

}
