package com.abhishek.educatorproject;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import static android.content.Context.MODE_PRIVATE;

public class Logout extends Fragment {

    private LogoutViewModel mViewModel;

    public static Logout newInstance() {
        return new Logout();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.logout_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(LogoutViewModel.class);


        // TODO: Use the ViewModel

        SharedPreferences checksend = getActivity().getSharedPreferences("check", MODE_PRIVATE);
        SharedPreferences.Editor edit2 = checksend.edit();

        SharedPreferences up = getActivity().getSharedPreferences("currentPhoneNumber", MODE_PRIVATE);
        SharedPreferences.Editor edit3 = up.edit();

        edit2.putInt("check",0);
        edit2.apply();
        edit3.putString("currentPhoneNumber", null);
        edit3.apply();

        FirebaseAuth.getInstance().signOut();

        Intent signout = new Intent(getActivity(), MainLoginActivity.class);
        startActivity(signout);
    }

}