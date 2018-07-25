package forest.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import mvc.Controller;

import forest.model.ForestModel;
import forest.view.ForestView;

/**
 * ForestのControllerを表すクラス
 * @author USUI Kazuma
 */
public class ForestController extends Controller {
	/**
	 * コンストラクタ
	 */
	public ForestController() {
		super();
	}

	/**
	 * マウスのクリックイベントを取得し、処理する
	 */
	@Override
	public void mouseClicked(MouseEvent aMouseEvent) {
		Point aPoint = aMouseEvent.getPoint();
		aPoint.translate(view.scrollAmount().x, view.scrollAmount().y);
		String nodeName = ((ForestModel)this.model).getNodeName(aPoint);
		System.out.println(String.format("%s (%s)", aPoint, nodeName));
	}

	/**
	 * マウスのドラッグイベントを取得し、処理する
	 */
	@Override
	public void mouseDragged(MouseEvent aMouseEvent) {
		super.mouseDragged(aMouseEvent);
	}

}
