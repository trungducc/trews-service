package daos;

import models.Trews;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

public class TrewsDAO extends BaseDAO<Trews> {

    @Inject
    TrewsDAO(JPAApi jpaApi) {
        super(jpaApi, Trews.class);
    }

}
