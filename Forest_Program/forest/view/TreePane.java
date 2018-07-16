package forest.view;
import pane.PaneController;
import pane.PaneModel;
import pane.PaneView;

import java.util.Optional;
import forest.model.Tree;

public class TreePane extends PaneView {
    public TreePane(PaneModel paneModel, PaneController paneController, Optional<Tree> tree) {
        super(paneModel, paneController);
        this.tree = tree;
    }

    private Optional<Tree> tree;

	public void onTreeChanged() {

	}

	public void setTree(Tree tree) {

	}

}
