package forest.view;

import java.util.Map;
import java.awt.Graphics;
import java.awt.Point;

import mvc.View;

import forest.model.ForestModel;
import forest.controller.ForestController;

/**
 * ForestのViewを表すクラス
 * @author USUI Kazuma
 */
@SuppressWarnings("serial")
public class ForestView extends View {
	/**
	 * コンストラクタ
	 * モデルとコントローラを設定する。
	 * @param model モデルのインスタンス
	 * @param controller コントローラのインスタンス
	 */
	public ForestView(ForestModel model, ForestController controller) {
		super(model, controller);
	}

	/**
	 * 指定されたグラフィックにForestの画像を出力する
	 * @param aGraphics グラフィックス・コンテキスト
	 */
	@Override
	public void paintComponent(Graphics aGraphics) {
		super.paintComponent(aGraphics);
	}

}
