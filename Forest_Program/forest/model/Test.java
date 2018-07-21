package forest.model;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

// import forest.model.TreeParser;
// import forest.model.Node;
// import forest.model.NodeData;
// import forest.model.Tree;

/**
 * Test
 */
public class Test {
	public static void main(String[] args) {
		TreeParser aTreeParser = new TreeParser();
		List<Node<NodeData>> aNodeList = aTreeParser.parseText(args[0]);
		List<Tree<NodeData>> aTreeList = new ArrayList<Tree<NodeData>>();

		aNodeList.forEach((aNode) -> {
			if (aNode.isRoot()) {
				Tree<NodeData> aTree = new Tree<NodeData>(aNode);
				// aTree.sortLevelOrder();
				aTreeList.add(aTree);
			}
		});

		aTreeList.forEach((aTree) -> {
			// Node<NodeData> root = aTree.getRoot();
			// root.getData().ifPresent((data) -> { System.out.printf("%s%n", data.getName()); });

			// System.out.print(aTree.getNodes());
			aTree.getNodes().forEach((aNode) -> {
				Test.printNode(aNode);
			});
		});
		// NodeData data1 = new NodeData(1, "nama");
		// NodeData data2 = new NodeData(1, "name");
		// Node<NodeData> node1 = new Node<NodeData>();
		// Node<NodeData> node2 = new Node<NodeData>();
		// node1.setData(data1);
		// node2.setData(data2);
		// System.out.println(node1.compareTo(node2));
	}

	public static void printNode(Node<NodeData> aNode) {
		for (int i = 0; i < aNode.getDepth(); i++) {
			System.out.printf("|-- ");
		}
		aNode.getData().ifPresent((data) -> { System.out.printf("%s%n", data.getName()); });
	}

	public static void printAllNodes(Node<NodeData> aNode) {
		
	}
}