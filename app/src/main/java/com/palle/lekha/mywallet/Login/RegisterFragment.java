package com.palle.lekha.mywallet.Login;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.palle.lekha.mywallet.Data.MyWalletContract;
import com.palle.lekha.mywallet.Data.MyWalletDatabase;
import com.palle.lekha.mywallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    private LoginClickedListener listener;
    private MyWalletDatabase database;

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;
    private Button buttonRegister;
    private TextView textViewLogin;

    public interface LoginClickedListener{
        public void onLoginClicked();
        public void onRegisteredSuccessfully(String username, String password);
    }

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (LoginClickedListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement registerClickedListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        database = new MyWalletDatabase(getActivity());


        View view = inflater.inflate(R.layout.fragment_register, container, false);
        editTextUsername = (EditText) view.findViewById(R.id.edit_text_username);
        editTextPassword = (EditText) view.findViewById(R.id.edit_text_password);
        editTextConfirmPassword = (EditText) view.findViewById(R.id.edit_text_confirm_password);
        buttonRegister = (Button) view.findViewById(R.id.button_register);
        textViewLogin = (TextView) view.findViewById(R.id.text_view_go_to_login);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim().toLowerCase();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                if(username.length() < 8 || password.length() < 8){
                    Toast.makeText(getActivity(), "Username and password must be greater an 8 characters", Toast.LENGTH_SHORT).show();
                } else if(!password.equals(confirmPassword)){
                    Toast.makeText(getActivity(), "Password and confirm pass do not match", Toast.LENGTH_SHORT).show();
                } else if(database.checkIfUsernameExists(username)){
                    Toast.makeText(getActivity(), "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    database.createLoginDetails(username, password);
                    Toast.makeText(getActivity(), "User created", Toast.LENGTH_SHORT).show();
                    listener.onRegisteredSuccessfully(username, password);
                }
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLoginClicked();
            }
        });

        return view;
    }



}
