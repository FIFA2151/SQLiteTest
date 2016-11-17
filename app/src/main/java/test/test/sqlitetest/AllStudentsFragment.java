package test.test.sqlitetest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class AllStudentsFragment extends Fragment {
    TextView counter;
    public AllStudentsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_all_students, container, false);
        final DatabaseHelper db = new DatabaseHelper(getContext());
        counter = (TextView) view.findViewById(R.id.counterTextView);
        ListView list = (ListView) view.findViewById(R.id.students_list_view);
        ArrayList<Student> students = db.getStudents();
        counter.append(Integer.toString(students.size()));
        StudentDetailsListAdapter adapter = new StudentDetailsListAdapter(getContext(),students);
        list.setAdapter(adapter);
        list.deferNotifyDataSetChanged();
        return view;
    }

}
