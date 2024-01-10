package com.romka_po.assistent.ui.components.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.romka_po.assistent.R
import com.romka_po.assistent.model.local.LocalModel

@Composable
fun CarSearch(
    text: MutableState<String>,
    searchModels: State<List<LocalModel>>,
    param: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        value = text.value,
        onValueChange = {
            text.value = it
            if (it != "") {
                param(it)
            }
        },
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .padding(1.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = { Text(text = "Бренд или марка") },
        shape = RoundedCornerShape(14.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true
    )
    if (searchModels.value.isNotEmpty()) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top)) {
            items(searchModels.value) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                ) {
                    Row {
                        Image(
                            modifier = Modifier
                                .padding(start = 12.dp, top = 12.dp, bottom = 17.dp, end = 12.dp)
                                .size(100.dp)
                                .clip(RoundedCornerShape(12.dp)).background(Color.Yellow),
                            painter = painterResource(id = R.drawable.aaa),
                            contentDescription = null,
                        )
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            Text(
                                text = it.name, style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight(500),

                                    )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = it.mark, style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(400),

                                    )
                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "${it.yearFrom} — ${if (it.yearTo == 0) "until now" else it.yearTo}",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF898282),
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Type: ${it.type}",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF898282),

                                    )
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}