package com.example.notepad

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemClick {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var stubContainer: LinearLayout
    private lateinit var fab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private lateinit var dataCopy: List<ToDoItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        swipeImplementation()
        observers()

        fab.setOnClickListener {
            val dialogFragment = CustomDialog(true, null)
            dialogFragment.show(supportFragmentManager, getString(R.string.custom_dialog))
        }

        mainViewModel.getAllItems()
    }

    private fun observers() {
        mainViewModel.todoItemListResult.observe(this, Observer {
            dataCopy = it
            adapter.updateList(it)
            screenDataValidation(it)
        })
    }

    private fun swipeImplementation() {

        // Swiping
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // For future implementation
                return false
            }

            override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                     dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top

                val deleteIcon = ContextCompat.getDrawable(this@MainActivity, R.drawable.baseline_delete_24)

                deleteIcon?.let {
                    // Params icon and background
                    val intrinsicWidth = deleteIcon.intrinsicWidth
                    val intrinsicHeight = deleteIcon.intrinsicHeight
                    val background = ColorDrawable()
                    val backgroundColor = getColor(R.color.red)

                    // Draw the red delete background
                    background.color = backgroundColor
                    background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                    background.draw(canvas)

                    // Calculate position of delete icon
                    val iconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
                    val iconMargin = (itemHeight - intrinsicHeight) / 2
                    val iconLeft = itemView.right - iconMargin - intrinsicWidth
                    val iconRight = itemView.right - iconMargin
                    val iconBottom = iconTop + intrinsicHeight

                    // Draw the delete icon
                    deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                    deleteIcon.draw(canvas)
                }
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to left direction.
                val position = viewHolder.adapterPosition
                val deletedToDoItem: ToDoItem = dataCopy[position]

                mainViewModel.deleteItem(deletedToDoItem)
                // then the Observer will run and change the list in the adapter

                // below line is to display our snack-bar with action.
                Snackbar.make(recyclerView, "Deleted " + deletedToDoItem.title, Snackbar.LENGTH_LONG)
                    .setAction(
                        getString(R.string.undo),
                        View.OnClickListener {
                            mainViewModel.insertItem(deletedToDoItem)
                            // then the Observer will run and change the list in the adapter
                        }).show()
            }
            // at last we are adding this to our recycler view.
        }).attachToRecyclerView(recyclerView)
    }

    private fun initViews() {
        stubContainer = findViewById(R.id.main_no_items_container)
        fab = findViewById(R.id.main_fab)
        recyclerView = findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(mutableListOf(), this)
        recyclerView.adapter = adapter
    }

    private fun screenDataValidation(list: List<ToDoItem>) {
        if(list.isEmpty()) {
            setupStub(showStub = true, showRecycler = false)
        } else {
            setupStub(showStub = false, showRecycler = true)
        }
    }

    private fun setupStub(showStub: Boolean, showRecycler: Boolean) {
        stubContainer.isVisible = showStub
        recyclerView.isVisible = showRecycler
    }

    override fun itemClicked(item: ToDoItem) {
        val dialogFragment = CustomDialog(false, item)
        dialogFragment.show(supportFragmentManager, getString(R.string.custom_dialog))
    }
}