package forest.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import forest.Const;

/**
 * ForestModelのtreesの内容からForestの画像を生成するクラス<br>
 * treesのnodesは深さ優先探索後順でソートされていることを前提としている。
 */
public class ForestImageCreator {
	/**
	 * 木たちを束縛する
	 */
	private List<Tree<NodeData>> trees = null;

	/**
	 * ノードのイテレータを束縛する
	 * 1つづつ順番に整列していくために使う
	 */
	private List<Iterator<Node<NodeData>>> nodeListIterators = new ArrayList<Iterator<Node<NodeData>>>();

	/**
	 * NodeとNodeの座標の関係を束縛する<br>
	 * 構造:<br>
	 * key = Node
	 * value.get(0) = drawRectの基準点<br>
	 * value.get(1) = drawRectの基準点に幅, 高さを足した数値<br>
	 */
	private Map<Node<NodeData>, List<Point>> nodePoints = new HashMap<Node<NodeData>, List<Point>>();

	/**
	 * 整列後のNodeとNodeの座標の関係を束縛する<br>
	 * 構造:<br>
	 * key = Node
	 * value.get(0) = drawRectの基準点<br>
	 * value.get(1) = drawRectの基準点に幅, 高さを足した数値<br>
	 */
	private Map<Node<NodeData>, List<Point>> calculatedNodePoints = new HashMap<Node<NodeData>, List<Point>>();

	/**
	 * 画像を束縛する
	 */
	private BufferedImage aImage = null;

	/**
	 * 画像の幅を束縛する
	 */
	private Integer imageWidth = Const.WINDOW_WIDTH;

	/**
	 * 画像の高さを束縛する
	 */
	private Integer imageHeight = Const.WINDOW_HEIGHT;

	/**
	 * 画像で現在整列済みの葉の数を束縛する
	 */
	private Integer leafCount = 0;

	/**
	 * treesを設定する
	 */
	public void setTrees(List<Tree<NodeData>> trees) {
		this.trees = trees;
		this.initialize();
	}

