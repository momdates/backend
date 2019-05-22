package com.momdatrbackend.momdatebuildweek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @ElementCollection
    @CollectionTable(name = "userinterests", joinColumns = @JoinColumn(name = "userid"))
    @Column(name = "interest")
    private List<String> interests = new ArrayList<>();
//    private Set<String> interests = new HashSet<>();

    @Column
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private String location;

    @Column
    private String zip;

    @Column
    private String imgurl;


    @ApiModelProperty(hidden = true)
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userroles = new ArrayList<>();

//    @OneToMany(mappedBy = "username",
//                cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("username")
//    private List<UserExperiences> experiences = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "userexperiences",
    joinColumns = {@JoinColumn(name = "userid")},
    inverseJoinColumns = {@JoinColumn(name = "expid")})
    @JsonIgnoreProperties("owner")
    private List<Experiences> experiences = new ArrayList<>();

    public User()
    {
    }

    public User(String name, String email, String password, String location, String zip, String imgurl,List<String> interests, List<UserRoles> userroles)
    {
        this.username = email;
        this.name = name;
        this.email = email;
        setPassword(password);
        this.location = location;
        this.zip = zip;
        this.imgurl = imgurl;
        for (UserRoles ur : userroles)
        {
            ur.setUser(this);
        }
        this.userroles = userroles;
        this.interests = interests;
    }

    public User(String name, String email, String password, String location, String zip, String imgurl,List<String> interests)
    {
        this.username = email;
        this.name = name;
        this.email = email;
        setPassword(password);
        this.location = location;
        this.zip = zip;
        this.imgurl = imgurl;
        this.interests = interests;
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

    public List<String> getInterests()
    {
        return interests;
    }

    public void setInterests(List<String> interests)
    {
        this.interests = interests;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getImgurl()
    {
        return imgurl;
    }

    public void setImgurl(String imgurl)
    {
        this.imgurl = imgurl;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = email;
    }


    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
        this.username = email;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    @JsonIgnore
    public String getPassword()
    {
        return password;
    }

    @JsonIgnore
    public List<UserRoles> getUserroles()
    {
        return userroles;
    }

    @JsonIgnore
    public void setUserroles(List<UserRoles> userroles)
    {
        this.userroles = userroles;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    @JsonIgnore
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
