package forest.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.Point;
import java.io.File;

import mvc.Model;

import forest.Const;
import forest.utility.ForestImageCreator;
import forest.utility.TreeParser;
import forest.tree.Tree;
import forest.tree.Node;
import forest.tree.NodeData;

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
	 * ノードの名前と座標を紐付けたMapを束縛する
	 */
	private Map<Node<NodeData>,List<Point>> nodePoints;

	/**
	 * 画像を生成するためのForestImageCreatorのインスタンスを束縛する
	 */
	private ForestImageCreator aForestImageCreator;

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
				aTree.sortPostOrder();
				this.trees.add(aTree);
			}
		});

		this.aForestImageCreator = new ForestImageCreator();
		this.aForestImageCreator.setTrees(this.getTrees());
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
				aTree.sortPostOrder();
				this.trees.add(aTree);
			}
		});

		this.aForestImageCreator = new ForestImageCreator();
		this.aForestImageCreator.setTrees(this.getTrees());
	}

	/**
	 * Forestの木のリストを返す
	 * @return 木のリスト
	 */
	public List<Tree<NodeData>> getTrees() {
		return this.trees;
	}

	/**
	 * 指定されたポイントにノードがあればその名前を返す
	 * @param aPoint ポイント
	 * @return 木のリスト
	 */
	public String getNodeName(Point aPoint) {
		String nodeName = "";
		Iterator<Node<NodeData>> aNodeIterator = this.nodePoints.keySet().iterator();
		while (aNodeIterator.hasNext()) {
			Node<NodeData> aNode = aNodeIterator.next();
			Point startPoint = this.nodePoints.get(aNode).get(0);
			Point endPoint = this.nodePoints.get(aNode).get(1);
			if ((int)aPoint.getX() > (int)startPoint.getX() &&
				(int)aPoint.getX() < (int)endPoint.getX() &&
				(int)aPoint.getY() > (int)startPoint.getY() &&
				(int)aPoint.getY() < (int)endPoint.getY()) {
					nodeName = aNode.getData().get().getName();
			}
		}
		return nodeName;
	}

	/**
	 * アニメーションを実行する
	 */
	public void animate() {
		while (this.aForestImageCreator.hasNext()) {
			try {
				this.picture(this.aForestImageCreator.next());
				this.nodePoints = this.aForestImageCreator.getNodePoints();
				this.changed();
				Thread.sleep(Const.SLEEP_TIME);
			} catch (InterruptedException anException) {
				System.err.println(anException);
				throw new RuntimeException(anException.toString());
			}
		}
	}

}
