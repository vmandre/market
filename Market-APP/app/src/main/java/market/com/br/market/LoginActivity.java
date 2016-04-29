package market.com.br.market;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnMessage = (Button) findViewById(R.id.btn_Login);
        btnMessage.setOnClickListener(btnEntrar);
    }

    private View.OnClickListener btnEntrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent it = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(it);
        }
    };
}
