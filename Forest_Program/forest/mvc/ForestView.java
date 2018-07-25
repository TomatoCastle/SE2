package forest.mvc;

import java.util.Map;
import java.awt.Graphics;
import java.awt.Point;

import mvc.View;

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

}
