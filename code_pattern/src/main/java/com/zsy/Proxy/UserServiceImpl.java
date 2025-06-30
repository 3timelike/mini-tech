package com.zsy.Proxy;

public class UserServiceImpl implements UserService {
    @Override
    public void addUser(String username) {
        System.out.println("Adding user: " + username);
    }

    @Override
    public void deleteUser(String username) {
        System.out.println("Deleting user: " + username);
    }
}