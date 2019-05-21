package com.momdatrbackend.momdatebuildweek.controller;

import com.momdatrbackend.momdatebuildweek.model.ErrorDetail;
import com.momdatrbackend.momdatebuildweek.model.Experiences;
import com.momdatrbackend.momdatebuildweek.service.ExperienceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
public class ExperienceController
{
    @Autowired
    ExperienceService experienceService;

    @ApiOperation(value = "Return all experiences and their owners", response = Experiences.class, responseContainer = "List")
    @GetMapping(value = "/experiences", produces = {"application/json"})
    public ResponseEntity<?> listAllExperiences()
    {
        ArrayList<Experiences> myUsers = experienceService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    @ApiOperation(value = "Return an experiences and their owners based off of the experience ID", response = Experiences.class, responseContainer = "List")
    @GetMapping(value = "/experience/{expid}",
            produces = {"application/json"})
    public ResponseEntity<?> getExperienceById(
            @PathVariable
                    Long expid)
    {
        Experiences r = experienceService.findExpById(expid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns current logged in users experiences", response = Experiences.class, responseContainer = "List")
    @GetMapping(value = "/currentexp", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserExperiences(Authentication authentication)
    {
        return new ResponseEntity<>(experienceService.findCurrentUserExp(), HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a new experience and assigns it to logged in user",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Experience added Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error adding Experience", response = ErrorDetail.class)
    } )
    @PostMapping(value = "/newexp", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createExperience(@Valid
                                        @RequestBody
                                        Experiences newexperience) throws URISyntaxException
    {
        newexperience =  experienceService.save(newexperience);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newExpURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{expid}")
                .buildAndExpand(newexperience.getExpid())
                .toUri();
        responseHeaders.setLocation(newExpURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates an experience",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "experience Updated Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error updating experience", response = ErrorDetail.class)
    } )
    @PutMapping(value = "/exp/{expid}")
    public ResponseEntity<?> updateExperience(@RequestBody Experiences updateexp, @PathVariable
            long expid)
    {
        experienceService.update(updateexp, expid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes an experience",
            response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "experience Deleted Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error Deleting experience", response = ErrorDetail.class)
    } )
    @DeleteMapping("/exp/{expid}")
    public ResponseEntity<?> deleteExperienceById(@PathVariable long expid)
    {
        experienceService.delete(expid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
