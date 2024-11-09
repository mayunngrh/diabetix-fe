package com.example.diabetix.presentation.analyze_page

import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
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
import java.io.File
import java.io.FileOutputStream

@Composable
fun AnalyzePageScreen(navController: NavController) {
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current



    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && photoUri != null) {

                loadImage(context, photoUri!!) { bitmap ->
                    imageBitmap = bitmap
                }
            }
        }
    )

    // Camera permission launcher
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                val uri = createImageUri(context)
                photoUri = uri
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Camera permission is required to take photos", Toast.LENGTH_SHORT).show()
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
                .height(450.dp)
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
            onClick = {
                if (imageBitmap != null) {
                    val imagePath = saveBitmapToCache(context, imageBitmap!!)
                    if (imagePath != null) {
                        val encodedImagePath = Uri.encode(imagePath)
                        navController.navigate("analyze_result/$encodedImagePath")
                    }
                } else {
                    Toast.makeText(context, "Mohon foto makanan anda terlebih dahulu!", Toast.LENGTH_SHORT).show()
                }
            },
            text = "Analisis"
        )
        MyOutlinedButton(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            onClick = {
                if (androidx.core.content.ContextCompat.checkSelfPermission(
                        context, android.Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val uri = createImageUri(context)
                    photoUri = uri
                    cameraLauncher.launch(uri)
                } else {
                    cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
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
    CoroutineScope(Dispatchers.IO).launch {
        val bitmap = loadImageFromUri(context, uri)
        withContext(Dispatchers.Main) {
            onBitmapLoaded(bitmap)
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

private fun saveBitmapToCache(context: Context, bitmap: Bitmap): String? {
    val cacheDir = context.cacheDir
    val file = File(cacheDir, "analyze_image_${System.currentTimeMillis()}.png")
    return try {
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
