package forest.model;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

import mvc.Model;

/**
 * ForestのModelを表すクラス
 * @author USUI Kazuma
 */
public class ForestModel extends Model {
	/**
	 * Forestの木のリストを束縛する
	 */
	private List<Tree<NodeData>> trees = new ArrayList<Tree<NodeData>>();

	/**
	 * コンストラクタ
	 * ファイルを読み込みTreeを作成する
	 * @param fileName 読み込むファイルの名前(パス)
	 */
	public ForestModel(String fileName) {
		super();
		TreeParser aTreeParser = new TreeParser();
		List<Node<NodeData>> aNodeList = aTreeParser.parseText(fileName);

		aNodeList.forEach((aNode) -> {
			if (aNode.isRoot()) {
				Tree<NodeData> aTree = new Tree<NodeData>(aNode);
				this.trees.add(aTree);
			}
		});
	}

	/**
	 * コンストラクタ
	 * ファイルを読み込みTreeを作成する
	 * @param aFile 読み込むファイル
	 */
	public ForestModel(File aFile) {
		super();
		TreeParser aTreeParser = new TreeParser();
		List<Node<NodeData>> aNodeList = aTreeParser.parseText(aFile);

		aNodeList.forEach((aNode) -> {
			if (aNode.isRoot()) {
				Tree<NodeData> aTree = new Tree<NodeData>(aNode);
				this.trees.add(aTree);
			}
		});
	}

	/**
	 * Forestの木のリストを返す
	 * @return 木のリスト
	 */
	public List<Tree<NodeData>> getTrees() {
		return this.trees;
	}

}
