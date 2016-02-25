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

        models.add(new ProgressView.Model("确认密码", ProgressView.STARTING));
        models.add(new ProgressView.Model("输入邮箱", ProgressView.AFTER));
        models.add(new ProgressView.Model("再次输入", ProgressView.AFTER));
        models.add(new ProgressView.Model("最终完成", ProgressView.AFTER));

        view.setData(models);


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                models.get(position).state = ProgressView.BEFORE;
                models.get(position + 1).state = ProgressView.STARTING;
                position++;
                view.setData(models);
            }
        }, 3000);


    }
}
