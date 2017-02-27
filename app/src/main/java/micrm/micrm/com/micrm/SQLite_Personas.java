package micrm.micrm.com.micrm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import micrm.micrm.com.micrm.DB_SQLite;

/**
 * Created by elizabethtarazona on 25/02/2017.
 */

public class SQLite_Personas {
    private DB_SQLite mBDSQLite;
    private SQLiteDatabase db;

    public SQLite_Personas(Context context){
        mBDSQLite = new DB_SQLite(context);
    }

    public void abrir(){
        Log.i("SQLite_Personas","SE ABRE CONEXION A LA BASE DE DATOS "+mBDSQLite.getDatabaseName());
        db = mBDSQLite.getReadableDatabase();
    }

    public void cerrar(){
        Log.i("SQLite_Personas","SE CIERRA CONEXION A LA BASE DE DATOS "+mBDSQLite.getDatabaseName());
        db.close();
    }
    public boolean agregarRegistro(String NombrePer,String TelefonoPer,String emailPer){
        if(NombrePer.length()>0)
        {
            ContentValues values = new ContentValues();
            values.put(mBDSQLite.NombrePer,NombrePer);
            values.put(mBDSQLite.TelefonoPer,TelefonoPer);
            values.put(mBDSQLite.emailPer,emailPer);

            Log.i("SQLite_Personas","Nuevo registro generado");
            return(db.insert(mBDSQLite.TablaPersonas,null,values)!=-1);
        }
        else{
            return  false;
        }
    }

    public boolean actualizarDatos(String id,String NombrePer,String TelefonoPer,String emailPer){
        ContentValues modificar= new ContentValues();
        modificar.put(mBDSQLite.id_per,id);
        modificar.put(mBDSQLite.NombrePer,NombrePer);
        modificar.put(mBDSQLite.TelefonoPer,TelefonoPer);
        modificar.put(mBDSQLite.emailPer,emailPer);
        int actualizar = db.update(mBDSQLite.TablaPersonas,modificar,mBDSQLite.id_per+"= "+id,null);
        Log.i("SQLite_Personas","Datos actualizados");

    return ((actualizar==1)?true:false);
    }
    public Cursor ConsultarRegistro(String id){
        String sql = "SELECT "+mBDSQLite.NombrePer+","+mBDSQLite.TelefonoPer+","+mBDSQLite.emailPer+
                " FROM "+mBDSQLite.TablaPersonas+" WHERE "+mBDSQLite.id_per+"= "+id;
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;

    }

    public boolean Borrar (String id){
        int eliminar = db.delete(mBDSQLite.TablaPersonas,mBDSQLite.id_per+"="+id,null);
        return ((eliminar==1)?true:false);
    }

    public Cursor ConsultarRegistros(){

        Cursor cursor =
                db.query(mBDSQLite.TablaPersonas,new String[]{
                        mBDSQLite.id_per,mBDSQLite.NombrePer,mBDSQLite.TelefonoPer,mBDSQLite.emailPer},
                        null,null,null,null,null);
        return  cursor;
    }
    public ArrayList<String> getFormatList(Cursor cursor ){
        ArrayList<String> Lista_datos = new ArrayList<String>();
        String item = "";
        if(cursor.moveToFirst()){
            do {
                item += "ID:[" + cursor.getInt(0) + "]\r\n";
                item += "Nombre: " + cursor.getString(1) + "\r\n";
                item += "Telefono: " + cursor.getString(2) + "\r\n";
                item += "e-Mail: " + cursor.getString(3) + "\r\n";
                Lista_datos.add(item);
                item="";
            }while (cursor.moveToNext());
            return Lista_datos;
        }
        return Lista_datos;
    }

    public ArrayList<String> getIds(Cursor cursor ){
        ArrayList<String> Lista_ids = new ArrayList<String>();
        String item = "";
        if(cursor.moveToFirst()){
            do {
                item+=cursor.getString(0);
                Lista_ids.add(item);
                item="";
            }while (cursor.moveToNext());
            return Lista_ids;
        }
        return Lista_ids;
    }
}
