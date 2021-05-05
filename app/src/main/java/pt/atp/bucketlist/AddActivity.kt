package pt.atp.bucketlist

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.layout_add_place.*
import java.io.IOException
import java.util.*

class AddActivity : AppCompatActivity()  {

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var storageReference: StorageReference? = null
    private var buttonChooseImage: FloatingActionButton? = null
    private var buttonUploadImage: FloatingActionButton? = null
    private var buttonAddPlacesToVisit: FloatingActionButton? = null
    private var buttonAddPlacesVisited: FloatingActionButton? = null
    private var descriptionText: EditText? = null
    private var countryText: EditText? = null
    private var placeText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        buttonChooseImage = findViewById(R.id.buttonChooseImage)
        buttonUploadImage = findViewById(R.id.buttonUploadImage)
        buttonAddPlacesToVisit = findViewById(R.id.buttonAddPlacesToVisit)
        buttonAddPlacesVisited = findViewById(R.id.buttonAddPlacesVisited)
        descriptionText = findViewById(R.id.descriptionText)
        countryText = findViewById(R.id.countryText)
        placeText = findViewById(R.id.placeText)

        storageReference = FirebaseStorage.getInstance().reference

        buttonChooseImage?.setOnClickListener {
            launchGallery()
        }

        buttonUploadImage?.setOnClickListener {
            layout_add_place.visibility=View.GONE
            layout_add_submit.visibility=View.VISIBLE
        }

        buttonAddPlacesToVisit?.setOnClickListener {
            uploadImage("PlaceToVisit")
        }

        buttonAddPlacesVisited?.setOnClickListener {
            uploadImage("PlaceVisited")
        }
    }

    private fun uploadImage(list : String) {
        if(filePath != null){
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

            uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    addUploadRecordToDb(downloadUri.toString(), list)
                } else {
                    Toast.makeText(applicationContext, "Error uploading image", Toast.LENGTH_SHORT).show()
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(applicationContext, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUploadRecordToDb(uri: String, list: String) {
        val db = FirebaseFirestore.getInstance()
        val data = hashMapOf(
            "imageUrl" to uri,
            "country" to countryText?.text.toString(),
            "place" to placeText?.text.toString(),
            "description" to descriptionText?.text.toString()
        )
        db.collection(list)
            .add(data)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Saved to DB", Toast.LENGTH_LONG).show()
                this.finish()
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error saving to DB", Toast.LENGTH_LONG).show()
            }

        db.collection(list).document("stories").collection(countryText?.text.toString())
            .add(data)
            .addOnSuccessListener {
                this.finish()
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error saving to DB", Toast.LENGTH_LONG).show()
            }


        val countryName = hashMapOf(
            "imageUrl" to uri,
            "country" to countryText?.text.toString(),
        )
        db.collection(list).document("countryList").collection("list").document(countryText?.text.toString()).set(countryName)
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(application?.contentResolver, filePath)
                image_preview.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}