package com.example.movieapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapplication.models.Movie
import com.example.movieapplication.models.getMovies
import com.example.movieapplication.widgets.MovieRow

@Composable
fun FavoritesScreen(navController: NavController){
    var favMovieList = getMovies()
    var favoriteList = listOf(favMovieList[1], favMovieList[4])

    MainContent(favoriteList = favoriteList, navController = navController) {
        LazyColumn{
            items(favoriteList){ favMovie ->
                MovieRow(movie = favMovie)                
            }
        }
    }
}

@Composable
fun MainContent(favoriteList: List<Movie>, navController: NavController, content: @Composable () -> Unit){

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "arrow back",
                        modifier = Modifier.clickable { navController.popBackStack() })
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Favorites")
                }
            })
        }
    ){
        content()
    }
}