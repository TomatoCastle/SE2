package forest.model;

import java.util.List;
import forest.view.TreePane;

/**
 * Forestの一つのTreeを表すクラス
 */
public class Tree {

	/**
	 * 含まれるNode一覧
	 */
	private List<Node> nodes;

	/**
	 * View一覧
	 */
	private List<TreePane> views;

	/**
	 * Nodeを新しくTreeに加える
	 */
	public void addNode(String name) {

	}

	/**
	 * ノード一覧を返す
	 */
	public List<Node> getNodes() {
		return null;
	}

	/**
	 * Viewに状態が変化したことを通知する
	 */
	public void notifyChangeToViews() {

	}

	/**
	 * Viewを追加する
	 */
	public void addView(TreePane treePane) {

	}

}
