package forest;

/**
 * 定数をクラス変数として保持するクラス
 */
public class Const extends Object {
	/**
	 * ウィンドウの幅
	 */
	public static final Integer WINDOW_WIDTH = 800;

	/**
	 * ウィンドウの高さ
	 */
	public static final Integer WINDOW_HEIGHT = 600;

	/**
	 * 画像の木構造の内容の上下左右の余白の大きさ
	 */
	public static final Integer CONTENT_MARGIN = 25;

	/**
	 * 各ノードの左の余白
	 */
	public static final Integer NODE_MARGIN_LEFT = 25;

	/**
	 * 各ノードの上の余白
	 */
	public static final Integer NODE_MARGIN_TOP = 2;

	/**
	 * 各ノードの文字の左右のパディング
	 */
	public static final Integer NODE_PADDING = 2;

	/**
	 * フォントサイズ
	 */
	public static final Integer FONT_SIZE = 12;

	/**
	 * フォントタイプ
	 */
	public static final String FONT_TYPE = "Times New Roman";

	/**
	 * アニメーションの次の画像を描画するまでの待ち時間
	 */
	public static final Integer SLEEP_TIME = 100;
}