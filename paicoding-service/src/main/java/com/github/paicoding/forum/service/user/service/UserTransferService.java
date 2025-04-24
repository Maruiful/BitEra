package com.github.paicoding.forum.service.user.service;

public interface UserTransferService {

    boolean transferUser(String uname, String pwd);

    boolean transferUser(String starNumber);
}
