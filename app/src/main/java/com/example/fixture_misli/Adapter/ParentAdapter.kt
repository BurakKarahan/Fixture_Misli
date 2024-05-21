package com.fixture_misli.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fixture_misli.Adapter.ChildAdapter
import com.example.fixture_misli.Model.Fixture
import com.example.fixture_misli.databinding.ParentLayoutBinding

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    private var parentList = ArrayList<Fixture>()
    fun setParentList(parentList: List<Fixture>) {
        this.parentList = parentList as ArrayList<Fixture>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ParentLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ParentLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parentItem = parentList[position]

        holder.binding.parentTextView.text = parentItem.to.n
        Glide.with(holder.itemView)
            .load(parentList[position].to.flag)
            .into(holder.binding.countryImageView)

        holder.binding.childRecyclerView.setHasFixedSize(true)
        holder.binding.childRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        val adapter = ChildAdapter(parentItem.data)
        holder.binding.childRecyclerView.adapter = adapter

        holder.binding.childRecyclerView.addItemDecoration(
            DividerItemDecoration(
                holder.binding.childRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun getItemCount(): Int {
        return parentList.size
    }
}