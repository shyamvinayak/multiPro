package com.example.add_mul_by_kotlin_methods.pdfGen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.add_mul_by_kotlin_methods.R
import java.io.File
import java.io.FileOutputStream


private const val CREATE_FILE_REQUEST_CODE = 1000

@Composable
fun PDFGenerator() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
      /*  item {
            Text(
                text = "PDF Generator",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
        }*/

        item {
            PdfGenerator()
        }
    }

}

@Composable
fun PdfGenerator() {
    val ctx = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    if (checkPermissions(ctx)) {
        //Toast.makeText(ctx, "Permissions Granted..", Toast.LENGTH_SHORT).show()
    } else {
        requestPermission(activity!!)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "PDF Generator",
            color = Color.Green,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            onClick = {
                //generatePDF(ctx)
            }) {
            Text(
                modifier = Modifier.padding(6.dp),
                text = "Generate PDF",
                color = Color.White
            )
        }
    }
}

fun generatePDF(context: Context) {
    val pageHeight = 1120
    val pageWidth = 792
    val pdfDocument = PdfDocument()
    val paint = Paint()
    val title = Paint()
    val bmp: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.android)
    val timescaleDB: Bitmap = Bitmap.createScaledBitmap(bmp, 140, 140, false)
    val myPageInfo: PdfDocument.PageInfo? =
        PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)
    val canvas: Canvas = myPage.canvas
    canvas.drawBitmap(timescaleDB, 56F, 40F, paint)
    title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
    title.textSize = 15F
    title.color = ContextCompat.getColor(context, R.color.purple_200)
    canvas.drawText("A portal for IT professionals.", 209F, 100F, title)
    canvas.drawText("Geeks for Geeks", 209F, 80F, title)
    title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
    title.color = ContextCompat.getColor(context, R.color.purple_200)
    title.textSize = 15F
    title.textAlign = Paint.Align.CENTER
    canvas.drawText("This is sample document which we have created.", 396F, 560F, title)
    pdfDocument.finishPage(myPage)
    val file = File(Environment.getExternalStorageDirectory().absolutePath, "GFG.pdf")
    try {
        pdfDocument.writeTo(FileOutputStream(file))
        Toast.makeText(context, "PDF file generated..", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        e.printStackTrace()
        println("Error:---$e")
        Toast.makeText(context, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
            .show()
    }
    pdfDocument.close()

   /* val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "application/pdf"
        putExtra(Intent.EXTRA_TITLE, "GFG.pdf")
    }
    (context as Activity).startActivityForResult(intent, CREATE_FILE_REQUEST_CODE)*/
}

fun checkPermissions(context: Context): Boolean {
    val writeStoragePermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val readStoragePermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    return writeStoragePermission == PackageManager.PERMISSION_GRANTED && readStoragePermission == PackageManager.PERMISSION_GRANTED
}

fun requestPermission(activity: Activity) {
    ActivityCompat.requestPermissions(
        activity,
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ), 101
    )
}


