package web.id.wahyou.animeq.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import web.id.wahyou.animeq.R
import web.id.wahyou.animeq.databinding.ActivityMainBinding
import web.id.wahyou.animeq.databinding.TabLayoutBinding
import web.id.wahyou.animeq.ui.anime.AnimeFragment
import web.id.wahyou.animeq.ui.manga.MangaFragment

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter : TabPagerAdapter by lazy {
        TabPagerAdapter(this, arrayListOf(AnimeFragment(), MangaFragment()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAdapter()
        setupSensitivity()
    }

    private fun setupAdapter() {
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            when (position) {
                0 -> { tab.customView = getTabLayout(getString(R.string.title_tab_anime), R.drawable.ic_baseline_home_24) }
                1 -> { tab.customView = getTabLayout(getString(R.string.title_tab_manga), R.drawable.ic_baseline_insert_chart_24) }
            }
        }.attach()
        binding.pager.setCurrentItem(0, true)
    }

    private fun getTabLayout(title: String, icon: Int): View? {
        val tabBinding = TabLayoutBinding.inflate(layoutInflater)
        tabBinding.title.text = title
        tabBinding.icon.setImageResource(icon)
        return tabBinding.root
    }

    private fun setupSensitivity() {
        try {
            val ff =
                ViewPager2::class.java.getDeclaredField("mRecyclerView")
            ff.isAccessible = true
            val recyclerView = ff[binding.pager] as RecyclerView
            val touchSlopField =
                RecyclerView::class.java.getDeclaredField("mTouchSlop")
            touchSlopField.isAccessible = true
            val touchSlop = touchSlopField[recyclerView] as Int
            touchSlopField[recyclerView] = touchSlop * 4
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}