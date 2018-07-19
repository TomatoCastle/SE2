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
	public Optional<Node> getParent() {
	    return null;
    }

	/**
	 * 子Node一覧を返す
	 */
	public List<Node> getChildren() {
        return null;
    }

	/**
	 * Nodeの名前を返す
	 */
	public String getName() {
        return null;
    }

	/**
	 * Nodeの深さを返す
	 */
	public Integer getDepth() {
        return null;
    }

	/**
	 * 親Nodeを設定する
	 */
	public void setParent(Node parent) {

    }

	/**
	 * 子Nodeを追加する
	 */
	public void addChild(Node child) {

    }

	/**
	 * xを設定する
	 */
	public void setX(Integer x) {

    }

	/**
	 * xの値を返す
	 */
	public Integer getX() {
        return null;
    }

	/**
	 * yを設定する
	 */
	public void setY(Integer y) {

    }

	/**
	 * yの値を返す
	 */
	public Integer getY() {
        return null;
    }

	/**
	 * このNodeがルートノードかを返す
	 */
	public Boolean isRoot() {
		return null;
	}

}
