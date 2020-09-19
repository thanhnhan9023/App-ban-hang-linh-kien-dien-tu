package Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythietbidientu2.Model.KhachHang;
import com.example.quanlythietbidientu2.Model.LoaiProduct;
import com.example.quanlythietbidientu2.Model.UserKhachHang;
import com.example.quanlythietbidientu2.R;
import com.example.quanlythietbidientu2.Retrofit.ResultAPI;
import com.example.quanlythietbidientu2.Utils.Common;
import com.google.firebase.database.collection.LLRBNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import GUI.SignActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RewardFragment extends Fragment {

private TextView dateNgaysinh,txtNgaySinh,txthaydoimatkhau;
private EditText txtDiachi,txtTenKhachHang,txtSodienthoai,txttendangnhap;
private RadioGroup radioGroup;
private RadioButton rdnam,rdnu;
private DatePickerDialog.OnDateSetListener datePickerDialog;
private Button btnLuu;
private ResultAPI mService;
private  String makh="";
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RewardFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        // Inflate the layout for this fragment
        mService=Common.getAPI();
          dateNgaysinh=view.findViewById(R.id.TxtThietLap);
          txtNgaySinh=view.findViewById(R.id.txtNgaySinh);
          txthaydoimatkhau=view.findViewById(R.id.txtThaydoimatkhau);
          txtSodienthoai=view.findViewById(R.id.txtSodienthoai);
          txtTenKhachHang=view.findViewById(R.id.txtTenKhachHang);
          txttendangnhap=view.findViewById(R.id.txtTendangnhap);
          rdnam=view.findViewById(R.id.rdNam);
          rdnu=view.findViewById(R.id.rdNu);
          txtDiachi=view.findViewById(R.id.txtDiachi);
            btnLuu=view.findViewById(R.id.btnLuuKH);
            btnLuu.setEnabled(false);
            getMakh();
            dateNgaysinh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar cal=Calendar.getInstance();
                    int day=cal.get(Calendar.DAY_OF_MONTH);
                    int month=cal.get(Calendar.MONTH);
                    int year=cal.get(Calendar.YEAR);

                    DatePickerDialog dialog=new DatePickerDialog(getContext(),android.R.style.Theme_Holo_Light_DarkActionBar,datePickerDialog,day,month,year);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
                datePickerDialog =new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                          //  dateNgaysinh.setVisibility(View.INVISIBLE);
                            txtNgaySinh.setText(dayOfMonth+"/"+(month+1)+"/"+year);


                    }
                };




                if(Common.getFirebaseAuth().getCurrentUser()!=null)
                {
                    btnLuu.setEnabled(true);
                }
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Date date=java.sql.Date.valueOf(txtNgaySinh.getText().toString());

                        Date date1= new Date();

                        try {
                            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgaySinh.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        String  gioitinh;
                        if(rdnam.isChecked())
                        {
                            gioitinh="Nam";
                        }
                        else
                        {
                            gioitinh="Nữ";
                        }


                        mService.savethongtinkhachang(makh,txtTenKhachHang.getText().toString(),
                                date1,txtDiachi.getText().toString(),txtSodienthoai.getText().toString(),gioitinh,"LK003").enqueue
                                (new Callback<KhachHang>() {
                            @Override
                            public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                                KhachHang kh=response.body();
                                if(kh!=null)
                                {

                                    Toast.makeText(getContext(), "update thành công", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<KhachHang> call, Throwable t) {

                            }
                        });
                    }
                });
        return  view;

    }
    private void getMakh() {

        compositeDisposable.add(mService.getMakhtheouser(Common.getmail()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()
                ).subscribe(new Consumer<List<KhachHang>>() {


            @Override
            public void accept(List<KhachHang> dskh) throws Exception {

                        makh=dskh.get(0).getMaKH();
                        if(makh==null)
                        {
                            return;
                        }


            }
        }));

    }
}