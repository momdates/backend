package com.momdatrbackend.momdatebuildweek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "users")
public class User extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private String location;

    @Column
    private String zip;



    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userroles = new ArrayList<>();

//    @OneToMany(mappedBy = "username",
//                cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("username")
//    private List<UserExperiences> experiences = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "userexperiences",
    joinColumns = {@JoinColumn(name = "userid")},
    inverseJoinColumns = {@JoinColumn(name = "expid")})
    @JsonIgnoreProperties("user")
    private List<Experiences> experiences = new ArrayList<>();

    public User()
    {
    }

    public User(String username, String email, String password, String location, String zip, List<UserRoles> userroles)
    {
        this.username = username;
        this.email = email;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
        this.location = location;
        this.zip = zip;
        for (UserRoles ur : userroles)
        {
            ur.setUser(this);
        }
        this.userroles = userroles;
    }

    public User(String username, String email, String password, String location, String zip)
    {
        this.username = username;
        this.email = email;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
        this.location = location;
        this.zip = zip;
    }

    public List<Experiences> getExperiences()
    {
        return experiences;
    }

    public void setExperiences(List<Experiences> experiences)
    {
        this.experiences = experiences;
    }

    public long getUserid()
    {
        return userid;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getPassword()
    {
        return password;
    }

    public List<UserRoles> getUserroles()
    {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles)
    {
        this.userroles = userroles;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userroles)
        {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }
        return rtnList;
    }
}
