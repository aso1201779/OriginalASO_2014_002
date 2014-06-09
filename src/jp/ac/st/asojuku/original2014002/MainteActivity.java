
package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainteActivity extends Activity implements View.OnClickListener{

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
		btnBack.setOnClickListener(this);
		Button btnDelete = (Button)findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(this);
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

}
