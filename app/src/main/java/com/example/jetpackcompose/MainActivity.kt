package com.example.jetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.jetpackcompose.repository.ContactDatabase
import com.example.jetpackcompose.repository.ContactEntity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var database: ContactDatabase

    private val fontFamily = FontFamily(
        Font(R.font.roboto_medium),
        Font(R.font.roboto_medium_italic)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(this, ContactDatabase::class.java, "ContactEntity").build()
        val contactEntity =
            ContactEntity(name = "Atharv", id = 0, contact = "8755328239", address = "Sector 75")
        MainScope().launch {
            database.contactDao().insertContact(contactEntity)
        }

        database.contactDao().getContact().observe(this) {
            Toast.makeText(this@MainActivity, it[0].name, Toast.LENGTH_SHORT).show()

        }



        setContent {
            val painter = painterResource(id = R.drawable.hanuman)
            Box(
                modifier = Modifier
            ) {
                ButtonBox(Modifier.fillMaxSize())
            }

        }
    }
}

@Composable
fun ButtonBox(modifier: Modifier = Modifier) {
    val color = remember {
        mutableStateOf(Color.Cyan)
    }
    Box(modifier = modifier
        .background(color.value)
        .clickable {
            color.value = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat()
            )
        })
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name, modifier = modifier
    )
}

@Composable
fun ImageCard(
    painter: Painter, contentDescription: String, title: String, modifier: Modifier = Modifier

) {
    Card(
        modifier = Modifier.width(200.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    title, style = TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                    )
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val painter = painterResource(id = R.drawable.hanuman)
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 50.sp,
                )
            ) {
                append("H")
            }
            append("ello")
            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 50.sp,
                )
            ) {
                append("W")
            }
            append("orld")
        },
        fontSize = 30.sp,
    )


}