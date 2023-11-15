package com.example.notepad

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CustomDialog(private var activity: MainActivity, private val isNewItem: Boolean, private val item: ToDoItem?) : Dialog(activity), View.OnClickListener {

    private lateinit var okButton: Button
    private lateinit var cancelButton: Button
    private lateinit var inputFieldTitle: EditText
    private lateinit var inputFieldDescription: EditText
    private lateinit var dialogLabel: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_template)

        initViews()
        dialogSizeControl()

        if(isNewItem) {
            createNewItem()
        } else {
            updateExistingItem()
        }

    }

    private fun updateExistingItem() {
        dialogLabel.text = context.getString(R.string.update_item)
        inputFieldTitle.setText(item?.title)
        inputFieldDescription.setText(item?.description)
    }

    private fun createNewItem() {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val titleFromPrefs = sharedPref.getString("titleKey", "")
        val descriptionFromPrefs = sharedPref.getString("descriptionKey", "")
        inputFieldTitle.setText(titleFromPrefs)
        inputFieldDescription.setText(descriptionFromPrefs)
    }

    private fun initViews() {
        inputFieldTitle = findViewById(R.id.dialog_input_title)
        inputFieldDescription = findViewById(R.id.dialog_input_description)
        dialogLabel = findViewById(R.id.dialog_label)
        okButton = findViewById(R.id.dialog_ok_button)
        cancelButton = findViewById(R.id.dialog_cancel_button)
        okButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)
    }

    /**
     * this need to control dialog size
     */
    private fun dialogSizeControl() {
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(this.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        this.window?.attributes = lp
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.dialog_ok_button -> okButtonClicker()
            R.id.dialog_cancel_button -> dismiss()
            else -> elseBeenClicked()
        }
    }

    private fun elseBeenClicked() {}

    private fun okButtonClicker() {
        if(isNewItem) {
            okNewItemBeenClicked()
        } else {
            okUpdateItemBeenClicked()
        }
        dismiss()
    }

    private fun okNewItemBeenClicked() {
        val inputTitleResul = inputFieldTitle.text.toString()
        val inputDescriptionResult = inputFieldDescription.text.toString()
        activity.insertItem(ToDoItem(0, inputTitleResul, inputDescriptionResult))
        inputFieldTitle.text.clear()
        inputFieldDescription.text.clear()
        dismiss()
    }

    private fun okUpdateItemBeenClicked() {
        val inputTitleResul = inputFieldTitle.text.toString()
        val inputDescriptionResult = inputFieldDescription.text.toString()
        // activity.updateItem(ToDoItem(item?.id!!, inputTitleResul, inputDescriptionResult))
        item?.id?.let { ToDoItem(it, inputTitleResul, inputDescriptionResult) }
            ?.let { activity.updateItem(it) } // здесь студия сама предложила так изменить строку выше
    }

    override fun onStop() {
        super.onStop()
        if(isNewItem) {
            val sharedPref: SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                val inputTitleResult = inputFieldTitle.text.toString()
                val inputDescriptionResult = inputFieldDescription.text.toString()
                putString("titleKey", inputTitleResult)
                putString("descriptionKey", inputDescriptionResult)
                apply()
            }
        }
    }

}