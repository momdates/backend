package com.momdatrbackend.momdatebuildweek.service;

import com.momdatrbackend.momdatebuildweek.model.Experiences;

import java.util.ArrayList;
import java.util.List;

public interface ExperienceService
{
    ArrayList<Experiences> findAll();

    Experiences findExpById(long id);

    void delete(long id);

    Experiences save(Experiences experiences);

    Experiences update(Experiences experiences, long id);

    List<Experiences> findCurrentUserExp();
}
