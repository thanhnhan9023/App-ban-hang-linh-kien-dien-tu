package Database.Database;

import android.arch.persistence.room.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

import Database.Model.GioHang;

public class DBHelper {


    Context context;

    String dbName = "Giohang.db";
    SQLiteDatabase db;
    public DBHelper() {

    }

    public DBHelper(Context context) {
        this.context = context;

    }

    private SQLiteDatabase openDB() {

        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }
    private void closeDB(SQLiteDatabase db) {
        db.close();
    }
    public void createTable() {
         db = openDB();
        String sqlGiohang = "CREATE TABLE IF NOT EXISTS GioHang (" +
                " ID_GioHang INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                " TenSp TEXT," +
                " LinkSp  TEXT," +
                " Giasp Float,"+"ThanhTien FLoat,"+"Soluongsp INTEGER,"+"ID_SP TEXT,"+"username);";
        db.execSQL(sqlGiohang);
        closeDB(db);
    }


    public  void deletetable()
    {
        db=openDB();
        String sql="DROP TABLE GioHang";
        db.execSQL(sql);
        closeDB(db);
    }


    public void insertGioHang(ArrayList<GioHang>  list){
        db = openDB();
        for(GioHang ca : list) {
            ContentValues cv = new ContentValues();
           // cv.put("ID_GioHang", ca.getIdgiohang());
            cv.put("TenSp", ca.getTensp());
            cv.put("LinkSp ",ca.getLinksp());
            cv.put("Giasp",ca.getGiasp());
            cv.put("ThanhTien",ca.getThanhtien());
            cv.put("Soluongsp",ca.getSoluongsp());
            cv.put("ID_SP",ca.getIdsp());
            cv.put("username",ca.getUsername());
            db.insert("GioHang", null, cv);
        }
        closeDB(db);
    }

    public  void xoa1sanpham(int idgiohang)
    {
       db=openDB();
        db.execSQL("DELETE FROM GioHang where ID_GioHang = ?", new Integer[]{idgiohang});
        db.close();
    }

    public  void xoa1giohang(String username)
    {
        db=openDB();
        db.execSQL("DELETE FROM GioHang where username='"+username+"'");
        db.close();
    }
    public  void capnhagiohang(String idsanpham,int soluongsp)
    {
            db=openDB();

        ContentValues cv = new ContentValues();
        //cv.put("ID_SP",idsanpham); //These Fields should be your String values of actual column names
        cv.put("Soluongsp",soluongsp);
      //  cv.put("Field2","Male");
        db.update("GioHang",cv,"ID_SP=?",new String[]{idsanpham});
        db.close();
//            db.execSQL("UPDATE product SET Soluongsp=?where ID_SP = ?",);
//            db.close();
    }

    public  void capnhatgiohangtheouser(String idsanpham,int soluongsp,String username)
    {
        db=openDB();
        db.execSQL("UPDATE GioHang set Soluongsp='"+soluongsp+"' WHERE ID_SP='"+idsanpham+"' and username='"+username+"'");
        db.close();
    }
    public  boolean kiemtrasanphamgiohang(String idsanpham)
    {       db=openDB();

            Cursor csr;
            //db.execSQL("select * from GioHang where ID_SP=?",new String[]{idsanpham});
             csr=db.rawQuery("select * from GioHang where ID_SP=?", new String[]{idsanpham});
             db.close();
          if(csr!=null) {
              Log.d("select1", "zo kiem tra");
              return true;
          }
          else if(csr==null)
              Log.d("select", "zo kiem tra");
        return  false;

    }

    public  void updateusergiohang(String username,String username2)
    {
        db=openDB();

        ContentValues cv = new ContentValues();
        //cv.put("ID_SP",idsanpham); //These Fields should be your String values of actual column names
        cv.put("username",username2);
        //  cv.put("Field2","Male");
        db.update("GioHang",cv,"username=?",new String[]{username});
        db.close();
    }


    public boolean isItemAvailable( String idsanpham,String username) {
        db=openDB();
        boolean exists = false;
        String query = "SELECT * FROM " + "GioHang" + " WHERE ID_SP='"+idsanpham+"' and username='"+username+"'";
        try {
         Cursor cursor=db.rawQuery(query,null);

            exists = (cursor.getCount() > 0);
            cursor.close();

        } catch (SQLiteException e) {

            e.printStackTrace();
            db.close();
        }
        return exists;
    }


    public ArrayList<GioHang> getALLGioHang(String username){
        db = openDB();
        ArrayList<GioHang> arr = new ArrayList<>();
        String sql = "select * from GioHang  where username='"+username+"'";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String tensp = csr.getString(1);
                    String linksp=csr.getString(2);
                    Float giasp=csr.getFloat(3);
                    Float thanhtien=csr.getFloat(4);
                    Integer soluong=csr.getInt(5);
                    String idsanpham=csr.getString(6);
                    String username1=csr.getString(7);
                    arr.add(new GioHang(id,tensp,idsanpham,linksp,giasp,thanhtien,soluong,username1));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }


}
