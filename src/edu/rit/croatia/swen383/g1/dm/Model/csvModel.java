package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;

public abstract class csvModel {
    protected FileHandler fileHandler;

    public abstract ArrayList<Object> read() throws IOException;

    public abstract void write() throws IOException;
}
