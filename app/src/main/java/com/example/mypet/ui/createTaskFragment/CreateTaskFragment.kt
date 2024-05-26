package com.example.mypet.ui.createTaskFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.mypet.R
import com.example.mypet.databinding.FragmentCreatePetBinding
import com.example.mypet.databinding.FragmentCreateTaskBinding
import com.example.mypet.domain.entity.NewTask
import com.example.mypet.domain.entity.Owner
import com.example.mypet.domain.entity.PetItem
import com.example.mypet.presentation.createTaskFragment.CreateTaskState
import com.example.mypet.presentation.createTaskFragment.CreateTaskViewModel
import com.example.mypet.util.showToast
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreateTaskFragment : Fragment() {

    private lateinit var binding: FragmentCreateTaskBinding

    private val viewModel: CreateTaskViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateTaskBinding.inflate(layoutInflater, container, false)

        viewModel.stateScreen.observe(viewLifecycleOwner) {
            render(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPets()
        initListener()
    }

    private fun initListener() {
        binding.addBtn.setOnClickListener {
            val pet = binding.petSpinner.selectedItem as PetItem
            val petId = pet.id

            val title = binding.nameTv.text.toString()
            val description = binding.ageTv.text.toString()
            val time = binding.dateTv.text.toString()
            val timeLong = convertStringToLong(time)

            viewModel.addTask(NewTask(petId, title, description, timeLong!!, Owner("")))
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.dateTv.setOnClickListener {
            showDatePicker()
        }
    }

    private fun render(it: CreateTaskState) {
        when (it) {
            is CreateTaskState.Error -> {
                binding.progressCircular.isVisible = false
                requireContext().showToast(it.msg)
            }

            CreateTaskState.Loading -> {
                binding.progressCircular.isVisible = true
            }

            is CreateTaskState.Success -> {
                binding.progressCircular.isVisible = false
                findNavController().popBackStack()
            }

            is CreateTaskState.SuccessPets -> {
                binding.progressCircular.isVisible = false
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    it.data
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.petSpinner.adapter = adapter
            }
        }
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Выберите дату")
            .build()

        datePicker.show(parentFragmentManager, "date_picker")
        datePicker.addOnPositiveButtonClickListener { selection ->

            binding.dateTv.text = convertLongToDate(selection)
        }
    }
    private fun convertLongToDate(time: Long): String {
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return format.format(Date(time))
    }

    private fun convertStringToLong(dateString: String, pattern: String = "dd.MM.yyyy"): Long? {
        return try {
            val format = SimpleDateFormat(pattern, Locale.getDefault())
            val date = format.parse(dateString)
            date?.time
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}