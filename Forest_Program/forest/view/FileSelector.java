package forest.view;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

/**
 * @author TomatoCastle
 * Created by Tomato on 2018/07/21.
 */
public class FileSelector {
    /**
     * JfileChooserの初期設定をします。
     */
    JFileChooser fc;
    public FileSelector(){
        this.fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("text file", "txt");
        this.fc.addChoosableFileFilter(filter);
    }

    /**
     * ファイル選択ダイアログを表示して、選択されたファイルを返します。
     * @return 選択されたファイルオブジェクト。選択されなかった場合はnull。
     */

    public File selectFile() {
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        } else {
            return null;
        }
    }

    /**
     * ファイル選択ダイヤログを表示して、選択されたファイルの名前を返します。
     * @return 選択されたファイルの名前。選択されなかった場合はnull。
     */

    public String selectFileName(){
        File selcetdFile = this.selectFile();
        if (selcetdFile != null) return selcetdFile.getName();
        else return null;
    }
}

