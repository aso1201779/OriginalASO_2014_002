
package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainteActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{

	//SQLiteデータベース空間を操作するインスタンス変数を宣言
	SQLiteDatabase sdb = null;
	//MySQLiteOpenHelperを操作するインスタンス変数を宣言
	MySQLiteOpenHelper helper = null;

	//リストにて選択したHitokotoテーブルのレコードの「_id」カラム値を保持する変数の宣言
	int selectedID = -1;
	//リストにて選択した行番号を保持する変数の宣言
	int lastPosition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainte);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		Button btnBack = (Button)findViewById(R.id.btnBack);
		Button btnDelete = (Button)findViewById(R.id.btnDelete);
		ListView lstHitokoto = (ListView)findViewById(R.id.lstHitokoto);

		//各ButtonにOnClickListenerをセット
		btnBack.setOnClickListener(this);
		btnDelete.setOnClickListener(this);

		//ListViewにOnItemClickListenerをセット
		lstHitokoto.setOnItemClickListener(this);

		//ListViewにDBの値をセット
		this.setDBValuetoList(lstHitokoto);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		Intent intent = null;
		switch(v.getId()){
			case R.id.btnDelete:

				break;

			case R.id.btnBack:
				intent = new Intent(MainteActivity.this, MainActivity.class);
				break;

		}
		startActivity(intent);
	}

	private void setDBValuetoList(ListView lstHitokoto){
		SQLiteCursor cursor = null;

		//クラスのフィールド変数がNULLなら、データベース空間オープン
		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			//異常終了
			Log.e("ERROR",e.toString());
		}
		//MySQLiteOpenHelpserにSELECT文を実行させて結果のカーソルを受け取る
		cursor = this.helper.selectHitokotoList(sdb);

		//dblayout:ListViewにさらにレイアウトを指定するもの
		int db_layout = android.R.layout.simple_list_item_activated_1;
		//form:カーソルからListviewに指定するカラムの値を指定するもの
		String[]from = {"phrase"};
		//to:Listviewの中に指定したdb_layoutに配置する。各行のview部品のid
		int[] to = new int[]{android.R.id.text1};

		//ListView にセットするアダプターを生成
		//カーソルをもとに、fromの列から、toのViewへ値のマッピングが行われる。
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

		//アダプターを指定します
		lstHitokoto.setAdapter(adapter);
	}

}
