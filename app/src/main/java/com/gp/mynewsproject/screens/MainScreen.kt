package com.gp.mynewsproject.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gp.mynewsproject.R
import com.gp.mynewsproject.data.DataOrException
import com.gp.mynewsproject.model.CategoryModel
import com.gp.mynewsproject.model.Data
import com.gp.mynewsproject.model.NewsModel


@Composable
fun MainScreen(navController: NavController, viewModel: MasterViewModel = hiltViewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                fontSize = 40.sp,
                text = "Today\nNews",
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(20.dp),
                lineHeight = 40.sp
            )

            SearchNews()
            val categoryList = arrayListOf(
                CategoryModel(category = "All",isSelected = true),
                CategoryModel(category = "Indian",isSelected = false),
                CategoryModel(category = "Business",isSelected = false),
                CategoryModel(category = "Sports",isSelected = false),
                CategoryModel(category = "World",isSelected = false),
                CategoryModel(category = "Politics",isSelected = false),
                CategoryModel(category = "Technology",isSelected = false),
                CategoryModel(category = "Startup",isSelected = false),
                CategoryModel(category = "Entertainment",isSelected = false),
                CategoryModel(category = "Miscellaneous",isSelected = false),
                CategoryModel(category = "Hatke",isSelected = false),
                CategoryModel(category = "Science",isSelected = false),
                CategoryModel(category = "Automobile",isSelected = false),
            )
            CategoryList(modifier = Modifier, tabWidth = 100.dp,categoryList,viewModel)


        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNews() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp, 20.dp, 0.dp),
        placeholder = { Text("Search News") },
        textStyle = LocalTextStyle.current
    )
}


@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    tabWidth: Dp = 100.dp,
    list:List<CategoryModel>,
    viewModel: MasterViewModel
) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp, 0.dp, 20.dp)
            .height(40.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        items(items = list) { categoryList ->
            CategoryItem(categoryList.isSelected, onClick = {
                    Log.d("clicked","Clicked>>>>>>>>>>>>>>>>>")
            }, tabWidth = tabWidth, text = categoryList.category)

        }

    }


    val data = produceState<DataOrException<NewsModel, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ){
        value = viewModel.getNews("all")

    }.value

    if(data.loading==true){
        CircularProgressIndicator()
    }else if(data.data!=null){
        LazyColumn{

            data.data?.let { result->
                items(items = result.data){ news->
                    newsItem(news)
                }
            }

        }
    }



}

@Composable
private fun CategoryItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    tabWidth: Dp,
    text: String,
) {
    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            White
        } else {
            Black
        },
        animationSpec = tween(easing = LinearEasing),
    )

    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * 0,
        animationSpec = tween(easing = LinearEasing),
    )

    if (isSelected) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(
                    min = tabWidth,
                )
                .offset(
                    x = indicatorOffset,
                )
                .clip(
                    shape = CircleShape,
                )
                .background(
                    color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                )
                .clickable {
                    onClick()
                }

        ) {
            Text(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        onClick()
                    }
                    .widthIn(
                        min = tabWidth,
                    )
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp,
                    ),
                text = text,
                color = tabTextColor,
                textAlign = TextAlign.Center,
            )
        }
    } else {
        Text(
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    onClick()
                }
                .widthIn(
                    min = tabWidth,
                )
                .padding(
                    vertical = 8.dp,
                    horizontal = 12.dp,
                ),
            text = text,
            color = tabTextColor,
            textAlign = TextAlign.Center,
        )
    }

}

@Composable
fun newsItem(data:Data){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 300.dp)
                .padding(20.dp), shape = ShapeDefaults.Medium
        ) {

            AsyncImage(
                model = data.imageUrl,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().height(300.dp),
                contentScale = ContentScale.FillBounds
            )


            /* Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 300.dp), contentScale = ContentScale.FillBounds)*/

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(20.dp).alpha(0.7f)
        ) {

            Spacer(modifier = Modifier.fillMaxWidth().height(150.dp))

            Column(
                modifier = Modifier.background(color = Black),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = data.title,
                    modifier = Modifier
                        .padding(10.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = White
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth().background(color = Black)
                    .alpha(0.5f)
                    .height(50.dp)
            ) {
                Text(
                    text = data.author,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = White,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(130.dp))
                Text(
                    text = data.date,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = White,
                    fontSize = 14.sp,
                    maxLines = 1
                )

            }

        }
    }
}




