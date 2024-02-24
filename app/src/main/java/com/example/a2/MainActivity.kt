package com.example.a2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a2.ui.theme.A2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A2Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    A2ArtworkScreen()
                }
            }
        }
    }
}
@Composable
fun A2ArtworkScreen() {
    // artwork data
    val artworks = listOf(
        Artwork(R.drawable.artwork1, R.string.artwork1_title, R.string.artwork1_year),
        Artwork(R.drawable.artwork2, R.string.artwork2_title, R.string.artwork2_year),
        Artwork(R.drawable.artwork3, R.string.artwork3_title, R.string.artwork3_year)
    )

    var currentArtworkIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ArtworkDisplay(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            currentArtwork = artworks[currentArtworkIndex].image
        )

        ArtworkTitle(
            title = artworks[currentArtworkIndex].title,
            year = artworks[currentArtworkIndex].year
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .weight(1f), // Use weight to make buttons share the space equally
                onClick = {
                    currentArtworkIndex = (currentArtworkIndex - 1 + artworks.size) % artworks.size
                }
            ) {
                Text(text = "Previous")
            }

            Button(
                modifier = Modifier
                    .weight(1f), // Use weight to make buttons share the space equally
                onClick = {
                    currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
                }
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Image(
        painter = painterResource(currentArtwork),
        contentDescription = stringResource(id = R.string.zero_two),
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Artwork title
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 32.sp
        )

        // Artwork year
        Text(
            text = "— ${stringResource(id = year)} —",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

data class Artwork(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val year: Int
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    A2Theme {
        A2ArtworkScreen()
    }
}