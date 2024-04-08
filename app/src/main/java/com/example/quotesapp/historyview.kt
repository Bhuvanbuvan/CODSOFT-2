package com.example.quotesapp

import android.content.ClipData.Item
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quotesapp.domin.localdb.database
import com.example.quotesapp.domin.localdb.favViewModel
import com.example.quotesapp.domin.localdb.favrepository
import com.example.quotesapp.domin.localdb.localtable
import com.example.quotesapp.domin.model.QuoteModel

@Composable
fun HistoryView(navHostController: NavHostController){
    Scaffold (
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(colorResource(id = R.color.topbar)),
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp),
                    onClick = {
                        navHostController.popBackStack()
                    }) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }

                Text(text = "Favorite Items",
                    fontSize = 32.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(900),
                    modifier = Modifier.padding(start = 15.dp))



            }
        }
    ){paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            val context= LocalContext.current
            val db= database.getInstence(context.applicationContext)
            val repo= favrepository(db.localdb())
            var favoritvm = favViewModel(repo)
            val list by favoritvm.getfavdata().collectAsState(initial = emptyList())
            LazyColumn {
                items(list){litem->
                    Item(item =litem )
                }
            }
        }

    }
}


@Composable
fun Item(item:localtable){



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




                }
            }





