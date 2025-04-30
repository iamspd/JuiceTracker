package com.example.juicetracker_interop.ui.bottomsheet

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.juicetracker_interop.R
import com.example.juicetracker_interop.data.model.Juice
import com.example.juicetracker_interop.ui.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBottomSheet(
    modifier: Modifier = Modifier,
    sheetScaffoldState: BottomSheetScaffoldState,
    homeViewModel: HomeViewModel,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val juice by homeViewModel.currentJuiceStream.collectAsStateWithLifecycle()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = sheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            SheetHeader(modifier = Modifier.fillMaxWidth())
            SheetForm(
                juice = juice,
                onUpdateJuice = homeViewModel::updateCurrentJuice,
                onCancelClick = onCancelClick,
                onSaveClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    ) {
        content()
    }
}

@Composable
fun SheetHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(R.string.juice_type),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
        )
        HorizontalDivider()
    }
}

@Composable
fun SheetForm(
    modifier: Modifier = Modifier,
    juice: Juice,
    onUpdateJuice: (Juice) -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputRow(
            modifier = Modifier.fillMaxWidth(),
            fieldLabel = R.string.juice_name,
            fieldValue = juice.name,
            onFieldValueChanged = { onUpdateJuice(juice.copy(name = it)) }
        )
        InputRow(
            modifier = Modifier.fillMaxWidth(),
            fieldLabel = R.string.juice_description,
            fieldValue = juice.description,
            onFieldValueChanged = { onUpdateJuice(juice.copy(description = it)) }
        )
        ButtonRow(
            modifier = Modifier.fillMaxWidth(),
            onCancelClick = onCancelClick,
            onSaveClick = onSaveClick
        )
    }
}

@Composable
fun InputRow(
    @StringRes fieldLabel: Int,
    fieldValue: String,
    onFieldValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(fieldLabel),
            style = MaterialTheme.typography.labelMedium
        )
        TextField(
            modifier = Modifier.weight(1f),
            value = fieldValue,
            onValueChange = onFieldValueChanged,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
    }
}

@Composable
fun ButtonRow(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(onCancelClick) {
            Text(stringResource(R.string.cancel))
        }
        FilledTonalButton(onClick = onSaveClick) {
            Text(stringResource(R.string.save))
        }
    }
}