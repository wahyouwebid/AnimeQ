package web.id.wahyou.animeq.ui.animedetail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.android.viewmodel.ext.android.viewModel
import web.id.wahyou.animeq.R
import web.id.wahyou.animeq.databinding.ActivityDetailAnimeBinding
import web.id.wahyou.animeq.databinding.BottomSheetSuccessBinding
import web.id.wahyou.animeq.ui.playvideo.PlayVideoActivity
import web.id.wahyou.animeq.utils.formatDate
import web.id.wahyou.core.domain.model.anime.Anime

class DetailAnimeActivity : AppCompatActivity() {

    private val viewModel : DetailAnimeViewModel by viewModel()

    private val binding: ActivityDetailAnimeBinding by lazy {
        ActivityDetailAnimeBinding.inflate(layoutInflater)
    }

    private val data : Anime? by lazy {
        intent.getParcelableExtra("data")
    }

    private var statusFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setData()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {

        with(binding) {
            if(data != null){
                statusFavorite = data?.isFavorite!!

                tvTitleToolbar.text = data?.canonicalTitle
                tvTitle.text = data?.canonicalTitle
                tvTotalEpisode.text = data?.episodeCount.toString() + " Episode"
                tvScore.text = data?.averageRating
                tvRelease.text = formatDate(data?.startDate)
                tvDuration.text = data?.totalDuration.toString() + " Minutes"
                tvShow.text = data?.subtype
                tvStatus.text = data?.status
                tvSynopsis.text = data?.synopsis.toString()

                Glide.with(this@DetailAnimeActivity).load(data?.coverImage).into(imgCover)
                Glide.with(this@DetailAnimeActivity).load(data?.posterImage).into(imgCoverSmall)

                checkStatusFavorite(data)
            }

            imgBack.setOnClickListener {
                finish()
            }

            imgCover.setOnClickListener {
                startActivity(Intent(this@DetailAnimeActivity, PlayVideoActivity::class.java).also {
                    it.putExtra("data", data)
                })
            }

            btnFavorite.setOnClickListener {
                setFavorite()
            }
        }
    }

    private fun setFavorite() {
        statusFavorite = !statusFavorite
        viewModel.setFavoriteAnime(data!!, statusFavorite)
        setStatusFavorite(statusFavorite)
    }

    private fun checkStatusFavorite(data: Anime?) {
        if(data!!.isFavorite) {
            setDrawableIsFavorite()
        } else {
            setDrawableNotFavorite()
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            setDrawableIsFavorite()
            successDialogAnime()
        } else {
            setDrawableNotFavorite()
        }
    }

    private fun setDrawableIsFavorite() {
        binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_favorite_24
                )
        )
    }

    private fun setDrawableNotFavorite() {
        binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_favorite_border_24
                )
        )
    }

    private fun successDialogAnime() {
        val binding = BottomSheetSuccessBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(binding.root)
        with(binding) {
            btnOk.setOnClickListener {
                try {
                    startActivity(
                        Intent(
                            this@DetailAnimeActivity,
                            Class.forName("web.id.wahyou.favorite.anime.AnimeFavoriteActivity")
                        )
                    )
                    dialog.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                    android.widget.Toast.makeText(
                        this@DetailAnimeActivity,
                        "Module not installed!",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        dialog.show()
    }
}