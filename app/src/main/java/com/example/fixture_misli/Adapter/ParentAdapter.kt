package com.fixture_misli.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fixture_misli.Adapter.ChildAdapter
import com.example.fixture_misli.Model.Data
import com.example.fixture_misli.Model.Fixture
import com.example.fixture_misli.Model.FixtureModelParcelable
import com.example.fixture_misli.Util.Constant
import com.example.fixture_misli.View.DetailActivity
import com.example.fixture_misli.databinding.ParentLayoutBinding

class ParentAdapter() : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

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
        holder.binding.childRecyclerView.layoutManager =
            LinearLayoutManager(holder.itemView.context)
        val adapter = ChildAdapter(parentItem.data)
        holder.binding.childRecyclerView.adapter = adapter

        holder.binding.childRecyclerView.addItemDecoration(
            DividerItemDecoration(
                holder.binding.childRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        adapter.setOnClickListener(object :
            ChildAdapter.OnClickListener {
            override fun onClick(position: Int, model: Data) {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java)
                val fixtureModel = FixtureModelParcelable(
                    model.d,
                    model.to.n,
                    model.to.flag,
                    if (model.sc.st == 2 || model.sc.st == 3) model.sc.min else 0,
                    model.sc.st,
                    model.ht.n,
                    if (model.sc.st != 1 && model.sc.st != 24) model.sc.ht.r else 0,
                    if (model.sc.st != 1 && model.sc.st != 24) model.sc.ht.c else 0,
                    if (model.sc.st != 1 && model.sc.st != 2 && model.sc.st != 24) model.sc.ht.ht else 0,
                    if (model.sc.st == 12) model.sc.ht.p else 0,
                    model.at.n,
                    if (model.sc.st != 1 && model.sc.st != 24) model.sc.at.r else 0,
                    if (model.sc.st != 1 && model.sc.st != 24) model.sc.at.c else 0,
                    if (model.sc.st != 1 && model.sc.st != 2 && model.sc.st != 24) model.sc.at.ht else 0,
                    if (model.sc.st == 12) model.sc.at.p else 0,
                )
                intent.putExtra(Constant.FIXTURE, fixtureModel)
                holder.itemView.context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int {
        return parentList.size
    }
}


