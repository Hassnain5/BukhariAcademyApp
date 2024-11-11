package com.hasnain.application.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.hasnain.application.R
import com.hasnain.application.models.AdvertisementModel

class AdPagerAdapter(private val context: Context, private val adList: List<AdvertisementModel>) : PagerAdapter() {

    override fun getCount(): Int {
        return adList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.item_advertisement, container, false)

        // Bind your data to the views in the layout
        val ad = adList[position]
        view.findViewById<TextView>(R.id.tv_ad_text1).text = ad.title
        view.findViewById<TextView>(R.id.tv_ad_text2).text = ad.description
        view.findViewById<Button>(R.id.btn_register).setOnClickListener {
            // Handle button click
        }
        view.findViewById<ImageView>(R.id.iv_ad_image).setImageResource(ad.imageResId)

        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
