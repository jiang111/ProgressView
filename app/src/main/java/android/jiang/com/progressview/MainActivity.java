package android.jiang.com.progressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressView view = (ProgressView) findViewById(R.id.ffff);

        List<ProgressView.Model> models = new ArrayList<>();

        models.add(new ProgressView.Model("确认密码", 1));
        models.add(new ProgressView.Model("输入邮箱", 1));
        models.add(new ProgressView.Model("再次输入", 2));
        models.add(new ProgressView.Model("最终完成", 3));

        view.setData(models);
    }
}
