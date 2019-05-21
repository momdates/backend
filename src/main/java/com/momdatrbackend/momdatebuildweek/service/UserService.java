package com.momdatrbackend.momdatebuildweek.service;
import com.momdatrbackend.momdatebuildweek.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService
{

    ArrayList<User> findAll();

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User update(User user, long id);

    User findCurrent();
}