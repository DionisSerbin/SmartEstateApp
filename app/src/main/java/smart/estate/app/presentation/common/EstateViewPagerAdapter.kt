package smart.estate.app.presentation.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import smart.estate.app.R

class EstateViewPagerAdapter(private val imageList: List<Int>) :
    RecyclerView.Adapter<EstateViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        private val imageView by lazy { view.findViewById<ImageView>(R.id.item_page_image_view) }

        fun bind(imageUrl: Int) {

            Glide.with(imageView)
                .load(imageUrl)
                .into(imageView)
        }
    }

    override fun getItemCount() = imageList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(imageList[position])
    }
}