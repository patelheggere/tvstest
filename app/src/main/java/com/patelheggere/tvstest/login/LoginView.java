package com.patelheggere.tvstest.login;

public interface LoginView {
    void networkError();
    void invalidCredentials();
    void hideProgressBar();
    void showProgressBar();
    void errorMessage();
    void loginSuccess();
}