	/**
	 * 画像のNodeの初期状態の座標を計算し、nodePointsを初期化する
	 * nodePointsには各ノードのy軸の位置を深さ優先探索の前順による順番、x軸の位置を左端に合わせて設定する。
	 * また、aCalculatedNodePointsに計算後のノードの位置を束縛しておく。
	 */
	public void initialize() {
		this.aImage = new BufferedImage(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT, BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D aGraphics = this.aImage.createGraphics();
		Font aFont = new Font(Const.FONT_TYPE, Font.PLAIN, Const.FONT_SIZE);
		aGraphics.setFont(aFont);
		FontMetrics aFontMetrics = aGraphics.getFontMetrics();

		Integer count;
		Iterator<Tree<NodeData>> aTreeListIterator;
		Iterator<Node<NodeData>> aNodeListIterator;
		count = 0;
		aTreeListIterator = this.trees.iterator();

		// 全Nodeの初期位置の計算
		while (aTreeListIterator.hasNext()) {
			Tree<NodeData> aTree = aTreeListIterator.next();
			aTree.sortPreOrder();
			aNodeListIterator = aTree.getNodes().iterator();
			while (aNodeListIterator.hasNext()) {
				Node<NodeData> aNode = aNodeListIterator.next();
				String aString = aNode.getData().get().getName();
				Integer stringWidth = aFontMetrics.stringWidth(aString) + Const.NODE_PADDING * 2;
				Integer stringHeight = aFontMetrics.getHeight();
				List<Point> aPointList = new ArrayList<Point>();
				Point startPoint = new Point(0, (aFontMetrics.getHeight() + Const.NODE_MARGIN_TOP) * count + Const.NODE_MARGIN_TOP + Const.CONTENT_MARGIN);
				Point endPoint = new Point((int)startPoint.getX() + stringWidth, (int)startPoint.getY() + stringHeight);
				aPointList.add(startPoint);
				aPointList.add(endPoint);
				this.nodePoints.put(aNode, aPointList);
				count++;
			}
		}

		// 全Nodeの整列後の位置の計算
		this.trees.forEach((aTree) -> {
			this.calculateNodePoint(aFontMetrics, aTree.getRoot(), new Point(0, 0));
		});

		// TreeのイテレータをListに束縛する。
		// 1つづつ順番に整列していくために使う
		List<Iterator<Node<NodeData>>> aIteratorList = new ArrayList<Iterator<Node<NodeData>>>();
		this.trees.forEach((aTree) -> {
			aTree.sortPostOrder();
			aIteratorList.add(aTree.getNodes().iterator());
		});
		this.nodeListIterators = aIteratorList;
	}

	/**
	 * Forestの次の画像があるかを返す
	 * @return 次の画像がある時Trueを返す
	 */
	public Boolean hasNext() {
		Iterator<Iterator<Node<NodeData>>> aIterator = this.nodeListIterators.iterator();
		while (aIterator.hasNext()) {
			Iterator<Node<NodeData>> aNodeIterator = aIterator.next();
			if (aNodeIterator.hasNext()) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * Forestの次の画像を返す
	 * @return 生成した画像を返す
	 */
	public BufferedImage next() {
		// 次のノードを計算済みの座標へ移動させる
		Iterator<Iterator<Node<NodeData>>> aIterator = this.nodeListIterators.iterator();
		while (aIterator.hasNext()) {
			Iterator<Node<NodeData>> aNodeIterator = aIterator.next();
			if (aNodeIterator.hasNext()) {
				Node<NodeData> aNode = aNodeIterator.next();
				this.nodePoints.put(aNode, this.calculatedNodePoints.get(aNode));
				break;
			}
		}

		this.imageWidth = Const.WINDOW_WIDTH;
		this.imageHeight = Const.WINDOW_HEIGHT;
		this.nodePoints.forEach((key, value) -> {
			Point endPoint = value.get(1);
			this.imageWidth = (this.imageWidth < (int)endPoint.getX() + Const.CONTENT_MARGIN) ? (int)endPoint.getX() + Const.CONTENT_MARGIN : this.imageWidth;
			this.imageHeight = (this.imageHeight < (int)endPoint.getY() + Const.CONTENT_MARGIN) ? (int)endPoint.getY() + Const.CONTENT_MARGIN : this.imageHeight;	
		});

		// 画像の生成
		this.aImage = new BufferedImage(this.imageWidth, this.imageHeight, BufferedImage.TYPE_BYTE_BINARY);
		if (this.trees != null) {
			Graphics2D aGraphics = aImage.createGraphics();
			Font aFont = new Font(Const.FONT_TYPE, Font.PLAIN, Const.FONT_SIZE);
			aGraphics.setFont(aFont);
			FontMetrics aFontMetrics = aGraphics.getFontMetrics();
			aGraphics.setColor(Color.WHITE);
			aGraphics.fillRect(0, 0, this.imageWidth, this.imageHeight);
			aGraphics.setColor(Color.BLACK);

			this.trees.forEach((aTree) -> {
				aTree.sortPostOrder();
				aTree.getNodes().forEach((aNode) -> {
					this.drawNode(aGraphics, aFontMetrics, aNode);
				});
			});
		}

		return aImage;
	}

	/**
	 * 座標とノードの名前を紐付けたMapを返す
	 * @return ノードの座標と名前: Map<Node<NodeData>, List<Point>> <br>
	 * 構造:<br>
	 * key = Node
	 * value.get(0) = drawRectの基準点<br>
	 * value.get(1) = drawRectの基準点に幅, 高さを足した数値<br>
	 */
	public Map<Node<NodeData>, List<Point>> getNodePoints() {
		return this.nodePoints;
	}

	/**
	 * Nodeの座標を計算する
	 * @param aFontMetrics フォントメトリクス
	 * @param aNode 座標を計算するNode
	 * @param aPoint 親Nodeの右下の座標
	 */
	private void calculateNodePoint(FontMetrics aFontMetrics, Node<NodeData> aNode, Point aPoint) {
		List<Point> aCalculatedPointList = new ArrayList<Point>();
		String aString = aNode.getData().get().getName();
		Integer stringWidth = aFontMetrics.stringWidth(aString) + Const.NODE_PADDING * 2;
		Integer stringHeight = aFontMetrics.getHeight();
		Point calculatedStartPoint = new Point((int)aPoint.getX() + Const.NODE_MARGIN_LEFT, 0);
		Point calculatedEndPoint = new Point((int)calculatedStartPoint.getX() + stringWidth, 0);
		if (aNode.isLeaf()) {
			calculatedStartPoint.setLocation((int)calculatedStartPoint.getX(), (stringHeight + Const.NODE_MARGIN_TOP) * this.leafCount + Const.NODE_MARGIN_TOP + Const.CONTENT_MARGIN);
			calculatedEndPoint.setLocation((int)calculatedEndPoint.getX(), (int)calculatedStartPoint.getY() + stringHeight);
			this.leafCount++;
		} else {
			Iterator<Node<NodeData>> childListIterator = aNode.getChildren().iterator();
			while (childListIterator.hasNext()) {
				Node<NodeData> child = childListIterator.next();
				this.calculateNodePoint(aFontMetrics, child, calculatedEndPoint);
			}
			Node<NodeData> firstChild = aNode.getChildren().get(0);
			Node<NodeData> lastChild = aNode.getChildren().get(aNode.getChildCount() - 1);
			Point firstChildPoint = this.calculatedNodePoints.get(firstChild).get(0);
			Point lastChildPoint = this.calculatedNodePoints.get(lastChild).get(0);
			calculatedStartPoint.setLocation((int)calculatedStartPoint.getX(), (int)(firstChildPoint.getY() + (lastChildPoint.getY() - firstChildPoint.getY()) / 2));
			calculatedEndPoint.setLocation((int)calculatedEndPoint.getX(), (int)calculatedStartPoint.getY() + stringHeight);
		}

		aCalculatedPointList.add(calculatedStartPoint);
		aCalculatedPointList.add(calculatedEndPoint);
		if (!this.calculatedNodePoints.containsKey(aNode)) {
			this.calculatedNodePoints.put(aNode, aCalculatedPointList);
		}
	}

	/**
	 * Nodeを描画するメソッド
	 * @param aGraphics 描画するGraphics
	 * @param aFontMetrics フォントメトリクス
	 * @param aNode 描画するNode
	 */
	private void drawNode(Graphics2D aGraphics, FontMetrics aFontMetrics, Node<NodeData> aNode) {
		String aString = aNode.getData().get().getName();
		Point startPoint = this.nodePoints.get(aNode).get(0);
		Point endPoint = this.nodePoints.get(aNode).get(1);
		Point endCenterPoint = new Point((int)endPoint.getX(), (int)endPoint.getY() - aFontMetrics.getHeight() / 2);
		aGraphics.drawString(aString, (int)startPoint.getX() + Const.NODE_PADDING, (int)startPoint.getY() + aFontMetrics.getHeight() - aFontMetrics.getDescent());
		aGraphics.drawRect((int)startPoint.getX(), (int)startPoint.getY(), (int)endPoint.getX() - (int)startPoint.getX(), (int)endPoint.getY() - (int)startPoint.getY());
		if (!aNode.isLeaf()) {
			aNode.getChildren().forEach((child) -> {
				Point childStartPoint = this.nodePoints.get(child).get(0);
				Point childStartCenterPoint = new Point((int)childStartPoint.getX(), (int)childStartPoint.getY() + aFontMetrics.getHeight() / 2 + 1);
				if (endCenterPoint.getY() < childStartCenterPoint.getY()) {
					aGraphics.drawLine((int)endCenterPoint.getX(), (int)endCenterPoint.getY(), (int)childStartCenterPoint.getX(), (int)childStartCenterPoint.getY());
				} else {
					aGraphics.drawLine((int)childStartCenterPoint.getX(), (int)childStartCenterPoint.getY(), (int)endCenterPoint.getX(), (int)endCenterPoint.getY());
				}
			});
		}
	}

}
