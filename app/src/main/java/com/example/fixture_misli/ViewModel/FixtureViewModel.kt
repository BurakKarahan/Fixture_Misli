package com.example.fixture_misli.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fixture_misli.Model.Fixture
import com.example.fixture_misli.Model.Misli
import com.example.fixture_misli.Service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FixtureViewModel : ViewModel(){

    private val parentList = ArrayList<Fixture>()

    private var fixtureLiveData = MutableLiveData<List<Fixture>>()
    fun getFixture() {
        RetrofitInstance.api.getFixtureResult().enqueue(object : Callback<Misli>{
            override fun onResponse(call: Call<Misli>, response: Response<Misli>) {
                if (response.body()!=null){

                    val result = response.body()!!.data.groupBy { it.to.i }
                    for (item in result) {
                        for (a in item.value) {
                            val fixture = Fixture(item.value, a.to)
                            parentList.add(fixture)
                            break
                        }
                    }
                    parentList.sortWith(compareBy({ it.to.p }, { it.to.n }))
                    for (item in parentList) {
                        (item.data as ArrayList).sortWith(compareBy({ it.d }, { it.i }))
                    }
                    fixtureLiveData.value = parentList
                }
                else {
                    return
                }
            }
            override fun onFailure(call: Call<Misli>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeFixtureLiveData() : LiveData<List<Fixture>> {
        return fixtureLiveData
    }
}
