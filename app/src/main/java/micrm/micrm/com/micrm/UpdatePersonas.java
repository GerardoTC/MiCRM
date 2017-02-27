package micrm.micrm.com.micrm;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import micrm.micrm.com.micrm.R;

public class UpdatePersonas extends AppCompatActivity {
    EditText Nombre,Telefono,email;
    Button actualizar;
    SQLite_Personas sqLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personas);
        final String id = getIntent().getExtras().getString("id");
        Nombre = (EditText) findViewById(R.id.nuevoNombrePer);
        Telefono = (EditText) findViewById(R.id.nuevoTelefonoPer);
        email = (EditText) findViewById(R.id.nuevoEmailPer);
        actualizar = (Button) findViewById(R.id.AcutalizarPersona);
        sqLite = new SQLite_Personas(this);
        sqLite.abrir();

        Cursor cursor = sqLite.ConsultarRegistro(id);
        if(cursor.moveToFirst()){
            Nombre.setText(cursor.getString(0));
            Telefono.setText(cursor.getString(1));
            email.setText(cursor.getString(2));
        }

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sqLite.actualizarDatos(id,Nombre.getText().toString(),Telefono.getText().toString(),
                        email.getText().toString()))
                {
                    Toast.makeText(UpdatePersonas.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdatePersonas.this,PersonasList.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(UpdatePersonas.this, "Error! Verifique sus datos ingresados", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
