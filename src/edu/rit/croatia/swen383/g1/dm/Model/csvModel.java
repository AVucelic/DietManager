package Model;

import java.io.IOException;
import java.util.ArrayList;

public abstract class csvModel {
    protected FileHandler fh;

    public csvModel(FileHandler fh) {
        this.fh = fh;
    }

    public abstract ArrayList<Object> read(String filepath) throws IOException;

    public abstract void write(String filepath, Object item) throws IOException;

    public abstract void update(int index, Object item) throws IOException;

    public abstract void remove(Object item) throws IOException;

    public abstract ArrayList<Object> getData();

    public abstract void setData(ArrayList<Object> data);
}
