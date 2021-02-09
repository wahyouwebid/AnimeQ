package web.id.wahyou.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import web.id.wahyou.core.databinding.AdapterAnimeBinding
import web.id.wahyou.core.domain.model.anime.Anime

class AnimeAdapter (private val showDetail: (Anime) -> Unit) : RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    private var data = ArrayList<Anime>()

    fun setData(animeTrendingList: List<Anime>?) {
        if (animeTrendingList == null) return
        data.clear()
        data.addAll(animeTrendingList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvType.text = data[position].subtype
            tvTitle.text = data[position].canonicalTitle
            tvRating.text = data[position].averageRating
            tvTotalEpisode.text = data[position].episodeCount.toString() + " Episode"
            Glide.with(holder.itemView.context).load(data[position].posterImage).into(imgCover)

            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            AdapterAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterAnimeBinding) : RecyclerView.ViewHolder(view.root)


}