//package com.momdatrbackend.momdatebuildweek.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@Table(name = "userexperiences")
//public class UserExperiences extends Auditable implements Serializable
//{
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"experiences", "hibernateLazyInitializer"})
//    @JoinColumn(name = "userid")
//    private User username;
//
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "expId")
//    @JsonIgnoreProperties({"experiences", "hibernateLazyInitializer"})
//    private Experiences experiences;
//
//    public UserExperiences(User username, Experiences experiences)
//    {
//        this.username = username;
//        this.experiences = experiences;
//    }
//
//    public User getUsername()
//    {
//        return username;
//    }
//
//    public void setUsername(User username)
//    {
//        this.username = username;
//    }
//
//    public Experiences getExperiences()
//    {
//        return experiences;
//    }
//
//    public void setExperiences(Experiences experiences)
//    {
//        this.experiences = experiences;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o)
//        {
//            return true;
//        }
//        if (!(o instanceof UserExperiences))
//        {
//            return false;
//        }
//        UserExperiences that = (UserExperiences) o;
//        return getUsername().equals(that.getUsername()) &&
//                Objects.equals(getExperiences(), that.getExperiences());
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return Objects.hash(getUsername(), getExperiences());
//    }
//}
//
