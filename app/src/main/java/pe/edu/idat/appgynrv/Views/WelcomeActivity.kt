package pe.edu.idat.appgynrv.Views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import pe.edu.idat.appgynrv.R


class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }, 4000)
    }
}
