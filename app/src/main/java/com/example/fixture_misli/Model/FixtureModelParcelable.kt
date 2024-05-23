package com.example.fixture_misli.Model

import android.os.Parcel
import android.os.Parcelable

data class FixtureModelParcelable (
    val date: Long,
    val leagueName: String?,
    val leagueFlag: String?,
    val min: Int,
    val st: Int,
    val homeTeamName: String?,
    val homeTeamR: Int,
    val homeTeamC: Int,
    val homeTeamHT: Int,
    val homeTeamP: Int,
    val awayTeamName: String?,
    val awayTeamR: Int,
    val awayTeamC: Int,
    val awayTeamHT: Int,
    val awayTeamP: Int ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(date)
        parcel.writeString(leagueName)
        parcel.writeString(leagueFlag)
        parcel.writeInt(min)
        parcel.writeInt(st)
        parcel.writeString(homeTeamName)
        parcel.writeInt(homeTeamR)
        parcel.writeInt(homeTeamC)
        parcel.writeInt(homeTeamHT)
        parcel.writeInt(homeTeamP)
        parcel.writeString(awayTeamName)
        parcel.writeInt(awayTeamR)
        parcel.writeInt(awayTeamC)
        parcel.writeInt(awayTeamHT)
        parcel.writeInt(awayTeamP)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FixtureModelParcelable> {
        override fun createFromParcel(parcel: Parcel): FixtureModelParcelable {
            return FixtureModelParcelable(parcel)
        }

        override fun newArray(size: Int): Array<FixtureModelParcelable?> {
            return arrayOfNulls(size)
        }
    }
}