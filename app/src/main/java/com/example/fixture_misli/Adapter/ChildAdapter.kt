package com.example.fixture_misli.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fixture_misli.Model.Data
import com.example.fixture_misli.R
import com.example.fixture_misli.databinding.ChildLayoutBinding
import java.text.SimpleDateFormat

class ChildAdapter(private val childList: List<Data>) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    private var onClickListener: ChildAdapter.OnClickListener? = null
    private lateinit var binding: ChildLayoutBinding

    inner class ChildViewHolder(itemView: ChildLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        binding = ChildLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Data)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {

        val parentItem = childList[position]

        holder.itemView.setOnClickListener {
            onClickListener!!.onClick(position, parentItem)
        }

        val simpleDateFormatHour = SimpleDateFormat("HH:mm")
        val hourString = simpleDateFormatHour.format(childList[position].d)

        if (childList[position].ht.rc != 0) {
            binding.homeTeamRedCardTextView.visibility = View.VISIBLE
        } else {
            binding.homeTeamRedCardTextView.visibility = View.GONE
        }

        if (childList[position].at.rc != 0) {
            binding.awayTeamRedCardTextView.visibility = View.VISIBLE
        } else {
            binding.awayTeamRedCardTextView.visibility = View.GONE
        }

        binding.homeTeamTextView.text = childList[position].ht.n
        binding.awayTeamTextView.text = childList[position].at.n

        val soccerStatus = childList[position].sc.st
        when (soccerStatus) {
            1 -> {
                binding.tvStatusLeft.text = hourString
                binding.finalScoreTextView.text = "V"
                binding.tvStatusRight.visibility = View.GONE
                binding.ivFavorite.visibility = View.VISIBLE

                val color = ContextCompat.getColor(holder.itemView.context, R.color.black)
                binding.tvStatusLeft.setTextColor(color)
            }

            2, 3 -> {
                binding.tvStatusLeft.text = childList[position].sc.min.toString()
                (childList[position].sc.ht.r.toString() + " - " + childList[position].sc.at.r.toString()).also {
                    binding.finalScoreTextView.text = it
                }
                binding.tvStatusRight.visibility = View.GONE
                binding.ivFavorite.visibility = View.VISIBLE
            }

            4 -> {
                binding.tvStatusLeft.text = childList[position].sc.abbr
                (childList[position].sc.ht.r.toString() + " - " + childList[position].sc.at.r.toString()).also {
                    binding.finalScoreTextView.text = it
                }
                binding.tvStatusRight.visibility = View.GONE
                binding.ivFavorite.visibility = View.VISIBLE
            }

            5, 6 -> {
                binding.tvStatusLeft.text = "MS"
                (childList[position].sc.ht.r.toString() + " - " + childList[position].sc.at.r.toString()).also {
                    binding.finalScoreTextView.text = it
                }
                (childList[position].sc.ht.ht.toString() + "-" + childList[position].sc.at.ht.toString()).also {
                    binding.tvStatusRight.text = it
                }
                binding.tvStatusRight.visibility = View.VISIBLE
                binding.ivFavorite.visibility = View.GONE
            }

            10 -> {
                binding.tvStatusLeft.text = childList[position].sc.abbr
                (childList[position].sc.ht.r.toString() + " - " + childList[position].sc.at.r.toString()).also {
                    binding.finalScoreTextView.text = it
                }
                (childList[position].sc.ht.ht.toString() + "-" + childList[position].sc.at.ht.toString()).also {
                    binding.tvStatusRight.text = it
                }
                binding.tvStatusRight.visibility = View.VISIBLE
                binding.ivFavorite.visibility = View.GONE
            }

            12 -> {
                binding.tvStatusLeft.text = "PENMS"
                (childList[position].sc.ht.r.toString() + " - " + childList[position].sc.at.r.toString()).also {
                    binding.finalScoreTextView.text = it
                }
                (childList[position].sc.ht.ht.toString() + "-" + childList[position].sc.at.ht.toString()).also {
                    binding.tvStatusRight.text = it
                }
                binding.tvStatusRight.visibility = View.VISIBLE
                binding.ivFavorite.visibility = View.GONE
            }

            24 -> {
                binding.tvStatusLeft.text = childList[position].sc.abbr
                binding.finalScoreTextView.text = "V"
                binding.tvStatusRight.visibility = View.GONE
                binding.ivFavorite.visibility = View.GONE
            }
        }

        binding.ivFavorite.setOnClickListener {
            binding.ivFavorite.setImageResource(R.drawable.favorite_on)
        }

    }
    // TODO: adding favorites will be done
    override fun getItemCount(): Int {
        return childList.size
    }
}
