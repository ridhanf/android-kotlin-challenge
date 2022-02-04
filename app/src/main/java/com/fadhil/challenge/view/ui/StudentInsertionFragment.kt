package com.fadhil.challenge.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fadhil.challenge.R
import com.fadhil.challenge.constant.Gender
import com.fadhil.challenge.data.entities.Student
import com.fadhil.challenge.databinding.FragmentStudentInsertionBinding
import com.fadhil.challenge.viewmodels.StudentInsertionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentInsertionFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentStudentInsertionBinding? = null
    private val binding get() = _binding!!
    private lateinit var spinner: Spinner
    private lateinit var selectedGender: Gender
    private var genderOption = arrayOf(Gender.MALE, Gender.FEMALE)
    private val viewModel: StudentInsertionViewModel by viewModels()
    lateinit var student: Student

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStudentInsertionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = binding.spnStudentGender
        spinner.onItemSelectedListener = this
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        binding.btnSaveStudent.setOnClickListener {
            addNewStudent()
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.etStudentName.text.toString(),
            selectedGender.toString(),
            binding.etStudentGpa.text.toString()
        )
    }

    private fun addNewStudent() {
        if (isEntryValid()) {
            viewModel.addNewStudent(
                binding.etStudentName.text.toString(),
                selectedGender,
                binding.etStudentGpa.text.toString().toFloat(),
            )
        }
        findNavController().navigateUp()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedGender = genderOption[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedGender = Gender.MALE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}