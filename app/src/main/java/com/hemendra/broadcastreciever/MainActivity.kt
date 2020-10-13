package com.hemendra.broadcastreciever

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         var alertDialog = AlertDialog.Builder(this)

        var infilter = IntentFilter()
        infilter.addAction(Intent.ACTION_POWER_CONNECTED)
        infilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        infilter.addAction(Intent.ACTION_SCREEN_OFF)
        infilter.addAction(Intent.ACTION_SCREEN_ON)
        infilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        infilter.addAction("example_event")
        registerReceiver(MyReciever(alertDialog),infilter)

        btn.setOnClickListener {
            var i = Intent()
            i.action= "example_event"
            i.putExtra("text","My Own Event Is triggered...")
            sendBroadcast(i)
        }
    }
}

class MyReciever(var alertDialog:AlertDialog.Builder) :BroadcastReceiver()
{
    override fun onReceive(p0: Context?, intent: Intent?) {

        alertDialog.setTitle("Broadcast Recieved.")
        alertDialog.setMessage(intent?.action)

        if(intent?.action.equals("example_event"))
        {
            alertDialog.setMessage(intent?.getStringExtra("text"))
        }

        alertDialog.setNegativeButton("cancel",object :DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }

        })
        alertDialog.setPositiveButton("cancel",object :DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }

        })
        alertDialog.show()

    }

}