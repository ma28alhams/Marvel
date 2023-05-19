package com.red_velvet.marvel.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.red_velvet.marvel.data.database.ComicsDataBase

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    abstract val LOG_TAG: String

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComicsDataBase.getInstance(applicationContext)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
    }

    abstract fun getLayoutResId(): Int

    protected fun log(value: Any) {
        Log.v(LOG_TAG, value.toString())
    }
}