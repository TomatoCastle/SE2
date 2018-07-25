package forest;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import javax.swing.JFrame;

import forest.model.ForestModel;
import forest.view.ForestView;
import forest.view.FileSelector;
import forest.controller.ForestController;

import java.awt.image.BufferedImage;
import java.awt.Dimension;

/**
 * 樹状整列の例題クラス：使い方の典型を示すのが目的のプログラムです。<br>
 * Makefileを用いた実行方法は以下の通りです。<br>
 * $ make tree  # 木を整列描画<br>
 * $ make forest  # 森を整列描画<br>
 * $ make semilattice  # 亜格子状の森を整列描画<br>
 */
public class Example extends Object
{
	/**
	 * 第1引数で樹状整列データファイルを受け取って樹状整列を実行します。<br>
	 * $ java -Dfile.encoding=UTF-8 -Xmx512m -Xss1024k -jar forest.jar resource/data/tree.txt<br>
	 * $ java -Dfile.encoding=UTF-8 -Xmx512m -Xss1024k -jar forest.jar resource/data/forest.txt<br>
	 * $ java -Dfile.encoding=UTF-8 -Xmx512m -Xss1024k -jar forest.jar resource/data/semilattice.txt<br>
	 * @param arguments 樹状整列データファイルを第1引数とする引数文字列群
	 */
	public static void main(String[] arguments)
	{
		File aFile;
		// 引数が無い（樹状整列データファイルの在り処がわからない）をチェックする。
		if (arguments.length < 1)
		{
			System.err.println("You need to select a file.");
			FileSelector aFileSelector = new FileSelector();
			aFile = aFileSelector.selectFile();
			// System.exit(1);
		} else {
			aFile = new File(arguments[0]);
		}

		// 指定された樹状整列データファイルの存在をチェックする。
		if (!(aFile.exists()))
		{
			System.err.println("'" + aFile + "' does not exist.");
			System.exit(1);
		}

		// MVCを作成する。
		ForestModel aModel = new ForestModel(aFile);
		ForestController aController = new ForestController();
		aController.setModel(aModel);
		ForestView aView = new ForestView(aModel, aController);

		JFrame aWindow = new JFrame("Forest");
		aWindow.getContentPane().add(aView);
		Dimension aDimension = new Dimension(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
		aWindow.setMinimumSize(aDimension);
		aWindow.setMaximumSize(aDimension);
		aWindow.setResizable(false);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.addNotify();
		Integer titleBarHeight = aWindow.getInsets().top;
		aWindow.setSize(aDimension.width, aDimension.height + titleBarHeight);
		aWindow.setLocation(50, 50);
		aWindow.setVisible(true);
		aWindow.toFront();
		aModel.animate();

		return;
	}
}
