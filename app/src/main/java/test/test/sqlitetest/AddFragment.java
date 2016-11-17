package test.test.sqlitetest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFragment extends Fragment {
    DatabaseHelper db;
    EditText studentName, rollNo;
    Button addButton;
    String studentNameText, rollNoText;
    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add, container, false);
        studentName = (EditText) view.findViewById(R.id.student_name);
        rollNo = (EditText) view.findViewById(R.id.roll_num);
        addButton = (Button) view.findViewById(R.id.addToDatabase);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentNameText = studentName.getText().toString();
                rollNoText = rollNo.getText().toString();
                if(studentNameText.length()==0|rollNoText.length()==0){
                    Toast.makeText(getContext(),"Any fields should not be empty!",Toast.LENGTH_LONG).show();
                    return;
                }
                db = new DatabaseHelper(getContext());
                String response;
                if(db.insertStudents(studentNameText,rollNoText)!=-1){
                    response = "New Student added into database!";
                    studentName.setText("");
                    rollNo.setText("");
                }else{
                    response = "Roll number must be unique";
                }
                Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                Log.d("SQLiteTest",response);
            }
        });
        return view;
    }


}
