package android.jiang.com.progressview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {


    List<android.support.v4.app.Fragment> mLists = new ArrayList<>();
    private List<ProgressView.Model> mModels;
    private ProgressView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (ProgressView) findViewById(R.id.ffff);

        mModels = new ArrayList<>();

        mLists.add(TestFragment.newInstance(0, "确认密码"));
        mLists.add(TestFragment.newInstance(1, "输入邮箱"));
        mLists.add(TestFragment.newInstance(2, "再次输入"));
        mLists.add(TestFragment.newInstance(3, "最终完成"));

        mModels.add(new ProgressView.Model("确认密码", ProgressView.STARTING));
        mModels.add(new ProgressView.Model("输入邮箱", ProgressView.AFTER));
        mModels.add(new ProgressView.Model("再次输入", ProgressView.AFTER));
        mModels.add(new ProgressView.Model("最终完成", ProgressView.AFTER));

        mView.setData(mModels);
        switchFragment(null, mLists.get(0));


    }

    public void switchFragment(Fragment from, Fragment to) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (from == null) {
            if (to != null) {
                transaction.replace(R.id.id_content, to);
                transaction.commit();
            }
        } else {
            if (!to.isAdded()) {
                transaction.hide(from).add(R.id.id_content, to).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }

        }

    }

    public void setPosition(int position) {
        switchFragment(mLists.get(position), mLists.get(position + 1));
        mModels.get(position).state = ProgressView.BEFORE;
        mModels.get(position + 1).state = ProgressView.STARTING;
        mView.setData(mModels);
    }
}
