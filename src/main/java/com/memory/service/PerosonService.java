package com.memory.service;

import com.memory.dao.PersonDao;
import com.memory.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerosonService {
    @Autowired
    private PersonDao personDao;

    public Person findAll() {
        return personDao.getPersonByUserName("Winbee");
    }
    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
