package ma.enset.appexamen_sokainadaabal.BD;





import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName ="table_name")

public class MainData implements Serializable {

    //Create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;

    // Create text column
    @ColumnInfo(name="text")
    private String text;

    @ColumnInfo(name="text1")
    private String nb;
    @ColumnInfo(name="text2")
    private String capitale;

    //Generate getters and setters

    public int getID() {
        return ID;
    }

    public String getText() {
        return text;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNb() {
        return nb;
    }

    public void setNb(String nb) {
        this.nb = nb;
    }

    public String getCapitale() {
        return capitale;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }
}

