package com.boutiquerugsmw.repository;

import com.boutiquerugsmw.model.ScheduledTestModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduledTestsRepository extends MongoRepository<ScheduledTestModel, Long> {

    int countFindByTestClassNameAndTestStatus(String testClassName, String testStatus);


}
