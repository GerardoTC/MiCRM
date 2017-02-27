package micrm.micrm.com.micrm;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrganizacionesList extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listView;
    ArrayAdapter<String> adapter;

    SQLite_Organizacion  sqLite;
    ArrayList<String> ListaIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizaciones_list);
        Toast.makeText(this, "Selecciona una organizacion para eliminar o modificar", Toast.LENGTH_SHORT).show();

        sqLite = new SQLite_Organizacion(this);
        sqLite.abrir();

        listView = (ListView) findViewById(R.id.ListaOrganizaciones);

        Cursor cursor = sqLite.ConsultarRegistros();

        ArrayList<String> ListaDatos =sqLite.getFormatList(cursor);
        ListaIDs = sqLite.getIds(cursor);

        adapter = new ArrayAdapter<String>(OrganizacionesList.this,android.R.layout.simple_list_item_1,ListaDatos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(OrganizacionesList.this);
        //Toast.makeText(getBaseContext(), cursor., Toast.LENGTH_SHORT).show();

        if(ListaDatos.size()==0){
            Toast.makeText(getBaseContext(), "No Existen Registros!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        final CharSequence[] option = {"Editar Organizacion", "Eliminar Organizacion", "Cancelar"};
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Elige una opción:");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Editar Organizacion"){
                    Intent i = new Intent(OrganizacionesList.this,UpdateOrganizacion.class);
                    i.putExtra("id",ListaIDs.get(position));
                    startActivity(i);

                }else if(option[which] == "Eliminar Organizacion"){
                    sqLite.Borrar(ListaIDs.get(position));
                    Intent i = new Intent(OrganizacionesList.this,OrganizacionesList.class);
                    startActivity(i);
                    Toast.makeText(OrganizacionesList.this, "Organizacion Eliminada!", Toast.LENGTH_SHORT).show();
                } else{
                    dialog.dismiss();
                }
            }
        });

        builder.show();

    }
}
