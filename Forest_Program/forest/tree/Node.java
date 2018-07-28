package forest.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Treeを表すためのNodeのクラス
 * 型引数にはObjectとComparableを実装したクラスを受け取ることができる
 * @author USUI Kazuma
 */
public class Node<Element extends Object & Comparable<? super Element>> extends Object implements Comparable<Node<Element>> {

	/**
	 * Nodeの持つデータを束縛する
	 */
	private Element data = null;

	/**
	 * 親のListを束縛する
	 */
	private List<Node<Element>> parents = new ArrayList<Node<Element>>();

	/**
	 * 子のListを束縛する
	 */
	private List<Node<Element>> children = new ArrayList<Node<Element>>();

	/**
	 * 親のNodeを返す
	 * @return 親Nodeの配列
	 */
	public List<Node<Element>> getParents() {
		this.parents.sort(Comparator.naturalOrder());
		return this.parents;
	}

	/**
	 * 子Node一覧を返す
	 * @return 子Nodeの配列
	 */
	public List<Node<Element>> getChildren() {
		this.children.sort(Comparator.naturalOrder());
		return this.children;
	}

	/**
	 * 親の数を返す
	 * @return 親Nodeの数
	 */
	public Integer getParentCount() {
		return this.parents.size();
	}

	/**
	 * 子の数を返す
	 * @return 親Nodeの数
	 */
	public Integer getChildCount() {
		return this.children.size();
	}

	/**
	 * Nodeのデータを返す
	 * @return Nodeの持つデータ
	 */
	public Optional<Element> getData() {
		return Optional.ofNullable(this.data);
	}

	/**
	 * Nodeの深さを返す
	 * @return Nodeの深さ
	 */
	public Integer getDepth() {
		return this.getDepth(0, this);
	}

	/**
	 * 再帰的に処理してNodeの深さを返す
	 * @return Nodeの深さ
	 */
	private Integer getDepth(Integer depth, Node<Element> node) {
		if (node.isRoot()) {
			return depth;
		}
		List<Integer> depthList = new ArrayList<Integer>();
		depthList.add(depth);
		node.parents.forEach((aParent) -> {
			depthList.add(this.getDepth(depth + 1, aParent));
		});
		depthList.sort(Comparator.reverseOrder());
		return depthList.get(0);
	}

	/**
	 * Nodeの高さを返す
	 * @return Nodeの高さ
	 */
	public Integer getHeight() {
		return this.getHeight(0, this);
	}

	/**
	 * 再帰的に処理してNodeの高さを返す
	 * @return Nodeの高さ
	 */
	private Integer getHeight(Integer height, Node<Element> node) {
		if (node.isLeaf()) {
			return height;
		}
		List<Integer> heightList = new ArrayList<Integer>();
		heightList.add(height);
		node.children.forEach((aChild) -> {
			heightList.add(this.getHeight(height + 1, aChild));
		});
		heightList.sort(Comparator.reverseOrder());
		return heightList.get(0);
	}

	/**
	 * 親Nodeを追加する
	 * @param parent 親Node
	 */
	public void addParent(Node<Element> parent) {
		this.parents.add(parent);
	}

	/**
	 * 子Nodeを追加する
	 * @param child 子Node
	 */
	public void addChild(Node<Element> child) {
		this.children.add(child);
	}

	/**
	 * データを設定する
	 * データオブジェクトはComparableインタフェースを実装していなければならない
	 * @param data データオブジェクト
	 */
	public void setData(Element data) {
		this.data = data;
	}

	/**
	 * このNodeがリーフNodeかを返す
	 * @return このNodeがリーフならばTrueを返す
	 */
	public Boolean isLeaf() {
		return this.getChildCount() == 0;
	}

	/**
	 * このNodeがルートNodeかを返す
	 *  @return このNodeがルートならばTrueを返す
	 */
	public Boolean isRoot() {
		return this.getParentCount() == 0;
	}

	/**
	 * オブジェクトの比較を行う
	 * 持つ値が同じであったとき同じオブジェクトであるとする
	 * @param object 比較するオブジェクト
	 * @return 比較したオブジェクトと同値であったときTrueを返す
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Node<?>) {
			return this.hashCode() == object.hashCode();
		}
		return false;
	}

	/**
	 * ハッシュコードを計算する 持つ値が同じである場合同じハッシュコードとなる
	 * このノードのデータ、親ノード全部のデータ、子ノード全部のデータが一致したとき同じオブジェクトであるとする
	 * @return ハッシュコード
	 */
	@Override
	public int hashCode() {
		List<Integer> parentDataHashCodeList = new ArrayList<Integer>();
		List<Integer> childDataHashCodeList = new ArrayList<Integer>();
		this.getParents().forEach((parent) -> {
			parentDataHashCodeList.add(parent.getData().hashCode());
		});
		this.getChildren().forEach((child) -> {
			childDataHashCodeList.add(child.getData().hashCode());
		});
		return Objects.hash(this.data, parentDataHashCodeList, childDataHashCodeList);
	}

	/**
	 * オブジェクトを文字列に変換して返す
	 * @return オブジェクトを表す文字列
	 */
	@Override
	public String toString() {
		StringBuilder aStringBuilder = new StringBuilder();
		aStringBuilder.append(String.format("%n{ data: %s, ", this.data.toString()));
		aStringBuilder.append(String.format("children: %s, ", this.getChildCount()));
		aStringBuilder.append(String.format("parents: %s ", this.getParentCount()));
		aStringBuilder.append("}");
		return aStringBuilder.toString();
	}

	/**
	 * オブジェクトを比較する
	 * compareToの実装によってsortによる自然順序付けを提供する
	 * Nodeのdataを比較し、その順序によって自然順序を提供する
	 * dataがnullだった場合もっとも自然順序が低いものとして扱われる
	 * 注: このクラスはequalsと一貫性のない自然順序付けを持ちます
	 * @param object 比較するオブジェクト
	 * @return 比較結果 このNodeのdataオブジェクトが持つcompareToメソッドを用いてdataを比較した結果
	 */
	@Override
	public int compareTo(Node<Element> object) {
		if (!this.getData().isPresent() && !object.getData().isPresent()) {
			return 0;
		}
		if (this.getData().isPresent() && !object.getData().isPresent()) {
			return -1;
		}
		if (!this.getData().isPresent() && object.getData().isPresent()) {
			return 1;
		}
		
		return this.getData().get().compareTo(object.getData().get());
	}

}
