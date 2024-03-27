package pe.edu.idat.appgynrv

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


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
