package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{

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


	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){
			Intent intent = null;
			case R.id.btnInsert:
				EditText etv = (EditText)findViewById(R.id.editHitokoto);
				String inputMsg = etv.getText().toString();


			case R.id.btnMainte:
				intent = new Intent(MainAvtivity.this,MainteActivity.class);
				startActivity(intent);

			case R.id.btnCheak:
				intent = new Intent(MainAvtivity.this,HitokotoActivity.class);
				startActivity(intent);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
