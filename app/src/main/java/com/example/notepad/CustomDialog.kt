package com.example.notepad

import android.app.ActionBar
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.notepad.Constants.PREFS_DESCRIPTION_KEY
import com.example.notepad.Constants.PREFS_TITLE_KEY

class CustomDialog(private val isNewItem: Boolean, private val item: ToDoItem?) : DialogFragment(), View.OnClickListener {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val customDialogViewModel: CustomDialogViewModel by activityViewModels()

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
        observers()
    }

    private fun observers() {
        customDialogViewModel.dataFromPrefsResult.observe(this, Observer {
            if(isNewItem) {
                inputFieldTitle.setText(it.title)
                inputFieldDescription.setText(it.description)
            }
        })
    }

    private fun updateExistingItem() {
        dialogLabel.text = getString(R.string.update_item)
        inputFieldTitle.setText(item?.title)
        inputFieldDescription.setText(item?.description)
    }

    private fun createNewItem() {
        customDialogViewModel.getTitleAndDescriptionFromPrefs(PREFS_TITLE_KEY, PREFS_DESCRIPTION_KEY)
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
        dialog?.window?.apply {
            val params = attributes
            params.width = ActionBar.LayoutParams.MATCH_PARENT
            params.height = ActionBar.LayoutParams.WRAP_CONTENT
            attributes = params
        }
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
    }

    private fun okUpdateItemBeenClicked() {
        val inputTitleResul = inputFieldTitle.text.toString()
        val inputDescriptionResult = inputFieldDescription.text.toString()
        item?.id?.let {
            val updatedItem = ToDoItem(it, inputTitleResul, inputDescriptionResult)
            mainViewModel.updateItem(updatedItem)
        }
    }

    override fun onStop() {
        super.onStop()
        if(isNewItem) {
            val inputTitleResult = inputFieldTitle.text.toString()
            val inputDescriptionResult = inputFieldDescription.text.toString()
            customDialogViewModel.saveStringInPrefs(PREFS_TITLE_KEY, inputTitleResult)
            customDialogViewModel.saveStringInPrefs(PREFS_DESCRIPTION_KEY, inputDescriptionResult)
        }
    }
}