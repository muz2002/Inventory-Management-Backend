package com.example.final_project.controller;

import com.example.final_project.dto.UserDTO;
import com.example.final_project.entity.User;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    private final String imageUploadPath = Paths.get("uploads", "profile_images").toAbsolutePath().toString(); // Correct upload path

    @PostMapping("/uploadProfileImage/{userId}")
    public ResponseEntity<Map<String, String>> uploadProfileImage(
            @PathVariable Integer userId,
            @RequestParam("file") MultipartFile file) {
        return userService.uploadProfileImage(userId, file);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        UserDTO userDTO = new UserDTO(user.getUserId(), user.getName(), user.getUsername(), user.getEmail());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/profileImage/{userId}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Integer userId) {
        String imagePath = userService.getProfileImagePath(userId); // Get the image filename

        if (imagePath == null || imagePath.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no path found
        }

        File imageFile = new File(imageUploadPath, imagePath); // Build file path

        if (!imageFile.exists()) {
            return ResponseEntity.notFound().build(); // Return 404 if image not found
        }

        try {
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath()); // Read the image file
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg") // Set content type
                    .body(imageBytes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build(); // Return 500 on IO exceptions
        }
    }
}