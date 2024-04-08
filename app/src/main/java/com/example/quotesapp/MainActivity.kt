package com.example.quotesapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quotesapp.domin.localdb.database
import com.example.quotesapp.domin.localdb.favViewModel
import com.example.quotesapp.domin.localdb.favrepository
import com.example.quotesapp.domin.localdb.localtable
import com.example.quotesapp.domin.model.QuoteModel
import com.example.quotesapp.domin.repository.QuoteRepo
import com.example.quotesapp.domin.retrofitinstence.RetrofitInstence
import com.example.quotesapp.ui.theme.QuotesAppTheme
import com.example.quotesapp.viewmodel.QuoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val apiservice = RetrofitInstence.apiProvide(RetrofitInstence.retrofitProvide())
                    val repository = QuoteRepo(apiservice)
                    val viewModel = QuoteViewModel(repository)


                    var navController= rememberNavController()

                    NavHost(navController = navController, startDestination = "homescreen") {
                        composable("homescreen"){
                            HomeScreen(viewModel = viewModel,navController)
                        }
                        composable("favscreen"){
                            HistoryView(navController)
                        }
                    }

                }
            }
        }
    }
}
@Composable
fun HomeScreen(viewModel: QuoteViewModel,navHostController: NavHostController){
    Scaffold (
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(colorResource(id = R.color.topbar)),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Quotes APP",
                    fontSize = 32.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(900),
                    modifier = Modifier.padding(start = 15.dp))
                Spacer(modifier = Modifier.width(150.dp))

                IconButton(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp),
                    onClick = {
                      navHostController.navigate("favscreen")
                    }) {
                    Icon(
                        modifier =Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Favorite, contentDescription = "")
                }
            }
        }
    ){paddingValue->
        
        Column(modifier = Modifier
            .padding(paddingValue)
            .fillMaxSize()
            .background(colorResource(id = R.color.peach))
            ) {
            DayOfQoutes(viewModel)
            Spacer(modifier = Modifier.height(20.dp))
            QoutesList(viewModel)
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DayOfQoutes(viewModel: QuoteViewModel){
    val items by viewModel.state.collectAsState()
    var  quotoitem:QuoteModel= QuoteModel("","")
    var rand = remember {
        (0..15).random()
    }
    for (item in items.indices){
        if (item==rand){
            quotoitem= items[item]
        }
    }
    var index=quotoitem.author.indexOf(',')
    var author=""
    try {
        author=quotoitem.author.removeRange(index,quotoitem.author.length)
    }catch (e:NegativeArraySizeException ){
        author=quotoitem.author
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(16.dp),
        border = BorderStroke(2.dp,Color.Black)
        ) {
        Text(modifier = Modifier.fillMaxWidth(),
            text = "Quote Of the Day", fontSize = 29.sp,
            fontWeight = FontWeight(1000),
            color = colorResource(id = R.color.DardGreen),
            textAlign = TextAlign.Center)
        Text(text = quotoitem.text,
            fontSize = 22.sp, fontWeight = FontWeight(700),
            color = Color.Black, textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp, start = 5.dp, end = 5.dp)
       )
        Text(text = "-" + author,
            fontSize = 17.sp,
            color = Color.Black,
            textAlign = TextAlign.End
            , modifier = Modifier
                .fillMaxWidth()
                .padding(end = 25.dp, top = 10.dp)
            )
        
    }
}


@Composable
fun QoutesList(viewModel: QuoteViewModel){

    val items by viewModel.state.collectAsState()





    LazyColumn {
        items(items){item->
            QouteItem(item)
        }
    }
}

@Composable
fun QouteItem(item: QuoteModel) {
    val context= LocalContext.current
    val db=database.getInstence(context.applicationContext)
    val repo=favrepository(db.localdb())
    var favoritvm =favViewModel(repo)


    val sendIntent:Intent=Intent().apply {
        action=Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT,item.text)
        type="item/plain"
    }

    var color by remember {
        mutableStateOf(Color.Black)
    }

    val shareIntent=Intent.createChooser(sendIntent,null)


    var index=item.author.indexOf(',')
    var author=""
    try {
        author=item.author.removeRange(index,item.author.length)
    }catch (e:NegativeArraySizeException ){
        author=item.author
    }


    Card(modifier = Modifier
        .padding(10.dp)
        .height(190.dp)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.lightblue))) {
        
        Text(text = item.text,
            fontSize = 23.sp,
            color = Color.Black,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 25.dp, end = 10.dp, top = 25.dp))
        
        Text(text = "-" + author,
            textAlign = TextAlign.End,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 75.dp, top = 10.dp))
        
        Spacer(modifier = Modifier.height(25.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)){
            
            Row(modifier = Modifier
                .padding(start = 20.dp)) {

                IconButton(onClick = {
                                     favoritvm.insertdata(localtable(0,item.text,item.author))
                    color = if (color==Color.Red){
                        Color.DarkGray
                    }else
                        Color.Red

                    Toast.makeText(context,"Added Favorit Quote",Toast.LENGTH_SHORT).show()
                },
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .height(60.dp)
                        .width(60.dp)
                        ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Favorite, contentDescription = "",
                      tint = color
                    )
                }
                Spacer(modifier = Modifier.width(180.dp))

                IconButton(onClick = {
                                     context.startActivity(shareIntent)
                },
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Share, contentDescription = "",
                    )
                }
                




            }
        }
        
    }
    
    
}
