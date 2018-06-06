package com.elcom.com.quickbloxex.view.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.elcom.com.quickbloxex.R;
import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.Consts;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.helper.StringifyArrayList;
import com.quickblox.core.request.QBPagedRequestBuilder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;

public class QuickBloxActivity extends AppCompatActivity {

    private static final String TAG = "QuickBloxActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_blox);

        signUpQuick();

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> login());
        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(v ->getAllUsers());

    }

    public void signUpQuick(){
        final QBUser user = new QBUser("ambition", "ambition");
        user.setExternalId("45345");
        user.setFacebookId("100233453457767");
        user.setTwitterId("182334635457");
        user.setEmail("Javck@mail.com");
        user.setFullName("Javck Bold");
        user.setPhone("+18904567812");
        StringifyArrayList<String> tags = new StringifyArrayList<String>();
        tags.add("car");
        tags.add("man");
        user.setTags(tags);
        user.setWebsite("www.mysite.com");

        QBUsers.signUp(user).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {
                Log.e("hailpt"," signUpQuick onSuccess "+qbUser.getEmail());
            }

            @Override
            public void onError(QBResponseException e) {

            }
        });
    }

    public void login(){
        QBUser user = new QBUser("ambition", "ambition");
        QBUsers.signIn(user).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {
                Log.e("hailpt"," login onSuccess "+qbUser.getEmail());
            }

            @Override
            public void onError(QBResponseException e) {

            }
        });
    }

    public void updateProfile(){
        QBUser user = new QBUser();
        user.setId(53779);
        user.setFullName("Ambition");
        user.setEmail("Ambition@gmail.com");

        QBUsers.updateUser(user).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException e) {

            }
        });
    }

    public void getAllUsers(){
        QBPagedRequestBuilder pagedRequestBuilder = new QBPagedRequestBuilder();
        pagedRequestBuilder.setPage(1);
        pagedRequestBuilder.setPerPage(50);

        QBUsers.getUsers(pagedRequestBuilder).performAsync(new QBEntityCallback<ArrayList<QBUser>>() {
            @Override
            public void onSuccess(ArrayList<QBUser> qbUsers, Bundle bundle) {
                Log.i(TAG, "Users: " + qbUsers.toString());
                Log.i(TAG, "currentPage: " + bundle.getInt(Consts.CURR_PAGE));
                Log.i(TAG, "perPage: " + bundle.getInt(Consts.PER_PAGE));
                Log.i(TAG, "totalPages: " + bundle.getInt(Consts.TOTAL_ENTRIES));
            }

            @Override
            public void onError(QBResponseException e) {

            }
        });


    }
}
