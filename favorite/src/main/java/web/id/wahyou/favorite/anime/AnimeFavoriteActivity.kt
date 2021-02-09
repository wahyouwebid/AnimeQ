package web.id.wahyou.favorite.anime

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import web.id.wahyou.animeq.ui.animedetail.DetailAnimeActivity
import web.id.wahyou.core.adapter.AnimeAdapter
import web.id.wahyou.core.domain.model.anime.Anime
import web.id.wahyou.favorite.databinding.ActivityFavoriteBinding
import web.id.wahyou.favorite.di.favoriteModule

class AnimeFavoriteActivity : AppCompatActivity() {

    private val viewModel : AnimeFavoriteViewModel by viewModel()

    private val binding : ActivityFavoriteBinding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    private val adapter: AnimeAdapter by lazy {
        AnimeAdapter {item -> detailAnime(item)}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)
        setupViewModel()
        setupAdapter()
    }

    private fun setupViewModel() {
        viewModel.favorite.observe(this, { anime ->
            adapter.setData(anime)
            setupEmptyData(anime)
            })
    }

    private fun setupAdapter() {
        with(binding) {
            rvAnime.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(this@AnimeFavoriteActivity, 1)
                it.setHasFixedSize(true)
            }
            
            imgBack.setOnClickListener { finish() }

        }
    }

    private fun setupEmptyData (anime: List<Anime>){
        if(anime.isNotEmpty()) {
            binding.lottieEmpty.visibility = View.GONE
        } else {
            binding.lottieEmpty.visibility = View.VISIBLE
        }
    }

    private fun detailAnime(anime: Anime) {
        startActivity(Intent(this, DetailAnimeActivity::class.java).also {
            it.putExtra("data", anime)
        })
    }
}