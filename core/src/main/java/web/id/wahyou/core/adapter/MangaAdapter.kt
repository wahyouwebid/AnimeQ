package web.id.wahyou.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import web.id.wahyou.core.databinding.AdapterMangaBinding
import web.id.wahyou.core.domain.model.manga.Manga

class MangaAdapter (private val showDetail: (Manga) -> Unit) : RecyclerView.Adapter<MangaAdapter.ViewHolder>() {

    private var data = ArrayList<Manga>()

    fun setData(animeList: List<Manga>?) {
        if (animeList == null) return
        data.clear()
        data.addAll(animeList)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {
            tvTitle.text = data[position].canonicalTitle
            Glide.with(holder.itemView.context).load(data[position].posterImage).into(imgCover)

            root.setOnClickListener {
                showDetail(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterMangaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    class ViewHolder(val view: AdapterMangaBinding) : RecyclerView.ViewHolder(view.root)
}