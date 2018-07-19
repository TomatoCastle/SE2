package forest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * 木構造を表すTreeクラス
 * Nodeクラスに依存する
 */
public class Tree<Element extends Object & Comparable<? super Element>> extends Object {

	/**
	 * 含まれるNode一覧を束縛する
	 */
	private List<Node<Element>> nodes = new ArrayList<Node<Element>>();

	/**
	 * rootNodeを束縛する
	 */
	private Node<Element> root;

	/**
	 * コンストラクタ
	 * ルートNodeから全Nodeを取得してnodesに束縛する
	 * そのとき、深さ優先探索の前順にソートして格納する
	 * @param root 木のルートNode
	 */
	public Tree(Node<Element> root) {
		this.root = root;
		this.sortPreOrder();
	}

	/**
	 * Node一覧を返す
	 * @return Nodeのリスト
	 */
	public List<Node<Element>> getNodes() {
		return this.nodes;
	}

	/**
	 * ルートNodeを返す
	 * @return ルートNode
	 */
	public Node<Element> getRoot() {
		return this.root;
	}

	/**
	 * 木の高さを返す
	 * @return 木全体の高さ
	 */
	public Integer getHeight() {
		return this.root.getHeight();
	}

	/**
	 * Nodeを新しくTreeに加える
	 * @param node 新しく追加するNode
	 */
	public void addNode(Node<Element> node) {
		this.nodes.add(node);
	}

	/**
	 * 深さ優先探索の前順でnodesのリストをソートする。
	 */
	public void sortPreOrder() {
		this.nodes = new ArrayList<Node<Element>>();
		this.addNode(this.root);
		this.getTreeNodesWithPreOrder(this.root);
	}

	/**
	 * 深さ優先探索の後順でnodesのリストをソートする。
	 */
	public void sortPostOrder() {
		this.nodes = new ArrayList<Node<Element>>();
		this.getTreeNodesWithPostOrder(this.root);
		this.addNode(this.root);
	}

	/**
	 * 幅優先探索でnodesのリストをソートする。
	 */
	public void sortLevelOrder() {
		List<List<Node<Element>>> levelList = new ArrayList<List<Node<Element>>>();
		IntStream.rangeClosed(0, this.getHeight()).forEach((index) -> {
			levelList.add(new ArrayList<Node<Element>>());
		});
		this.getNodes().forEach((aNode) -> {
			levelList.get(aNode.getDepth()).add(aNode);
		});
		this.nodes = new ArrayList<Node<Element>>();
		levelList.forEach((nodeList) -> {
			nodeList.forEach((aNode) -> {
				this.addNode(aNode);
			});
		});
	}

	/**
	 * 子Nodeを再帰的に探索して全てのNodeを取得する
	 * このとき、深さ優先探索の前順で配列に格納する
	 * @param aNode Node
	 */
	private void getTreeNodesWithPreOrder(Node<Element> aNode) {
		List<Node<Element>> childList = new CopyOnWriteArrayList<Node<Element>>(aNode.getChildren());
		childList.forEach((child) -> {
			if (!this.getNodes().contains(child)) {
				this.addNode(child);
			}
			if (child.getChildCount() != 0) {
				this.getTreeNodesWithPreOrder(child);
			}
		});
	}

	/**
	 * 子Nodeを再帰的に探索して全てのNodeを取得する
	 * このとき、深さ優先探索の後順で配列に格納する
	 * @param aNode Node
	 */
	private void getTreeNodesWithPostOrder(Node<Element> aNode) {
		List<Node<Element>> childList = new CopyOnWriteArrayList<Node<Element>>(aNode.getChildren());
		childList.forEach((child) -> {
			if (child.getChildCount() != 0) {
				this.getTreeNodesWithPostOrder(child);
			}
			if (!this.getNodes().contains(child)) {
				this.addNode(child);
			}
		});
	}

	/**
	 * オブジェクトの比較を行う
	 * 持つ値が同じであったとき同じオブジェクトであるとする
	 * @param object 比較するオブジェクト
	 * @return 比較したオブジェクトと同値であったときTrueを返す
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (!(object instanceof Tree))
			return false;
			Tree otherTree = (Tree) object;
		return this.hashCode() == otherTree.hashCode();
	}

	/**
	 * ハッシュコードを計算する 持つ値が同じである場合同じハッシュコードとなる
	 * @return ハッシュコード
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.nodes, this.root);
	}

	/**
	 * オブジェクトを文字列に変換して返す
	 * @return オブジェクトを表す文字列
	 */
	@Override
	public String toString() {
		StringBuilder aStringBuilder = new StringBuilder();
		aStringBuilder.append(String.format("%n{ nodes: %s, ", this.nodes.toString()));
		aStringBuilder.append(String.format("root: %s, ", this.root.toString()));
		aStringBuilder.append("}");
		return aStringBuilder.toString();
	}

}
