package test.test.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "StudentsDatabase";
    public static final String TABLE_NAME = "students";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String ROLL_NUMBER_COLUMN = "roll_number";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME +
                        "( "+ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        NAME_COLUMN+" TEXT NOT NULL," +
                        ROLL_NUMBER_COLUMN+" TEXT NOT NULL UNIQUE )"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+"");
        onCreate(db);
    }
    public long insertStudents(String name, String rollNumber){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN,name);
        contentValues.put(ROLL_NUMBER_COLUMN,rollNumber);
        return db.insert(TABLE_NAME,null,contentValues);
    }
    public ArrayList<Student> findStudents(String keyword){
        ArrayList<Student> students= new ArrayList<Student>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM "+TABLE_NAME+" where "+NAME_COLUMN+" like '%"+keyword+"%' or "+ROLL_NUMBER_COLUMN+" like '%"+keyword+"%';", null);
        Log.d("Test","SELECT * FROM "+TABLE_NAME+" where "+NAME_COLUMN+" like '%"+keyword+"%' or "+ROLL_NUMBER_COLUMN+" like '%"+keyword+"%'");
        return makeListFromCursor(rs);
    }
    public ArrayList<Student> getStudents(){

        SQLiteDatabase db = getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM "+TABLE_NAME+";", null);
        return makeListFromCursor(rs);
    }
    private ArrayList<Student> makeListFromCursor(Cursor rs){
        ArrayList<Student> students= new ArrayList<Student>();
        rs.moveToFirst();
        while(!rs.isAfterLast()){
            String id = rs.getString(rs.getColumnIndex(ID_COLUMN));
            String name = rs.getString(rs.getColumnIndex(NAME_COLUMN));
            String rollNum = rs.getString(rs.getColumnIndex(ROLL_NUMBER_COLUMN));
            students.add(new Student(id,name,rollNum));
            rs.moveToNext();
        }
        rs.close();
        return students;
    }

}
