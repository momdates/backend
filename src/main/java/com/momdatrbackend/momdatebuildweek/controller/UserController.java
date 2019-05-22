package com.momdatrbackend.momdatebuildweek.controller;

import com.momdatrbackend.momdatebuildweek.model.ErrorDetail;
import com.momdatrbackend.momdatebuildweek.model.User;
import com.momdatrbackend.momdatebuildweek.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Return all users and their experiences", response = User.class, responseContainer = "List")
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        ArrayList<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    @ApiOperation(value = "Registers a new user",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registration successful", response = void.class),
            @ApiResponse(code = 500, message = "Error registering", response = ErrorDetail.class)
    } )
    @PostMapping(value = "/newuser", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Returns current logged in user and their experiences", response = User.class, responseContainer = "List")
    @GetMapping(value = "/current", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUser(Authentication authentication)
    {
        return new ResponseEntity<>(userService.findCurrent(), HttpStatus.OK);
    }

    @ApiOperation(value = "Updates a user",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Updated Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error updating User", response = ErrorDetail.class)
    } )
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long id)
    {
        userService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
