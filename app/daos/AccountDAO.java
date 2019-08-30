package daos;

import models.Account;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

public class AccountDAO extends BaseDAO<Account> {

    @Inject
    AccountDAO(JPAApi jpaApi) {
        super(jpaApi, Account.class);
    }

    public boolean usernameExists(String username) {
        return findByUsername(username) != null;
    }

    public Account findByUsername(String username) {
        return firstRecord("username", username);
    }

    public Account findById(long id) {
        return firstRecord("id", id);
    }

}
