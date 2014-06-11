package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements View.OnClickListener{

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		Button btnInsert = (Button)findViewById(R.id.btnInsert);
		btnInsert.setOnClickListener(this);
		Button btnMainte = (Button)findViewById(R.id.btnMainte);
		btnMainte.setOnClickListener(this);
		Button btnCheak = (Button)findViewById(R.id.btnCheak);
		btnCheak.setOnClickListener(this);

		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			//異常終了
			return;
		}


	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		Intent intent = null;
		switch(v.getId()){
			case R.id.btnInsert://登録ボタン
				//エディットテキストからの内容情報を取り出す
				EditText etv = (EditText)findViewById(R.id.editHitokoto);
				String inputMsg = etv.getText().toString();

				//inputMsgがnullでない、かつ、からでない場合のみif文内を実行
				if(inputMsg != null && !inputMsg.isEmpty()){
					//MySQLiteOpenHelperのインサートメソッドを呼び出し
					helper.insertHitokoto(sdb, inputMsg);
				}
				//入力欄をクリア
				etv.setText("");
				break;

			case R.id.btnMainte://メンテボタン
				intent = new Intent(MainActivity.this, MainteActivity.class);
				startActivity(intent);
				break;
			case R.id.btnCheak://一言チェックボタン

				//MySQLiteOpenHelperのセレクト一言メソッドを呼び出して一言をランダムに取得
				String strHitokoto = helper.selectRandomHitokoto(sdb);

				//インテントのインスタンス生成
				intent = new Intent(MainActivity.this, HitokotoActivity.class);
				//インテントに一言を混入
				intent.putExtra("hitokoto", strHitokoto);

				//次画面のアクティビティ起動
				startActivity(intent);
				break;

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
