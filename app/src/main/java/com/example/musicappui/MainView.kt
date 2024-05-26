package com.example.musicappui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import  androidx.navigation.compose.currentBackStackEntryAsState
import  androidx.navigation.compose.rememberNavController

@OptIn( ExperimentalMaterial3Api::class , ExperimentalMaterialApi::class)

@Composable
fun MainView() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope:CoroutineScope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isSheetFullScreen by remember {
        mutableStateOf(false)
    }
    val modifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    var title = remember {
        mutableStateOf("Home")
    }
    var currentScreen = remember {
        viewModel.currentScreen.value
    }

    val modalSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded}
    )

    val roundCornerRadius = if(isSheetFullScreen) 0.dp else 12.dp

    val dialogOpen = remember {
        mutableStateOf(false)
    }

    val bottomBar: @Composable () -> Unit = {
        if( currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home){
                BottomNavigation(Modifier.wrapContentSize()) {
                    screensOnBottom.forEach{
                        item->
                        val isSelected = currentRoute == item.dRoute
                    BottomNavigationItem(
                        selected = currentRoute == item.dRoute,
                        onClick = { controller.navigate(item.dRoute)
                                  title.value = item.title},
                        icon = {
                            val tint = if(isSelected) Color.White else Color.Black
                            Icon(tint = tint, painter = painterResource(id = item.icon), contentDescription = item.title) },
                        label = { Text(text = item.title)},
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                        )
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheet,
        sheetShape = RoundedCornerShape(topStart = roundCornerRadius, topEnd = roundCornerRadius),
        sheetContent = {
          MoreBottomSheet(modifier = modifier)
        }
    ) {
        Scaffold(
            bottomBar = bottomBar,
            topBar = {
                TopAppBar(title = { Text(text = title.value) },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                if(modalSheet.isVisible) {
                                    modalSheet .hide()
                                } else {
                                    modalSheet.show()
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Menu")
                        }
                    })
            },scaffoldState = scaffoldState,
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)) {
                    items(screensInDrawer){
                            item->
                        DrawableItem(selected = currentRoute == item.route, item = item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if(item.route == "AddAccount"){
                                dialogOpen.value = true
                            } else {
                                controller.navigate(item.dRoute)
                                title.value = item.title
                            }

                        }
                    }
                }
            }

        ){
            Navigation(navController = controller, viewModel = viewModel, pd = it)
            AccountDialog(dialogOpen = dialogOpen)
        }
    }
}


@Composable
fun MoreBottomSheet(modifier: Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                MaterialTheme.colorScheme.primary
            )
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(tint = Color.White, painter = painterResource(id = R.drawable.baseline_library_music_24), contentDescription = "", modifier = Modifier.padding(8.dp))
                Text("Setting", color = Color.White, modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp)
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(tint = Color.White, painter = painterResource(id = R.drawable.baseline_mic_external_on_24), contentDescription = "", modifier = Modifier.padding(8.dp))
                Text("Share", color = Color.White,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp
                )
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(tint = Color.White, painter = painterResource(id = R.drawable.baseline_home_24), contentDescription = "", modifier = Modifier.padding(8.dp))
                Text("More", color = Color.White, modifier = Modifier.padding(8.dp),
                    fontSize = 20.sp)
            }

        }
    }

}

@Composable
fun DrawableItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawableItemClicked:() -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.White

    Row( modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 8.dp, vertical = 16.dp)
        .background(background)
        .clickable {
            onDrawableItemClicked()
        }
    ){
         androidx.compose.material3.Icon(
             painter = painterResource(id = item.icon),
             contentDescription = item.title,
             Modifier.padding(end = 8.dp, top = 8.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.headlineMedium
            )
    }
}


@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues) {
    NavHost(navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route ,
        modifier = Modifier.padding(pd)){
        composable(Screen.BottomScreen.Home.dRoute){
            HomeView()
        }

        composable(Screen.BottomScreen.Library.dRoute) {
            LibraryView()
        }
        composable(Screen.BottomScreen.Browser.dRoute){
            Browser()
        }
        composable(Screen.DrawerScreen.Account.route){
            AccountView() 
        }
        composable(Screen.DrawerScreen.Subscription.route){
            SubscriptionView()
        }
    }
}
