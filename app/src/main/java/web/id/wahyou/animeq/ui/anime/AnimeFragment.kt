package web.id.wahyou.animeq.ui.anime

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.android.viewmodel.ext.android.viewModel
import web.id.wahyou.animeq.databinding.BottomSheetBinding
import web.id.wahyou.animeq.databinding.FragmentAnimeBinding
import web.id.wahyou.animeq.ui.animedetail.DetailAnimeActivity
import web.id.wahyou.animeq.utils.sheetBehavior
import web.id.wahyou.core.adapter.AnimeAdapter
import web.id.wahyou.core.domain.model.anime.Anime
import web.id.wahyou.core.domain.state.MainState

class AnimeFragment : Fragment() {
    private lateinit var behavior: BottomSheetBehavior<*>
    private val viewModel: AnimeViewModel by viewModel()

    private val binding : FragmentAnimeBinding by lazy {
        FragmentAnimeBinding.inflate(layoutInflater)
    }

    private val animeAdapter : AnimeAdapter by lazy {
        AnimeAdapter { item -> detailAnime(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomsheet()
        setupViewModel()
        setupAdapter()
    }

    private fun setupViewModel() {
        viewModel.anime.observe(viewLifecycleOwner, {
            when (it) {
                is MainState.Loading -> isLoading(true)
                is MainState.Success -> getDataSuccess(it)
                is MainState.Error -> showError()
            }
        })
    }

    private fun setupAdapter() {
        with(binding) {
            rvAnime.also {
                it.adapter = animeAdapter
                it.layoutManager = GridLayoutManager(requireContext(), 1)
                it.setHasFixedSize(true)
            }

            imgFavorite.also {
                imgFavorite.setOnClickListener {
                    try {
                        startActivity(Intent(requireContext(), Class.forName("web.id.wahyou.favorite.anime.AnimeFavoriteActivity")))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            requireContext(),
                            "Module not installed!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun getDataSuccess(anime: MainState<List<Anime>>) {
        animeAdapter.setData(anime.data)
        isLoading(false)
    }

    private fun detailAnime(anime: Anime) {
        startActivity(Intent(requireContext(), DetailAnimeActivity::class.java).also {
            it.putExtra("data", anime)
        })
    }

    private fun isLoading(isLoading: Boolean) {
        if(isLoading) {
            binding.pgAnime.visibility = View.VISIBLE
            binding.rvAnime.visibility = View.INVISIBLE
        } else {
            binding.pgAnime.visibility = View.GONE
            binding.rvAnime.visibility = View.VISIBLE
        }
    }

    private fun setupBottomsheet(){
        behavior = binding.bottomSheet.sheetBehavior()
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun showError() {
        val binding = BottomSheetBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(binding.root)
        with(binding) {
            btnOk.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}