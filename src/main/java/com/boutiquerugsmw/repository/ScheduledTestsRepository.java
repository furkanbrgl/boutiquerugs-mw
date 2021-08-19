package com.boutiquerugsmw.repository;

import com.boutiquerugsmw.model.ScheduledTestModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduledTestsRepository extends MongoRepository<ScheduledTestModel, Long> {

    int countFindByTestClassNameAndTestStatus(String testClassName, String testStatus);

//    ScheduledTestModel findScheduledTestModelById(long testId);


}
