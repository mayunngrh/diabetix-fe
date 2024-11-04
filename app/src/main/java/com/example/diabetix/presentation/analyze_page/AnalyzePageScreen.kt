package com.example.diabetix.presentation.analyze_page

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.diabetix.R
import com.example.diabetix.component.MyButton
import com.example.diabetix.component.MyOutlinedButton
import com.example.diabetix.ui.theme.CustomTheme
import com.example.diabetix.ui.theme.GreenNormal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AnalyzePageScreen(navController: NavController) {
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && photoUri != null) {
                // Load the image when the photo is captured
                loadImage(context, photoUri!!) { bitmap ->
                    imageBitmap = bitmap // Update the state with the loaded bitmap
                }
            }
        }
    )

    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(GreenNormal),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                modifier = Modifier.padding(24.dp),
                text = "Detail Makanan",
                style = CustomTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        // BOX image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(24.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
            imageBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Foto Analyze",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )
            } ?: AsyncImage(
                modifier = Modifier.matchParentSize(),
                model = R.drawable.photo_no_picture,
                contentDescription = "No Picture",
                contentScale = ContentScale.Crop
            )
        }

        MyButton(
            modifier = Modifier.padding(horizontal = 24.dp),
            onClick = { /* TODO: Implement analysis logic */ },
            text = "Analisis"
        )
        MyOutlinedButton(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            onClick = {
                val uri = createImageUri(context)
                photoUri = uri // Store the URI
                cameraLauncher.launch(uri)
            },
            text = "Ambil Foto"
        )
    }
}

private fun createImageUri(context: Context): Uri {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }
    return context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    )!!
}

private fun loadImage(context: Context, uri: Uri, onBitmapLoaded: (Bitmap?) -> Unit) {
    // Launch a coroutine to load the image
    CoroutineScope(Dispatchers.IO).launch {
        val bitmap = loadImageFromUri(context, uri)
        withContext(Dispatchers.Main) {
            onBitmapLoaded(bitmap) // Pass the loaded bitmap back to the UI
        }
    }
}

private suspend fun loadImageFromUri(context: Context, uri: Uri): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
