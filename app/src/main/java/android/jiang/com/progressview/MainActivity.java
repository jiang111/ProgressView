package android.jiang.com.progressview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Handler mHandler = new Handler();
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressView view = (ProgressView) findViewById(R.id.ffff);

        final List<ProgressView.Model> models = new ArrayList<>();

        models.add(new ProgressView.Model("确认密码", 2));
        models.add(new ProgressView.Model("输入邮箱", 3));
        models.add(new ProgressView.Model("再次输入", 3));
        models.add(new ProgressView.Model("最终完成", 3));

        view.setData(models);


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                models.get(position).state = 1;
                models.get(position + 1).state = 2;
                position++;
                view.setData(models);
            }
        }, 3000);


    }
}
