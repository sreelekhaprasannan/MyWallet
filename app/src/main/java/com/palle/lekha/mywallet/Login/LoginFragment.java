package com.palle.lekha.mywallet.Login;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.palle.lekha.mywallet.Data.MyWalletDatabase;
import com.palle.lekha.mywallet.MainActivity;
import com.palle.lekha.mywallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    MyWalletDatabase database;

    private RegisterClickedListener listener;

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private TextView textViewRegister;
    private CheckBox checkBox;
    private TextInputLayout textInputLayoutUsername, textInputLayoutPassword;

    public interface RegisterClickedListener {
        public void onRegisterClicked();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (RegisterClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement registerClickedListener");
        }
    }

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        database = new MyWalletDatabase(getActivity());

        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        editTextUsername = (EditText) view.findViewById(R.id.edit_text_username);
        editTextPassword = (EditText) view.findViewById(R.id.edit_text_password);
        buttonLogin = (Button) view.findViewById(R.id.button_login);
        textViewRegister = (TextView) view.findViewById(R.id.text_view_register);
        checkBox = (CheckBox) view.findViewById(R.id.checkbox_remember_password);

        textInputLayoutUsername = (TextInputLayout) view.findViewById(R.id.input_layout_username);
        textInputLayoutPassword = (TextInputLayout) view.findViewById(R.id.input_layout_password);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Remember password?");
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setMessage("Are you sure?");
                    builder.setCancelable(false); // Set if the dialog can be closed by clicking outside the bounds

                    builder.setPositiveButton("Absolutely", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Handle positive click
                            Toast.makeText(getActivity(), "Yes clicked", Toast.LENGTH_SHORT).show();

                        }
                    });
                    builder.setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (checkBox.isChecked()) {
                                checkBox.setChecked(false);
                            }
                        }
                    });
                    //endregion

                    //region Show the dialog
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    checkBox.setChecked(false);
                }

            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim().toLowerCase();
                String password = editTextPassword.getText().toString().trim();

                if (!validateUsername() || !validatePassword()) {
                    //Toast.makeText(getActivity(), "Username and password must be greater an 5 characters", Toast.LENGTH_SHORT).show();
                } else if (!database.checkIfUsernameAndPasswordExist(username, password)) {
                    //Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                    textInputLayoutUsername.setError("Invalid credentials");
                    textInputLayoutPassword.setError("Invalid credentials");
                } else {
                    Intent in = new Intent(getActivity(), MainActivity.class);
                    startActivity(in);
                    getActivity().finish();
                    Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*LoginActivity loginActivity = (LoginActivity) getActivity();
                loginActivity.registerClicked();*/
                listener.onRegisterClicked();
            }
        });
        return view;
    }


    private boolean validateUsername() {

        String username = editTextUsername.getText().toString().trim().toLowerCase();

        if (username.length() <= 5) {
            textInputLayoutUsername.setError("Username must be greater than 5 characters");
            return false;
        } else {
            textInputLayoutUsername.setErrorEnabled(false);
            return true;
        }
    }


    private boolean validatePassword() {
        String password = editTextPassword.getText().toString().trim().toLowerCase();

        if (password.length() <= 8) {
            textInputLayoutPassword.setError("Password must be greater than 8 characters");
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LoginActivity loginActivity = (LoginActivity) getActivity();
        String[] usernameAndPassword = loginActivity.getUsernameAndPassword();

        editTextUsername.setText(usernameAndPassword[0]);
        editTextPassword.setText(usernameAndPassword[1]);
    }

}
