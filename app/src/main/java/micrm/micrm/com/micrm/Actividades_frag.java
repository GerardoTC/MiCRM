package micrm.micrm.com.micrm;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Actividades_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Actividades_frag extends Fragment implements View.OnClickListener{
    private Button agregarActividad;

    private OnFragmentInteractionListener mListener;

    public Actividades_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_actividades_frag, container, false);
        agregarActividad = (Button) v.findViewById(R.id.agregar_actividad);
        agregarActividad.setOnClickListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.agregar_actividad){
        AlertDialog.Builder cuadroDialogo = new AlertDialog.Builder(getContext());
        cuadroDialogo.setTitle("Agregar Actividad");
        cuadroDialogo.setMessage("Desea agregar activiidad?");
        cuadroDialogo.setCancelable(false);
        cuadroDialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Actividad agregada!", Toast.LENGTH_SHORT).show();
            }
        });
        cuadroDialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"Cancelado!",Toast.LENGTH_SHORT).show();
            }
        });
        cuadroDialogo.show();
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
