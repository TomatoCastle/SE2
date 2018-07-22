package forest.view;

import mvc.View;
import forest.model.ForestModel;
import forest.controller.ForestController;
import java.util.Map;
import java.awt.Graphics;

/**
 * ForestのViewを表すクラス
 */
public class ForestView extends mvc.View, View {

	/**
	 * モデルを束縛する
	 */
	private ForestModel model;

	/**
	 * コントローラを束縛する
	 */
	private ForestController controller;

	/**
	 * ノードの名前と座標を紐付けたMapを束縛する
	 */
	private Map<String,Point> nodePoints;

	public void ForestView(ForestModel model, ForestController controller) {

	}

	/**
	 * 画像の入力を得て出力する
	 */
	@Override
	public void paintComponent(Graphics aGraphics) {

	}

}
