package GUI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlythietbidientu2.Model.UserKhachHang;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Maps;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResiginActivity extends AppCompatActivity {

    private Button btnSign;

    private EditText edtUserND1, edtGmail, edtPasswrod;
    FirebaseAuth firebaseAuth; //  authfirebase
    FirebaseFirestore firebaseFirestore; // đưa dữ liệu lên firbase
    ResultAPI mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resigin);
        anhxa();
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin(edtGmail.getText().toString().trim(), edtPasswrod.getText().toString().trim());
            }
        });

    }
        public  void anhxa()
        {
        btnSign=findViewById(R.id.btnSignIn);
        edtGmail=findViewById(R.id.edtGmail);
        edtPasswrod=findViewById(R.id.edtPassword);
        edtUserND1=findViewById(R.id.edtUserND);
        }
    private void checkLogin(String email, String password) {

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            Map<String,Object> userdata = new HashMap<>();

            userdata.put("tt",edtUserND1.getText().toString());
            Log.d("check", "onCdsads ");
             firebaseFirestore.collection("User").add(userdata).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                 @Override
                 public void onComplete(@NonNull Task<DocumentReference> task) {
                     if (task.isSuccessful())
                     {
                         mService.saveuserkhachhang(firebaseAuth.getCurrentUser().getEmail(),
                                 edtPasswrod.getText().toString(),"KH001").enqueue(new Callback<UserKhachHang>() {
                             @Override
                             public void onResponse(Call<UserKhachHang> call, Response<UserKhachHang> response) {
                           //    getApplicationContext().wait(300);
                         }

                             @Override
                             public void onFailure(Call<UserKhachHang> call, Throwable t) {

                             }
                         });
                         ;

                         startActivity(new Intent(ResiginActivity.this,SignActivity.class));
                         finish();
                     }
                 }
             });






        });
    }
}









