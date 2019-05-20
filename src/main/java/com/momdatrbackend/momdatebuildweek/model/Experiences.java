package com.momdatrbackend.momdatebuildweek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "experiences")
public class Experiences extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long expid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String expdesc;

    @Column(nullable = false)
    private String explocation;

    @Column(nullable = false)
    private double price;

//    @OneToMany(mappedBy = "experiences",
//    cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("experiences")
//    private List<UserExperiences> experiences;

    @ManyToOne
    @JsonIgnoreProperties("experiences")
    private User user;

    public Experiences()
    {
    }

    public Experiences(String title, String expdesc, String explocation, double price)
    {
        this.title = title;
        this.expdesc = expdesc;
        this.explocation = explocation;
        this.price = price;
    }

    public long getExpId()
    {
        return expid;
    }

    public void setExpId(long expid)
    {
        this.expid = expid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }



    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public long getExpid()
    {
        return expid;
    }

    public void setExpid(long expid)
    {
        this.expid = expid;
    }

    public String getExpdesc()
    {
        return expdesc;
    }

    public void setExpdesc(String expdesc)
    {
        this.expdesc = expdesc;
    }

    public String getExplocation()
    {
        return explocation;
    }

    public void setExplocation(String explocation)
    {
        this.explocation = explocation;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
