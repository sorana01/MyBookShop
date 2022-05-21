package com.example.book_shop.services;

import com.example.book_shop.exceptions.*;
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


    public static void addUser(String username, String password, String role, String full_name, String address,
                               String phone_number, String email, String confirm_password) throws UsernameAlreadyExistsException,
            EmptyTextFieldsException, EmailAlreadyUsedException, WrongPasswordConfirmationException, PhoneNumberAlreadyUsedException, NotAllCharactersAreDigitsException {
        checkEmptyTextFields(username, password, role, full_name, address, email, phone_number);
        checkUserDoesNotAlreadyExist(username);
        checkUsedEmail(email);
        checkPasswordConfirmation(password, confirm_password);
        checkAllDigitsEntered(phone_number);
        checkPhoneNumberNotUsed(phone_number);
        user_database.add(new User(username, encodePassword(username, password), role, full_name, address, phone_number, email));
        persistUsers();
    }

    public static void checkUserCredentials(String username,String password,String role) throws EmptyTextFieldsException, UsernameDoesNotExistException, WrongPasswordException, WrongRoleException {
        int oku = 0, okp = 0, okr = 0;
        for (User user : user_database) {
            if (Objects.equals(username, user.getUsername())) {
                oku = 1;
                if (Objects.equals(role, user.getRole()))
                    okr = 1;
            }
            if (Objects.equals(encodePassword(username, password), user.getPassword()))
                okp = 1;
        }

        if (oku == 0)
            throw new UsernameDoesNotExistException();
        if (okr == 0)
            throw new WrongRoleException();
        if (okp == 0)
            throw new WrongPasswordException();

    }

    public static void checkAllDigitsEntered(String string) throws NotAllCharactersAreDigitsException {
        if (string.matches("[0-9]+") != true) {
            throw new NotAllCharactersAreDigitsException();
        }
    }

    private static void checkPhoneNumberNotUsed(String phone_number) throws PhoneNumberAlreadyUsedException {
        for (User user : user_database) {
            if (Objects.equals(phone_number, user.getPhone_number()))
                throw new PhoneNumberAlreadyUsedException();
        }
    }

    private static void checkPasswordConfirmation(String password, String confirm_password) throws WrongPasswordConfirmationException {
        if( !Objects.equals(password, confirm_password))
            throw new WrongPasswordConfirmationException();
    }

    private static void checkUsedEmail(String email) throws EmailAlreadyUsedException{
        for (User user : user_database) {
            if (Objects.equals(email, user.getEmail()))
                throw new EmailAlreadyUsedException();
        }
    }

    private static void checkEmptyTextFields(String username, String password, String role, String full_name,
                                             String address, String email, String phone_number) throws EmptyTextFieldsException {
        if( Objects.equals(username,"") || Objects.equals(password, "") || Objects.equals(full_name,"")
                || Objects.equals(address,"") || Objects.equals(email,"") || Objects.equals(phone_number,""))
            throw new EmptyTextFieldsException();
        else if( !( Objects.equals(role,"Manager") || Objects.equals(role,"Client") || Objects.equals(role, "Courier") ))
            throw new EmptyTextFieldsException();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : user_database) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException();
        }
    }

    public static String getLoggedUser(String username){
        for (User user : user_database) {
            if (Objects.equals(username, user.getUsername()))
                return username;
        }
        return "";
    }

    public static String getUserRole(String username){
        for (User user : user_database) {
            if (Objects.equals(username, user.getUsername()))
                if(Objects.equals(user.getRole(),"Client"))
                    return "Client";
                else if (Objects.equals(user.getRole(), "Manager"))
                    return "Manager";
                else
                    return "Courier";
        }
        return "";
    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), user_database);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

}
