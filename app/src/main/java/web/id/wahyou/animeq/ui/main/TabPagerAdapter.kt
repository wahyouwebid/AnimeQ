package web.id.wahyou.animeq.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPagerAdapter(activity: AppCompatActivity, private val fragments: ArrayList<Fragment>) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}