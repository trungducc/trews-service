package daos;

import com.google.inject.Inject;
import models.Reaction;
import models.ReactionType;
import play.db.jpa.JPAApi;

import javax.persistence.Query;
import java.util.List;

public class ReactionDAO extends BaseDAO<Reaction> {

    @Inject
    ReactionDAO(JPAApi jpaApi) {
        super(jpaApi, Reaction.class);
    }

    public Reaction firstRecord(long trewsId, long accountId) {
        return jpaApi.withTransaction(em -> {
            String qlString = "select r from Reaction r where r.trewsId = :trewsId and r.accountId = :accountId";
            Query query = em.createQuery(qlString).setParameter("trewsId", trewsId).setParameter("accountId", accountId);
            List<Reaction> result = query.getResultList();
            return result.isEmpty() ? null : detach(result.get(0));
        });
    }

}
