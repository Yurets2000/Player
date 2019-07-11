package player;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class PlayerFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f != null) {
            String name = f.getName();
            int i = name.lastIndexOf('.');
            if (i > 0 && i < name.length() - 1) {
                return name.substring(i + 1).equalsIgnoreCase("wav");
            }
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "wav files";
    }
}