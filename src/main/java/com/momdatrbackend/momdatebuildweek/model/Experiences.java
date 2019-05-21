package com.momdatrbackend.momdatebuildweek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column
    private String kids;

    @Column
    private String dates;

    @Column
    private String blurb;

    @Column
    private String availability;

    @Column(nullable = false)
    private double price;

    @Column
    private String expimgurl;

//    @OneToMany(mappedBy = "experiences",
//    cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("experiences")
//    private List<UserExperiences> experiences;

    @ManyToMany(mappedBy = "experiences")
    @JsonIgnoreProperties("experiences")
    private List<User> owner = new ArrayList<>();

    public Experiences()
    {
    }

    public Experiences(String title, String explocation, String kids, String dates, String blurb, String availability, String expdesc, String expimgurl, double price)
    {
        this.title = title;
        this.expdesc = expdesc;
        this.explocation = explocation;
        this.kids = kids;
        this.dates = dates;
        this.blurb = blurb;
        this.availability = availability;
        this.price = price;
        this.expimgurl = expimgurl;
    }


    public String getExpimgurl()
    {
        return expimgurl;
    }

    public void setExpimgurl(String expimgurl)
    {
        this.expimgurl = expimgurl;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDates()
    {
        return dates;
    }

    public void setDates(String dates)
    {
        this.dates = dates;
    }

    public String getBlurb()
    {
        return blurb;
    }

    public void setBlurb(String blurb)
    {
        this.blurb = blurb;
    }

    public String getAvailability()
    {
        return availability;
    }

    public void setAvailability(String availability)
    {
        this.availability = availability;
    }

    public String getKids()
    {
        return kids;
    }

    public void setKids(String kids)
    {
        this.kids = kids;
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

    public List<User> getOwner()
    {
        return owner;
    }

    public void setOwner(List<User> owner)
    {
        this.owner = owner;
    }
}
