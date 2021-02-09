package web.id.wahyou.animeq.ui.manga

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
import web.id.wahyou.animeq.databinding.FragmentMangaBinding
import web.id.wahyou.animeq.ui.mangadetail.DetailMangaActivity
import web.id.wahyou.animeq.utils.sheetBehavior
import web.id.wahyou.core.adapter.MangaAdapter
import web.id.wahyou.core.domain.model.manga.Manga
import web.id.wahyou.core.domain.state.MainState

class MangaFragment : Fragment() {
    private lateinit var behavior: BottomSheetBehavior<*>
    private val viewModel: MangaViewModel by viewModel()

    private val binding : FragmentMangaBinding by lazy {
        FragmentMangaBinding.inflate(layoutInflater)
    }

    private val mangaAdapter : MangaAdapter by lazy {
        MangaAdapter { item -> detailManga(item)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomsheet()
        setupViewModel()
        setupAdapter()
    }

    private fun setupViewModel() {
        viewModel.manga.observe(viewLifecycleOwner, {
            when (it) {
                is MainState.Loading -> isLoading(true)
                is MainState.Success -> getDataSuccess(it)
                is MainState.Error -> showError()
            }
        })
    }

    private fun setupAdapter() {
        with(binding) {
            rvManga.also {
                it.adapter = mangaAdapter
                it.layoutManager = GridLayoutManager(requireContext(), 3)
                it.setHasFixedSize(true)
            }

            imgFavorite.also {
                imgFavorite.setOnClickListener {
                    try {
                        startActivity(
                            Intent(
                                requireContext(),
                                Class.forName("web.id.wahyou.favorite.manga.MangaFavoriteActivity")
                            )
                        )
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

    private fun getDataSuccess(anime: MainState<List<Manga>>) {
        mangaAdapter.setData(anime.data)
        isLoading(false)
    }

    private fun detailManga(anime: Manga) {
        startActivity(Intent(requireContext(), DetailMangaActivity::class.java).also {
            it.putExtra("data", anime)
        })
    }

    private fun isLoading(isLoading: Boolean) {
        if(isLoading) {
            binding.pgManga.visibility = View.VISIBLE
            binding.rvManga.visibility = View.INVISIBLE
        } else {
            binding.pgManga.visibility = View.GONE
            binding.rvManga.visibility = View.VISIBLE
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}