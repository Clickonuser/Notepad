package com.example.notepad

import android.app.ActionBar
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class CustomDialog(private val isNewItem: Boolean, private val item: ToDoItem?) : DialogFragment(), View.OnClickListener {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var okButton: Button
    private lateinit var cancelButton: Button
    private lateinit var inputFieldTitle: EditText
    private lateinit var inputFieldDescription: EditText
    private lateinit var dialogLabel: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val view: View = inflater.inflate(R.layout.dialog_template, container, false)

        initViews(view)

        if(isNewItem) {
            createNewItem()
        } else {
            updateExistingItem()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        dialogSizeControl()
    }

    private fun updateExistingItem() {
        dialogLabel.text = getString(R.string.update_item)
        inputFieldTitle.setText(item?.title)
        inputFieldDescription.setText(item?.description)
    }

    private fun createNewItem() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val titleFromPrefs = sharedPref?.getString("titleKey", "")
        val descriptionFromPrefs = sharedPref?.getString("descriptionKey", "")
        inputFieldTitle.setText(titleFromPrefs)
        inputFieldDescription.setText(descriptionFromPrefs)
    }

    private fun initViews(view: View) {
        inputFieldTitle = view.findViewById(R.id.dialog_input_title)
        inputFieldDescription = view.findViewById(R.id.dialog_input_description)
        dialogLabel = view.findViewById(R.id.dialog_label)
        okButton = view.findViewById(R.id.dialog_ok_button)
        cancelButton = view.findViewById(R.id.dialog_cancel_button)
        okButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)
    }

    /**
     * this need to control dialog size
     */
    private fun dialogSizeControl() {
        // TODO Improve the code later
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ActionBar.LayoutParams.MATCH_PARENT
        params.height = ActionBar.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
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
        mainViewModel.insertItem(ToDoItem(0, inputTitleResul, inputDescriptionResult))
        inputFieldTitle.text.clear()
        inputFieldDescription.text.clear()
        dismiss()
    }

    private fun okUpdateItemBeenClicked() {
        val inputTitleResul = inputFieldTitle.text.toString()
        val inputDescriptionResult = inputFieldDescription.text.toString()
        // activity.updateItem(ToDoItem(item?.id!!, inputTitleResul, inputDescriptionResult))
        item?.id?.let { ToDoItem(it, inputTitleResul, inputDescriptionResult) }
            ?.let { mainViewModel.updateItem(it) } // здесь студия сама предложила так изменить строку выше
    }

    override fun onStop() {
        super.onStop()
        if(isNewItem) {
            val sharedPref: SharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
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