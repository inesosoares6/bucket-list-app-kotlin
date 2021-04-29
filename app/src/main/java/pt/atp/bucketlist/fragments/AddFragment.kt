package pt.atp.bucketlist.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.layout_add_place.*
import pt.atp.bucketlist.MainActivity
import pt.atp.bucketlist.R
import java.io.IOException
import java.util.*

class AddFragment : Fragment(R.layout.fragment_add) {

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

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView: View = inflater.inflate(R.layout.fragment_add,container,false)
        buttonChooseImage = rootView.findViewById(R.id.buttonChooseImage)
        buttonUploadImage = rootView.findViewById(R.id.buttonUploadImage)
        buttonAddPlacesToVisit = rootView.findViewById(R.id.buttonAddPlacesToVisit)
        buttonAddPlacesVisited = rootView.findViewById(R.id.buttonAddPlacesVisited)
        descriptionText = rootView.findViewById(R.id.descriptionText)
        countryText = rootView.findViewById(R.id.countryText)
        placeText = rootView.findViewById(R.id.placeText)

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

        return rootView
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
                    Toast.makeText(context, "Error uploading image", Toast.LENGTH_SHORT).show()
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(context, "Please Upload an Image", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(context, "Saved to DB", Toast.LENGTH_LONG).show()
                activity?.finish()
                val intent = Intent(context,MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error saving to DB", Toast.LENGTH_LONG).show()
            }

        db.collection(list).document("stories").collection(countryText?.text.toString())
            .add(data)
            .addOnSuccessListener {
                activity?.finish()
                val intent = Intent(context,MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error saving to DB", Toast.LENGTH_LONG).show()
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
                val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, filePath)
                image_preview.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}