package pr2.demo.com.mycaculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText text1;
    private RadioButton man;
    private RadioButton woman;
    private Button btton;
    private EditText text2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        text1 = (EditText) findViewById(R.id.text1);
        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);
        btton = (Button) findViewById(R.id.button);
        text2 = (EditText) findViewById(R.id.text2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {

        btton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!text1.getText().toString().trim().equals("")) {
                    if (man.isChecked() || woman.isChecked()) {
                        double weight = 0;
                        weight = Double.parseDouble(text1.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("____评估结果____");
                        if (man.isChecked()) {
                            sb.append("男性标准身高");
                            double result = height(weight, "男");
                            sb.append((int) result + "(厘米）");

                        }
                        if (woman.isChecked()) {
                            sb.append("女性标准身高");
                            double result = height(weight, "女");
                            sb.append((int) result + "(厘米）");
                        }
                        String result = sb.toString();
                        text2.setText(result);
                    } else {
                        show("请选择性别");
                    }

                } else {
                    show("请输入体重");
                }
            }


        });
    }


    private double height(double weight, String sex) {
        double height;
        if (sex == "男") {
            height = 170 - (62 - weight) / 0.6;
        } else {
            height = 158 - (52 - weight) / 0.5;
        }
        return height;
    }


    private void show(String message) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
