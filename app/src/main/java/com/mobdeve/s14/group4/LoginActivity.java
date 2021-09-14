package com.mobdeve.s14.group4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;

    private Button btnLogin;
    private Button btnGoogleLogin;

    private EditText etEmail;
    private EditText etPassword;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener authStateListener;

    private GoogleSignInClient mGoogleSignInClient;

    private ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.initComponents();
        this.initFirebase();
        this.initGoogleSignIn();
    }

    private void initFirebase(){
        this.mDatabase = FirebaseDatabase.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        this.databaseReference = mDatabase.getReference("users");

    }

    private void initComponents(){
        this.btnLogin = findViewById(R.id.login_btn_login);
        this.btnGoogleLogin = findViewById(R.id.login_btn_google);
        this.etEmail = findViewById(R.id.login_et_email);
        this.etPassword = findViewById(R.id.login_et_password);
        pbLogin = findViewById(R.id.login_pb);



        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLogin.setVisibility(View.VISIBLE);
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!isEmpty(email, password)) {
                    signIn(email, password);
                }
            }
        });
    }

    private boolean isEmpty(String e, String p){

        if(e.isEmpty()){
            this.etEmail.setError("Required field");
            this.etEmail.requestFocus();
            return true;
        }
        return false;
    }

    private void signIn(String email, String password) {
        // this.pbLogin.setVisibility(View.VISIBLE); ADD PROGRESS BAR LATER

        this.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           successfulLogin();
                        } else {
                            failedLogin();
                        }
                    }
                });
    }

    private void successfulLogin(){
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                pbLogin.setVisibility(View.GONE);
                if (user != null) {
                    // User is signed in
                    DataHelper.loadUser(user.getUid());
                    Log.d("SIGNED IN LOGGED IN", "onAuthStateChanged:signed_in:" + user.getUid());

                    moveToHomeActivity();
                    finish();
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
            }
        };

        authStateListener.onAuthStateChanged(FirebaseAuth.getInstance());
    }

    private void failedLogin(){
        //this.btnNext.setVisibility(View.GONE);
        Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show();

    }

    private void initGoogleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        this.mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        this.btnGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLogin.setVisibility(View.VISIBLE);
                Intent i = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(i, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try{
                GoogleSignInAccount account = task.getResult (ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account);
            } catch (ApiException e){
                Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show();
                Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        String idToken = account.getIdToken();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("SUCCESS", "signInWithCredential:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

                            if (firebaseUser != null){
                                String id = firebaseUser.getUid();
                                addGoogleUser(account, id);
                                Log.d("SUCCESS", "successfully added Google user");
                            }
                            else{
                                Log.d("FAILURE", "failed to add Google user");
                            }
                        } else {
                            Log.w("FAILURE", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    private void addGoogleUser(GoogleSignInAccount account, String userId){
        UserDatabase userDatabase = new UserDatabase();
        userDatabase.getGoogleUser(account.getId(), new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                pbLogin.setVisibility(View.GONE);

                User user = new User((com.mobdeve.s14.group4.FirebaseUser) o);
                DataHelper.setGlobalUser(user); //add to static class
                Log.d("SUCCESS", "signInResult:success for " + account.getDisplayName());

                moveToHomeActivity();
            }

            @Override
            public void onFailure() {
                pbLogin.setVisibility(View.GONE);

                User user = new User(account.getId(), account.getDisplayName(), account.getGivenName(), account.getFamilyName());
                user.setUserId(userId);

                DataHelper.setGlobalUser(user); //add to static class
                new UserDatabase().addGoogleUser(user);

                moveToHomeActivity();
            }
        });
    }

    private void moveToHomeActivity(){
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
    }
}