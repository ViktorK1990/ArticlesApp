package com.example.articleapp.models;

import java.io.*;

public class User implements Serializable {
    private String login, password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static void authUser(String login, String password) {
        User user = new User(login, Password.md5String(password));
        try {
            FileOutputStream fos = new FileOutputStream("auth.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static User getUser() {
        User user = new User();
        try {
            FileInputStream fis = new FileInputStream("auth.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            user = (User) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean isUserExist() {
        File file = new File("auth.txt");
        return file.exists();
    }

    public static void exitUser() {
        File file = new File("auth.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    public static void main(String[] args) {
        System.out.println(getUser().getLogin());
        System.out.println(getUser().getPassword());
    }
}
