package com.example.demo.controller;

import com.example.demo.entity.Profile;
import com.example.demo.repo.ProfileRepo;
import com.example.demo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService svc;

    @GetMapping("/{id}")
    public ResponseEntity<String> getPersonName(@PathVariable Long id) {
        return svc.getPersonName(id);
    }

    @PostMapping("/")
    public Profile createProfile(@RequestBody Profile profile) {
        return svc.createProfile(profile);
    }

    @GetMapping("/")
    public List<Profile> getAllProfiles() {
        return (List<Profile>) svc.getAllProfiles();
    }

    @PutMapping("/{id}")
    public Profile updateProfile(Long id, Profile newProfile) {
        svc.updateProfile(id,newProfile);
        return newProfile;
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(Long id) {
        svc.deleteProfile(id);
    }
}

