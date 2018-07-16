package forest.view;

import pane.PaneController;
import pane.PaneModel;
import pane.PaneView;
import java.util.Optional;
import java.awt.Graphics;
import forest.model.Tree;

public class TreePane extends PaneView {

	/**
	 * コンストラクタ
	*/
    public TreePane(PaneModel paneModel, PaneController paneController, Optional<Tree> tree) {
        super(paneModel, paneController);
        this.tree = tree;
    }

    private Optional<Tree> tree;

    /**
	 * modelの更新を通知を受けてviewを更新するメソッド
    */
	public void onTreeChanged() {
		Tree treeModel = new Tree();
		treeModel.notifyChangeToViews();
	}

	/**
	 * Treeをセットする
	*/
	public void setTree(Tree tree) {
		this.tree = Optional.of(tree);
	}

	/**
  	 * JFrameに描画するためのメソッド
  	 * @param aGraphics グラフィックス・コンテキスト
  	*/
	@Override
	public void paintComponent(Graphics aGraphics) {
		super.paintComponent(aGraphics);
		return;
	}

}
