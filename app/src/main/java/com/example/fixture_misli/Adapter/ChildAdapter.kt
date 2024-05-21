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

    inner class ChildViewHolder(itemView: ChildLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {}

    private lateinit var binding: ChildLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        binding = ChildLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {

        val simpleDateFormatDay = SimpleDateFormat("dd MMMM")
        val dayString = simpleDateFormatDay.format(childList[position].d - 10800000)
        val simpleDateFormatHour = SimpleDateFormat("HH:mm")
        val hourString = simpleDateFormatHour.format(childList[position].d - 10800000)

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

        binding.homeTeamTextView.text = childList[position].ht.sn
        binding.awayTeamTextView.text = childList[position].at.sn

        val soccerStatus = childList[position].sc.st
        when (soccerStatus) {
            1 -> {
                binding.statusTextView.text = dayString
                binding.finalScoreTextView.text = "V"
                binding.halfScoreTextView.text = hourString
            }
            2 -> {
                if (childList[position].sc.abbr == "1.Y") {
                    binding.statusTextView.text = childList[position].sc.min.toString()
                    binding.finalScoreTextView.text = childList[position].sc.ht.c.toString() + " - " + childList[position].sc.at.c.toString()
                    binding.halfScoreTextView.text = childList[position].sc.ht.c.toString() + " - " + childList[position].sc.at.c.toString()

                    val color = ContextCompat.getColor(holder.itemView.context, R.color.red)
                    binding.statusTextView.setTextColor(color)

                } else if (childList[position].sc.abbr == "ERT") {
                    binding.statusTextView.text = dayString
                    binding.finalScoreTextView.text = childList[position].sc.abbr
                    binding.halfScoreTextView.text = hourString
                }
            }
            3 -> {
                binding.statusTextView.text = childList[position].sc.min.toString()
                binding.finalScoreTextView.text = childList[position].sc.ht.c.toString() + " - " + childList[position].sc.at.c.toString()
                binding.halfScoreTextView.text = childList[position].sc.ht.ht.toString() + " - " + childList[position].sc.at.ht.toString()

                val color = ContextCompat.getColor(holder.itemView.context, R.color.red)
                binding.statusTextView.setTextColor(color)
            }
            4 -> {
                binding.statusTextView.text = childList[position].sc.min.toString()
                binding.finalScoreTextView.text = childList[position].sc.abbr
                binding.halfScoreTextView.text = childList[position].sc.ht.c.toString() + " - " + childList[position].sc.at.c.toString()

                val color = ContextCompat.getColor(holder.itemView.context, R.color.red)
                binding.statusTextView.setTextColor(color)
            }
            5, 6 -> {
                binding.statusTextView.text = "MS"
                binding.finalScoreTextView.text = childList[position].sc.ht.c.toString() + " - " + childList[position].sc.at.c.toString()
                binding.halfScoreTextView.text = childList[position].sc.ht.ht.toString() + " - " + childList[position].sc.at.ht.toString()
            }
            10 -> {
                binding.statusTextView.text = childList[position].sc.abbr
                binding.finalScoreTextView.text = childList[position].sc.ht.c.toString() + " - " + childList[position].sc.at.c.toString()
                binding.halfScoreTextView.text = childList[position].sc.ht.ht.toString() + " - " + childList[position].sc.at.ht.toString()
            }
            12 -> {
                binding.statusTextView.text = "PEN"
                binding.finalScoreTextView.text = childList[position].sc.ht.c.toString() + " - " + childList[position].sc.at.c.toString()
                binding.halfScoreTextView.text = childList[position].sc.ht.ht.toString() + " - " + childList[position].sc.at.ht.toString()
            }
            24 -> {
                binding.statusTextView.text = dayString
                binding.finalScoreTextView.text = childList[position].sc.abbr
                binding.halfScoreTextView.text = hourString
            }
        }
    }

    override fun getItemCount(): Int {
        return childList.size
    }

}
