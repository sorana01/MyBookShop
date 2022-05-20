package com.example.book_shop.services;

import com.example.book_shop.exceptions.CouldNotWriteUsersException;
import com.example.book_shop.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;


public class UserService {
    private static List<User> user_database;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("users.json");
    public static List<User> getUsers() {
        return user_database;
    }

    public static void loadUsersFromFile() throws IOException{

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("users.json")), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        user_database = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<>() {
        });
    }



}
