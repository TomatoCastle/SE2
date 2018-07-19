package forest.model;

import java.util.Objects;

/**
 * Nodeの持つデータを表すクラス
 */
public class NodeData extends Object implements Comparable<NodeData> {

	/**
	 * Node番号を束縛する
	 */
	private Integer number;

	/**
	 * Node名を束縛する
	 */
	private String name;

	/**
	 * 探索済みフラグ
	 */
	private Boolean visited = Boolean.FALSE;

	/**
	 * コンストラクタ
	 * Node番号とNode名を受け取り設定する
	 * @param number Nodeの名前
	 * @param name Nodeの番号
	 */
	public NodeData(Integer number, String name) {
		this.number = number;
		this.name = name;
	}

	/**
	 * Nodeの番号を返す
	 * @return Nodeの番号
	 */
	public Integer getNumber() {
		return this.number;
	}

	/**
	 * Nodeの名前を返す
	 * @return Nodeの名前
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Nodeが探索済みか返す
	 * @return このNodeが探索済みであった場合Trueを返す
	 */
	public Boolean isVisited() {
		return this.visited;
	}

	/**
	 * 探索時に呼び出す
	 * このNodeが探索されたことを知らせる
	 */
	public void visit() {
		this.visited = Boolean.TRUE;
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
		if (!(object instanceof NodeData))
			return false;
			NodeData otherNodeData = (NodeData) object;
		return this.hashCode() == otherNodeData.hashCode();
	}

	/**
	 * ハッシュコードを計算する
	 * 持つ値が同じである場合同じハッシュコードとなる
	 * @return ハッシュコード
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.number, this.name, this.visited);
	}

	/**
	 * オブジェクトを文字列に変換して返す
	 * @return オブジェクトを表す文字列
	 */
	@Override
	public String toString() {
		return String.format("%n{ name: %s number: %d, visited: %b }", this.name, this.number, this.visited);
	}

	/**
	 * オブジェクトを比較する
	 * compareToの実装によってsortによる自然順序付けを提供する
	 * まずNodeDataのnameを比較し、比較した結果同じ値でなかった場合は名前による自然順序を提供する
	 * NodeDataのnameが同じであった場合numberを比較する
	 * name, numberの両方が同じ値であった場合visitedの値を比較する
	 * @param object 比較するオブジェクト
	 * @return 比較結果 このNodeのdataオブジェクトが持つcompareToメソッドを用いてdataを比較した結果
	 */
	@Override
	public int compareTo(NodeData object) {
		Integer compareResult = this.getName().compareTo(object.getName());
		if (compareResult != 0) {
			return compareResult;
		}
		compareResult = Integer.compare(this.getNumber(), object.getNumber());
		if (compareResult != 0) {
			return compareResult;
		}
		compareResult = Boolean.compare(this.isVisited(), object.isVisited());
		return compareResult;
	}

}
