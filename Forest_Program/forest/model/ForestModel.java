package forest.model;

import mvc.Model;
import java.util.List;

/**
 *  ForestのModelを表すクラス
 * @author USUI Kazuma
 */
public class ForestModel extends Object, mvc.Model, Model {

	/**
	 * viewのリストを束縛する
	 */
	protected List<ForestView> views;

	/**
	 * Forestの木のリストを束縛する
	 */
	private List<Tree<NodeData>> trees;

	/**
	 * コンストラクタ
	 * ファイルを読み込みTreeを作成する
	 */
	public ForestModel(String fileName) {

	}

	/**
	 * Forestの木のリストを返す
	 */
	public List<Tree<NodeData>> getTrees() {
		return null;
	}

}
