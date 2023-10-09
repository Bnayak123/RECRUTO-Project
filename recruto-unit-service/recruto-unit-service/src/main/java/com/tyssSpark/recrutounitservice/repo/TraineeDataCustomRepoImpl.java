package com.tyssSpark.recrutounitservice.repo;

import com.tyssSpark.recrutounitservice.dto.BenchUnitData;
import com.tyssSpark.recrutounitservice.dto.TraineeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
@Repository
public class TraineeDataCustomRepoImpl implements TraineeDataCustomRepo{
    @Autowired
    EntityManager entityManager;

    @Autowired
    BenchUnitRepo benchUnitRepo;

//    @SuppressWarnings("removal")
    @Override
    public List<BenchUnitData> getAllMasterData(Map<String, Object> validData) {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        log.info(methodName, " has been invoked . ");
        List<BenchUnitData> list = new ArrayList<BenchUnitData>();
        System.out.println(validData);
        try {
            if (validData != null) {
                Set<String> keySet1 = validData.keySet();
                if (keySet1 != null) {
                    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaQuery createQuery = criteriaBuilder.createQuery();
                    Root<TraineeData> root = createQuery.from(TraineeData.class);
                    createQuery.select(root);
                    Object[] array = keySet1.toArray();
                    int size = array.length;
                    Predicate p1 = criteriaBuilder.equal(root.get(String.valueOf(array[0])), validData.get(array[0]));
                    createQuery.where(p1);
                    List<TraineeData> resultList = entityManager.createQuery(createQuery).getResultList();
                    System.out.println(resultList);
                    if (resultList != null && resultList.size() != 0) {
                        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
                        CriteriaQuery createQuery2 = builder.createQuery();
                        Root<BenchUnitData> root2 = createQuery2.from(BenchUnitData.class);
                        createQuery2.select(root2);
                        BenchUnitData findByMasterData = null;
                        System.out.println("success");
                        for (int i = 0; i <  resultList.size(); i++) {
                            System.out.println("Hello For loop");
                            System.out.println(resultList.get(0));
                            findByMasterData = benchUnitRepo.getMasterData(resultList.get(i).getId());
                            System.out.println("getting data");
                            list.add(findByMasterData);
                        }
                    }

                } else {
                    log.info(methodName, " No values in Map .  ");
                }
            } else {
                log.info(methodName, " method has been invoked with null parameter . ");
            }

        } catch (Exception e) {
            log.error(methodName,  e.getMessage());
        }
        return list;
    }
}
