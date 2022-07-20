package com.e444er.movie.common

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun EditText.textChangeFlow(): Flow<String> {
    return callbackFlow<String> {
        val textTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySendBlocking(s?.toString().orEmpty())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        this@textChangeFlow.addTextChangedListener(textTextChangedListener)
        awaitClose {
            Log.d("TAGG", "awaitClose")
            this@textChangeFlow.removeTextChangedListener(textTextChangedListener)
        }
    }
}




//private fun swipeToDelete() {
//    ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
//        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
//        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//    ) {
//        override fun onMove(
//            recyclerView: RecyclerView,
//            viewHolder: RecyclerView.ViewHolder,
//            target: RecyclerView.ViewHolder
//        ): Boolean {
//            return true
//        }
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//
//            viewModel.deleteMovies(args.movie)
//            Snackbar.make(
//                binding.root,
//                "item", Snackbar.LENGTH_SHORT
//            ).apply {
//                setAction("Отмена") {
//                    viewModel.addMovies(args.movie)
//                }
//            }
//        }
//    }).attachToRecyclerView(binding.recyclerview)
//}
