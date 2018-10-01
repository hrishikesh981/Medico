package com.example.grajmane.medico_1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Register extends AppCompatActivity {


    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init_main();
    }

    public void init_main()
    {
        Button btn=(Button) findViewById(R.id.login_regactivity_but);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_login=new Intent(Register.this,Login.class);
                startActivity(main_login);
            }
        });
    }


    public void register(View view) {
        EditText euname=findViewById(R.id.email_register);
        EditText epwd=findViewById(R.id.password_2_register);
        EditText epwd2=findViewById(R.id.passwords_register);
        //final TextView tset=findViewById(R.id.textView4);
        if(epwd.getText().toString().equals(epwd2.getText().toString()))
        {
            mFirebaseAuth.createUserWithEmailAndPassword(euname.getText().toString(),epwd.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("", "createUserWihEmail:Successful");
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                if (user != null) {
                                    gotoHome(user);
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                               // tset.setText(task.getException().toString());
                            }
                        }
                    });
        }


    }



    public void gotoLogin(View view) {
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);


    }private void gotoHome( FirebaseUser currFirebaseUser) {

        Intent intent=new Intent(this,NavigationD.class);
//        TextView tset1=findViewById(R.id.navbar_email);
//        tset1.setText(currFirebaseUser.getEmail());
        startActivity(intent);
    }

}
