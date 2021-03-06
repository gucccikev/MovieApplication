package com.example.movieapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapplication.models.Movie
import com.example.movieapplication.models.getMovies
import com.example.movieapplication.viewmodels.FavoritesViewModel
import com.example.movieapplication.widgets.HorizontalScrollableImageView
import com.example.movieapplication.widgets.MovieRow

@Composable
fun DetailScreen(navController: NavController, viewModel: FavoritesViewModel, movieId: String?){
    val movie = filterMovie(movieId = movieId)

    var fav by remember {
        mutableStateOf(false)
    }

    fav = viewModel.checkIfAlreadyFavMovie(movie)

    MainContent(movie, navController, viewModel) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column {
                MovieRow(movie = movie,
                    alreadyFavMovie = fav,
                    onItemClick = { movieId -> navController.navigate("detailscreen/$movieId") },
                    onFavoriteIconClick = {
                        fav = viewModel.checkIfAlreadyFavMovie(movie)
                        if (fav) {
                            viewModel.removeFavMovie(movie)
                            fav = false
                        } else {
                            viewModel.addFavMovie(movie)
                            fav = true
                        }
                    },
                    showFavIcon = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                Text(text = "Movie Images", style = MaterialTheme.typography.h5, modifier = Modifier.align(Alignment.CenterHorizontally))

                HorizontalScrollableImageView(movie = movie)
            }
        }
    }
}

@Composable
fun MainContent(movie: Movie, navController: NavController, favoritesViewModel: FavoritesViewModel, content: @Composable () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "arrow back",
                        modifier = Modifier.clickable { navController.popBackStack() })
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = movie.title)
                }
            })
        }
    ){
        content()
    }
}

fun filterMovie(movieId: String?): Movie{
    return getMovies().filter { movie ->
        movie.id == movieId
    }[0]
}
