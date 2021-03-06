package forest.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;

import utility.StringUtility;

import forest.tree.Tree;
import forest.tree.Node;
import forest.tree.NodeData;

/**
 * テキストをパースしてNode一覧を取得するクラス
 * @author USUI Kazuma
 */
public class TreeParser extends Object {
	/**
	 * テキストをパースしてNode一覧を取得する
	 * @param aFile ファイル
	 * @return ファイルから読み込んだNodeのリスト
	 */
	public List<Node<NodeData>> parseText(File aFile) {
		List<Node<NodeData>> aNodeList = new ArrayList<Node<NodeData>>();
		Node<NodeData> aNode;

		List<String> aStringList = StringUtility.readTextFromFile(aFile);
		Iterator<String> anIterator = aStringList.iterator();
		while (anIterator.hasNext()) {
			String aString = anIterator.next();
			if (aString.equals("trees:")) {
				while (anIterator.hasNext()) {
					aString = anIterator.next();
					if (aString.equals("nodes:")) {
						break;
					}
				}
			}
			if (aString.equals("nodes:")) {
				while (anIterator.hasNext()) {
					aString = anIterator.next();
					if (aString.equals("branches:")) {
						break;
					}
					List<String> values = StringUtility.splitString(aString, ", ");
					aNode = new Node<NodeData>();
					aNode.setData(new NodeData(Integer.valueOf(values.get(0)), values.get(1)));
					aNodeList.add(aNode);
				}
			}
			if (aString.equals("branches:")) {
				while (anIterator.hasNext()) {
					aString = anIterator.next();
					List<String> values = StringUtility.splitString(aString, ", ");
					aNodeList.get(Integer.valueOf(values.get(0)) - 1).addChild(aNodeList.get(Integer.valueOf(values.get(1)) - 1));
					aNodeList.get(Integer.valueOf(values.get(1)) - 1).addParent(aNodeList.get(Integer.valueOf(values.get(0)) - 1));
				}
			}
		}

		return aNodeList;
	}

	/**
	 * テキストをパースしてNode一覧を取得する
	 * @param fileName ファイル名
	 * @return ファイルから読み込んだNodeのリスト
	 */
	public List<Node<NodeData>> parseText(String fileName) {
		return this.parseText(new File(fileName));
	}

}
