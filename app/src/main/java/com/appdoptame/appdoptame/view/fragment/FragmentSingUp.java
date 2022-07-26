package com.appdoptame.appdoptame.view.fragment;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.appdoptame.appdoptame.R;
import com.appdoptame.appdoptame.data.firestore.UserRepositoryFS;
import com.appdoptame.appdoptame.data.listener.CompleteListener;
import com.appdoptame.appdoptame.util.EditTextExtractor;
import com.appdoptame.appdoptame.util.StatusBarHeightGetter;
import com.appdoptame.appdoptame.view.fragmentcontroller.FragmentController;
import com.appdoptame.appdoptame.view.fragmentcontroller.SetFragmentCreateUser;

import java.util.concurrent.TimeUnit;

public class FragmentSingUp extends Fragment {
    private ConstraintLayout statusBar;
    private ImageButton      backButton;
    private EditText         emailField;
    private EditText         passwordField;
    private EditText         passwordConfirmField;
    private TextView         registerButton;

    @SuppressLint("InflateParams") @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        postponeEnterTransition(1, TimeUnit.MILLISECONDS);
        return inflater.inflate(R.layout.fragment_sing_up, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComponents();
    }

    private void loadComponents(){
        statusBar            = requireView().findViewById(R.id.sing_up_status_bar);
        backButton           = requireView().findViewById(R.id.sing_up_back_button);
        emailField           = requireView().findViewById(R.id.sing_up_email_field);
        passwordField        = requireView().findViewById(R.id.sing_up_password_field);
        passwordConfirmField = requireView().findViewById(R.id.sing_up_confirm_password_field);
        registerButton       = requireView().findViewById(R.id.sing_up_sing_up_button);

        loadStatusBar();
        loadBackButton();
        loadRegisterButton();
    }

    private void loadRegisterButton(){
        registerButton.setOnClickListener(v -> {
            String email           = EditTextExtractor.get(emailField);
            String password        = EditTextExtractor.get(passwordField);
            String confirmPassword = EditTextExtractor.get(passwordConfirmField);

            UserRepositoryFS.getInstance().singUp(email, password, confirmPassword, null, new CompleteListener() {
                @Override
                public void onSuccess() {
                    FragmentController.removeAllFragments();
                    SetFragmentCreateUser.set();
                }

                @Override
                public void onFailure() {
                    Toast.makeText(getApplicationContext(), "Algo salio mal, intentalo  nuevo", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void loadBackButton(){
        backButton.setOnClickListener(v -> FragmentController.onBackPressed());
    }

    private void loadStatusBar(){
        // Añadiendo padding superior en base a la altura de la barra de notificaciones
        ConstraintLayout.LayoutParams params =
                (ConstraintLayout.LayoutParams) statusBar.getLayoutParams();
        params.topMargin = StatusBarHeightGetter.get();
        statusBar.setLayoutParams(params);
    }
}

