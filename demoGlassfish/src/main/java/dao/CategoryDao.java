package dao;

import Model.CategoryEntity;
import Utility.HiberUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public List<CategoryEntity> getAll(){
        List<CategoryEntity> categoryEntities =   new ArrayList<CategoryEntity>();
        Session s;
        s = HiberUtil.getSession();
        CriteriaBuilder bob = s.getCriteriaBuilder();
        CriteriaQuery query =  bob.createQuery(CategoryEntity.class);
        query.from(CategoryEntity.class);
        categoryEntities = s.createQuery(query).getResultList();
        s.close();
        return categoryEntities;

    }
}
