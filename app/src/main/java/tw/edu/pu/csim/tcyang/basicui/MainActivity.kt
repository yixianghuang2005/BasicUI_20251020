package tw.edu.pu.csim.tcyang.basicui


import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable // 引入 clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.csim.tcyang.basicui.ui.theme.BasicUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {

    var Animals = listOf(R.drawable.animal0, R.drawable.animal1, // animal1 是企鵝
        R.drawable.animal2, R.drawable.animal3, // animal2 是青蛙
        R.drawable.animal4, R.drawable.animal5,
        R.drawable.animal6, R.drawable.animal7,
        R.drawable.animal8, R.drawable.animal9)

    var AnimalsName = arrayListOf("鴨子","企鵝",
        "青蛙","貓頭鷹","海豚", "牛", "無尾熊", "獅子", "狐狸", "小雞")

    var flag  by remember{ mutableStateOf("test")}

    val context = LocalContext.current

    var mper: MediaPlayer? by remember { mutableStateOf(null) }



    // ⭐ 新增：用於控制最下方圖片的狀態
    var bottomAnimalImageResId by remember { mutableStateOf(R.drawable.animal1) } // 初始為企鵝

    // 使用 DisposableEffect 來管理 MediaPlayer 的生命週期
    DisposableEffect(Unit) {
        onDispose {
            mper?.release()
            mper = null
        }
    }


    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0BBE4)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = stringResource(R.string.app_title),
            fontSize = 25.sp,
            color = Color.Blue,
            fontFamily = FontFamily(Font(R.font.kai))

        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(text = stringResource(R.string.app_author),
            fontSize = 20.sp,
            color = Color(0xFF654321)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Row {
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Android 圖示",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow),
                alpha = 0.6f,
            )

            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "Compose icon",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.firebase),
                contentDescription = "Firebase icon",
                modifier = Modifier.size(100.dp)
            )

        }

        Spacer(modifier = Modifier.size(10.dp))

        LazyRow {
            items(51) { index ->
                Text(text = "$index:")
                Text(text = AnimalsName[index % 10])

                Image(
                    painter = painterResource(id = Animals[index % 10]),
                    contentDescription = "可愛動物",
                    modifier = Modifier.size(60.dp)
                )

            }
        }

        Spacer(modifier = Modifier.size(10.dp))


        Button(
            onClick = {
                if(flag == "text"){
                    flag = "abc"
                }
                else{
                    flag = "text"
                }

                Toast.makeText(
                    context,        // 傳入 Context
                    "Compose 按鈕被點擊了!",        // 訊息內容
                    Toast.LENGTH_SHORT // 顯示時間長度 (SHORT 或 LONG)
                ).show()
            }
        ) {
            Text(text = "歡迎修課")

        }

        Text(text= flag)

        Spacer(modifier = Modifier.size(10.dp))
        Row{
            Button(onClick = {
                mper?.release()  //釋放資源
                mper = null // 清除舊引用
                mper = MediaPlayer.create(context, R.raw.tcyang) //設定音樂
                mper?.start()    //開始播放
            } ,
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .fillMaxHeight(0.8f),

                colors = buttonColors(Color.Green)
            ) {
                Text(text = "歡迎", color = Color.Blue)
                Text(text = "修課", color = Color.Red)
                Image(painterResource(id = R.drawable.teacher),
                    contentDescription ="teacher icon")
            }

            Spacer(modifier = Modifier.size(10.dp))

            Button(onClick = {
                mper?.release()  //釋放資源
                mper = null // 清除舊引用
                mper = MediaPlayer.create(context, R.raw.fly) //設定音樂
                mper?.start()
            },modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.4f),
                colors = buttonColors(Color.Blue)
            ) {
                Text(text = "展翅飛翔", color = Color.White)
                Image(
                    painterResource(id = R.drawable.fly),
                    contentDescription ="fly icon")

            }

            Spacer(modifier = Modifier.size(10.dp))

            Button(onClick = {
            }) {
                Text(text = "結束App")
            }

            Spacer(modifier = Modifier.size(10.dp))



        }

        // ⭐ 最下方新增的圖片元件，初始為企鵝，點擊可切換為青蛙
        Spacer(modifier = Modifier.size(20.dp)) // 增加一些間距
        Image(
            painter = painterResource(id = bottomAnimalImageResId),
            contentDescription = "可點擊切換的動物圖片",
            modifier = Modifier
                .size(120.dp) // 設定圖片大小
                .clickable { // ⭐ 讓圖片可以被點擊
                    // 點擊時，在企鵝和青蛙之間切換
                    bottomAnimalImageResId = if (bottomAnimalImageResId == R.drawable.animal1) {
                        R.drawable.animal2 // 切換到青蛙
                    } else {
                        R.drawable.animal1 // 切換回企鵝
                    }
                }
        )

    }
}

