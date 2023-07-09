package com.cbcds.aventura.feature.auth.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cbcds.aventura.core.model.AppFeature

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OnboardingPager(items: List<AppFeature>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()

    Column(modifier = modifier) {
        HorizontalPager(pageCount = items.size, state = pagerState) { page ->
            OnboardingPagerItem(items[page])
        }
        OnboardingPagerIndicator(
            pageCount = items.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(top = 50.dp),
        )
    }
}

@Composable
private fun OnboardingPagerItem(item: AppFeature) {
    Column(
        modifier = Modifier.height(350.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
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

@Composable
private fun OnboardingPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0 until pageCount) {
            val indicatorColor = if (i <= currentPage) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
            val isItemActivated = i == currentPage
            if (!isItemActivated) {
                val radius = 8.dp.value
                Canvas(
                    modifier = Modifier.size(8.dp),
                    onDraw = { drawCircle(indicatorColor, radius = radius) }
                )
            } else {
                val innerRadius = 8.dp.value
                val outerRadius = 18.dp.value
                Canvas(
                    modifier = Modifier.size(8.dp),
                    onDraw = {
                        drawCircle(
                            color = indicatorColor,
                            radius = innerRadius,
                        )
                        drawCircle(
                            color = indicatorColor,
                            radius = outerRadius,
                            style = Stroke(width = 4.dp.value),
                        )
                    }
                )
            }

            val isLastItem = i == pageCount - 1
            if (!isLastItem) {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}
