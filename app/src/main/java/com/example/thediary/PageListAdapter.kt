package com.example.thediary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PageListAdapter: ListAdapter<Page, PageListAdapter.PageViewHolder>(PageComparator())
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val current= getItem(position)
        holder.bind(current.title,current.content)
    }


    class PageViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        private val titleItemView: TextView= itemView.findViewById(R.id.title)
        private val contentItemView:TextView= itemView.findViewById(R.id.content)

        fun bind(text1:String?,text2:String)
        {
            titleItemView.text= text1
            contentItemView.text= text2
        }

        companion object {
            fun create(parent: ViewGroup): PageViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_item, parent, false)
                return PageViewHolder(view)
            }


    }


}
    class PageComparator:DiffUtil.ItemCallback<Page>(){
        override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean {
            return  oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean {
            return  oldItem.title==oldItem.title
        }


    }




}