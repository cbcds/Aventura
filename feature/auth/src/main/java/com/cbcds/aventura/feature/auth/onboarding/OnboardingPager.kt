package com.cbcds.aventura.feature.auth.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cbcds.aventura.core.model.AppFeature
import com.cbcds.aventura.core.ui.component.base.RadioButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
@OptIn(ExperimentalPagerApi::class)
fun OnboardingPager(items: List<AppFeature>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()

    Column(modifier = modifier) {
        HorizontalPager(count = items.size, state = pagerState) {
            OnboardingPagerItem(items[currentPage])
        }
        OnboardingPagerIndicator(pagerState, modifier = Modifier.padding(top = 50.dp))
    }
}

@Composable
private fun OnboardingPagerItem(item: AppFeature) {
    Column(
        modifier = Modifier.height(350.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(item.imageResId),
            modifier = Modifier.height(200.dp),
            alignment = Alignment.Center,
            contentDescription = null,
        )
        Text(
            text = item.title,
            modifier = Modifier.padding(start = 20.dp, top = 45.dp, end = 20.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = item.subtitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun OnboardingPagerIndicator(pagerState: PagerState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val radius = 8.dp.value
        for (i in 0 until pagerState.pageCount) {
            val indicatorColor = if (i <= pagerState.currentPage) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
            val isItemActivated = i == pagerState.currentPage
            if (!isItemActivated) {
                Canvas(
                    modifier = Modifier.size(8.dp),
                    onDraw = { drawCircle(indicatorColor, radius = radius) }
                )
            } else {
                RadioButton(
                    selected = true,
                    onClick = {},
                    modifier = Modifier.size(18.dp),
                )
            }

            val isLastItem = i == pagerState.pageCount - 1
            val isNextItemActivated = i + 1 == pagerState.currentPage
            if (!isLastItem) {
                val spacerWidth = if (isItemActivated || isNextItemActivated) 15.dp else 16.dp
                Spacer(modifier = Modifier.width(spacerWidth))
            }
        }
    }
}
