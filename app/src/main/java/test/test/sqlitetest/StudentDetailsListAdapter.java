package test.test.sqlitetest;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
public class StudentDetailsListAdapter extends ArrayAdapter<Student>{
    public StudentDetailsListAdapter(Context context, ArrayList<Student> students){
        super(context,R.layout.student_row,students);
    }
    private static class ViewHolder {
        TextView txtName;
        TextView txtRollNo;
        TextView txtID;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.student_row, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtRollNo = (TextView) convertView.findViewById(R.id.rollnum);
            viewHolder.txtID = (TextView) convertView.findViewById(R.id.ID);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtName.setText(student.name);
        viewHolder.txtRollNo.setText(student.rollNum);
        String ID = position+1 +"";
        viewHolder.txtID.setText(ID);
        return convertView;
    }
}
