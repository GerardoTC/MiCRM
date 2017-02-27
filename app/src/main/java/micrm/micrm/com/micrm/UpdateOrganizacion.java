package micrm.micrm.com.micrm;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateOrganizacion extends AppCompatActivity {
    EditText nombre;
    EditText direccion;
    EditText telefono;
    Button actualizar;
    SQLite_Organizacion sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_organizacion);

        final String id = getIntent().getExtras().getString("id");

        nombre = (EditText) findViewById(R.id.updateOrg_nombre);
        direccion = (EditText) findViewById(R.id.updateOrg_Dir);
        telefono =(EditText) findViewById(R.id.updateOrg_tel);
        actualizar = (Button) findViewById(R.id.Actualizar_org);

        sqLite = new SQLite_Organizacion(this);
        sqLite.abrir();
        Cursor cursor = sqLite.ConsultarRegistro(id);
        if(cursor.moveToFirst()){
            nombre.setText(cursor.getString(0));
            direccion.setText(cursor.getString(1));
            telefono.setText(cursor.getString(2));
        }
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(sqLite.actualizarDatos(id,nombre.getText().toString(),direccion.getText().toString(),
                        telefono.getText().toString()))
                {
                    Toast.makeText(UpdateOrganizacion.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateOrganizacion.this,OrganizacionesList.class);

                    startActivity(i);
                }
                else
                {
                    Toast.makeText(UpdateOrganizacion.this, "Error! Verifique sus datos ingresados", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
