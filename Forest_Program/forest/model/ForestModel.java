package forest.model;

import java.util.List;
import forest.view.TreePane;

/**
 * ForestのModelを表すクラス
 */
public class ForestModel extends Object {

	/**
	 * Treeのインスタンスの配列を保持する
	 */
	private List<Tree> trees;

	/**
	 * viewのインスタンスを保持する
	 */
	private List<TreePane> views;

	/**
	 * modelの変更をviewに伝える
	 */
	public void notifyChangeToViews() {

	}

	/**
	 * viewを追加する
	 */
	public void addView(TreePane treePane) {

	}

	/**
	 * ファイルを読み込みTreeを作成する
	 */
	public void read(String fileName) {

	}

}
