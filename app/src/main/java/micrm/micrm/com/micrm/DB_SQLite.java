package micrm.micrm.com.micrm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DB_SQLite extends SQLiteOpenHelper {
    private static final String DATABASE="DBMICRM";
    private static final int VERSION = 1;

    public final String TablaPersonas ="Personas";
    public final String id_per = "id_per";
    public final String NombrePer = "NombrePer";
    public final String TelefonoPer = "TelefonoPer";
    public final String emailPer  ="emailPer";

    public final String TablaOrganizaciones ="Organizaciones";
    public final String id_Org = "id_Org";
    public final String NombreOrg = "NombreOrg";
    public final String DireccionOrg = "DireccionOrg";
    public final String TelefonoOrg  ="TelefonoOrg";

    public final String sql1 = "CREATE TABLE "+TablaPersonas+" ("+id_per+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NombrePer+" TEXT, "+TelefonoPer+" INTEGER,"+emailPer+" TEXT" +")";

    public final String sql2 = "CREATE TABLE "+TablaOrganizaciones+" ("+id_Org+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NombreOrg+" TEXT, "+DireccionOrg+" TEXT,"+TelefonoOrg+" INTEGER" +")";

    public DB_SQLite(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql1);
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TablaPersonas);
        db.execSQL("DROP TABLE IF EXISTS "+TablaOrganizaciones);
        db.execSQL(sql1);
        db.execSQL(sql2);

    }
}
