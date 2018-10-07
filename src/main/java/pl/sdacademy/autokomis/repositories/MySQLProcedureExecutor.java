package pl.sdacademy.autokomis.repositories;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class MySQLProcedureExecutor {

    @PersistenceContext
    private EntityManager em;

    public void inOnlyTest(String inParam1) {
        this.em.createNativeQuery("begin in_only_test(:inParam1); end;")
                .setParameter("inParam1", inParam1)
                .executeUpdate();
    }

    public List sqlProcedureSelect(String sqlStatement) {
        return this.em.createNativeQuery(sqlStatement)
                .getResultList();
    }



}
