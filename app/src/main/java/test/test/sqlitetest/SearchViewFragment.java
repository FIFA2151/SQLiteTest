package test.test.sqlitetest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchViewFragment extends Fragment implements SearchView.OnQueryTextListener {

    SearchView mSearchView;
    DatabaseHelper db;
    ListView list;
    public SearchViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_view, container, false);
        mSearchView = (SearchView) view.findViewById(R.id.keyword);
        mSearchView.setOnQueryTextListener(this);
        db = new DatabaseHelper(getContext());
        list = (ListView) view.findViewById(R.id.search_list_view);
        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(query.length()==0){
            list.setAdapter(null);
            return false;
        }
        ArrayList<Student> students = db.findStudents(query);
        final StudentDetailsListAdapter adapter = new StudentDetailsListAdapter(getContext(),students);
        list.setAdapter(adapter);
        list.deferNotifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length()==0){
            list.setAdapter(null);
            return false;
        }
        ArrayList<Student> students = db.findStudents(newText);
        final StudentDetailsListAdapter adapter = new StudentDetailsListAdapter(getContext(),students);
        list.setAdapter(adapter);
        list.deferNotifyDataSetChanged();
        return true;
    }
}
