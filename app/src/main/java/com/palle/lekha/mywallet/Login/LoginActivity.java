package com.palle.lekha.mywallet.Login;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.palle.lekha.mywallet.R;

public class LoginActivity extends AppCompatActivity implements LoginFragment.RegisterClickedListener, RegisterFragment.LoginClickedListener{

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // Call some material design API's here
        } else {
            // Implement this feature without material design
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.login_container, loginFragment, "LoginFragmentTag");
        fragmentTransaction.commit();

    }


    public void registerClicked(){
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_container, registerFragment, "RegisterFragment");
        transaction.addToBackStack("");
        transaction.commit();
    }

    @Override
    public void onRegisterClicked() {
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_container, registerFragment, "RegisterFragment");
        transaction.addToBackStack("");
        transaction.commit();
    }

    @Override
    public void onLoginClicked() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(); // Equal to pressing back button
    }

    @Override
    public void onRegisteredSuccessfully(String username, String password) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(); // Equal to pressing back button
        this.username = username;
        this.password = password;
    }

    public String[] getUsernameAndPassword(){
        return new String[]{username, password};
    }
}

