package forest.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Tomato on 2018/07/21.
 */
public class TreeTest {
    @Before
    public void setUp() throws Exception {

        TreeParser aTreeParser = new TreeParser();
        List<Node<NodeData>> aNodeList = aTreeParser.parseText("test");
        List<Tree<NodeData>> aTreeList = new ArrayList<Tree<NodeData>>();

        aNodeList.stream().filter(Node::isRoot).forEach((aNode) -> {
                Tree<NodeData> aTree = new Tree<NodeData>(aNode);
                // aTree.sortLevelOrder();
                aTreeList.add(aTree);
            }
        });
    }

    @Test
    public void getNodes() throws Exception {
    }

    @Test
    public void getRoot() throws Exception {
    }

    @Test
    public void getHeight() throws Exception {
    }

    @Test
    public void addNode() throws Exception {
    }

    @Test
    public void sortPreOrder() throws Exception {
    }

    @Test
    public void sortPostOrder() throws Exception {
    }

    @Test
    public void sortLevelOrder() throws Exception {
    }

    @Test
    public void equals() throws Exception {
    }

    @Test
    public void hashCode() throws Exception {
    }

    @Test
    public void toString() throws Exception {
    }

}