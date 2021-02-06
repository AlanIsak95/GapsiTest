package com.techno.gapsi.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techno.gapsi.R
import com.techno.gapsi.data.model.Item
import com.techno.gapsi.data.room.DbRepository
import com.techno.gapsi.data.room.callback.ICallbackBase
import com.techno.gapsi.data.room.callback.ICallbackListDataResponse
import com.techno.gapsi.data.room.entity.HistorySearch
import com.techno.gapsi.ui.home.adapter.HomeAdapter

class HomeActivity : AppCompatActivity(), HomePresenter.View {

    private lateinit var  rvList      : RecyclerView
    private lateinit var  progressBar : ProgressBar
    private lateinit var  adapter     : HomeAdapter
    private lateinit var  btnSearch   : Button
    private lateinit var  actvSearch  : AutoCompleteTextView


    private val homePresenter = HomePresenter()
    private var dbFactory = DbRepository(lifecycleScope)


    /**
     * Hola, No me dio tiempo de implementar el ViewBinding ni Dagger TT_TT
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initViews()
        initValues()
        getDbData()

        setOnClickListener()



    }

    private fun getDbData() {

        dbFactory.isDbEnable(this@HomeActivity, object : ICallbackBase{

            override fun success(response: Boolean) {


                if (response){
                    setHistoryData()
                }


            }

        })






    }

    fun setHistoryData(){

        dbFactory.getAllData(this@HomeActivity, object : ICallbackListDataResponse {
            override fun success(response: List<HistorySearch>) {

                updateAutoCompleteText(response)

            }
        })



    }

    private fun updateAutoCompleteText(listData : List<HistorySearch>){


        var dataArray = arrayListOf<String>()

        for (item : HistorySearch in listData)
            dataArray.add(item.data)

        var adapter = ArrayAdapter(this@HomeActivity,android.R.layout.simple_list_item_1,dataArray)
        actvSearch.threshold = 0
        actvSearch.setAdapter(adapter)
        actvSearch.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) actvSearch.showDropDown()}


    }

    private fun initViews() {

        rvList      = findViewById(R.id.rvLista)
        progressBar = findViewById(R.id.progressBar)
        btnSearch   = findViewById(R.id.btn_search)
        actvSearch = findViewById(R.id.actvSearch)

        homePresenter.setView(this)

    }


    private fun initValues() {

        val linearLayoutManager = LinearLayoutManager(this)
        rvList.layoutManager = linearLayoutManager
        homePresenter.setView(this)


    }



    private fun setOnClickListener() {

        btnSearch.setOnClickListener {



            if (actvSearch.text.isNullOrEmpty().not()){
                setProgressVisible(true)
                homePresenter.executeHomeService(actvSearch.text.toString())
                dbFactory.insertData(this@HomeActivity, HistorySearch(actvSearch.text.toString()))
                it.isEnabled =false



                resetFocustAutocompleteTV()
                actvSearch.setText("")

                //Oculta KeyBoard
                val imm = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)



            }
            else
                Toast.makeText(this@HomeActivity,"Ingresa un valor",Toast.LENGTH_LONG).show()


        }


        actvSearch.setOnDismissListener {    resetFocustAutocompleteTV()    }



    }


    private fun resetFocustAutocompleteTV(){

        actvSearch.isFocusableInTouchMode = false;
        actvSearch.isFocusable = false;
        actvSearch.isFocusableInTouchMode = true;
        actvSearch.isFocusable = true;

    }

    override fun setProgressVisible(boolean: Boolean) {
        progressBar.visibility =  if (boolean) View.VISIBLE else View.GONE

    }


    override fun showData(list: List<Item>) {
        btnSearch.isEnabled= true
        adapter = HomeAdapter(list,applicationContext)
        rvList.adapter = adapter
        setProgressVisible(false)
        getDbData()


    }



}