package GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.quanlythietbidientu2.Adapter.MenugiaohangAdapter;
import com.example.quanlythietbidientu2.R;

import java.util.ArrayList;

public class GiaoHang extends AppCompatActivity {

    RecyclerView recmenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_hang);
        recmenu=findViewById(R.id.RecMenuDonhang);
        recmenu.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayout.HORIZONTAL, false));
        recmenu.setHasFixedSize(true);

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Chờ xác nhận");
        arrayList.add("Chờ Lấy hàng");
        arrayList.add("Đang giao");
        arrayList.add("Đã Giao");
        arrayList.add("Đã Hủy");
        arrayList.add("Đã Thanh Toán");
        MenugiaohangAdapter menugiaohangAdapter=new MenugiaohangAdapter(this,arrayList);
        recmenu.setAdapter(menugiaohangAdapter);





    }
}