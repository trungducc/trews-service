package daos;

import models.Trews;
import play.db.jpa.JPAApi;
import services.trews.ListTrewsParameters;

import javax.inject.Inject;
import javax.persistence.Query;
import java.util.List;

public class TrewsDAO extends BaseDAO<Trews> {

    @Inject
    TrewsDAO(JPAApi jpaApi) {
        super(jpaApi, Trews.class);
    }

    public List<Trews> find(ListTrewsParameters parameters) {
        return jpaApi.withTransaction(em -> {
            String qlString = "select t from Trews t";
            Query query = em.createQuery(qlString);
            List<Trews> result = query.getResultList();
            return result;
        });
    }

}
