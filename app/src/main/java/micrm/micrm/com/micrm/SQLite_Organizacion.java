package micrm.micrm.com.micrm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by elizabethtarazona on 25/02/2017.
 */

public class SQLite_Organizacion {
    private  DB_SQLite mBDSQLite;
    private SQLiteDatabase db;

    public SQLite_Organizacion(Context context){
        mBDSQLite = new DB_SQLite(context);
    }

    public void abrir(){
        Log.i("SQLite","SE ABRE CONEXION A LA BASE DE DATOS "+mBDSQLite.getDatabaseName());
        db = mBDSQLite.getReadableDatabase();
    }

    public void cerrar(){
        Log.i("SQLite","SE CIERRA CONEXION A LA BASE DE DATOS "+mBDSQLite.getDatabaseName());
        db.close();
    }
    public boolean add(String Nombre,String Direccion,String Telefono ){
        if(Nombre.length()>0)
        {
            ContentValues values = new ContentValues();
            values.put(mBDSQLite.NombreOrg,Nombre);
            values.put(mBDSQLite.DireccionOrg,Direccion);
            values.put(mBDSQLite.TelefonoOrg,Telefono);

            Log.i("SQLite","Nuevo registro generado");
            boolean d =  (db.insert(mBDSQLite.TablaOrganizaciones,null,values)!=-1);


            return d;
        }
        else{
            return  false;
        }
    }
    public boolean Borrar(String id){
        int a = db.delete(mBDSQLite.TablaOrganizaciones,mBDSQLite.id_Org + "=" + id,null);
        if(a==1){
          return true;
        }
        else {
          return false;
        }
    }
    public boolean actualizarDatos(String id, String Nombre, String Direccion, String Telefono) {
        ContentValues values = new ContentValues();
        values.put(mBDSQLite.NombreOrg,Nombre);
        values.put(mBDSQLite.DireccionOrg,Direccion);
        values.put(mBDSQLite.TelefonoOrg,Telefono);
        int actualizar = db.update(mBDSQLite.TablaOrganizaciones,values,mBDSQLite.id_Org+"= "+id,null);


        return ((actualizar==1)?true:false);
    }

    public Cursor ConsultarRegistros(){

        Cursor cursor =
                db.query(mBDSQLite.TablaOrganizaciones,new String[]{
                                mBDSQLite.id_Org,mBDSQLite.NombreOrg,mBDSQLite.DireccionOrg,mBDSQLite.TelefonoOrg},
                        null,null,null,null,null);

        return  cursor;
    }
    public Cursor ConsultarRegistro(String id){
        String sql = "SELECT "+mBDSQLite.NombreOrg+","+mBDSQLite.DireccionOrg+","+mBDSQLite.TelefonoOrg+
                " FROM "+mBDSQLite.TablaOrganizaciones+" WHERE "+mBDSQLite.id_Org+"= "+id;
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;

    }
    public ArrayList<String> getFormatList(Cursor cursor ){
        ArrayList<String> Lista_datos = new ArrayList<String>();
        String item = "";
        if(cursor.moveToFirst()){
            do {
                item += "ID:[" + cursor.getInt(0) + "]\r\n";
                item += "Nombre:" + cursor.getString(1) + "\r\n";
                item += "Direccion:" + cursor.getString(2) + "\r\n";
                item += "Telefono:" + cursor.getString(3) + "\r\n";
                Lista_datos.add(item);
                item="";
            }while (cursor.moveToNext());
            return Lista_datos;
        }
        return Lista_datos;
    }
    public ArrayList<String> getIds(Cursor cursor ){
        ArrayList<String> Lista_datos = new ArrayList<String>();
        String item = "";
        if(cursor.moveToFirst()){
            do {
                item += cursor.getString(0);
                Lista_datos.add(item);
                item="";
            }while (cursor.moveToNext());
            return Lista_datos;
        }
        return Lista_datos;
    }

}
