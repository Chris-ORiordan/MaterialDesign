package com.google.codelabs.mdc.java.shrine;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.design.button.MaterialButton;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment implements NavigationHost{

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shr_login_fragment, container, false);
        final TextInputLayout passwordTextInputLayout = view.findViewById(R.id.password_layout);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPasswordValid(passwordEditText.getText())){
                    passwordTextInputLayout.setError(getString(R.string.shr_error_password));
                } else {
                    passwordTextInputLayout.setError(null);
                    navigateTo(new ProductGridFragment(), false);
                }
            }
        });
        return view;
    }
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
        if(addToBackstack){
            fragmentTransaction.addToBackStack("loginFragment");
        }
        fragmentTransaction.commit();
    }
}
