package forest.model;

import java.util.Optional;
import java.util.List;

/**
 * Forestの一つ一つの項目を表すクラス
 * 
 */
public class Node {

	private String name;

	private Integer x;

	private Integer y;

	/**
	 * 親のNodeを返す
	 */
	public abstract Optional<Node> getParent();

	/**
	 * 子Node一覧を返す
	 */
	public abstract List<Node> getChildren();

	/**
	 * Nodeの名前を返す
	 */
	public abstract String getName();

	/**
	 * Nodeの深さを返す
	 */
	public abstract Integer getDepth();

	/**
	 * 親Nodeを設定する
	 */
	public abstract void setParent(Node parent);

	/**
	 * 子Nodeを追加する
	 */
	public abstract void addChild(Node child);

	/**
	 * xを設定する
	 */
	public abstract void setX(Integer x);

	/**
	 * xの値を返す
	 */
	public abstract Integer getX();

	/**
	 * yを設定する
	 */
	public abstract void setY(Integer y);

	/**
	 * yの値を返す
	 */
	public abstract Integer getY();

	/**
	 * このNodeがルートノードかを返す
	 */
	public Boolean isRoot() {
		return null;
	}

}
