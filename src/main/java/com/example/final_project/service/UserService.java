package com.example.final_project.service;

import com.example.final_project.entity.User;
import com.example.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final String IMAGE_UPLOAD_DIR = Paths.get("uploads", "profile_images").toAbsolutePath().toString();
    // Directory to save images
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public ResponseEntity<Map<String, String>> uploadProfileImage(Integer userId, MultipartFile file) {
        Map<String, String> response = new HashMap<>(); // Create a map to hold the response
        try {
            // Create the directory if it doesn't exist
            File dir = new File(IMAGE_UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Save the file
            String fileName = userId + "_" + file.getOriginalFilename(); // Create filename
            Path filePath = Paths.get(IMAGE_UPLOAD_DIR, fileName); // Full file path
            Files.copy(file.getInputStream(), filePath); // Save the file

            // Update user profile image URL (relative URL)
            String imageUrl = fileName; // Store only the filename
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            user.setProfileImageUrl(imageUrl); // Save the logical URL
            userRepository.save(user); // Update user in database

            response.put("message", "Profile image uploaded successfully: " + fileName); // Add message to the map
            response.put("imageUrl", "/users/profileImage/" + userId); // Logical URL for frontend access
            return ResponseEntity.ok(response); // Return the response as JSON
        } catch (IOException e) {
            e.printStackTrace();
            response.put("message", "Failed to upload image");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // Return error as JSON
        }
    }


    public String getProfileImagePath(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getProfileImageUrl(); // Return the stored image filename
    }

    // Other user-related methods
}