package com.itmo.moonphase

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itmo.moonphase.databinding.ListItemMoonPhaseBinding
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class MoonPhaseAdapter(
    context: Context,
    private val moonPhases: List<MoonPhaseInfo>,
) : RecyclerView.Adapter<MoonPhaseAdapter.MoonPhaseViewHolder>() {

    private val dateTimeFormatter = DateTimeFormatter.ofPattern(context.getString(R.string.moonPhase_dateTime_format))
    private val percentFormatter = NumberFormat.getPercentInstance().apply { minimumFractionDigits = 0 }

    class MoonPhaseViewHolder(
        private val binding: ListItemMoonPhaseBinding,
        private val dateTimeFormatter: DateTimeFormatter,
        private val percentFormatter: NumberFormat,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(moonPhaseInfo: MoonPhaseInfo) = binding.let { with(moonPhaseInfo) {
            it.tvMoonPhaseDate.text = dateTimeFormatter.format(dateTime)
            it.imgMoonPhase.setImageResource(getMoonPhaseImage(name))
            it.tvMoonPhaseName.text = name
            it.tvMoonPhaseAge.text = age.roundToInt().toString()
            it.tvMoonPhaseIllumination.text = percentFormatter.format(illumination)
        } }

        private fun getMoonPhaseImage(moonPhaseName: String) = with(binding.root.context) { when(moonPhaseName) {
            getString(R.string.moonPhase_newMoon) -> R.drawable.emoji_new_moon
            getString(R.string.moonPhase_waxingCrescent) -> R.drawable.emoji_waxing_crescent
            getString(R.string.moonPhase_firstQuarter) -> R.drawable.emoji_first_quarter
            getString(R.string.moonPhase_waxingGibbous) -> R.drawable.emoji_waxing_gibbous
            getString(R.string.moonPhase_fullMoon) -> R.drawable.emoji_full_moon
            getString(R.string.moonPhase_waningGibbous) -> R.drawable.emoji_wanning_gibbous
            getString(R.string.moonPhase_lastQuarter) -> R.drawable.emoji_last_quarter
            getString(R.string.moonPhase_waningCrescent) -> R.drawable.emoji_wanning_crescent
            else -> R.drawable.ic_launcher_background
        } }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoonPhaseViewHolder {
        val binding = ListItemMoonPhaseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return MoonPhaseViewHolder(binding, dateTimeFormatter, percentFormatter)
    }

    override fun onBindViewHolder(holder: MoonPhaseViewHolder, position: Int) =
        holder.bind(moonPhases[position])

    override fun getItemCount() = moonPhases.size
}