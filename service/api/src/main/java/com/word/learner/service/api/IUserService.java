package com.word.learner.service.api;

public interface IUserService {


    void login(String login,String password) throws UserAuthenticationException;


}
