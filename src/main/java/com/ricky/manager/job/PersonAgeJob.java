package com.ricky.manager.job;

import com.ricky.manager.entity.InnerPerson;
import com.ricky.manager.entity.OverhaulPerson;
import com.ricky.manager.mapper.InnerPersonMapper;
import com.ricky.manager.mapper.OverhaulPersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Description: 人员年龄定时任务
 * @Author: Ricky Charles
 * @Date: 2022-05-19 11:38
 **/

@Slf4j
@Component
public class PersonAgeJob {

    @Autowired
    private InnerPersonMapper innerPersonMapper;

    @Autowired
    private OverhaulPersonMapper overhaulPersonMapper;

    @Scheduled(cron="0 1 0 1 1 ?")
    public void handleInnerPersonAge() {
        List<InnerPerson> innerPersonList = innerPersonMapper.selectAll();
        for (InnerPerson innerPerson : innerPersonList) {
            innerPerson.setAge(innerPerson.getAge() + 1);
            innerPersonMapper.updateByPrimaryKey(innerPerson);
        }
    }

    @Scheduled(cron="0 1 1 1 1 ?")
    public void handleOverhaulPersonAge() {
        List<OverhaulPerson> overhaulPeopleList = overhaulPersonMapper.selectAll();
        for (OverhaulPerson overhaulPerson : overhaulPeopleList) {
            overhaulPerson.setAge(overhaulPerson.getAge() + 1);
            overhaulPersonMapper.updateByPrimaryKey(overhaulPerson);
        }
    }
}
