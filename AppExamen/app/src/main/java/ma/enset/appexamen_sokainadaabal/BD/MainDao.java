package ma.enset.appexamen_sokainadaabal.BD;



import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDao {

    // Insert query
    @Insert(onConflict =REPLACE)
    void insert(MainData mainData);
    // Delete query
    @Query("DELETE FROM table_name where ID=:sID")
    void delete(int sID);
    @Delete
    void reset(List<MainData> mainData);
    //to do

    // Update query
    @Query("UPDATE table_name SET text= :sText, text1= :sText1,text2 = :sText2 where ID=:sID")
    void update(int sID, String sText,String sText1,String sText2);

    // Get all data query
    @Query("SELECT * FROM table_name")
    List<MainData> getAll();


}
