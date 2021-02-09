package web.id.wahyou.animeq.ui.mangadetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.android.viewmodel.ext.android.viewModel
import web.id.wahyou.animeq.R
import web.id.wahyou.animeq.databinding.ActivityDetailMangaBinding
import web.id.wahyou.animeq.databinding.BottomSheetSuccessBinding
import web.id.wahyou.animeq.utils.formatDate
import web.id.wahyou.core.domain.model.manga.Manga

class DetailMangaActivity : AppCompatActivity() {

    private val viewModel : DetailMangaViewModel by viewModel()

    private val binding : ActivityDetailMangaBinding by lazy {
        ActivityDetailMangaBinding.inflate(layoutInflater)
    }

    private val data : Manga? by lazy {
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
        statusFavorite = data?.isFavorite!!

        with(binding) {
            if(data != null){
                tvTitleToolbar.text = data?.canonicalTitle
                tvTitle.text = data?.canonicalTitle
                tvRelease.text = if(data?.startDate != "") formatDate(data?.startDate) else "-"
                tvType.text = data?.type
                tvRating.text = data?.averageRating
                tvSerialize.text = data?.serialization
                tvChapter.text = data?.chapterCount.toString() + " Chapter"
                tvUserCount.text = data?.userCount.toString() + " Users Read"
                tvStatusManga.text = data?.status
                tvVolume.text = data?.volumeCount.toString() + " Volumes"
                tvRank.text = data?.popularityRank.toString() + " Popular Rank"
                tvSynopsis.text = data?.synopsis
                Glide.with(this@DetailMangaActivity).load(data?.posterImage).into(imgCover)
                checkStatusFavorite(data)
            }

            imgBack.setOnClickListener {
                finish()
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

    private fun checkStatusFavorite(data : Manga?) {
        if(data?.isFavorite!!) {
            setDrawableIsFavorite()
        } else {
            setDrawableNotFavorite()
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            setDrawableIsFavorite()
            successDialogManga()
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

    private fun successDialogManga() {
        val binding = BottomSheetSuccessBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(binding.root)
        with(binding) {
            btnOk.setOnClickListener {
                try {
                    startActivity(
                        android.content.Intent(
                            this@DetailMangaActivity,
                            Class.forName("web.id.wahyou.favorite.manga.MangaFavoriteActivity")
                        )
                    )
                    dialog.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                    android.widget.Toast.makeText(
                        this@DetailMangaActivity,
                        "Module not installed!",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        dialog.show()
    }
}