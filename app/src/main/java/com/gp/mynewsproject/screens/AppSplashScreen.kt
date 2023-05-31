package com.gp.mynewsproject.screens


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gp.mynewsproject.R
import com.gp.mynewsproject.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun AppSplashScreen(navController: NavController) {

    //splash animation

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.6f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = { OvershootInterpolator(8f).getInterpolation(it) })
        )

        delay(2000L)
        navController.navigate(AppScreens.MainScreen.name)
    })

    SplashContent(scale = scale)

}

@Composable
fun SplashContent(scale:Animatable<Float,AnimationVector1D>){
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(), color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
            //splash center icon
            Image(
                painter = painterResource(id = R.drawable.news_icon),
                modifier = Modifier
                    .size(500.dp)
                    .scale(scale.value),
                contentDescription = ""
            )

        }

    }
}
