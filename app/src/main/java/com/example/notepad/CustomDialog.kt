package com.example.notepad

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

class CustomDialog(var activity: MainActivity) : Dialog(activity), View.OnClickListener {

    private lateinit var okButton: Button
    private lateinit var cancelButton: Button
    private lateinit var inputFieldTitle: EditText
    private lateinit var inputFieldDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_template)

        initViews()
        dialogSizeControl()

    }

    private fun initViews() {
        inputFieldTitle = findViewById(R.id.dialog_input_title)
        inputFieldDescription = findViewById(R.id.dialog_input_description)
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

    private fun okButtonClicker() {
        val inputTitleResul = inputFieldTitle.text.toString()
        val inputDescriptionResult = inputFieldDescription.text.toString()
        activity.insertItem(ToDoItem(0, inputTitleResul, inputDescriptionResult))
        dismiss()
    }

    private fun elseBeenClicked() {}

}