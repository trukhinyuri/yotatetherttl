package ru.antiyotazapret.yotatetherttl;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.app.Activity;
import ru.antiyotazapret.yotatetherttl.ShellExecuter;
public class MainActivity extends Activity {
    EditText input;
    Button btn;
    CheckBox cb0;
    TextView out;
    String command;
    ShellExecuter exe = new ShellExecuter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onMyClick(View v) {
        int ttlnumber = 63;
        String error=null;
        switch(v.getId())
        {
            case R.id.btn: ttlnumber=127; break;
            case R.id.button2: ttlnumber=63; break;
            case R.id.button3:
                TextView tv = (TextView) findViewById(R.id.textView2);
                input = (EditText)findViewById(R.id.txt);
                if(input.length()>0) {
                    ttlnumber = Integer.parseInt(input.getText().toString());
                    if (ttlnumber > 1 && ttlnumber < 255) {

                    } else
                    {
                        error="Please, enter a correct TTL!";
                    }
                }
                else
                {
                    error="Please, enter something.";
                }
                break;
        }
        TextView tv = (TextView) findViewById(R.id.textView2);
        if(error==null) {
            command = "settings put global airplane_mode_on 1";
            command += "\nam broadcast -a android.intent.action.AIRPLANE_MODE --ez state true";
            command += "\nsettings put global tether_dun_required 0";
            exe.Executer(command);
            command = "echo \"" + ttlnumber + "\" > /proc/sys/net/ipv4/ip_default_ttl";
            command += "\nsettings put global airplane_mode_on 0";
            command += "\nam broadcast -a android.intent.action.AIRPLANE_MODE --ez state false";
            exe.Executer(command);
            tv.setText("OK. Now you can turn on tethering!");
        }
        else
        {
            tv.setText(error);
        }
    }


 }

