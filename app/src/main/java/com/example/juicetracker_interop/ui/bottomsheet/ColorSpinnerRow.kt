package com.example.juicetracker_interop.ui.bottomsheet

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.juicetracker_interop.R
import com.example.juicetracker_interop.data.model.JuiceColor

class SpinnerAdapter(val onColorChange: (Int) -> Unit) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onColorChange(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        onColorChange(0)
    }
}

@Composable
fun ColorSpinnerRow(
    colorSpinnerPosition: Int,
    onColorChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val juiceColorArray =
        JuiceColor.entries.map { juiceColor -> stringResource(juiceColor.label) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.juice_color),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.weight(1f)
        )
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            factory = { context ->
                Spinner(context).apply {
                    adapter = ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_dropdown_item,
                        juiceColorArray
                    )
                }
            },
            update = { spinner ->
                spinner.setSelection(colorSpinnerPosition)
                spinner.onItemSelectedListener = SpinnerAdapter(onColorChange)
            }
        )
    }
}