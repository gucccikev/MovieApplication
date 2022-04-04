package com.example.movieapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapplication.models.Movie
import com.example.movieapplication.models.getMovies
import com.example.movieapplication.ui.theme.MovieApplicationTheme
import com.example.movieapplication.viewmodels.FavoritesViewModel
import com.example.movieapplication.widgets.MovieRow


@Composable
fun HomeScreen(navController: NavController, viewModel: FavoritesViewModel){

    var showMenu by remember {
        mutableStateOf(false)
    }

    MovieApplicationTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Movies") },
                    actions = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                        }

                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = { navController.navigate("favoritesscreen")}) {
                                Row {
                                    Icon(imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorites",
                                        modifier = Modifier.padding(4.dp)
                                    )
                                    Text(text = "Favorites",
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .width(100.dp)
                                    )
                                }
                            }
                        }
                    }
                )
            }
        ){
            MainContent(navController)
        }
    }

}

@Composable
fun MainContent(navController: NavController, movieList: List<Movie> = getMovies()) {

    LazyColumn{
        items(movieList) { movie ->
            MovieRow(movie = movie){ movieId ->
                navController.navigate("detailscreen/$movieId")
            }
        }
    }
}

