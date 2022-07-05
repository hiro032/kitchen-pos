package hiro.kitchenpos.acceptance;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class DatabaseClean {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void cleanDatabase() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        List<Object[]> tables = entityManager.createNativeQuery("SHOW TABLES")
                .getResultList();

        for (Object[] table : tables) {
            String tableName = (String) table[0];
            truncateTable(tableName);
        }
    }

    private void truncateTable(String tableName) {
        entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
    }
}
