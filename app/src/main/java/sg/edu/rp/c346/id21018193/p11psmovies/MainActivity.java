package sg.edu.rp.c346.id21018193.p11psmovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnShow, btnInsert, btnFilter;
    ListView lvMovies;
    ArrayList<Movies> alMoviesList;
    CustomAdapter caMovies;
    Spinner spnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test
        btnShow = findViewById(R.id.buttonShow);
        btnInsert = findViewById(R.id.buttonInsert);
        btnFilter = findViewById(R.id.buttonFilter);
        lvMovies = findViewById(R.id.listViewMovies);
//        spnFilter = findViewById(R.id.spinner);
        alMoviesList = new ArrayList<>();
        caMovies = new CustomAdapter(this, R.layout.row, alMoviesList);
        lvMovies.setAdapter(caMovies);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                alMoviesList.clear();
                alMoviesList.addAll(dbh.getAllMovie());
                caMovies.notifyDataSetChanged();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                       InsertActivity.class);
                startActivity(i);
            }
        });

        //filter isnt working for now
//        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String filtertext ="";
//                if(spnFilter.getSelectedItem().equals("G")){
//                    filtertext = "G";
//                } else if(spnFilter.getSelectedItem().equals("PG")) {
//                    filtertext = "PG";
//                } else if(spnFilter.getSelectedItem().equals("PG13")) {
//                    filtertext = "PG13";
//                } else if(spnFilter.getSelectedItem().equals("NC16")) {
//                    filtertext = "NC16";
//                } else if(spnFilter.getSelectedItem().equals("M18")){
//                    filtertext = "M18";
//                }else if(spnFilter.getSelectedItem().equals("R21")){
//                    filtertext = "R21";
//                }
//                DBHelper dbh = new DBHelper(MainActivity.this);
//                alMoviesList.clear();
//                alMoviesList.addAll(dbh.getFilterMovie(filtertext));
//                caMovies.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                // Inflate the filter.xml layout file
//                LayoutInflater inflater =
//                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View viewDialog = inflater.inflate(R.layout.filter, null);
//
//                // Obtain the UI component in the filter.xml layout
//                // It needs to be defined as "final", so that it can used in the onClick() method later
//                final TextView tvFilter = viewDialog.findViewById(R.id.textViewFilter);
//
//                final EditText etFilter = viewDialog.findViewById(R.id.editTextFilter);
//
//                if(spnFilter.getSelectedItem()=="Title"){
//                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
//                    myBuilder.setView(viewDialog);  // Set the view of the dialog
//                    myBuilder.setTitle("Filter Title");
//                    tvFilter.setText("Enter title name to filter");
//                    myBuilder.setPositiveButton("FILTER", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // Extract the text entered by the user
//                            String filterword = etFilter.getText().toString();
//                            // Set the text to the TextView
//                            DBHelper dbh = new DBHelper(MainActivity.this);
//                            alMoviesList.clear();
//                            alMoviesList.addAll(dbh.getFilterTitle(filterword));
//                            caMovies.notifyDataSetChanged();
//                        }
//                    });
//                    myBuilder.setNegativeButton("CANCEL", null);
//                    AlertDialog myDialog = myBuilder.create();
//                    myDialog.show();
//                } else if (spnFilter.getSelectedItem()=="Genre"){
//
//                } else if (spnFilter.getSelectedItem()=="Year"){
//
//                } else if (spnFilter.getSelectedItem()=="Rating"){
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        btnFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Inflate the filter.xml layout file
//                LayoutInflater inflater =
//                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View viewDialog = inflater.inflate(R.layout.filter, null);
//
//                // Obtain the UI component in the filter.xml layout
//                // It needs to be defined as "final", so that it can used in the onClick() method later
//                final EditText etFilter = viewDialog.findViewById(R.id.editTextFilter);
//
//                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
//                myBuilder.setView(viewDialog);  // Set the view of the dialog
//                myBuilder.setTitle("Filter Title");
//                myBuilder.setPositiveButton("FILTER", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                            // Extract the text entered by the user
//                            String filterword = etFilter.getText().toString();
//                            // Set the text to the TextView
//                            DBHelper dbh = new DBHelper(MainActivity.this);
//                            alMoviesList.clear();
//                            alMoviesList.addAll(dbh.getFilterTitle(filterword));
//                            caMovies.notifyDataSetChanged();
//                        }
//                    });
//                myBuilder.setNegativeButton("CANCEL", null);
//                AlertDialog myDialog = myBuilder.create();
//                myDialog.show();
//            }
//        });


        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Movies data = alMoviesList.get(position);
                Intent editI = new Intent(MainActivity.this, ModifyActivity.class);
                editI.putExtra("data", data);
                startActivity(editI);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        DBHelper dbh = new DBHelper(MainActivity.this);
        alMoviesList.clear();
        alMoviesList.addAll(dbh.getAllMovie());
        caMovies.notifyDataSetChanged();
    }
}