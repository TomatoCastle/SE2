package forest.view;

import java.util.List;
import java.util.Map;
import java.awt.Point;
import java.awt.image.BufferedImage;

import forest.model.Tree;
import forest.model.NodeData;

/**
 * ForestModelのtreesの内容からForestの画像を生成するクラス
 */
public class ForestImageCreator {

	/**
	 * 木たちを束縛する
	 */
	private List<Tree<NodeData>> trees;

	public ForestImageCreator(List<Tree<NodeData>> trees) {

	}

	/**
	 * treesを更新する
	 */
	public void updateTrees(List<Tree<NodeData>> trees) {

	}

	/**
	 * Forestの画像を返す
	 */
	public BufferedImage createImage() {
		return null;
	}

	/**
	 * ノードの名前と座標を紐付けたMapを返す
	 */
	public Map<String,Point> getNodePoints() {
		return null;
	}

}
