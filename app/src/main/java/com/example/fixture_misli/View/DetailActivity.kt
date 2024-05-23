package com.example.fixture_misli.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.fixture_misli.Model.FixtureModelParcelable
import com.example.fixture_misli.R
import com.example.fixture_misli.Util.Constant
import com.example.fixture_misli.databinding.ActivityDetailBinding
import com.example.fixture_misli.databinding.ActivityMainBinding
import com.fixture_misli.Adapter.ParentAdapter
import java.text.SimpleDateFormat
import java.util.Locale

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var fixtureModel: FixtureModelParcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: adding favorites will be done
        binding.ivFavorite.setOnClickListener {
            binding.ivFavorite.setImageResource(R.drawable.favorite_on_detail)
        }

        intent?.let {
            fixtureModel = intent.getParcelableExtra<FixtureModelParcelable>(Constant.FIXTURE)
        }

        val simpleDateFormatDay = SimpleDateFormat("dd MMMM yyyy", Locale("tr"))
        val dateString = simpleDateFormatDay.format(fixtureModel?.date ?: 0)
        val simpleDateFormatHour = SimpleDateFormat("HH:mm")
        val hourString = simpleDateFormatHour.format(fixtureModel?.date ?: 0)

        ((fixtureModel?.homeTeamName ?: "-") + " - " + (fixtureModel?.awayTeamName
            ?: "-")).also { binding.tvTeamName.text = it }

        val status = fixtureModel?.st
        when (status) {
            1 -> {
                (hourString + "\n" + dateString).also { binding.tvStatus.text = it }
                binding.tvScore.visibility = View.GONE
                binding.cvScoreDetail.visibility = View.GONE
            }

            2 -> {
                binding.tvStatus.text = fixtureModel?.min.toString()
                val colorTvScore = ContextCompat.getColor(this, R.color.red)
                binding.tvStatus.setTextColor(colorTvScore)

                ((fixtureModel?.homeTeamC ?: 0).toString() + " - " + (fixtureModel?.awayTeamC
                    ?: 0).toString()).also { binding.tvScore.text = it }

                binding.cvScoreDetail.visibility = View.GONE
            }

            3 -> {
                binding.tvStatus.text = fixtureModel?.min.toString()
                val colorTvScore = ContextCompat.getColor(this, R.color.red)
                binding.tvStatus.setTextColor(colorTvScore)

                ((fixtureModel?.homeTeamC ?: 0).toString() + " - " + (fixtureModel?.awayTeamC
                    ?: 0).toString()).also { binding.tvScore.text = it }

                ("İY " + (fixtureModel?.homeTeamHT
                    ?: 0).toString() + "-" + (fixtureModel?.awayTeamHT
                    ?: 0).toString()).also { binding.tvScoreDetail.text = it }
                binding.cvScoreDetail.visibility = View.VISIBLE
            }

            4 -> {
                binding.tvStatus.text = "ilk Yarı Sonucu"

                ((fixtureModel?.homeTeamC ?: 0).toString() + " - " + (fixtureModel?.awayTeamC
                    ?: 0).toString()).also { binding.tvScore.text = it }

                ("İY " + (fixtureModel?.homeTeamHT
                    ?: 0).toString() + "-" + (fixtureModel?.awayTeamHT
                    ?: 0).toString()).also { binding.tvScoreDetail.text = it }
                binding.cvScoreDetail.visibility = View.VISIBLE
            }

            5, 6 -> {
                binding.tvStatus.text = "Maç Sonucu"

                ((fixtureModel?.homeTeamC ?: 0).toString() + " - " + (fixtureModel?.awayTeamC
                    ?: 0).toString()).also { binding.tvScore.text = it }

                ("İY " + (fixtureModel?.homeTeamHT
                    ?: 0).toString() + "-" + (fixtureModel?.awayTeamHT
                    ?: 0).toString()).also { binding.tvScoreDetail.text = it }
                binding.cvScoreDetail.visibility = View.VISIBLE
            }

            12 -> {
                binding.tvStatus.text = "Penaltı MS"

                ((fixtureModel?.homeTeamC ?: 0).toString() + " - " + (fixtureModel?.awayTeamC
                    ?: 0).toString()).also { binding.tvScore.text = it }

                ("İY " + (fixtureModel?.homeTeamHT
                    ?: 0).toString() + "-" + (fixtureModel?.awayTeamHT ?: 0).toString()
                        + "   MS " + (fixtureModel?.homeTeamR
                    ?: 0).toString() + "-" + (fixtureModel?.awayTeamR ?: 0).toString()
                        + "   PEN " + (fixtureModel?.homeTeamP
                    ?: 0).toString() + "-" + (fixtureModel?.awayTeamP
                    ?: 0).toString()).also { binding.tvScoreDetail.text = it }
                binding.cvScoreDetail.visibility = View.VISIBLE
            }

            24 -> {
                binding.tvStatus.text = "Ertelendi"
                binding.tvScore.text = hourString
                binding.cvScoreDetail.visibility = View.GONE
            }
        }
    }
}