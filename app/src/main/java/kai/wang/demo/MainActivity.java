package kai.wang.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kai.wang.compile.Title;
import kai.wang.demo.arrow.ArrowDemo;

@Title(title = "hahahaha")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new RxCreateDemo();
//        new RxSchedulerDemo();
//        new RxOptionDemo();
//        new RxBackPressedDemo();
        new ArrowDemo();
    }
}
