package com.picpay.desafio.android.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.picpay.desafio.android.R
import com.picpay.desafio.android.adapter.UserListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModel()
    private val adapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupObservables()
        setupView()
    }

    private fun setupView() {
        rvUserList.adapter = adapter
    }

    private fun setupObservables() {
        viewModel.userList.observe(this, Observer { list ->
            adapter.users = list
        })

        viewModel.messageError.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })

        viewModel.spinner.observe(this, Observer { isVisible ->
            if (isVisible) pbUserList.visibility = View.VISIBLE
            else pbUserList.visibility = View.GONE
        })
    }

    override fun onResume() {
        super.onResume()
        Log.d("TESTE", "onResume")
        viewModel.getUserList()
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("TESTE", "onDestroy")
    }
}
