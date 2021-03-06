package com.thejuki.kformmaster.view

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.View
import android.widget.Button
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.github.vivchar.rendererrecyclerviewadapter.ViewState
import com.github.vivchar.rendererrecyclerviewadapter.ViewStateProvider
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.thejuki.kformmaster.R
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.model.FormButtonElement
import com.thejuki.kformmaster.state.FormButtonViewState

/**
 * Form Button ViewBinder
 *
 * View Binder for [FormButtonElement]
 *
 * @author **TheJuki** ([GitHub](https://github.com/TheJuki))
 * @version 1.0
 */
class FormButtonViewBinder(private val context: Context, private val formBuilder: FormBuildHelper, @LayoutRes private val layoutID: Int?) : BaseFormViewBinder() {
    val viewBinder = ViewBinder(layoutID
            ?: R.layout.form_element_button, FormButtonElement::class.java, { model, finder, _ ->
        val itemView = finder.getRootView() as View
        val dividerView = finder.find(R.id.formElementDivider) as? View
        baseSetup(model, dividerView, null, null, itemView, null)

        val button = finder.find(R.id.formElementValue) as Button
        button.text = model.valueAsString

        model.editView = button

        button.setOnClickListener {
            model.setValue(model.value)
            formBuilder.onValueChanged(model)
        }
    }, object : ViewStateProvider<FormButtonElement, ViewHolder> {
        override fun createViewStateID(model: FormButtonElement): Int {
            return model.id
        }

        override fun createViewState(holder: ViewHolder): ViewState<ViewHolder> {
            return FormButtonViewState(holder)
        }
    })
}
