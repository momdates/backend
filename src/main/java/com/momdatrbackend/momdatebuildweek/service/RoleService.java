package com.momdatrbackend.momdatebuildweek.service;

import com.momdatrbackend.momdatebuildweek.model.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);
}