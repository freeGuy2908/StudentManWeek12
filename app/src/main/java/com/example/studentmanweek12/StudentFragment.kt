package com.example.studentmanweek12

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentFragment : Fragment() {
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var saveButton: Button
    private var student: Student? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_student_fragment, container, false)

        nameEditText = view.findViewById(R.id.nameEditText)
        idEditText = view.findViewById(R.id.idEditText)
        saveButton = view.findViewById(R.id.saveButton)

        // Lấy dữ liệu sinh viên từ Bundle (nếu có)
        arguments?.let {
            student = it.getSerializable("student") as Student?
            student?.let { student ->
                nameEditText.setText(student.name)
                idEditText.setText(student.id)
            }
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()

            // Trả dữ liệu về MainFragment
            findNavController().navigate(R.id.action_student_to_main)
        }

        return view
    }
}
