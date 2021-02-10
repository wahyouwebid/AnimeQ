package web.id.wahyou.animeq.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import web.id.wahyou.animeq.databinding.ActivitySplashBinding
import web.id.wahyou.animeq.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        moveMainActivity()
    }

    private fun moveMainActivity() {
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}