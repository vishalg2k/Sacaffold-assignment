package com.example.scaffoldassignment

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest.Builder.Companion.fromUri
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldassignment.ui.theme.ScaffoldAssignmentTheme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerControlView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class AudioVideoPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting4(rememberNavController())
                }
            }
        }
    }
}

@Composable
fun Greeting4(navController: NavController, modifier: Modifier = Modifier) {
    val ctx = LocalContext.current

    val audioPlayer = MediaPlayer.create(ctx,R.raw.mystupidheart)
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Audio Player")
        Row( modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { audioPlayer.start() }) {
                Text(text = "Play")
            }
            Button(onClick = { audioPlayer.pause() }) {
                Text(text = "Pause")
            }
            Button(onClick = { audioPlayer.stop() }) {
                Text(text = "Stop")
            }

        }

        Text(text = "Video Player")
        Row (modifier = Modifier.fillMaxWidth()){

            val link = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
//            val videoply = ExoPlayer.Builder(ctx).build().apply {
//                setMediaItem(MediaItem.fromUri(link))
//                playWhenReady = false
//                prepare()
//            }
            val exoplayer = ExoPlayer.Builder(ctx).build().also{
                exoPlayer -> val mediaItem = MediaItem.Builder()
                .setUri(link)
                .build()
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
            }
            
            DisposableEffect(
                AndroidView(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),factory = {
                    StyledPlayerView(ctx).apply {
                        player = exoplayer
                    }
                })
            ){
                onDispose {
                    exoplayer.release()
                }
            }

//            val intvidSrc = ProgressiveMediaSource.Factory(data)
//            AndroidView(modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp),
//                factory = {StyledPlayerView(ctx).apply {
//                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
//                    player = videoply
//                } })


            Button(onClick = { /*TODO*/ }) {
                Text(text = "Play")

            }
        }

        Button(onClick = {
            navController.navigate("Home")
        }) {
            Text(text = "Go to home")

        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ScaffoldAssignmentTheme {
        Greeting4(rememberNavController())
    }
}