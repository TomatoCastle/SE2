package forest.utility;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * @author TomatoCastle
 * Created by Tomato on 2018/07/21.
 */
public class FileSelector extends Object{

    /**
     * ファイル選択するためのオブジェクトを束縛するフィールド。
     */
    private JFileChooser aFileChoser;

    /**
     * JfileChooserの初期設定をします。
     */
    public FileSelector(){
        this.aFileChoser = new JFileChooser();
    }

    /**
     * ファイル選択ダイアログを表示して、選択されたファイルオブジェクトを返します。
     * @return 選択されたファイルオブジェクト。選択されなかった場合はnull。
     */
    public File selectFile(){
        this.aFileChoser.resetChoosableFileFilters();
        int returnVal = aFileChoser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            return aFileChoser.getSelectedFile();
        }else{
            return null;
        }
    }

    /**
     * ファイル選択ダイアログを表示して、選択されたファイルオブジェクトを返します。
     * @param filter ファイルの選択に条件をつけるためのフィルターオブジェクト
     * @return 選択されたファイルオブジェクト。選択されなかった場合はnull。
     */
    public File selectFile(FileNameExtensionFilter filter){
        this.aFileChoser.setFileFilter(filter);
        int returnVal = aFileChoser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            return aFileChoser.getSelectedFile();
        }else{
            return null;
        }
    }

    /**
     * ファイル選択ダイヤログを表示して、選択されたファイルの名前を返します。
     * @return 選択されたファイルの名前。選択されなかった場合はnull。
     */

    public String selectFileName(){
        File selcetdFile = this.selectFile();
        if(selcetdFile != null){
            return selcetdFile.getName();
        }
        else{
            return null;
        }
    }
    /**
     * ファイル選択ダイヤログを表示して、選択されたファイルの名前を返します。
     * @param filter ファイルの選択に条件をつけるためのフィルターオブジェクト。
     * @return 選択されたファイルの名前。選択されなかった場合はnull
     */

    public String selectFileName(FileNameExtensionFilter filter){
        File selcetdFile = this.selectFile(filter);
        if(selcetdFile != null){
            return selcetdFile.getName();
        }
        else{
            return null;
        }
    }
}

