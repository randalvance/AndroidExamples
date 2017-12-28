package com.randalvance.bitsandpizzas

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CaptionedImagesAdapter(private var captions: Array<String>, private var imageIds: Array<Int>) :
        RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>() {

    var onCardClick : ((Int) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val cardView = holder?.cardView
        val imageView = cardView!!.findViewById<ImageView>(R.id.info_image)
        val drawable = ContextCompat.getDrawable(cardView!!.context, imageIds[position])

        imageView.setImageDrawable(drawable)
        imageView.contentDescription = captions[position]

        val textView = cardView.findViewById< TextView>(R.id.info_text)
        textView.text = captions[position]

        cardView?.setOnClickListener {
            onCardClick?.invoke(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent?.context).inflate(
                R.layout.card_captioned_image,
                parent,
                false
        ) as CardView

        return ViewHolder(cv)
    }

    override fun getItemCount(): Int = captions.size

    inner class ViewHolder(val cardView: CardView?) : RecyclerView.ViewHolder(cardView)

    interface Listener {
        fun onClick(position: Int)
    }
}