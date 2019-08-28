package daos;

import play.db.jpa.JPAApi;

import javax.persistence.Query;
import java.util.List;

public class BaseDAO<T> {

    protected final JPAApi jpaApi;
    protected final Class<T> clazz;

    BaseDAO(JPAApi jpaApi, Class<T> clazz) {
        this.jpaApi = jpaApi;
        this.clazz = clazz;
    }

    public void create(T newT) {
        jpaApi.withTransaction(em -> {
            em.persist(newT);
            em.flush();
            detach(newT);
        });
    }

    protected T detach(T o) {
        if (o != null) {
            jpaApi.withTransaction(em -> {
                em.detach(o);
            });
        }
        return o;
    }

    public T firstRecord(String column, String value) {
        return jpaApi.withTransaction(em -> {
            String qlString = "select t from " + clazz.getSimpleName() + " t where t." + column + " = :" + column;
            Query query = em.createQuery(qlString).setParameter(column, value);
            List<T> result = query.getResultList();
            return result.isEmpty() ? null : detach(result.get(0));
        });
    }

}