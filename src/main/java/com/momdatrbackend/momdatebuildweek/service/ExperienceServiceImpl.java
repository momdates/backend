package com.momdatrbackend.momdatebuildweek.service;

import com.momdatrbackend.momdatebuildweek.exception.ResourceNotFoundException;
import com.momdatrbackend.momdatebuildweek.model.Experiences;
import com.momdatrbackend.momdatebuildweek.model.User;
import com.momdatrbackend.momdatebuildweek.repos.ExperienceRepository;
import com.momdatrbackend.momdatebuildweek.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "experienceService")
public class ExperienceServiceImpl implements ExperienceService
{
    @Autowired
    ExperienceRepository experienceRepos;

    @Autowired
    private UserRepository userrepos;

    @Override
    public ArrayList<Experiences> findAll()
    {
        ArrayList<Experiences> list = new ArrayList<>();
       experienceRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Experiences findExpById(long id)
    {
        return experienceRepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());
        if (currentUser.getExperiences().contains(experienceRepos.findById(id)))
        {
            if (experienceRepos.findById(id).isPresent())
            {
                experienceRepos.deleteById(id);
            } else
            {
                throw new EntityNotFoundException(Long.toString(id));
            }
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id) + " Not current user");
        }
    }

    @Override
    public List<Experiences> findCurrentUserExp()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());
        List<Experiences> myExp = currentUser.getExperiences();
        return myExp;
    }

    @Transactional
    @Override
    public Experiences save(Experiences experiences)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());

        Experiences newExperience = new Experiences();
        newExperience.setTitle(experiences.getTitle());
        newExperience.setExpdesc(experiences.getExpdesc());
        newExperience.setExplocation(experiences.getExplocation());
        newExperience.setPrice(experiences.getPrice());
        newExperience.setExpimgurl(experiences.getExpimgurl());
        newExperience.setDates(experiences.getDates());
        newExperience.setKids(experiences.getKids());
        newExperience.setBlurb(experiences.getBlurb());
        newExperience.setAvailability(experiences.getAvailability());

       List<Experiences> userExp = currentUser.getExperiences();
       userExp.add(newExperience);
       currentUser.setExperiences(userExp);
        return experienceRepos.save(newExperience);
    }

    @Transactional
    @Override
    public Experiences update(Experiences experiences, long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());


        Experiences currentExp = experienceRepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        if (currentUser != null)
        {
        if (currentUser.getExperiences().contains(currentExp))
        {
            if (experiences.getTitle() != null)
            {
                currentExp.setTitle(experiences.getTitle());
            }
            if (experiences.getExpdesc() != null)
            {
                currentExp.setExpdesc(experiences.getExpdesc());
            }
            if (experiences.getExplocation() != null)
            {
                currentExp.setExplocation(experiences.getExplocation());
            }
            if (experiences.getPrice() != 0)
            {
                currentExp.setPrice(experiences.getPrice());
            }

            if (experiences.getKids() != null)
            {
                currentExp.setKids(experiences.getKids());
            }
            if (experiences.getAvailability() != null)
            {
                currentExp.setAvailability(experiences.getAvailability());
            }

            if (experiences.getBlurb() != null)
            {
                currentExp.setBlurb(experiences.getBlurb());
            }
            if (experiences.getDates() != null)
            {
                currentExp.setDates(experiences.getDates());
            }
            if (experiences.getExpimgurl() != null)
            {
                currentExp.setExpimgurl(experiences.getExpimgurl());
            }
            return experienceRepos.save(currentExp);
        }
        else
        {
            throw new ResourceNotFoundException(Long.toString(id) + " Not current user");
        }
    }
        else
    {
        throw new ResourceNotFoundException(authentication.getName());
    }
}
}
