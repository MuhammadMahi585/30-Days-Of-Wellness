package com.example.a30daysofwellness


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.a30daysofwellness.data.Wellness
import com.example.a30daysofwellness.data.days
import coil.compose.rememberImagePainter
import com.example.a30daysofwellness.ui.theme.a30DaysOfWellnessTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
         a30DaysOfWellnessTheme(darkTheme = false) {
         WellnessList()
         }
        }
    }
}
@Composable
fun WellnessList(){
    Scaffold(
        topBar = {
            WellnessTopAppBar()
        }
    ){
        LazyColumn (contentPadding = it){
           itemsIndexed(days){index,wellness ->
               DaysStructure(
                   wellness = wellness,
                   day = index+1,
                   modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding)),
               )
           }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysStructure(
    wellness: Wellness,
    day: Int,
    modifier: Modifier,
){
    var expand by remember {
         mutableStateOf(false)
    }
   Card(onClick = {expand=!expand},
       modifier = modifier
           .clip(MaterialTheme.shapes.medium)) {
       Column(
           modifier = Modifier
               .padding(dimensionResource(id = R.dimen.small_padding))
               .animateContentSize(
                   animationSpec = spring(
                       dampingRatio = Spring.DampingRatioHighBouncy,
                       stiffness = Spring.StiffnessVeryLow
                   )
               )
       ) {
           Text(text = "Day $day",
               style = MaterialTheme.typography.displayMedium,
               modifier = Modifier
                   .align(alignment = Alignment.CenterHorizontally))

              Text(text = stringResource(id = wellness.title),
                  style = MaterialTheme.typography.titleSmall,
              )

              Image(
                //  painter = rememberAsyncImagePainter(model = wellness.imageId),
                  painter = painterResource(id = wellness.imageId),
                  contentDescription = "Image $day",
                  modifier = Modifier
                      .height(dimensionResource(id = R.dimen.image_height))
                      .clip(MaterialTheme.shapes.small)
                      .fillMaxWidth(),
                  contentScale = ContentScale.Crop)
           if(expand){
           Text(
               text = stringResource(id = wellness.description),
               style = MaterialTheme.typography.displaySmall)
       }
       }
   }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessTopAppBar(){
    CenterAlignedTopAppBar(title = {
        Surface {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium)
        }
    })
}
@Preview
@Composable
fun AppDisplay(){
    a30DaysOfWellnessTheme {
        WellnessList()
    }
}
@Preview
@Composable
fun AppDisplay2(){
    a30DaysOfWellnessTheme(darkTheme = true) {
        WellnessList()
    }
}

