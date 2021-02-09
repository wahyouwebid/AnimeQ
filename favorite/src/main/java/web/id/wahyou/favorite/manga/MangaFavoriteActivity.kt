package web.id.wahyou.favorite.manga

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import web.id.wahyou.animeq.ui.mangadetail.DetailMangaActivity
import web.id.wahyou.core.adapter.MangaAdapter
import web.id.wahyou.core.domain.model.manga.Manga
import web.id.wahyou.favorite.databinding.ActivityFavoriteMangaBinding
import web.id.wahyou.favorite.di.favoriteModule

class MangaFavoriteActivity : AppCompatActivity() {

    private val viewModel : MangaFavoriteViewModel by viewModel()

    private val binding : ActivityFavoriteMangaBinding by lazy {
        ActivityFavoriteMangaBinding.inflate(layoutInflater)
    }

    private val adapter : MangaAdapter by lazy {
        MangaAdapter { item -> detailManga(item)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)
        setupViewModel()
        setupAdapter()
    }

    private fun setupViewModel() {
        viewModel.favorite.observe(this, { manga ->
            adapter.setData(manga)
            setupEmptyData(manga)
        })
    }

    private fun setupAdapter() {
        with(binding) {
            rvManga.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(this@MangaFavoriteActivity, 3)
                it.setHasFixedSize(true)
            }

            imgBack.setOnClickListener { finish() }

        }
    }

    private fun setupEmptyData (anime: List<Manga>){
        if(anime.isNotEmpty()) {
            binding.lottieEmpty.visibility = View.GONE
        } else {
            binding.lottieEmpty.visibility = View.VISIBLE
        }
    }

    private fun detailManga(anime: Manga) {
        startActivity(Intent(this, DetailMangaActivity::class.java).also {
            it.putExtra("data", anime)
        })
    }
}