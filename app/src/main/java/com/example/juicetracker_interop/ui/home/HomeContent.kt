package com.example.juicetracker_interop.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import com.example.juicetracker_interop.R
import com.example.juicetracker_interop.data.model.Juice
import com.example.juicetracker_interop.data.model.JuiceColor

@Composable
fun JuiceTrackerList(
    juices: List<Juice>,
    onDelete: (Juice) -> Unit,
    onUpdate: (Juice) -> Unit,
    contentPaddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPaddingValues,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(juices, key = { juice -> juice.id }) { juice ->
            JuiceListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onUpdate(juice) },
                juice = juice,
                onDelete = { onDelete(juice) }
            )
        }
    }
}

@Composable
fun JuiceListItem(
    modifier: Modifier = Modifier,
    juice: Juice,
    onDelete: (Juice) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        JuiceImageIcon(color = juice.color)
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_little_small))
        ) {
            Text(
                text = juice.name,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = juice.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
            Ratings(rating = juice.rating)
        }
        IconButton({ onDelete(juice) }) {
            Icon(
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = stringResource(R.string.delete)
            )
        }
    }
}

@Composable
fun Ratings(rating: Int) {
    val displayDescription = pluralStringResource(R.plurals.number_of_stars, count = rating)

    Row(
        modifier = Modifier
            .semantics {
                contentDescription = displayDescription
            }
    ) {
        repeat(rating) {
            Image(
                modifier = Modifier.size(dimensionResource(R.dimen.star_image_size)),
                contentDescription = null,
                painter = painterResource(R.drawable.star)
            )
        }
    }
}

@Composable
fun JuiceImageIcon(
    modifier: Modifier = Modifier,
    color: String
) {
    val juiceIconContentDescription = stringResource(R.string.juice_color_image, color)

    Box(
        modifier = modifier
            .semantics {
                contentDescription = juiceIconContentDescription
            }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_juice_color),
            contentDescription = null,
            tint = JuiceColor.valueOf(color).color,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            painter = painterResource(R.drawable.ic_juice_clear),
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Text(
                text = stringResource(R.string.top_bar_title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    )
}

@Composable
fun FAB(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(dimensionResource(R.dimen.fab_elevation))
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_plus_24),
            contentDescription = stringResource(R.string.add_juice)
        )
    }
}