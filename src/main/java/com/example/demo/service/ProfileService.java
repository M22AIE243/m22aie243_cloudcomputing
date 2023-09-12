package com.example.demo.service;

import com.example.demo.entity.Profile;
import com.example.demo.repo.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {


        @Autowired
        private ProfileRepo profileRepository;

        @GetMapping("/{id}")
        public ResponseEntity<String> getPersonName(@PathVariable Long id) {
        Optional<Profile> personOptional = profileRepository.findById(id);

        if (personOptional.isPresent()) {
            Profile person = personOptional.get();
            return ResponseEntity.ok(person.getName());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        @PostMapping("/")
        public Profile createProfile(@RequestBody Profile profile) {
        return profileRepository.save(profile);
    }

        @GetMapping("/")
        public List<Profile> getAllProfiles() {
        return (List<Profile>) profileRepository.findAll();
    }

        @PutMapping("/{id}")
        public Profile updateProfile(Long id, Profile newProfile) {
        if (profileRepository.existsById(id)) {
            newProfile.setId(id);
            return profileRepository.save(newProfile);
        } else {
            throw new RuntimeException("Profile not found with ID: " + id);
        }
    }

        @DeleteMapping("/{id}")
        public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    }

