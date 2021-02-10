package web.id.wahyou.animeq.ui.playvideo

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import web.id.wahyou.animeq.databinding.ActivityPlayVideoBinding
import web.id.wahyou.core.domain.model.anime.Anime

class PlayVideoActivity : AppCompatActivity() {

    private val binding: ActivityPlayVideoBinding by lazy {
        ActivityPlayVideoBinding.inflate(layoutInflater)
    }

    private val data : Anime? by lazy {
        intent.getParcelableExtra("data")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupPlayer()
    }

    private fun setupPlayer() {
        with(binding) {
            ytPlayer.also {
                lifecycle.addObserver(it)
                it.initialize(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.cueVideo(data!!.youtubeVideoId, 0F)
                            youTubePlayer.play()
                        }
                    }, true
                )
                it.addFullScreenListener(object : YouTubePlayerFullScreenListener {
                    override fun onYouTubePlayerEnterFullScreen() {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    }

                    override fun onYouTubePlayerExitFullScreen() {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    }
                })
                it.enableBackgroundPlayback(true)
            }
        }
    }
}