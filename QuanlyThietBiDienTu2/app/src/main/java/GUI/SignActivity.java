package GUI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythietbidientu2.Model.UserKhachHang;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import Database.Database.DBHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignActivity extends AppCompatActivity {



        private  EditText edtUserDN,edtPassDN;
        private  Button  btnLogin,btnRegister;
        private  TextView TxtForegetPass;
        ResultAPI mService;

    FirebaseAuth firebaseAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        anhxa();
        mService=Common.getAPI();
        DBHelper data=new DBHelper(getApplicationContext());
        btnLogin.setEnabled(false);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
            private  void checkLogin()
            {
                if(CheckVaildEmail(edtUserDN.getText().toString().trim()))
                {
                    if(edtPassDN.getText().length()>=6)
                    {
                        firebaseAuth=FirebaseAuth.getInstance();

                        firebaseAuth.signInWithEmailAndPassword(edtUserDN.getText().toString(),
                                edtPassDN.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    mService.saveuserkhachhang(edtUserDN.getText().toString(),
                                            edtPassDN.getText().toString(),null).enqueue(new Callback<UserKhachHang>() {
                                        @Override
                                        public void onResponse(Call<UserKhachHang> call, Response<UserKhachHang> response) {
                                            UserKhachHang userKhachHang=response.body();
                                            if(userKhachHang!=null)
                                            {
                                                Toast.makeText(SignActivity.this, "Thêm user thành công", Toast.LENGTH_SHORT).show();

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<UserKhachHang> call, Throwable t) {

                                        }
                                    });

                                    data.updateusergiohang("",edtUserDN.getText().toString());
                                    Common.email=edtUserDN.getText().toString();


                                   // Common.username=edtUserDN.getText().toString();
                                    startActivity(new Intent(SignActivity.this, HomeActivity.class));
                                    finish();
                                }
                                else
                                {

                                    Toast.makeText(SignActivity.this,"Sai tài khoản mật khẩu" ,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        {

                        }

                    }
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(SignActivity.this, ResiginActivity.class));
              //finish();
            }
        });
        edtUserDN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPassDN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TxtForegetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(SignActivity.this,ForrgetPassActivity.class));
//                finish();
//                Toast.makeText(SignActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void anhxa()
    {
        edtUserDN=findViewById(R.id.edtUserDangNhap);
        edtPassDN=findViewById(R.id.edtpasswordDN);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister1);
        TxtForegetPass=findViewById(R.id.txtForegetPassword);

    }


    private void checkinput()
    {
        if(CheckTextEmpty(edtUserDN.getText().toString().trim())
                && CheckTextEmpty(edtPassDN.getText().toString().trim()))
        {
            btnLogin.setEnabled(true);
        }
        else
        {
            btnLogin.setEnabled(false);
        }
    }

    public  boolean CheckTextEmpty(String s)
    {
        if(!TextUtils.isEmpty(s))
            return  true;
        return  false;
    }
    public  boolean CheckVaildEmail(String s)
    {
        String emailregex="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern=Pattern.compile(emailregex);
        if(!CheckTextEmpty(s) || !pattern.matcher(s).matches())
            return  false;
        return  true;
    }
}